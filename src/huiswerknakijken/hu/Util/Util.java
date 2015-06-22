package huiswerknakijken.hu.Util;

import java.util.Date;

public class Util {
	
	public static int boolToInt(boolean b){
		if (b)
			return 0; //true
		return 1; //false
	}
	
	public static boolean intToBool(int i){
		if (i == 0)
			return true;
		else if(i == 1)
			return false;
		System.out.println("Trying to convert int value above 1 to a bool, returning FALSE!");
		return false;
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

}
