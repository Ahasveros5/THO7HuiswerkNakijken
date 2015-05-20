<%@ include file="header.jsp" %>

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