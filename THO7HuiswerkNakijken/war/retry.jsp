<jsp:useBean id="formHandler" class="nl.hu.ThemaOpdracht4.Form" scope="request"/>
<html> 
<body>
<form action="Check.jsp" method=post>
<center>
<table cellpadding=4 cellspacing=2 border=0>
<th bgcolor="lightblue" colspan=2>
<font size=5>User Registration</font>
<br>
<font size=2 color="red"><sup>*</sup> Required Fields </font>
</th>

<tr bgcolor="lightblue">
<td valign=top> 
<B>Voornaam<sup>*</sup></B> 
<br>
<input type="text" name="invoer_naam" value='<%=formHandler.getInvoer_naam()%>' size=15 maxlength=20>
<br><font size=2 
color=red><%=formHandler.getErrorMsg("voornaam")%></font>
</td>

<td  valign=top>
<B>Achternaam<sup>*</sup></B>
<br>
<input type="text" name="invoer_achternaam" 
value='<%=formHandler.getInvoer_achternaam()%>' size=15 maxlength=20>
<br><font size=2 
color=red><%=formHandler.getErrorMsg("achternaam")%></font>
</td>
</tr>

<tr bgcolor="lightblue">
<td valign=top>
<B>E-Mail<sup>*</sup></B> 
<br>
<input type="text" name="email1" value='<%=formHandler.getEmail1()%>' 
size=25  maxlength=125>
<br><font size=2 color=red><%=formHandler.getErrorMsg("email1")%></font>
</td>

<td  valign=top>
<B>Confirm E-Mail<sup>*</sup></B> 
<br>
<input type="text" name="zip" value='<%=formHandler.getEmailb()%>' size=25  
maxlength=125>
<br><font size=2 color=red><%=formHandler.getErrorMsg("email2")%></font>
</td>
</tr>

<tr bgcolor="lightblue">
<td valign=top>
<B>Password<sup>*</sup></B> 
<br>
<input type="password" name="password1" size=10 
value='<%=formHandler.getPassword1()%>'  maxlength=10>
<br><font size=2 
color=red><%=formHandler.getErrorMsg("password1")%></font>
</td>

<td  valign=top>
<B>Confirm Password<sup>*</sup></B>
<br>
<input type="password" name="password2" size=10 
value='<%=formHandler.getPassword2()%>'  maxlength=10>
<br><font size=2 
color=red><%=formHandler.getErrorMsg("password2")%></font>
</td>
<br>
</tr>

<tr bgcolor="lightblue">
<td valign=top colspan=2> 
<B>Country<sup>*</sup></B>
<br>
<input type="text" name="country" size=10 
value='<%=formHandler.getCountry()%>'  maxlength=10>
<br><font size=2 
color=red><%=formHandler.getErrorMsg("country")%></font>
</td>
</tr>

<tr bgcolor="lightblue">
<td colspan=2 align=center>
<input type="submit" value="Submit"> <input type="reset"  
value="Reset">
</td>
</tr>
</table>
</center>
</form>
</body>
</html>


