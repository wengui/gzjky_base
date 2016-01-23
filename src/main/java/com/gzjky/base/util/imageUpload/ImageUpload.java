package com.gzjky.base.util.imageUpload;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 1.将图片转成2进制保存到数据库
 * 2.将数据库的2进制转成流直接显示在页面上，action.xml返回类型是stream
 * @author yuting
 *
 */
public class ImageUpload {
	
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
	 * 将byte[]转换成ByteArrayInputStream用于在页面显示
	 * 
	 * @param image 图片
	 *            
	 * @return ByteArrayInputStream 流
	 */
	private static ByteArrayInputStream convertBytesToStream(byte[] image) {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(image);

		return inputStream;
	}

}
