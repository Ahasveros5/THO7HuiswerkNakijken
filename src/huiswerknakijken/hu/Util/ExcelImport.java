package huiswerknakijken.hu.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


//import statements
public class ExcelImport
{
    public static List<Object> readFile(File f) throws FileNotFoundException
    {
    	
    	List<Object> temp = new ArrayList<Object>();
    	//f = new File("C:\\xampp\\tomcat\\temp\\Template Leerling informatie-1430476871960.xlsx");
    	InputStream is = new FileInputStream(f);
    	System.out.println("BOA");
        try
        {
        	System.out.println("BOA2");
        	if(f == null)
        		System.out.println("nullllllerino");
        	else
        		System.out.println("not nulllerino");
        	//f = new File("C:\\Users\\Guido Laptop\\Documents\\THO7HuiswerkNakijken\\te1mp.xlsx");
        	if(f.exists())
        		System.out.println("exists");
        	else
        		System.out.println("not existing");
        	System.out.println("path: " + f.getAbsolutePath());
        	System.out.println("name: " + f.getName());
            //Create Workbook instance holding reference to .xlsx file
        	XSSFWorkbook workbook = null;
        	try {
        		workbook = new XSSFWorkbook(is);
        	} catch(Exception e){
        		e.getStackTrace();
        	}
            System.out.println("BOA3");
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
            	System.out.println("JAAAAAAAA");
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                 
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                        	DecimalFormat df = new DecimalFormat("#"); 
                            temp.add(df.format(cell.getNumericCellValue()));
                            break;
                        case Cell.CELL_TYPE_STRING:
                            temp.add(cell.getStringCellValue());
                            break;
                    }
                    
                }
            }
            f.delete();
            workbook.close();
            System.out.println(temp.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return temp;
    }
}