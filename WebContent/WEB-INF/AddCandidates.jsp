<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Candidates</title>
</head>
<body>

	 
	   <c:if test="${user==null && admin==null}">
    
      <h3><a href="./Login">Login</a></h3>
      </c:if>
      
      
      <c:if test="${user != null || admin != null }">
      <h3><a href="./Logout">Logout</a></h3>
      </c:if>
      

    <table border="1" >
    
    <form action='AddCandidates' method='post'>
    
    <tr> 
    <th>Name:</th>
    <td> <input type='text' name='name' /></td>
    </tr>
    
    <tr>
    <th>Speciality: </th>
    <td><input type='text' name='speciality' /> </td>
    </tr>
    
    <tr> 
    <th>Presentation: </th>
    <td><input type='text' name='presentation' /> </td>
    </tr>
    
    <tr>
    <td colspan="2" rowspan="1"><input type='submit' name='add' value='Add' /> </td>
    </tr>

    </form>

</body>
</html>