package cn.huihongcloud.controller.openlayers;

import cn.huihongcloud.entity.GeoMappingEntity;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.mapper.UserMapper;
import cn.huihongcloud.service.UserService;
import io.swagger.annotations.ApiOperation;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


@RestController
@RequestMapping("/geoserver")
public class oplController {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Value("${com.youkaiyu.geoserver.filepath}")
    private String path;

    @Value("${com.youkaiyu.geoserver.url}")
    private String RESTURL;

    @Value("${com.youkaiyu.geoserver.user}")
    private String RESTUSER;

    @Value("${com.youkaiyu.geoserver.pass}")
    private String RESTPW;



    @ApiOperation("上传维护信息")
    @RequestMapping("/upload")
    public Object addMaintenanceData(@RequestParam("username") String username,
                                     @RequestPart("file") MultipartFile shapeFile,
                                     @RequestParam("module") String module,
                                     @RequestParam("eps") String eps
                                    ) throws Exception {

        User user = userService.getUserByUserName(username);
        final long l = System.currentTimeMillis();
        final int i = (int)( l % 10000 );

        String layerName = "A" + user.getAdcode() + i;



//        String RESTURL  = "http://localhost:8080/geoserver";
//        String RESTUSER = "admin";
//        String RESTPW   = "geoserver";

        GeoServerRESTReader reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);


        File file = new File(path + layerName + ".zip");
        FileUtils.copyInputStreamToFile(shapeFile.getInputStream(), file);

        ZipFile zf = new ZipFile(path + layerName + ".zip");
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        String[] zipLayers;
        String zipLayer;
        String base = "";

        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
            } else {

                base = FilenameUtils.getBaseName(ze.getName());

            }
        }
        zin.closeEntry();
        GeoMappingEntity geoMappingEntity = new GeoMappingEntity();
        geoMappingEntity.setLayername(base);
        geoMappingEntity.setWorkname(layerName);
        geoMappingEntity.setModule(module);
        geoMappingEntity.setUserid(user.getAdcode() + user.getUsername());
        if(userMapper.countIfLayerExists(geoMappingEntity.getUserid(),geoMappingEntity.getModule())>0){
            userMapper.updateLayerInfo(geoMappingEntity.getUserid(),geoMappingEntity.getModule(),geoMappingEntity.getWorkname(),geoMappingEntity.getLayername());
        }else {
            userMapper.insertLayerInfo(geoMappingEntity.getUserid(),geoMappingEntity.getModule(),geoMappingEntity.getWorkname(),geoMappingEntity.getLayername());
        }
        //坐标系需要指定
        boolean created = publisher.createWorkspace(layerName);
        boolean published = publisher.publishShp(layerName, layerName, base, file, eps, "default_point");

//
        return "OK";
//        return Result.ok();
        //return null;
    }

    @ApiOperation("获得模块图层信息")
    @RequestMapping("/getLayerInfo")
    public Object getLayerInfo(@RequestParam("userid") String userid,@RequestParam("module") String module){
        if(userMapper.countIfLayerExists(userid,module)>0){
            return userMapper.getLayerInfo(userid,module);
        }else {
            return "NO";
        }
    }

    @RequestMapping("/downloadFileAction")
    public String downloadFileAction(@RequestParam("userid") String userid,@RequestParam("module") String module,
                                   HttpServletRequest request, HttpServletResponse response) {
        GeoMappingEntity geoMappingEntity = userMapper.getLayerInfo(userid,module);
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/zip");
        FileInputStream fis = null;
        String data = path + geoMappingEntity.getWorkname() + ".zip";
        try {
            File file = new File(path + geoMappingEntity.getWorkname() + ".zip");
            getFile(data.getBytes(),file.getName());
            responseTo(file,response);
//            file.delete();

        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

    public static void responseTo(File file, HttpServletResponse res) {  //将文件发送到前端
        res.setHeader("content-type", "application/zip");
        res.setContentType("application/zip");
        res.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        byte[] buff = new byte[1024*1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void getFile(byte[] bfile, String fileName) {    //创建文件
        File file=new File(fileName);
        try {
            if (!file.exists()){file.createNewFile();}
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
