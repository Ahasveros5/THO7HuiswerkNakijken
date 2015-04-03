package huiswerknakijken.hu.Util;

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

}
