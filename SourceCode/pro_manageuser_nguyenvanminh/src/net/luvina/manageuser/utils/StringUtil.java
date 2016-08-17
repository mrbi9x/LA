/**
 * Copyright(C) @2016 Luvina Software Company
 * StringUtil.java, Jun 27, 2016, Nguyễn Văn Minh
 */
package net.luvina.manageuser.utils;

import java.lang.Character.UnicodeBlock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil - Chứa các hàm common của dự án về kiểu String
 * @author Nguyễn Văn Minh
 *
 */
public class StringUtil {

	/**
	 *
	 */
	public StringUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * checkKanakata - Kiểm tra chuỗi có phải là chuỗi katakana hay không
	 * @param strCheck - Chuỗi cần kiểm tra
	 * @return True - Nếu là chuỗi katakana
	 * 	False - Nếu không phải là chuỗi katakana
	 */
	public static boolean checkKanakata(String strCheck) {
			for (char c : strCheck.toCharArray()) {
				if ( (UnicodeBlock.of(c) != UnicodeBlock.KATAKANA) && (!Character.isDigit(c)) && (!Character.isWhitespace(c)) ) {
					return false;
				}
			}
		return true;
	}

	/**
	 * isEnglish - Kiểm tra chuỗi có phải là ký tự tiếng anh hay không
	 * @param strCheck - chuỗi cần kiểm tra
	 * @return true - Nếu là ký tự tiếng anh 1 byte
	 * false - Nếu không phải là ký tự 1 byte - Tiếng anh
	 */
	public static boolean isEnglish(String strCheck) {
        return Pattern.matches("[a-zA-Z0-9]+", strCheck);
    }

	/**
	 * isDigit - Hàm kiểm tra ký tự có phải là chữ số hay không
	 * @param c - ký tự cần kiểm tra
	 * @return true - Nếu là chữ số
	 * false - Nếu không phải là chữ số
	 */
	public static boolean isDigit(char c) {
		int x = (int) c;
		if ((x >= 48) && (x <= 57)) {
			return true;
		}
		return false;
	}

	/**
	 * checkMatcherPattern - Kiểm tra chuỗi có khớp với biểu thức hay không
	 * @param strCheck
	 * @param patternCheck
	 * @return true - Nếu khớp
	 * @return false - Nếu không khớp
	 */
	public static boolean checkMatcherPattern (String strCheck, String patternCheck) {
		Pattern pattern = Pattern.compile(patternCheck);
		Matcher matcher = pattern.matcher(strCheck);
		return matcher.matches();
	}

}
