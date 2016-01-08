package com.gzjky.base.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.oro.text.perl.Perl5Util;

public class Validator extends EmailValidator {

	public interface Re {

		/** 正規表現 : 全角 */
		String FULL = "^[\uFF00-\uFFFF]*$";

		/** 正規表現: URL */
		String URL = "(((http\\:\\/\\/)((www\\.[a-zA-Z0-9\\-\\.]+)|[a-zA-Z0-9\\-\\.]+))|(www\\.[a-zA-Z0-9\\-\\.]+))[a-zA-Z0-9\\-\\.](\\:[0-9]+)*(/($|[a-zA-Z0-9\\.\\,\\;\\?\\'\\\\\\+&%\\$#\\[\\]\\=~_\\-]+))*";
		String URLHTTPS = "(((https\\:\\/\\/)((www\\.[a-zA-Z0-9\\-\\.]+)|[a-zA-Z0-9\\-\\.]+))|(www\\.[a-zA-Z0-9\\-\\.]+))[a-zA-Z0-9\\-\\.](\\:[0-9]+)*(/($|[a-zA-Z0-9\\.\\,\\;\\?\\'\\\\\\+&%\\$#\\[\\]\\=~_\\-]+))*";

		String URL_NO_PROTOCOL = "(/($|[a-zA-Z0-9\\.\\,\\;\\?\\'\\\\\\+&%\\$#\\[\\]\\=~_\\-]+))*";

		/** 正規表現 : 英字パターン */
		String ALPHABET = "[a-zA-Z]+";

		/** 正規表現 : 英数字パターン */
		String ALPHA_NUMBER = "[a-zA-Z0-9]+";

		/** 正規表現 : 英数字パターン、[_]、[-] */
		String ALPHA_NUMBER2 = "[a-zA-Z0-9\\_\\-]+";

		/** 正規表現 : 半角英数字とハイフン */
		String ALPHA_NUMBER3 = "[a-zA-Z0-9\\-]+";

		/** 正規表現 : 半角英数字 */
		String HALF_ALPHA_NUMBER = "[a-zA-Z0-9\\s\r\n]+";

		/** 正規表現 : 数字パターン */
		String NUMBER = "[0-9]+";

		String NUMBER2 = "-?[0-9]+";
		
		/** 正規表現 : 数値パターン */
		String NUMERIC = "-?[0-9]+(\\.?[0-9]*)?";

		String DOUBLE = "^[0-9]+(\\.([0-9]+))?$";

		String TEL = "[0-9\\-]+";

		String PHONE = "[0-9\\-\\+]+";

		// String EMAIL =
		// "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

		String SPECIAL_CHARS = "\\(\\)<>@,;:'\\\\\\\"\\.\\[\\]";

		String VALID_CHARS = "[^\\s" + SPECIAL_CHARS + "]";

		String QUOTED_USER = "(\"[^\"]*\")";

		String WORD = "((" + VALID_CHARS + "|')+|" + QUOTED_USER + ")";

		/**
		 * メールチェックPLUS、日本のdocomoの場合もできる。 ASSERT<br>
		 * EmailTypeValidator.isEmail("motoishi@team-lab.com"); → true 2<br>
		 * EmailTypeValidator.isEmail("motoishi.@team-lab.com"); → true
		 * 3<br>
		 * EmailTypeValidator.isEmail("motoishi+motoishi@team-lab.com");
		 * → true<br>
		 * EmailTypeValidator.isEmail("mot.o.ishi.@team-lab.com") ; →
		 * true 5<br>
		 * EmailTypeValidator.isEmail(".motoishi@team-.lab.com"); →
		 * false 6<br>
		 * EmailTypeValidator.isEmail("motoishi@team-lab.com."); → false
		 * 7<br>
		 * EmailTypeValidator.isEmail("motoishi@.team-lab.com"); → false
		 * 8<br>
		 * EmailTypeValidator.isEmail("motoishi.. @docomo.ne.jp"); →
		 * false
		 * 
		 */
		String EMAIL_PLUS = "/^\\s*" + WORD + "(\\.|" + WORD + ")*\\s*$/";

