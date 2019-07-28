<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!DOCTYPE html>
<html>
    <head>
    <style>
table, th,td{
  border-style: solid;
  border-width: medium;
}
</style>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
     url="jdbc:mysql://localhost:3306/db"  
     user="root"  password="root"/>
        <title></title>
     <sql:query dataSource="${db}" var="rs">  
SELECT distinct zonetype from zones ;
</sql:query> 
 
<script>
$(document).ready(function (){
	
});
</script>
    </head>
    
    <body>
    <h3><spring:message code="label.headerMessage2"/></h3>
    <table>
    <tr><th><spring:message code="label.zoneName"/></th><th><h4><spring:message code="label.propertyType"/></h4></th><th><h4><spring:message code="label.amount"/></h4></th></tr>
    <c:forEach var="row" items="${rs.rows}">
	<tr>
    	<td rowspan="2">${row.zonetype}</td>
		<td>Owner</td>
		<td>
			<sql:query dataSource="${db}" var="rs1">  
			select totalTaxPayable from taxRecord inner join zones where taxrecord.zones_z_id=zones.z_id and zones.zoneType="${row.zonetype}" and taxrecord.status='owner';
			</sql:query>
			<c:set var="Total" value="${0}" />
			<c:forEach var="row1" items="${rs1.rows}">
			<c:set var="Total" value="${Total + row1.totalTaxPayable}" />
			</c:forEach>
			Rs. ${Total}
		</td>
	</tr> 
	<tr>
		<td>Tenanted</td>
		<td>
			<sql:query dataSource="${db}" var="rs2">  
			select totalTaxPayable from taxRecord inner join zones where taxrecord.zones_z_id=zones.z_id and zones.zoneType="${row.zonetype}" and taxrecord.status='tenanted';
			</sql:query>
			<c:set var="Total" value="${0}" />
			<c:forEach var="row2" items="${rs2.rows}">
			<c:set var="Total" value="${Total + row2.totalTaxPayable}" />
			</c:forEach>
			Rs. ${Total}
		</td>
	</tr>
	</c:forEach>
	</table> 
    </body>
</html>