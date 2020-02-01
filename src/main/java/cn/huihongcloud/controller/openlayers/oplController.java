package cn.huihongcloud.controller.openlayers;

import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.UserService;
import io.swagger.annotations.ApiOperation;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


@RestController
@RequestMapping("/geoserver")
public class oplController {
    @Autowired
    UserService userService;

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
                                     @RequestPart("file") MultipartFile shapeFile
                                    ) throws Exception {

        User user = userService.getUserByUserName(username);
        final long l = System.currentTimeMillis();
        final int i = (int)( l % 10000 );

        String layerName = user.getAdcode() + i;
        System.out.println(layerName);
        System.out.println(user.getUsername());
        System.out.println(shapeFile);
//        String RESTURL  = "http://localhost:8080/geoserver";
//        String RESTUSER = "admin";
//        String RESTPW   = "geoserver";

        GeoServerRESTReader reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
        GeoServerRESTPublisher publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
        boolean created = publisher.createWorkspace(layerName);

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
                System.out.println(ze.getName());
                base = FilenameUtils.getBaseName(ze.getName());
                System.out.println(base);
            }
        }
        zin.closeEntry();

    boolean published = publisher.publishShp(layerName, layerName, base, file, "EPSG:4326", "default_point");
        System.out.println("转换之后的文件："+file);
//        System.out.println(published);
        return "OK";
//        return Result.ok();
        //return null;
    }
}
