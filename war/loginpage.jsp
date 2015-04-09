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
   	<div id="Inlogfield">
   		<h2>Inloggen</h2>
   		<form action="LoginServlet.do" method="post">
   		
   		<input class = "input" type="text" size="25" name="email_login" value="E-mail adres" ><br />
   		<input class = "input" type="password" size="25" name="ww_login" value="Wachtwoord" /><br />
   		<br />
   		<input class="button" type="submit" name="knop" value="Inloggen" />
   		</form>
   	</div>
  
  <style>
   	input:-webkit-input-placeholder {
    color: #b5b5b5;
}

input-moz-placeholder {
    color: #b5b5b5;
}

.input {
    background: #f5f5f5;
    font-size: 0.8rem;
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    border: none;
    padding: 13px 10px;
    width: 270px;
    margin-bottom: 20px;
    box-shadow: inset 0 2px 3px rgba( 0, 0, 0, 0.1 );
    clear: both;
}

.input:focus {
    background: #fff;
    box-shadow: 0 0 0 3px #fff38e, inset 0 2px 3px rgba( 0, 0, 0, 0.2 ), 0px 5px 5px rgba( 0, 0, 0, 0.15 );
    outline: none;
}
   	</style>

   	
   	
   	<%@ include file="footer.jsp" %>