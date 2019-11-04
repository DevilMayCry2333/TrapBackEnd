package cn.huihongcloud.controller;

import cn.huihongcloud.component.JWTComponent;
import cn.huihongcloud.entity.common.Result;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.entity.user.User;
import cn.huihongcloud.service.DeviceService;
import cn.huihongcloud.service.UserService;
import cn.huihongcloud.util.DistUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by 钟晖宏 on 2018/11/24
 */
@Controller()
public class QRNewController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DistUtil distUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTComponent jwtComponent;

    @PostMapping("auth_api/QRcode")
    @ResponseBody
    public Object addQRCode(@RequestAttribute("username") String currentUsername, @RequestBody List<Map<String, Object>> data) {
        User currentUser = userService.getUserByUserName(currentUsername);
        String adcode = currentUser.getAdcode();
        long count = deviceService.countDeviceInArea(currentUser.getAdcode());
        System.out.println("数量:" + count);

        long index = count;

        String maxIdString = deviceService.getMaxDeviceIdInArea(adcode);
        Long maxId = null;
        if (maxIdString == null) {
            maxId = Long.parseLong(adcode) * 1000000;
        } else {
            maxId = Long.parseLong(maxIdString);
        }
        /*
        for (int i = 0; i < num; ++i, ++index) {
            Device device = new Device();
            String[] names = distUtil.getNames(adcode, null);
            device.setProvince(names[0]);
            device.setCity(names[1]);
            device.setArea(names[2]);
//            device.setTown(names[3]);
            device.setAdcode(adcode);
            device.setManager(username);
//            device.setTowncode(towncode);
            device.setId(adcode + String.format("%06d", index));
            device.setQrcode("Test1");
            System.out.println(device);
            deviceService.addDevice(device);
        }
        */
        for (Map item: data) {
            String username = (String) item.get("username");
            Integer num = (Integer) item.get("num");
            System.out.println(username + " " + num);
            for (int i = 0; i < num; ++i, ++index) {
                Device device = new Device();
                device.setAdcode(adcode);
                String[] names = distUtil.getNames(adcode, null);
                device.setProvince(names[0]);
                device.setCity(names[1]);
                device.setArea(names[2]);
                device.setManager(username);
                device.setId(Long.toString(maxId + i + 1));
                device.setQrcode("Test1");
                System.out.println(device);
                deviceService.addDevice(device);
            }
            maxIdString = deviceService.getMaxDeviceIdInArea(adcode);
            maxId = null;
            if (maxIdString == null) {
                maxId = Long.parseLong(adcode) * 1000000;
            } else {
                maxId = Long.parseLong(maxIdString);
            }
        }
        return Result.ok();
    }

    @GetMapping("/export/QRCodeList")
    public void exportQRCodeList(HttpServletResponse response, String token,
                              @RequestParam(required = false) String adcode) throws IOException {
        response.setContentType("application/excel");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "export.xls");
        String username = jwtComponent.verify(token);
        User user = userService.getUserByUserName(username);
        Integer role = user.getRole();

        List<Device> list = new ArrayList<>();
        if (role == 3) {
            list = deviceService.getDeviceByLocation(user.getAdcode(), null, null);
            if(list.size()>0) {
                Workbook workbook = deviceService.exportQRCodeList(list);
                workbook.write(response.getOutputStream());
            }
        }

        if (role == 4) {
            list = deviceService.getDeviceandWorkerByManager(username);
            if(list.size()>0) {
                Workbook workbook = deviceService.exportQRCodeManagerList(list);
                workbook.write(response.getOutputStream());
            }
        }

    }

    @GetMapping("/QRCode")
    public void output(HttpServletResponse response, String token) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "output.pdf");
        System.out.println(token);
        if (StringUtils.isEmpty(token)) {
            return;
        }

        String username = jwtComponent.verify(token);
        User user = userService.getUserByUserName(username);
        Integer role = user.getRole();
        /*
            当前只有县级用户与管理员可以下载二维码
         */
        List<Device> list = new ArrayList<>();
        if (role == 3) {
            list = deviceService.getDeviceByLocation(user.getAdcode(), null, null);
        }

        if (role == 4) {
            list = deviceService.getDeviceByManager(username);
        }

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.setMargins(0,0,5,0);
        document.open();
        PdfPTable pdfPTable = new PdfPTable(3);
        int size = 0;
        for (Device item: list) {
            String deviceId = item.getScanId();

            System.out.println(item.getScanId());

            System.out.println("deviceId");
            
            BitMatrix bitMatrix = new MultiFormatWriter().encode(deviceId, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage buffImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "png", byteArrayOutputStream);
            Image image = Image.getInstance(byteArrayOutputStream.toByteArray());
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setPaddingBottom(30);
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.addElement(image);
            Paragraph paragraph = new Paragraph(deviceId);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setPaddingTop(0);
            pdfPCell.addElement(paragraph);
            pdfPTable.addCell(pdfPCell);
            size ++;
        }
        while (size % 3 != 0) {
            pdfPTable.addCell(new PdfPCell());
            size ++;
        }
        document.add(pdfPTable);
        document.close();
    }

}
