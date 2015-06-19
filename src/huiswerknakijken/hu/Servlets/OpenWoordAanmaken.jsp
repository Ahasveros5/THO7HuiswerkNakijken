 <%@ include file="header.jsp" %>
	
	<h2>Vraag aanmaken</h2>
	
	 <form action="OpenWoordAanmakenServlet.do" method="post" id="openwoord">
			  <label><b>Naam</b></label>
			  <input type="text" size="25" name="Naam" /><br />
			  <label><b>Vraag</b></label>
			  <textarea name="vraag" form="openwoord">	</textarea> <input type="text" size="10" name="antwoord"/> <input type="button" value="Voeg toe"/>
			  <br />
			  
			  	<script language="javascript" type="text/javascript">
					function addtext() {
						var newtext = document.myform.antwoord.value;
						if (document.myform.placement[1].checked) {
							document.myform.outputtext.value = "";
							}
						document.myform.vraag.value += "$" + newtext + "$";
					}
				</script>
			  
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
								
					<input class = "button" value = "Opslaan" type="submit" name="knop" />
			   </form>
			   
			</body>
		</html>
			  
			
			  