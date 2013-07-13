package com.sumavision.itv.utils;

/**
 * 
 * @author 郭鹏
 *
 */

public class StringUtils {

	public static boolean isBlank( String input ) 
	{
		if ( input == null || "".equals( input ) )
			return true;
		
		for ( int i = 0; i < input.length(); i++ ) 
		{
			char c = input.charAt( i );
			if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean isHanzi(String str){
		  
	    char[] chars=str.toCharArray(); 
	    boolean isGB2312=false; 
        if(chars.length != str.getBytes().length){
        	
        	isGB2312 = true;
        }
	    
	    return isGB2312; 
	}
}
