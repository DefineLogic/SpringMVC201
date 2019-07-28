<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.button {background-color:#0000ff;}
#error{color:#DC143C;}
#exception{color:#DC143C;}
</style> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.2.js"></script>
<script type ="text/javascript">
	$(document).ready(function (){
		if(!isNaN($('#totalParam').val())||($('#totalParam').val())==0){
		document.getElementById('pbutton').disabled =true;
		}
		 var d = new Date();	
		  var n = d.getFullYear();
		  document.getElementById("assessYearParam").value = n;
		$( "#totalParam" ).focus(function() {
  //alert( "Handler for .focus() called." );
  	var assessyear=$("#assessYearParam").val();
  	var constructedyear=$("#constructedYearParam").val();
  	var name=$('#nameParam').val();
  	var email=$('#emailParam').val();
  	var address=$('#addressParam').val();
  	var area=$('#areaParam').val();
  	var zone=$('#zoneParam').val();
  	var status=$('#statusParam').val();
  	var desc=$('#descriptionParam').val();
  	if(!isNaN(name)||name.length==0)
		{
		alert("Invalid name entered.");
		document.activeElement.blur();
		return;
		}
  	if(!isNaN(address||address.length==0))
		{
		alert("Invalid address entered.");
		document.activeElement.blur();
		return;
		}
  	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if (reg.test(email) == false) 
     {
         alert('Invalid Email Address');
         document.activeElement.blur();
         return ;
     }
  	if(isNaN(constructedyear))
  		{
  		alert("Constructed year should be a number.");
  		document.activeElement.blur();
  		return;
  		}
  	if(constructedyear<=0)
  	{
  		alert("Constructed year cannot be a zero or less.");
  		document.activeElement.blur();
  		return;
  	}
  	if(constructedyear>assessyear)
  	{
  		alert("Constructed year should be lesser or equal to asssessyear.");
  		document.activeElement.blur();
  		return;
  	}
  	if(isNaN(area))
		{
		alert("Area should be a number.");
		document.activeElement.blur();
		return;
		}
	if(area<=0)
	{
		alert("Area cannot be a zero or less.");
		document.activeElement.blur();
		return;
	}
  	
  	//alert(zone);
  	$.ajax({
  			url:'calculate',
  			type:'POST',
  			data:{zone:zone,status:status,desc:desc,area:area,assessyear:assessyear,constructedyear:constructedyear},
  			success:function(data){
					document.getElementById("totalParam").value = data;
					document.activeElement.blur();
  					alert("Total Tax Payable="+data);
  					document.getElementById('pbutton').disabled =false;
  		     		

  			}
  	});
   
  	 
});	
			
			
	});
</script>
</head>
<body> 
<h1><spring:message code="label.headerMessage1"/></h1>  
 	   <div id="error"><form:errors path="taxRecord1.*"/></div>
 	   <div id="exception"><h3>${exceptionmessage}</h3></div>
 	   <br/>
       <form:form method="POST" action="save">    
        <table >  
        <tr>    
          <td><spring:message code="label.yearOfAssessment"/></td>    
          <td><form:input id ="assessYearParam" path="yearOfAssessment" readonly="true"/></td>  
         </tr>     
		<tr>    
          <td><spring:message code="label.ownerName"/></td>    
          <td><form:input id="nameParam" path="ownerName" /></td>  
         </tr> 
			<tr>    
          <td><spring:message code="label.email"/></td>    
          <td><form:input id="emailParam" path="email" /></td>  
         </tr>  
         <tr>    
          <td><spring:message code="label.address"/></td>    
          <td><form:textarea id="addressParam" path="propertyAddress" rows="4" cols="40"/></td>  
         </tr>  
         <tr>    
          <td><spring:message code="label.zone"/></td>    
          <td><select id ="zoneParam" name="zones.zoneType">
          <option value="1">Zone A</option>
          <option value="2">Zone B</option>
          <option value="3">Zone C</option>
			</select></td>  
         </tr> 
         <tr>    
          <td><spring:message code="label.desc"/></td>    
          <td><select id ="descriptionParam" name="descriptionOfProperty.description">
         		 <option value="1">RCC buildings</option>
       			 <option value="2">RCC buildings with cement or red-oxide flooring</option>
       			 <option value="3">Tiled/Sheet of all kinds</option>
			</select></td>  
         </tr>
          <tr>    
          <td><spring:message code="label.status"/></td>    
          <td><div>
        <select id ="statusParam" name="status">
        <option value="owner">OWNER</option>
        <option value="tenanted">TENANTED</option>
        </select>
    </div></td>  
         </tr>
         <tr>    
          <td><spring:message code="label.yearOfConstruction"/></td>    
          <td><form:input id ="constructedYearParam" path="buildingConstructedYear" /></td>  
         </tr>
         <tr>    
          <td><spring:message code="label.area"/></td>    
          <td><form:input id ="areaParam" path="builtUpArea" /></td>  
         </tr>
         <tr>    
          <td><spring:message code="label.totalTax"/></td>    
          <td><form:input id ="totalParam" path="totalTaxPayable" readonly="true"/><spring:message code="label.totalTaxInfo"/> 
         </td></tr>  
         <tr>    
          <td><input type="submit" class="button" name="action" value="Cancel"></td>    
          <td><input type="submit" id="pbutton" class="button" name="action" value="Pay Tax" /></td>    
         </tr>  
        </table>    
       </form:form>    
    </body>   
</body>
</html>