package huiswerknakijken.hu.LeraarServlets;

import huiswerknakijken.hu.DAO.ClassDAO;
import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Klass;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Util.ExcelImport;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;


public class LeerlingImporteerServlet extends HttpServlet {
	private String saveFile=System.getProperty("java.io.tmpdir"); //The excel file get's saved in the temp directory of Tomcat
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		File file = getFileFromServer(req,resp);
		List<Object> users = ExcelImport.readFile(file);
		PersonDAO dao = new PersonDAO();
		Student s = null;
		int i = -4;
		//Here we loop through the objects retrieved from the excel file
		//We use 'i' to determine which value we retrieve:
		//0 = studentID
		//1 = First name
		//2 = Last name
		//3 = Class name
		//4 = Email
		for (Object o : users){
			if(i<0){
				i++;
				continue;
			}
			if(i == 0){
				if (s == null){
					s = new Student();
					s.setID(Integer.parseInt((String) o));
					s.setRole(UserRole.Student);
					s.setPassword("");
				}
			}
			
			
			if(i==1)
				s.setFirstName(o.toString());
			else if(i==2)
				s.setLastName(o.toString());
			else if(i==3){
				ClassDAO cdao = new ClassDAO();
				Klass c = cdao.retrieveByName(o.toString(), 1);
				if (c == null){
					c = new Klass(o.toString());
					cdao.add(c);
				}
				s.setMainClass(c);		
			}
			else if(i==4)
			{
				System.out.println("FIX EXCEL FILE!");
				s.setEmail(o.toString());
				if (dao.retrieveByEmail(s.getEmail(), 0) == null)
					dao.addStudent(s);
				i = -1;
			}
			i++;
		}
		rd = req.getRequestDispatcher("LeraarOverzicht.jsp");
		if (rd != null){
			rd.forward(req, resp);
		}
		//doGet(req, resp);
	}
	
	protected File getFileFromServer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        
		 response.setContentType("application/ms-excel");
		 PrintWriter out = response.getWriter();
		 File f = null;
		 try {
			 boolean ismultipart=ServletFileUpload.isMultipartContent(request);
			 if(!ismultipart){
	
			 }
			 else
			 {
				 FileItemFactory factory = new DiskFileItemFactory();
				 ServletFileUpload upload = new ServletFileUpload(factory);	
				 List items = null;
	
				 try{
	
					 items = upload.parseRequest(request);
				 }catch(Exception e){
					 e.printStackTrace();
				 }	
				 Iterator itr = items.iterator();
				 while(itr.hasNext()){
					 Object o = itr.next();
					 FileItem item = (FileItem)o;
					 
					 if(item.isFormField()){
		
					 }else{
						 String itemname = item.getName();
						 if((itemname==null || itemname.equals(""))){
							 continue;
						 }
						 String filename = FilenameUtils.getName(itemname);
						 f = checkExist(filename);
						 item.write(f);
					 }
				 }
			 }	

		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 finally {
		 out.close();
		 }
		 return f;
	}

	 private File checkExist(String fileName) {
		 File f = new File(saveFile+"/"+fileName);
		 
		 if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		else{
			 StringBuffer sb = new StringBuffer(fileName);
			 sb.insert(sb.lastIndexOf("."),"-"+new Date().getTime());
			 f = new File(saveFile+"/"+sb.toString());
		 }
		 return f;
	 }
		    
	
	
}
