 <%@ include file="header.jsp" %>
   
   	 	<div class="user_register_container">
     <h2>Registreren Leraar</h2>
     <div id="myform">	
     
		  <fieldset id="Klant">

			  <form action="LeraarRegistratieServlet.do" method="post">
			  <label><b>Voornaam:</b></label>
			  <input type="text" size="25" name="invoer_naam" /><br />
			  <label><b>Achternaam:</b></label>
			  <input type="text" size="25" name="invoer_achternaam" /><br />
              
</fieldset><br />
			  <fieldset id="deelnemer">
			 
			  <label><b>E-mailadres:</b></label>
			  <input type="text" size="25" name="invoer_email" /> <br />
			  <label><b>E-mailadres:</b><span>Ter Bevestiging</span></label>
			  <input type="text" size="25" name="invoer_emailb" /> <br /> 
			  <label><b>Wachtwoord:</b></label>
			  <input type="password" size="25" name="invoer_ww" /><br />
			  <label><b>Wachtwoord:</b><span>Ter Bevestiging</span></label>
			  <input type="password" size="25" name="invoer_wwb" /> <br/>
			  </fieldset>
			  
			  
			  <input class="button" type="submit" name="knop" value="Registreren!" />
		   </form>		
           
     </div>
    </div>
       
     </div>   
        <div id="footer_border">
        </div>
        <div id="footer">
        <div id="footer_container">
        <div id="footer_blok1">
        test
        </div>
        <div id="footer_blok2">
        test
        </div>
        <div id="footer_blok3">
        test
        </div>
        </div> 
        
        </div>
        
	</body>
</html>