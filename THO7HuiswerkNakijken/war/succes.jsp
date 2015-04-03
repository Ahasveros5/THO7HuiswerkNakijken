<jsp:useBean id="formHandler" class="nl.hu.ThemaOpdracht4.Form" scope="request"/>
<html>
<body>
<center>
<table cellpadding=1 cellspacing=1 border="1" >
<th bgcolor="lightblue" colspan=2>
<font size=5>User Registration Successfull!</font>
</th>
<font size=4>
<tr bgcolor="lightblue">
<td valign=top> 
<b>Real Name</b> 
<br>
<jsp:getProperty name="formHandler" property="invoer_naam"/>
</td>
<td valign=top colspan=2>
<b>User Name</b>
<br>
<jsp:getProperty name="formHandler" property="userName"/>
</td>
</tr>
<tr bgcolor="lightblue">
<td valign=top>
<b>E-Mail</b> 
<br>
<jsp:getProperty name="formHandler" property="email"/>
<br></td>
<td valign=top>
<b>Country</b> 
<br>
<jsp:getProperty name="formHandler" property="country"/>
</td>
</tr>

</font>
</table>
</center>
</body>
</html>

