<%@ include file="header.jsp" %>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
 <h3>vakken overzicht</h3>
 
 <div id = "vakkenbox">
 
 </div>
 
 <div id ="PopUpWindow">
 <dialog id="window">  
    <h3>Vak Aanmaken</h3>  
    <p>Vak naam: </p>  
    <form action="VakAanmakenServlet.do" method="post">
    <input type = "text" name = "Vaknaam" size="40"/> <br>
  	<input class="button" type="submit" name="knop" value="Ok" />
  	<button id="exit">Cancel</button>
	</form>		
    
</dialog>  
<script>
(function() {  
    var dialog = document.getElementById('window');  
    document.getElementById('show').onclick = function() {  
        dialog.show();  
    };  
    document.getElementById('exit').onclick = function() {  
        dialog.close();  
    };  
})();
</script>

</div>
</body>
</html>