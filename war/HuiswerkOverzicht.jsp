<!DOCTYPE html>
<html>
	<head>
		<title>Vraag aanmaken</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
	<%@ include file="header.jsp" %>	
	<% Object msgs = request.getAttribute("msgs");
	if (msgs != null) {
		out.println("<div id=\"messagebox\">");
		out.println(msgs); 
		out.println("</div>");
	} 
	%>
	</body>
	</html>