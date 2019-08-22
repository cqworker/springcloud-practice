package com.kw13.util;

import java.text.DecimalFormat;

public class AsstUtils
{

	/**
	 * 字符串是否为空或null
	 * 
	 * @param param
	 * @return
	 */
	public static boolean empty(String param)
	{
		return param == null || param.trim().length() < 1;
	}

	/**
	 * 返回空串
	 * 
	 * @param param
	 * @return
	 */
	public static String nvl(String param)
	{
		return param != null ? param : "";
	}

	public static int parseint(String param)
	{
		return parseint(param, 0);
	}

	/**
	 * 将一个字符串转换为int,提供一个失败时的缺省值
	 * 
	 * @param param
	 * @param dftValue
	 * @return
	 */
	public static int parseint(String param, int dftValue)
	{
		int i = dftValue;
		try
		{
			if (!empty(param)) {
				i = Integer.parseInt(param);
			}
		}
		catch (Exception exception)
		{
		}
		return i;
	}

	public static byte parsebyte(String param)
	{
		return parsebyte(param, (byte) 0);
	}

	/**
	 * 将一个字符串转换为byte,提供一个失败时的缺省值
	 * 
	 * @param param
	 * @param dftValue
	 * @return
	 */
	public static byte parsebyte(String param, byte dftValue)
	{
		byte i = dftValue;
		try
		{
			if (!empty(param)) {
				i = Byte.parseByte(param);
			}
		}
		catch (Exception exception)
		{
		}
		return i;
	}

	public static short parseshort(String param)
	{
		return parseshort(param, (short) 0);
	}

	/**
	 * 将一个字符串转换为byte,提供一个失败时的缺省值
	 * 
	 * @param param
	 * @param dftValue
	 * @return
	 */
	public static short parseshort(String param, short dftValue)
	{
		short i = dftValue;
		try
		{
			if (!empty(param)) {
				i = Short.parseShort(param);
			}
		}
		catch (Exception exception)
		{
		}
		return i;
	}

	public static double parsedouble(String param)
	{
		return parsedouble(param, 0.0);
	}

	public static double parsedouble(String param, double dftValue)
	{
		double df = dftValue;
		try
		{
			if (!empty(param)) {
				df = Double.parseDouble(param);
			}
		}
		catch (Exception exception)
		{
		}
		return df;
	}

	public static float parsefloat(String param)
	{
		return parsefloat(param, (float) 0.0);
	}

	public static float parsefloat(String param, float dftValue)
	{
		float df = dftValue;
		try
		{
			if (!empty(param)) {
				df = Float.parseFloat(param);
			}
		}
		catch (Exception exception)
		{
		}
		return df;
	}

	public static long parselong(String param, long dftValue)
	{
		long l = dftValue;
		try
		{
			if (!empty(param))
				l = Long.parseLong(param);
		}
		catch (Exception exception)
		{
		}
		return l;
	}

	public static long parselong(String param)
	{
		long l = 0L;
		try
		{
			if (!empty(param))
				l = Long.parseLong(param);
		}
		catch (Exception exception)
		{
		}
		return l;
	}

	public static boolean parseboolean(String param)
	{
		return parseboolean(param, false);
	}

	public static boolean parseboolean(String param, boolean dftvalue)
	{
		if (empty(param))
			return dftvalue;
		switch (param.charAt(0))
		{
			case 49: // '1'
			case 84: // 'T'
			case 89: // 'Y'
			case 116: // 't'
			case 121: // 'y'
				return true;
			default:
				return false;
		}
	}

	public static String doubleToStr(double value, String format)
	{
		if (format == null || format.length() == 0)
			format = "#0.00";
		DecimalFormat sdf = new DecimalFormat(format);
		return sdf.format(value);
	}

	public static String doubleToStr(double value, int length)
	{
		if (length < 0)
			length = 2;
		if (length > 10)
			length = 10;

		if (length <= 0)
			return doubleToStr(value, "#0");
		else
		{
			StringBuffer tmpFormat = new StringBuffer("#0.");
			for (int i = 0; i < length; i++)
				tmpFormat.append("0");
			return doubleToStr(value, tmpFormat.toString());
		}
	}

