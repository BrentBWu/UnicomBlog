package com.unicom.blog.utils;

/**
 * <p>Title: 应用模块</p>
 * <p>Description: 代码描述</p>
 *
 * @author huzy
 * @date 2017-1-10 17:08
 */
public class StringUtil {
    /**
     * 判断字符串是否为null
     *
     * @param s
     *            需要非空判断的字符串
     * @return 为空返回true,否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null ? true : "".equals(s.trim());
    }

    /**
     * 判断不为null
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
    
    /**
     * 字符串中是否有大写字母，有则返回大写字母index，无则返回-1
     * @param src
     * @return
     */
    public static Integer withUpperCase(String src) {
    	for(int i = 0; i < src.length(); i++)
    	{
    		char c = src.charAt(i);
    		if (!Character.isLowerCase(c))
    		{
    			return i;
    		}
    	}
    	return -1;
    }

    /**
     * 按照大写字符拆分为表字段格式 如:parentId 转换结果为 parent_id
     * @param src
     * @return
     */
    public static String split4Mybatis(String src) {
		StringBuilder r = new StringBuilder();
		Integer index = withUpperCase(src);
		if (index>-1) {
			if (src.substring(0, index).length()>1) {
				r.append(src.substring(0, index)).append("_")
					.append(src.substring(index, index+1).toLowerCase());
			} else {
				r.append(src.substring(0, index+1).toLowerCase());
			}
			r.append(split4Mybatis(src.substring(index+1)));
		} else {
			r.append(src);
		}
		return r.toString();
	}

	/**
	 * 按照给定长度提取字符串，并增加指定后缀，默认后缀 '...'
	 * @param src
	 * @param limit
	 * @param suffix
	 * @return
	 */
	public static String abstractString(String src,Integer limit,String suffix) {
		String result = src;
		if (limit!=null) {
			if (src.length()>limit) {
				if (isEmpty(suffix)) {
					suffix = "...";
				}
				result = new String(src.substring(0,limit-suffix.length()).concat(suffix));
			}
		}
		return result;
	}

	public static String toString(Object[] array) {
		int len = array.length;
		if ( len == 0 ) {
			return "";
		}
		StringBuilder buf = new StringBuilder( len * 12 );
		for ( int i = 0; i < len - 1; i++ ) {
			buf.append( array[i] ).append( ", " );
		}
		return buf.append( array[len - 1] ).toString();
	}
	
	public static String hidePhone(String phoneCode){
		return phoneCode.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
	}
	
	public static String hideCard(String cardCode){
		return cardCode.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2");
	}
}
