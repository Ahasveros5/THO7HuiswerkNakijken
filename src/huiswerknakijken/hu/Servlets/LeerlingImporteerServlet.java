package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.DAO.PersonDAO;
import huiswerknakijken.hu.Domain.Person;
import huiswerknakijken.hu.Domain.Person.UserRole;
import huiswerknakijken.hu.Domain.Student;
import huiswerknakijken.hu.Util.ExcelImport;

import java.io.File;
import java.io.FileInputStream;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class LeerlingImporteerServlet extends HttpServlet {
	private String saveFile=System.getProperty("java.io.tmpdir");
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		File file;
		//System.out.println("ummmm: " + saveFile);
		file = getFileFromServer(req,resp);
		System.out.println("boia");
		List<Object> users = ExcelImport.readFile(file);
		System.out.println("temp: " + users.toString());
		PersonDAO dao = new PersonDAO();
		Person s = null;
		int i = 0;
		for (Object o : users){
			i++;
			if(o instanceof Number){
				if (s == null){
					s = new Student();
					s.setID((int) o);
				} else {
					if (dao.retrieveByEmail(s.getEmail(), 0) == null)
						dao.add(s);
					s = new Student();
					s.setID((int) o);
				}
			}
			if(i==1)
				s.setFirstName((String) o);
			else if(i==2)
				s.setLastName((String) o);
			else if(i==3){
				s.setEmail(s.getFirstName() + "." + s.getLastName() + "@student.hu.nl");
				i = 0;
				s.setRole(UserRole.Student);
				//Class shit moet hier nog
			}
			System.out.println("tralalala");
		}
		rd = req.getRequestDispatcher("LeraarOverzicht.jsp");
		if (rd != null)
			rd.forward(req, resp);
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
					 System.out.println(o.toString() + "\n");
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
		 
		 System.out.println("saving");
		 if(!f.exists()){
			 System.out.println("Creating new");
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		else{
			 System.out.println("saved");
			 StringBuffer sb = new StringBuffer(fileName);
			 sb.insert(sb.lastIndexOf("."),"-"+new Date().getTime());
			 f = new File(saveFile+"/"+sb.toString());
		 }
		 return f;
	 }
		    
	
	
}
