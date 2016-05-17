<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cure Compass - Home</title>

<link rel="stylesheet" href="./demo.css">
<link rel="stylesheet" href="./form-validation.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
</head>
<body>
<div class="main-content">
        
        <form class="form-validation" method="get" action="/CureCompassWeb/getHospitals">
            <div class="form-title-row">
                <h1>Cure Compass</h1>
            </div>

            <div class="form-row form-input-name-row">

                <label>
                    <span>Full name</span>
                    <input type="text" name="name">
                </label>
			</div>

            <div class="form-row form-input-email-row">

                <label>
                    <span>Email</span>
                    <input type="email" name="email" value="email@example.com">
                </label>
                
            </div>
            
            <div class="form-row">
                <label>
                    <span>Disorder/Symptom</span>
		            	<select id="selectedDisorder" name="selectedDisorder">
		                    <c:forEach items="${disorders}" var="disorderName"  >
		                    	<option value = "${disorderName}">${disorderName}</option>
		                    </c:forEach>
		                </select>
                  </label>

            </div>
            
            <div class="form-row">
            	 <label class="form-checkbox">
            		<span>Show results by:
            		</span>
            		<input type="checkbox" name="rating" value="rating" checked>Post-surgical complication probability<br>
					<center><input type="checkbox" name="cost" value="cost" checked>Cost of procedure<br>
            	</label>
            
            
            </div>
		   <div class="form-row">
                <button type="submit">Search hospitals</button>
            </div>
  
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
        </form>
    </div>


</body>
</html>