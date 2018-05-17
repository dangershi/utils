package com.aiiju.util.qrcode;

import java.awt.Color;  
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  

import javax.imageio.ImageIO;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aiiju.util.common.MD5;
import com.swetake.util.Qrcode;  
  
/** 
 * QRCode二维码生成器 
 */  
public class QRCodeEncoderHandler {  
	public static final Logger logger = LoggerFactory.getLogger("QRCodeEncoderHandler");
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 
     * @param imgPath 
     */  
    public static void encoderQRCode(String content, String imgPath) {  
        try {  
  
            Qrcode qrcodeHandler = new Qrcode();  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            qrcodeHandler.setQrcodeVersion(7);  
  
            byte[] contentBytes = content.getBytes("gb2312");  
  
            BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);  
  
            Graphics2D gs = bufImg.createGraphics();  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, 140, 140);  
  
            // 设定图像颜色 : BLACK  
            gs.setColor(Color.BLACK);  
  
            // 设置偏移量 不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容 
            if (contentBytes.length > 0 && contentBytes.length < 120) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
            	logger.error("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");
            }  
  
            gs.dispose();  
            bufImg.flush();  
  
            File imgFile = new File(imgPath);  
            // 生成二维码QRCode图片  
            ImageIO.write(bufImg, "png", imgFile);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
            logger.error("生成二维码发生异常！");
        } 
    }  
    
    public static void main(String[] args) {  
        
        //公司的面试扫码签到的二维码地址
        String imgPath = "D:/test_qrcode/testNameQRCode.png";  
        String content = "https://www.baidu.com";  
        QRCodeEncoderHandler.encoderQRCode(content, imgPath);
    	
    }  
}  