	public static int fastSearch(int[] a, int value)
	{
		if (a != null)
		{
			int low = 0;
			int high = a.length - 1;
			int mid;

			while (low <= high)
			{
				mid = low >> 1 + high >> 1;
				if (a[mid] == value)
					return mid;
				else if (a[mid] > value)
					high = mid - 1;
				else
					low = mid + 1;
			}
		}
		return -1;
	}
	
	/**
	 * 将指定字符串转成int数组。
	 * 
	 * @param param 指定字符串。
	 * @param regex 分隔符。
	 * @return 通过指定字符串转换的int数组。
	 */
	public static int[] string2Ints(String param, String regex) {
		return string2Ints(param, regex, 0);
	}
	
	/**
	 * 将指定字符串转成int数组。
	 * 
	 * @param param 指定字符串。
	 * @param regex 分隔符。
	 * @param dftvalue 不能转换时的默认值。
	 * @return 通过指定字符串转换的int数组。
	 */
	public static int[] string2Ints(String param, String regex, 
			int dftvalue) {
		if (param == null || regex == null) {
			return new int[0];
		}
		
		String[] strs = param.split(regex);
		int[] ret = new int[strs.length];
		for (int i = 0; i < strs.length; i ++) {
			ret[i] = parseint(strs[i].trim(), dftvalue);
		}
		
		return ret;
	}
	
	/**
	 * 将指定字符串转成short数组。
	 * 
	 * @param param 指定字符串。
	 * @param regex 分隔符。
	 * @return 通过指定字符串转换的short数组。
	 */
	public static short[] string2Shorts(String param, String regex) {
		return string2Shorts(param, regex, (short) 0);
	}
	
	/**
	 * 将指定字符串转成short数组。
	 * 
	 * @param param 指定字符串。
	 * @param regex 分隔符。
	 * @param dftvalue 不能转换时的默认值。
	 * @return 通过指定字符串转换的short数组。
	 */
	public static short[] string2Shorts(String param, String regex, 
			short dftvalue) {
		if (param == null || regex == null) {
			return new short[0];
		}
		
		String[] strs = param.split(regex);
		short[] ret = new short[strs.length];
		for (int i = 0; i < strs.length; i ++) {
			ret[i] = parseshort(strs[i].trim(), dftvalue);
		}
		
		return ret;
	}
	
	/**
	 * 将指定字符串转成byte数组。
	 * 
	 * @param param 指定字符串。
	 * @param regex 分隔符。
	 * @return 通过指定字符串转换的byte数组。
	 */
	public static byte[] string2Bytes(String param, String regex) {
		return string2Bytes(param, regex, (byte) 0);
	}
	
	/**
	 * 将指定字符串转成byte数组。
	 * 
	 * @param param 指定字符串。
	 * @param regex 分隔符。
	 * @param dftvalue 不能转换时的默认值。
	 * @return 通过指定字符串转换的byte数组。
	 */
	public static byte[] string2Bytes(String param, String regex, 
			byte dftvalue) {
		if (param == null || regex == null) {
			return new byte[0];
		}
		
		String[] strs = param.split(regex);
		byte[] ret = new byte[strs.length];
		for (int i = 0; i < strs.length; i ++) {
			ret[i] = parsebyte(strs[i].trim(), dftvalue);
		}
		
		return ret;
	}
	
	/**
	 * 将指定int数组转成字符串。
	 * @param array 指定数组。
	 * @param regex 分隔符。
	 * @return 指定int数组转成的字符串。
	 * @author 2012-9-5 侯雄飞
	 */
	public static String ints2String(int[] array, String regex) {
		if (array == null || regex == null) {
			return "";
		}
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i ++) {
			if (i > 0) {
				buffer.append(regex);
			}
			buffer.append(array[i]);
		}
		
		return buffer.toString();
	}

}
