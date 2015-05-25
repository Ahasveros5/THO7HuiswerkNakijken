 <%@ include file="header.jsp" %>
   
   	 	<div class="user_register_container">
     <h2>Registreren Leerling</h2>
     <div id="myform">	
     
		  <fieldset id="Klant">

			  <form class="registerform" action="NieuwWachtwoordServlet.do" method="post">
			  <label><b>Wachtwoord:</b></label>
			  <input type="password" size="25" name="invoer_ww" /><br />
			  <label><b>Wachtwoord:</b><span>Ter Bevestiging</span></label>
			  <input type="password" size="25" name="invoer_wwb" /> <br/>
			  
			   <input class="button" type="submit" name="knop" value="Bevestigen!" />
			  </form>
			  </fieldset>
			 
		
           
     </div>
    </div>
       
     </div>   

        
        
	</body>
</html>