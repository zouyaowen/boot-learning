package com.zou.learning.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * PDF操作类
 *
 * @author zou
 * @date 2020-02-14 2:01 下午
 */
@Slf4j
public class PdfUtil {

    public static void wordToPdf() {
        // 模板路径
        //String templatePath = "D:/pdm/one.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:/pdm/practise.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            // 读取pdf模板
            reader = new PdfReader("text.pdf");
            // 输出流
            out = new FileOutputStream(newPDFPath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            String[] str = {"123456789", "TOP__ONE", "男", "2000-01-01", "130222111133338888", "河北省张家口市"};
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                System.out.println(name);
                form.setField(name, str[i++]);
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }


    public static BufferedImage pdfToImage(InputStream inputStream) {
        //图像合并使用参数
        int width = 0; // 总宽度
        int[] singleImgRGB; // 保存一张图片中的RGB数据
        int shiftHeight = 0;
        BufferedImage imageResult = null;//保存每张图片的像素值
        try {
            //利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(inputStream);
            PDFTextStripper text = new PDFTextStripper();
            String text1 = text.getText(pdDocument);
            log.info("读取PDF文字:" + text1);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            //循环每个页码
            for (int i = 0, len = pdDocument.getNumberOfPages(); i < len; i++) {
                //dpi参数越大，越清晰
                BufferedImage image = renderer.renderImageWithDPI(i, 300, ImageType.RGB);
                int imageHeight = image.getHeight();
                int imageWidth = image.getWidth();
                if (i == 0) {//计算高度和偏移量
                    width = imageWidth;//使用第一张图片宽度;
                    //保存每页图片的像素值
                    imageResult = new BufferedImage(width, imageHeight * len, BufferedImage.TYPE_INT_RGB);
                } else {
                    shiftHeight += imageHeight; // 计算偏移高度
                }
                singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width); // 写入流中
            }
            pdDocument.close();
        } catch (Exception e) {
            log.error(" 从PDF转换JPG格式异常!", e);
        }
        return imageResult;
    }

    public static String pdf2Image(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        Map<String, String> params = new HashMap<String, String>();
        params.put("url", urlStr);
        InputStream in = null;
        PDDocument doc = null;
        // 将pdf装图片 并且自定义图片得格式大小
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            doc = PDDocument.load(inputStream);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // Windows native DPI，PdfExchangeDpi是float类型的，越大文件越大，清晰度越高，可以根据需求来调整，我们使用的是80
                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                ImageIO.write(image, "PNG", baos);
                baos.flush();
                //ImageIO.write(image, type, new File(fileAddress+"\\"+filename+"_"+(i+1)+"."+type));
                String base64 = Base64Utils.encodeToString(baos.toByteArray());
                baos.close();
            }
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
        return null;
    }


}