		/** 正規表現:tag */
		String RE_TAG = "<[^>]*>";

		/** 有効日付 */
		String DATE = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";

		String SLASH_YYYY_MM_DD = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
		
		String BARS_YYYY_MM_DD = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

		String BARS_YYYY_MM_DD_HH_MM_SS = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";
		
		String BARS_YYYY_MM_DD_HH_MM_SS_SSS = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\.[0-9]{3}";

		// HH:mm:ss
		String TIME_HH_MM_SS = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
	}

	/** 正規表現 : 全角 */
	private static final Pattern RE_FULL = Pattern.compile(Re.FULL);

	/** 正規表現 : パスワードチェック「英数字」「!"#$%&'()*+,-./:;<=>?@[\]^_`{｜}」含める */
	public static final Pattern RE_PASSWORDCHECK = Pattern
			.compile("[a-zA-Z0-9!\"#$%&'()*+,-./:;<=>?@^_`{｜}\\[\\]\\\\]+");

	/** 正規表現 : 英数字パターン */
	private static final Pattern RE_ALPHANUMBER = Pattern.compile(Re.ALPHA_NUMBER);

	/** 正規表現 : 英字パターン */
	private static final Pattern RE_ALPHABET = Pattern.compile(Re.ALPHABET);

	/** 正規表現 : 数字パターン */
	private static final Pattern RE_NUMBER = Pattern.compile(Re.NUMBER);
	private static final Pattern RE_NUMBER2 = Pattern.compile(Re.NUMBER2);

	/** 正規表現 : 数値パターン */
	private static final Pattern RE_NUMERIC = Pattern.compile(Re.NUMERIC);

	private static final Pattern RE_DOUBLE = Pattern.compile(Re.DOUBLE);

	/** 正規表現 : 英数字パターン、[_]、[-] */
	private static final Pattern RE_ALPHANUMBER2 = Pattern.compile(Re.ALPHA_NUMBER2);

	/** 正規表現 : 半角英数字とハイフン */
	private static final Pattern RE_ALPHANUMBER3 = Pattern.compile(Re.ALPHA_NUMBER3);

	/** 正規表現 : 英数字パターン */
	private static final Pattern RE_HALFALPHANUMBER = Pattern.compile(Re.HALF_ALPHA_NUMBER);

	/** 正規表現: TEL */
	private static final Pattern TEL = Pattern.compile(Re.TEL);

	private static final Pattern PHONE = Pattern.compile(Re.PHONE);

	/** 正規表現: EMAIL */
	private static final Pattern EMAIL = Pattern.compile(Re.EMAIL_PLUS);

	/** 正規表現: URL */
	private static final Pattern URL = Pattern.compile(Re.URL, Pattern.CASE_INSENSITIVE);
	private static final Pattern URLHTTPS = Pattern.compile(Re.URLHTTPS, Pattern.CASE_INSENSITIVE);

	private static final Pattern URL_NO_PROTOCOL = Pattern.compile(Re.URL_NO_PROTOCOL);

	/** 正規表現式：Date */
	private static final Pattern DATE = Pattern.compile(Re.DATE);

	private static final Pattern RE_SLASH_YYYY_MM_DD = Pattern.compile(Re.SLASH_YYYY_MM_DD);
	
	private static final Pattern RE_BARS_YYYY_MM_DD = Pattern.compile(Re.BARS_YYYY_MM_DD);
	
	private static final Pattern RE_BARS_YYYY_MM_DD_HH_MM_SS = Pattern.compile(Re.BARS_YYYY_MM_DD_HH_MM_SS);
	
	private static final Pattern RE_BARS_YYYY_MM_DD_HH_MM_SS_SSS = Pattern.compile(Re.BARS_YYYY_MM_DD_HH_MM_SS_SSS);

