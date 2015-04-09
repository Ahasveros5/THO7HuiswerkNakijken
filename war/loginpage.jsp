<!DOCTYPE html>
<html>
	<head>
		<title>Loginpage</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
        <link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	</head>
	
	<body>
	<div>
		<%@ include file="header.jsp" %>
   </div> 
   		
   <h2>Inloggen</h2>
   
   	<div id="Inlogfield">
   		<h2>Inloggen</h2>
   		<form action="LoginServlet.do" method="post">
   		<label><b>E-mail adres</b></label>
   		<input type="text" size="25" name="email_login" /><br />
   		<label><b>Wachtwoord</b></label>
   		<input type="password" size="25" name="ww_login" /><br />
   		<br />
   		<input class="button" type="submit" name="knop" value="Inloggen" />
   		</form>
   	</div>
   	</div>
   	
   	
   	<%@ include file="footer.jsp" %>