<!DOCTYPE html>
<html>
	<head>
		<title>Auto registratie</title>
		<link rel="stylesheet" type="text/css" href="atd_final.css" />
        <link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	</head>
	
	<body>
	
		<div id="header">
   	 	<div id="header_content">
    			
                
                
                 <div id="logo_atd_small_holder">
<div id="logo_atd_small_shake_holder" class="shake" a="" href="/atd">
                	<a href="/atd" class="shake" id="logo_atd_small"></a>
               
</div>
                </div>
                
                
     			<div id="navigation_header">
                <ul>
<li><a href="">Over Ons</a></li>
<li><a href="">Aanmelden</a></li>
<li><a href="">Registreren</a></li>
<li><a href="">Contact</a></li>

</ul>
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
   <h2>Inloggen</h2>
   
   	<div id="Inlogfield">
   		<form action="LoginServlet.do" method="post">
   		<label><b>E-mail adres</b></label>
   		<input type="text" size="25" name="email_login" /><br />
   		<label><b>Wachtwoord</b></label>
   		<input type="password" size="25" name="ww_login" /><br />
   		
   		<input class="button" type="submit" name="knop" value="Inloggen" />
   	</div>
   	
   	</form>
   	
   	
   	
   	
   	
   	
   	
   	
   	