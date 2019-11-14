package com.self.cloud.demo.api;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * @author: liruichuan
 * @Date: 2019/11/14 09:48
 * @Description:
 */
public class HtmlToImage {


    public static void main(String[] args) throws Exception{
        htmlToImage();
    }

    public static void htmlToImage()throws Exception{


        //设置网址
        JEditorPane jEditorPane = new JEditorPane(new URL("https://www.baidu.com"));

        //设置图片大小
        jEditorPane.setSize(10000,10000);

        BufferedImage bufferedImage = new BufferedImage(jEditorPane.getWidth(),jEditorPane.getHeight(),BufferedImage.TYPE_INT_ARGB);

        SwingUtilities.paintComponent(bufferedImage.createGraphics(),jEditorPane,new JPanel(),0,0,bufferedImage.getWidth(),bufferedImage.getHeight());

        ImageIO.write(bufferedImage,"png",new File("d://baidu.png"));

    }
}
