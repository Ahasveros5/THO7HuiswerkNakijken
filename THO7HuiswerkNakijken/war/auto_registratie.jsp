<!DOCTYPE html>
<html>
	<head>
		<title>Auto registratie</title>
		<link rel="stylesheet" type="text/css" href="http://thestrike.nl/atd/css/atd_final.css" />
        <link href='http://fonts.googleapis.com/css?family=Handlee' rel='stylesheet' type='text/css'>
	</head>
	
	<body>
	
		<div id="header">
   	 	<div id="header_content">
    			
                
                
                 <div id="logo_atd_small_holder">
<div id="logo_atd_small_shake_holder" class="shake" a="" href="/atd">
                	<a href="/atd" class="shake" id="logo_atd_small"></a>
               
</div>
                </div>
                
                
     			<div id="navigation_header">
                <ul>
<li><a href="">Over Ons</a></li>
<li><a href="">Aanmelden</a></li>
<li><a href="">Registreren</a></li>
<li><a href="">Contact</a></li>

</ul>
     			</div>
     	</div> 
   </div> 
<% Object msgs = request.getAttribute("msgs"); 
	if (msgs != null) {
		out.println("<div id=\"messagebox\">");
		out.println(msgs); 
		out.println("</div>");
	} 
%> 
   	 	<div class="user_register_container">
     <h2>Registreer uw auto</h2>
     <div id="myform">	
     <form action="AutoRegistratieServlet.do" method="post">
		  <fieldset id="Model">
          <div id="darkbanner">
			  <legend><h3>Model</h3></legend></div>
              <div id="darkbannerwrap">
						</div>
			  <label><b>Merk:</b></label>
			  <input type="text" size="25" name="invoer_merk" /><br />
			  <label><b>Type:</b></label>
			  <input type="text" size="25" name="invoer_auto_type" /><br />
              <label><b>Kleur:</b></label>
			  <input type="text" size="25" name="invoer_kleur" /><br />
              
              <label><b>Aantal deuren:</b></label>
<select name="aantal_deuren">
	<option value="1">Deuren</option>
	<option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
    <option value="5">5</option>
    </select><br />
     <label><b>Carrosserie:</b></label>
<select name="carrosserie_type">
	<option value="default">Carrosserie</option>
	<option value="Hatchback">Hatchback</option>
    <option value="Stationwagen">Stationwagen</option>
    <option value="MPV">MPV</option>
    <option value="Roadster">Roadster</option>
     <option value="Cabrio">Cabrio</option>
     <option value="Overig">Overig</option>
    </select><br />
              
    

</fieldset><br />
			  <fieldset id="Specificatie">
			  <div id="darkbanner">
			  <legend><h3>Specificatie</h3></legend></div>
              <div id="darkbannerwrap">
						</div>
			  <label><b>Kenteken:</b></label>
			  <input type="text" size="25" name="invoer_kenteken" /><br />
              <label><b>Serienummer:</b></label>
			  <input type="text" size="25" name="invoer_serienummer" /><br />
              <label><b>Transmissie:</b></label>
			  <input type="radio" name="invoer_transmissie" value="Handgeschakeld" />Handgeschakeld
              <input type="radio" name="invoer_transmissie" value="Automaat" />Automaat<br />
              <label><b>Brandstof:</b></label>
			  <input type="radio" name="invoer_brandstof" value="Benzine" />Benzine
              <input type="radio" name="invoer_brandstof" value="Diesel" />Diesel
              <input type="radio" name="invoer_brandstof" value="LPG" />LPG
              <br />
              <label><b>Bouwjaar:</b></label>
    

<select name="Bouwjaar_Year">
	<option value="default">Jaar</option>
    <option value="2013">2013</option>
    <option value="2012">2012</option>
	<option value="2011">2011</option>
    <option value="2010">2010</option>
    <option value="2009">2009</option>
    <option value="2008">2008</option>
    <option value="2007">2007</option>
    <option value="2006">2006</option>
    <option value="2005">2005</option>
	<option value="2004">2004</option>
	<option value="2004">2004</option>
	<option value="2003">2003</option>
	<option value="2002">2002</option>
	<option value="2001">2001</option>
	<option value="2000">2000</option>
	<option value="1999">1999</option>
	<option value="1998">1998</option>
	<option value="1997">1997</option>
	<option value="1996">1996</option>
	<option value="1995">1995</option>
	<option value="1994">1994</option>
	<option value="1993">1993</option>
	<option value="1992">1992</option>
	<option value="1991">1991</option>
	<option value="1990">1990</option>
	<option value="1989">1989</option>
	<option value="1988">1988</option>
	<option value="1987">1987</option>
	<option value="1986">1986</option>
	<option value="1985">1985</option>
	<option value="1984">1984</option>
	<option value="1983">1983</option>
	<option value="1982">1982</option>
	<option value="1981">1981</option>
	<option value="1980">1980</option>
	<option value="1979">1979</option>
	<option value="1978">1978</option>
	<option value="1977">1977</option>
	<option value="1976">1976</option>
	<option value="1975">1975</option>
	<option value="1974">1974</option>
	<option value="1973">1973</option>
	<option value="1972">1972</option>
	<option value="1971">1971</option>
	<option value="1970">1970</option>
	<option value="1969">1969</option>
	<option value="1968">1968</option>
	<option value="1967">1967</option>
	<option value="1966">1966</option>
	<option value="1965">1965</option>
	<option value="1964">1964</option>
	<option value="1963">1963</option>
	<option value="1962">1962</option>
	<option value="1961">1961</option>
	<option value="1960">1960</option>
	