	private static final Pattern RE_TIME_HH_MM_SS = Pattern.compile(Re.TIME_HH_MM_SS);

	/**
	 * 英数字属性チェック。<br/>
	 * 対象文字列が英数字のみで構成されていることをチェックする。<br>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が英数字のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に英数字以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isAlphaNumber(String str) {
		return (str != null) && RE_ALPHANUMBER.matcher(str).matches();
	}

	/**
	 * パスワードフォーマットチェック.<br/>
	 * 英数字と「!"#$%&'()*+,-./:;<=>?@[\]^_`{｜}」一部の記号が受け付ける
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPassword(String s) {
		return (s != null) && RE_PASSWORDCHECK.matcher(s).matches();
	}

	/**
	 * 英字属性チェック。<br/>
	 * 対象文字列が英字のみで構成されていることをチェックする。<br/>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が英字のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に英字以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isAlphabet(String str) {
		return (str != null) && RE_ALPHABET.matcher(str).matches();
	}

	/**
	 * 英数字属性チェック。<br/>
	 * 対象文字列が英数字のみで構成されていることをチェックする。<br>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が英数字のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に英数字以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isTel(String str) {
		return (str != null) && TEL.matcher(str).matches();
	}

	public static boolean isPhone(String str) {
		return (str != null) && PHONE.matcher(str).matches();
	}

	/**
	 * 数字属性チェック。<br/>
	 * 対象文字列が数字のみで構成されていることをチェックする。<br/>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が数字のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に数字以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isNumber(String str) {
		return !StringUtils.isBlank(str) && RE_NUMBER.matcher(str).matches();
	}
	
	/**
	 * 数字属性チェック。<br/>
	 * 対象文字列が数字のみ(符号を含むことができる)で構成されていることをチェックする。<br/>
	 *
	 * <pre>
     * Validator.isNumber2(null)   = false
     * Validator.isNumber2("")     = false
     * Validator.isNumber2("  ")   = false
     * Validator.isNumber2("123")  = true
     * Validator.isNumber2("12 3") = false
     * Validator.isNumber2("ab2c") = false
     * Validator.isNumber2("12-3") = false
     * Validator.isNumber2("12.3") = false
     * Validator.isNumber2("-123") = true
     * </pre>
	 * @param str 対象文字列
	 * @return 対象文字列が数字のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に数字以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isNumber2(String str) {
		return !StringUtils.isBlank(str) && RE_NUMBER2.matcher(str).matches();
	}

	/**
	 * 数値属性チェック。<br/>
	 * 対象文字列が数値(符号や小数点を含むことができる)として解釈できることをチェックする。<br/>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が数値として解釈できる場合 : <code>true</code><br/>
	 *         対象文字列が数値として解釈できない場合 : <code>false</code>
	 */
	public static boolean isNumeric(String str) {
		return !StringUtils.isBlank(str) && RE_NUMERIC.matcher(str).matches();
	}

	/**
	 * 数値double属性チェック。<br/>
	 * 対象文字列が数値(小数点を含むことができる)として解釈できることをチェックする。<br/>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が数値として解釈できる場合 : <code>true</code><br/>
	 *         対象文字列が数値として解釈できない場合 : <code>false</code>
	 */
	public static boolean isDouble(String str) {
		return !StringUtils.isBlank(str) && RE_DOUBLE.matcher(str).matches();
	}

	/**
	 * メール有効性のチェック<br>
	 * ドットが連続する、＠の直前にドットがある　を許容する
	 */
	public static boolean isMailAddr(String mailAddr) {
		return new Validator().isValid(mailAddr);
	}

	protected boolean isValidUser(String mailAddr) {
		Perl5Util userMatcher = new Perl5Util();
		if (!userMatcher.match(Re.EMAIL_PLUS, mailAddr)) {
			return false;
		}

		if (mailAddr.indexOf(" ") != -1) {
			return false;
		}
		return true;
	}

