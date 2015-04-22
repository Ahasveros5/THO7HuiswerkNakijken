package huiswerknakijken.hu.Servlets;

import huiswerknakijken.hu.Util.ExcelImport;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeerlingImporteerServlet extends HttpServlet {
	
	 /*protected void doGet(
		        HttpServletRequest request, HttpServletResponse response
		        ) throws ServletException, IOException {
		        
		        // Not used in our simple example - see text.
		        // String imageName = request.getParameter("imageName");
		        
		        // For this example, just create our input stream from our sample byte array:
		        ByteArrayInputStream iStream = new ByteArrayInputStream();
		        
		        // Determine the length of the content data.
		        // In our simple example, I can get the length from the hard-coded byte array.
		        // If you're getting your imaqe from a database or file,
		        // you'll need to adjust this code to do what is appropriate:
		        int length = SampleImage.sampleImage.length;
		        
		        // Hard-coded for a GIF image - see text.
		        response.setContentType("image/gif");
		        response.setContentLength(length);
		        
		        // Get the output stream from our response object, so we
		        // can write our image data to the client:
		        ServletOutputStream oStream = response.getOutputStream();
		        
		        // Now, loop through buffer reads of our content, and send it to the client:
		        byte [] buffer = new byte[1024];
		        int len;
		        while ((len = iStream.read(buffer)) != -1) {
		            oStream.write(buffer, 0, len);
		        }
		        
		        // Now that we've sent the image data to the client, close down all the resources:
		        iStream.close();
		        
		        oStream.flush();
		        oStream.close();
		        
		        // And we're done. Just let the method return at this point.
		    }*/
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		RequestDispatcher rd = null;
		HttpSession session = req.getSession();
		
		File f = (File)session.getAttribute("file");
		ExcelImport.readFile(f);
		//doGet(req, resp);
	}
}
