<!DOCTYPE html>
<html>
	<head>
		<title>Vraag aanmaken</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
		
	<% Object msgs = request.getAttribute("msgs");
	if (msgs != null) {
		out.println("<div id=\"messagebox\">");
		out.println(msgs); 
		out.println("</div>");
	} 
	%> 
	
	<h2>Vraag aanmaken</h2>
	
	 <form action="MultipleChoiceAanmakenServlet.do" method="post">
			  <label><b>Vraag</b></label>
			  <input type="text" size="25" name="vraagMultipleChoice" /><br />
			  <br />
			  
			  
			  <label><b>Mogelijke antwoorden</b></label>
			  <div id="dynamicInput">
         		 Antwoord 1<br><input type="text" name="Antwoord1">
   			  </div>
			  <input type="button" value="Voeg Antwoord toe" onClick="addTextField('dynamicInput');">	
			  
			  
			  <script language="javascript" type="text/javascript">
			  var i = 1;
			  function addTextField(divName){
				  var newdiv = document.createElement('div');
				  i = i + 1;
		          newdiv.innerHTML = "Antwoord " + i + " <br><input type='text' name='Antwoord"+i"'><input type= 'button' value='X' onClick='deleteTextField('dynamicInput');>"
		          document.getElementById(divName).appendChild(newdiv);
		          //i++;
			  }
			  function deleteTextField(divName){
				var parent = document.getElementById(divName);
				var child = parent.firstElementChild;
				  document.getElementById(divName).removeChild(child);
			  }
			  </script>
			  
			  <br/>
			  
			  <input class = "button" value = "Opslaan" type="submit" name="knop" />

			   <label><b>Goede Antwoord</b></label>
			   </form>
			   
			</body>
		</html>
			  
			
			  