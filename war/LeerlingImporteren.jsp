
<!DOCTYPE html>
<html>
	<head>
		<title>Leerling importeren</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	</head>
	
	<body>
<%@ include file="header.jsp" %>
	 <div class="body_background">
		<div id="header">
   	 	<div id="header_content">
                             
     			<div id="navigation_header">

     			</div>
     	</div> 
   </div> 
   
   
<% Object msgs = request.getAttribute("msgs");
	if (msgs != null) {
		out.println("<div id=\"messagebox\">");
		out.println(msgs); 
		out.println("</div>");
	} 
%> 
   
   	 	<div class="user_register_container">
     <h2>Registreren Leerling</h2>
     <div id="myform">	
     
			<fieldset id="Klant">

			  	<form action="LeerlingImporteerServlet.do" method="post" accept=".xlsx" enctype="multipart/form-data">
					<input type="file" name="file" size="50" />
					<br />
					<input type="submit" value="Upload File" />
				</form>
			</fieldset>
			 
		
           
     </div>
    </div>
       
     </div>   

        
        
	</body>
</html>