package com.gzjky.base.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

/** 
 * 验证Utils类
 * 
 * @author yuting
 */ 
public class VaildateUtils {
	/** 
	 * 对象がnullかチェックします。
	 * 
	 * @param object 对象
	 * @return boolean true:nullである false:nullではない
	 * @see org.apache.commons.validator.GenericValidator#isBlankOrNull(java.lang.String)
	 */
	public static boolean isNull(Object object) {
		boolean isNull = false;
		if (object == null) {
			isNull = true;
		}
		return isNull;
	}
	
	/** 
	 * 文字列が空かチェックします。
	 * 
	 * @param value 文字列
	 * @return boolean true:空である false:空ではない
	 * @see org.apache.commons.validator.GenericValidator#isBlankOrNull(java.lang.String)
	 */
	public static boolean isNullOrEmpty(String value) {
		return GenericValidator.isBlankOrNull(value);
	}

	/** 
	 * Listが空かチェックします。
	 * 
	 * @param list List
	 * @return boolean true:空である false:空ではない
	 */
	public static boolean isEmptyList(List<?> list) {
		return (list == null || list.size() == 0);
	}
	
	/** 
	 * Arrayが空かチェックします。
	 * 
	 * @param array array
	 * @return boolean true:空である false:空ではない
	 */
	public static boolean isEmptyArray(String[] array) {
		return (array == null || array.length == 0);
	}
	
    /**
     * 验证用户密码
     * @param password
     * @return boolean true:有效 false:无效
     */
    public static boolean validatePassword(String password){
        String regex = "^[0-9a-zA-Z]{4,8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}