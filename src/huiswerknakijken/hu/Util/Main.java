package huiswerknakijken.hu.Util;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	
	public static void main(String [ ] args) throws FileNotFoundException
	{
		try {
			Date date = null;
            String target = "31 - 12 - 2015 Om 23:59";
            target = target.replace(" Om ", " ");
            System.out.println(target);
            DateFormat df = new SimpleDateFormat("dd - mm - yyyy hh:mm");
            date =  df.parse(target);
            System.out.println(date); 
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
	}

}