	/**
	 * <br/>
	 * 対象文字列が英数字、[_]、[-]のみで構成されていることをチェックする。<br>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が英数字、[_]、[-]のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に英数字、[_]、[-]以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isAlphaNumber2(String str) {
		return (str != null) && RE_ALPHANUMBER2.matcher(str).matches();
	}

	/**
	 * <br/>
	 * 対象文字列が半角英数字とハイフンのみで構成されていることをチェックする。<br>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が半角英数字とハイフンのみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に半角英数字とハイフン以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isAlphaNumber3(String str) {
		return (str != null) && RE_ALPHANUMBER3.matcher(str).matches();
	}

	/**
	 * <br/>
	 * 対象文字列が半角英数字のみで構成されていることをチェックする。<br>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が半角英数字のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に半角英数字以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isHalfAlphaNumber(String str) {
		return (str != null) && RE_HALFALPHANUMBER.matcher(str).matches();
	}

	/**
	 * 対象文字列が全角のみで構成されていることをチェックする。<br>
	 * 
	 * @param str 対象文字列
	 * @return 対象文字列が全角のみで構成されている場合 : <code>true</code><br/>
	 *         対象文字列に全角以外の文字が含まれる場合 : <code>false</code>
	 */
	public static boolean isFull(String str) {
		return (str != null) && RE_FULL.matcher(str).matches();
	}

	public static boolean isFormatedDouble(String str, int plusCount, int minusCount) {
		String regex;
		if (plusCount <= 0) {
			if (minusCount <= 0) {
				regex = "[\\+|\\-]?0";
			} else {
				regex = "[\\+|\\-]?0+(.[0-9]{0," + minusCount + "})?";
			}
		} else {
			if (minusCount <= 0) {
				regex = "[\\+|\\-]?0*[0-9]{1," + plusCount + "}";
			} else {
				regex = "[\\+|\\-]?0*[0-9]{1," + plusCount + "}(.[0-9]{0," + minusCount + "})?";
			}
		}
		Pattern double_pat = Pattern.compile(regex);
		return !StringUtils.isBlank(str) && double_pat.matcher(str).matches();
	}

	/**
	 * URLチェック
	 * 
	 * @param str
	 * @return
	 * @author zx
	 */
	public static boolean isURL(String str) {
		return URL.matcher(str).matches();
	}

	public static boolean isURLNoProtocol(String str) {
		return URL_NO_PROTOCOL.matcher(str).matches();
	}

	public static boolean isValidateyyyyMMddDate(String str) {

		if (!isSlashyyyyMMdd(str)) {
			return false;
		}

		// String[] dt = str.split(StringUtil.Characters.SLASH);
		//
		// return DATE.matcher(
		// StringUtils.join(new String[] { dt[0], dt[1], dt[2] }, "-"))
		// .matches();'
		return DATE.matcher(str).matches();
	}

	public static boolean isSlashyyyyMMdd(String str) {
		return RE_SLASH_YYYY_MM_DD.matcher(str).matches();
	}
	
	public static boolean isBarsyyyyMMdd(String str) {
		return RE_BARS_YYYY_MM_DD.matcher(str).matches();
	}

	public static boolean isBarsyyyyMMddHHmmss(String str) {
		return RE_BARS_YYYY_MM_DD_HH_MM_SS.matcher(str).matches();
	}
	
	public static boolean isBarsyyyyMMddHHmmssSSS(String str) {
		return RE_BARS_YYYY_MM_DD_HH_MM_SS_SSS.matcher(str).matches();
	}

	public static boolean isTimeHHMMSS(String s) {
		return RE_TIME_HH_MM_SS.matcher(s).matches();
	}

	/**
	 * URLチェック
	 * 
	 * @param str
	 * @return
	 * @author cxj
	 */
	public static boolean isURLS(String str) {
		return URL.matcher(str).matches() || URLHTTPS.matcher(str).matches();
	}
}
