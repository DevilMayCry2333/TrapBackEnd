package cn.huihongcloud.controller;

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
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 钟晖宏 on 2018/9/16
 */
@Controller
public class QRController {

//    @PostMapping("/QRCode")
    public void generateQRCode(HttpServletResponse response, String prefix, int start, int end) throws Exception{

        response.setContentType("application/zip");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "packed.zip");

        OutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        for (int i = start; i <= end; ++i) {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(prefix + i, BarcodeFormat.QR_CODE, 500, 500);
            BufferedImage buffImg = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ZipEntry entry = new ZipEntry(prefix + i + ".png");
            zipOutputStream.putNextEntry(entry);
            ImageIO.write(buffImg, "png", zipOutputStream);
            zipOutputStream.flush();
        }

        zipOutputStream.close();

        outputStream.flush();
        outputStream.close();
    }

    @PostMapping("/QRCode")
    public void output(HttpServletResponse response, String prefix, int start, int end) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition",
                "attachment; filename=" +  "output.pdf");
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.setMargins(0,0,5,0);
        document.open();
        PdfPTable pdfPTable = new PdfPTable(3);
        int size = 0;
        for (int i = start; i <= end; ++i) {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(prefix + i, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage buffImg = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(buffImg, "png", byteArrayOutputStream);
            Image image = Image.getInstance(byteArrayOutputStream.toByteArray());
            PdfPCell pdfPCell = new PdfPCell();
            pdfPCell.setPaddingBottom(30);
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.addElement(image);
            Paragraph paragraph = new Paragraph(prefix + i);
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
