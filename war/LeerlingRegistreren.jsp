 <%@ include file="header.jsp" %>
   
   	 	<div class="user_register_container">
     <h2>Registreren Leerling</h2>
     <div id="myform">	
     
		  <fieldset id="Klant">

			  <form class="registerform" action="LeerlingRegistratieServlet.do" method="post">
			  <label><b>Voornaam:</b></label>
			  <input type="text" size="25" name="invoer_naam" /><br />
			  <label><b>Achternaam:</b></label>
			  <input type="text" size="25" name="invoer_achternaam" /><br />
              <label><b>Studentnummer:</b></label>
			  <input type="text" size="25" name="invoer_studentnr" /><br />
			  <label><b>E-mailadres:</b></label>
			  <input type="text" size="25" name="invoer_email" /> <br />
			  <label><b>E-mailadres:</b><span>Ter Bevestiging</span></label>
			  <input type="text" size="25" name="invoer_emailb" /> <br /> 
			  <label><b>Wachtwoord:</b></label>
			  <input type="password" size="25" name="invoer_ww" /><br />
			  <label><b>Wachtwoord:</b><span>Ter Bevestiging</span></label>
			  <input type="password" size="25" name="invoer_wwb" /> <br/>
			  
			   <input class="button" type="submit" name="knop" value="Registreren!" />
			  </form>
			  </fieldset>
			 
		
           
     </div>
    </div>
       
     </div>   

        
        
	</body>
</html>