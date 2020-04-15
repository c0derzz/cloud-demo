package com.self.cloud.demo.pdftoimage;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author: liruichuan
 * @Date: 2020/1/3 12:54
 * @Description:
 */
public class PDFToImageUtils {

    /**
     * 转换整个文档
     * @param filePath 文件地址
     * @param filename PDF文件名
     * @param type 图片类型
     */
    public static void pdfTopng(String filePath,String filename,String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(filePath+"\\"+filename+".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, type, new File(filePath+"\\"+filename+"_"+(i+1)+"."+type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *自由确定起始页和终止页
     * @param filePath 文件地址
     * @param filename pdf文件名
     * @param indexOfStart 开始页  开始转换的页码，从0开始
     * @param indexOfEnd 结束页  停止转换的页码，-1为全部
     * @param type 图片类型
     */
    public static void pdf2png(String filePath,String filename,int indexOfStart,int indexOfEnd,String type) {
        // 将pdf装图片 并且自定义图片得格式大小
        File file = new File(filePath+"\\"+filename+".pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = indexOfStart; i < indexOfEnd; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, type, new File(filePath+"\\"+filename+"_"+(i+1)+"."+type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        pdf2png("D:\\software\\pdf","spring-cloud",11,12,"png");
    }
}
