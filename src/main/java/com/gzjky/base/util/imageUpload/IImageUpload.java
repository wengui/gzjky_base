package com.gzjky.base.util.imageUpload;

import java.io.ByteArrayInputStream;
import java.io.File;

public interface IImageUpload {

	/**
	 * 将图片转成2进制
	 * 
	 * @param file
	 * @return
	 */
	public byte[] imageToByte(File file) ;
	
	/**
	 * 将图片转成2进制输出
	 * @return
	 */
    public ByteArrayInputStream getImageBinary(File file);
	
	
	/**
	 * 将byte[]转换成ByteArrayInputStream用于在页面显示
	 * 
	 * @param image 图片
	 *            
	 * @return ByteArrayInputStream 流
	 */
    public ByteArrayInputStream convertBytesToStream(byte[] image);
}