</select> <br/>
<label><b>Aanschafdatum:</b></label>
    <select name="Aanschaf_Month">
	<option value="default">Maand</option>
	<option value="January">Januari</option>
	<option value="Febuary">Febuari</option>
	<option value="March">Maart</option>
	<option value="April">April</option>
	<option value="May">Mei</option>
	<option value="June">Juni</option>
	<option value="July">Juli</option>
	<option value="August">Augustus</option>
	<option value="September">September</option>
	<option value="October">Oktober</option>
	<option value="November">November</option>
	<option value="December">December</option>
</select>

<select name="Aanschaf_Day">
	<option value="default">Dag</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	<option value="13">13</option>
	<option value="14">14</option>
	<option value="15">15</option>
	<option value="16">16</option>
	<option value="17">17</option>
	<option value="18">18</option>
	<option value="19">19</option>
	<option value="20">20</option>
	<option value="21">21</option>
	<option value="22">22</option>
	<option value="23">23</option>
	<option value="24">24</option>
	<option value="25">25</option>
	<option value="26">26</option>
	<option value="27">27</option>
	<option value="28">28</option>
	<option value="29">29</option>
	<option value="30">30</option>
	<option value="31">31</option>
</select>

<select name="Aanschaf_Year">
	<option value="default">Jaar</option>
    <option value="2013">2013</option>
    <option value="2012">2012</option>
	<option value="2011">2011</option>
    <option value="2010">2010</option>
    <option value="2009">2009</option>
    <option value="2008">2008</option>
    <option value="2007">2007</option>
    <option value="2006">2006</option>
    <option value="2005">2005</option>
	<option value="2004">2004</option>
	<option value="2004">2004</option>
	<option value="2003">2003</option>
	<option value="2002">2002</option>
	<option value="2001">2001</option>
	<option value="2000">2000</option>
	<option value="1999">1999</option>
	<option value="1998">1998</option>
	<option value="1997">1997</option>
	<option value="1996">1996</option>
	<option value="1995">1995</option>
	<option value="1994">1994</option>
	<option value="1993">1993</option>
	<option value="1992">1992</option>
	<option value="1991">1991</option>
	<option value="1990">1990</option>
	<option value="1989">1989</option>
	<option value="1988">1988</option>
	<option value="1987">1987</option>
	<option value="1986">1986</option>
	<option value="1985">1985</option>
	<option value="1984">1984</option>
	<option value="1983">1983</option>
	<option value="1982">1982</option>
	<option value="1981">1981</option>
	<option value="1980">1980</option>
	<option value="1979">1979</option>
	<option value="1978">1978</option>
	<option value="1977">1977</option>
	<option value="1976">1976</option>
	<option value="1975">1975</option>
	<option value="1974">1974</option>
	<option value="1973">1973</option>
	<option value="1972">1972</option>
	<option value="1971">1971</option>
	<option value="1970">1970</option>
	<option value="1969">1969</option>
	<option value="1968">1968</option>
	<option value="1967">1967</option>
	<option value="1966">1966</option>
	<option value="1965">1965</option>
	<option value="1964">1964</option>
	<option value="1963">1963</option>
	<option value="1962">1962</option>
	<option value="1961">1961</option>
	<option value="1960">1960</option>
</select>

              
              
              
              
<label><b>APK tot:</b></label>
<select name="APK_tot_Month">
	<option value="default">Maand</option>
	<option value="January">Januari</option>
	<option value="Febuary">Febuari</option>
	<option value="March">Maart</option>
	<option value="April">April</option>
	<option value="May">Mei</option>
	<option value="June">Juni</option>
	<option value="July">Juli</option>
	<option value="August">Augustus</option>
	<option value="September">September</option>
	<option value="October">Oktober</option>
	<option value="November">November</option>
	<option value="December">December</option>
</select>

<select name="APK_tot_Day">
	<option value="default">Dag</option>
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
	<option value="10">10</option>
	<option value="11">11</option>
	<option value="12">12</option>
	<option value="13">13</option>
	<option value="14">14</option>
	<option value="15">15</option>
	<option value="16">16</option>
	<option value="17">17</option>
	<option value="18">18</option>
	<option value="19">19</option>
	<option value="20">20</option>
	<option value="21">21</option>
	<option value="22">22</option>
	<option value="23">23</option>
	<option value="24">24</option>
	<option value="25">25</option>
	<option value="26">26</option>
	<option value="27">27</option>
	<option value="28">28</option>
	<option value="29">29</option>
	<option value="30">30</option>
	<option value="31">31</option>
</select>

<select name="APK_tot_Year">
	<option value="default">Jaar</option>
    <option value="2004">2015</option>
    <option value="2014">2014</option>    
    <option value="2013">2013</option>
    
</select>
			  </fieldset> <br/>
			  <fieldset id="Bijzonderheden">
			 <div id="darkbanner">
			  <legend><h3>Bijzonderheden</h3></legend></div>
              <div id="darkbannerwrap">
						</div>
			  <label><b>Opmerkingen:</b></label>
			  <textarea name="invoer_bijzonderheden" cols="20" rows="6" class="auto_registration" /></textarea> <br />
			  
			  </fieldset>
			  
			  
			  <input class="button" type="submit" name="knop" value="Registreer auto" />
		   </form>		
           
     </div>
    </div>
       
        
        
        
	</body>
</html>