<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Cure Compass - Hospitals</title>

<link rel="stylesheet" href="./demo.css">
<link rel="stylesheet" href="./form-validation.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
</head>
<style>
#hor-minimalist-b
{
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	background: #fff;
	margin: 45px;
	width: 480px;
	border-collapse: collapse;
	text-align: left;
	color: #039;
}
#hor-minimalist-b th
{
	font-size: 14px;
	font-weight: normal;
	color: #039;
	padding: 10px 8px;
	border-bottom: 2px solid #6678b1;
}
#hor-minimalist-b td
{
	border-bottom: 1px solid #ccc;
	color: #669;
	padding: 6px 8px;
}
#hor-minimalist-b tbody tr:hover td
{
	color: #009;
}
</style>

<body>

<div class="main-content">
	<form class="form-validation" method="get" action="/CureCompassWeb/HospitalSearch">
	<div class="form-title-row">
         <h1>Cure Compass</h1>
    </div>
	
	<div class="form-row form-input-name-row">
	
	<p id="hor-minimalist-b">${disorder }</p>
	
	<table id="hor-minimalist-b">
		<tr>
			<th>Hospital</th>
			<c:if test="${not empty cost}">
				<th>Cost USD</th>
			</c:if>
			<c:if test="${not empty rating}">
				<th>Post-surgical complications rating</th>
			</c:if>
		</tr>
		
		<c:forEach items="${result}" var="details"  >
			<tr>
				<td>${details.name }</td>
				<c:if test="${not empty cost}">
					<td>${details.cost }</td>
				</c:if>
				<c:if test="${not empty rating}">
					<td>${details.rating }</td>
				</c:if>
		    </tr>
		</c:forEach>
	</table>
	</div>
	<div class="form-row">
          <button type="submit">Reset form</button>
     </div>
         <p style="font-size:8px"><i>*Based on data provided by https://data.cms.gov/Medicare/Inpatient-Prospective-Payment-System-IPPS-Provider/</i></p>
        
	</div>
 </form>
</div>

</body>
</html>