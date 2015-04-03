
<!DOCTYPE html>
<html>
	<head>
		<title>Klantregistratie</title>
		<link rel="stylesheet" type="text/css" href="http://thestrike.nl/atd/css/atd_final.css" />
        <link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	</head>
	
	<body>

	 <div class="body_background">
		<div id="header">
   	 	<div id="header_content">
    			
               
                
                 <div id="logo_atd_small_holder">
<div id="logo_atd_small_shake_holder" class="shake" a="" href="/atd">
                	<a href="/atd" class="shake" id="logo_atd_small"></a>
               
</div>
                </div>
                
                
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
     <h2>Registreren Leraar</h2>
     <div id="myform">	
     
		  <fieldset id="Klant">

			  <form action="KlantRegistratieServlet.do" method="post">
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