package com.gzjky.base.util.imageUpload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

/**
 * 1.将图片转成2进制保存到数据库
 * 2.将数据库的2进制转成流直接显示在页面上，action.xml返回类型是stream
 * @author yuting
 *
 */
@Component
public class ImageUpload implements IImageUpload{
	
	//private File file = null; // 文件对象   
    //private String inputDir; // 输入图路径  
    //private String outputDir; // 输出图路径  
    //private String inputFileName; // 输入图文件名  
    //private String outputFileName; // 输出图文件名  
    private int outputWidth = 160; // 默认输出图片宽  
    private int outputHeight = 160; // 默认输出图片高  
    //private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放) 
	
	/**
	 * 将图片转成2进制
	 * 
	 * @param file
	 * @return
	 */
	public byte[] imageToByte(File file) {

		byte[] by = new byte[(int) file.length()];

		try {
			// BufferedImage tt = ImageIO.read(filePath);
			
			FileInputStream inputStream = new FileInputStream(file);
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			byte[] b = new byte[(int) file.length()];

			int len = 0;
			while ((len = inputStream.read(b)) > 0) {
				bytestream.write(b, 0, len);
				len = inputStream.read(b);
			}
			by = bytestream.toByteArray();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return by;
	}
	
	/**
	 * 将图片转成2进制输出
	 * @return
	 */
    public ByteArrayInputStream getImageBinary(File file){      

        // 将文件转成2进制
        byte[] b = imageToByte(file);
        
        ByteArrayInputStream inputStream = convertBytesToStream(b);
        
        return inputStream;
    }
	
	
	/**
	 * 将byte[]转换成ByteArrayInputStream用于在页面显示
	 * 
	 * @param image 图片
	 *            
	 * @return ByteArrayInputStream 流
	 */
	public ByteArrayInputStream convertBytesToStream(byte[] image) {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(image);

		return inputStream;
	}

	/**
	 * 对图片进行压缩处理
	 */
	@Override
	public byte[] zipImageFile(File oldFile) {
		byte[] imageByte = null;
		
		if (oldFile == null) {    
            return null;    
        }  
		
		try {    
            /** 对服务器上的临时文件进行处理 */    
            Image srcFile = ImageIO.read(oldFile);    
            
            int newWidth; 
            int newHeight;   
            // 为等比缩放计算输出的图片宽度及高度   
            double rate1 = ((double) srcFile.getWidth(null)) / (double) outputWidth + 0.1;   
            double rate2 = ((double) srcFile.getHeight(null)) / (double) outputHeight + 0.1;   
            // 根据缩放比率大的进行缩放控制   
            double rate = rate1 > rate2 ? rate1 : rate2;   
            newWidth = (int) (((double) srcFile.getWidth(null)) / rate);   
            newHeight = (int) (((double) srcFile.getHeight(null)) / rate); 
            
            BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB); 
            
            /** 宽,高设定 */    
            tag.getGraphics().drawImage(srcFile.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);   
    
            /** 压缩之后临时存放位置 */    
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
            ImageIO.write(tag, "gif", out);  
            imageByte = out.toByteArray();  
            out.close();  
    
        } catch (FileNotFoundException e) {    
            e.printStackTrace();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }
		
		return imageByte;
	}


}
