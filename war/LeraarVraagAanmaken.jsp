 <%@ include file="header.jsp" %>
	
	<h2>Vraag aanmaken</h2>
	
	 <form action="MultipleChoiceAanmakenServlet.do" method="post">
			  <label><b>Vraag</b></label>
			  <input type="text" size="25" name="vraagMultipleChoice" /><br />
			  <br />
			  
			  
			  <label><b>Mogelijke antwoorden</b></label>
			  <div id="dynamicInput">
         		 Antwoord 1<input type="text" name="Antwoord1"><br>
         		 Antwoord 2<input type="text" name="Antwoord2"><br>
         		 Antwoord 3<input type="text" name="Antwoord3"><br>
         		 Antwoord 4<input type="text" name="Antwoord4"><br>
   			  </div>
			  
			<!--  <input type="button" value="Voeg Antwoord toe" onClick="addTextField('dynamicInput');">	
			  
			  
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
			  </script> -->
			  
			  <br>
			  
			  
				<br>
			   <label><b>Goede Antwoord</b></label><br><br>
			   
				Antwoord  <select name="GoedeAntwoord">
								<option value = "1">1</option>
								<option value = "2">2</option>
								<option value = "3">3</option>
								<option value = "4">4</option>
								
								</select><br>
								
								<input class = "button" value = "Opslaan" type="submit" name="knop" />
			   </form>
			   
			</body>
		</html>
			  
			
			  