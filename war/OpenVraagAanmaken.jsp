 <%@ include file="header.jsp" %>
	
	<h2>Vraag aanmaken</h2>
	
	 <form action="OpenVraagAanmakenServlet.do" method="post">
			  <label><b>Naam</b></label>
			  <input type="text" size="25" name="Naam" /><br />
			  <label><b>Vraag</b></label>
			  <input type="text" size="25" name="OpenVraag" /><br />
			  <br />
			  
			  
			  <label><b>Keywords:</b></label>
			  <br/> Gebruik via: Keyword1,Keyword2,Keyword3 etc...
			  <input type="text" size="50" name="Keywords"/>
			  <br/>
			  <input type="submit" name="Maak aan" />
			  <br>	
			  
			  </form>
<%@ include file="footer.jsp" %>		  