<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Candidates</title>
</head>
<body>

 
 	   <c:if test="${user==null && admin==null}">
    
      <h3><a href="./Login">Login</a></h3>
      </c:if>
      
      
      <c:if test="${user != null || admin != null }">
      <h3><a href="./Logout">Logout</a></h3>
      </c:if>
      
      
        <table border="1" >
        
        <form action='EditCandidates' method='post'>
       
       
        <tr> 
        <th>Id:</th>
        <td> ${ entry.getId()}</td>
        </tr>
        
        <tr>
        <th>Name:</th>
        <td><input type='text' name='name' value= ${ entry.getName()}  /></td>
        </tr>
        
        <tr>
        <th>Speciality:</th>
        <td> <input type='text' name='speciality' value=${ entry.getSpeciality()}  /></td>
        </tr>
        
        <tr>
        <th>Presentation:</th>
        <td> <input type='text' name='presentation' value=${ entry.getPresentation() } /></td>
        </tr>
        
        <input type='hidden' name='id' value=${ id} />
        
        <tr><td colspan="2" rowspan="1"><input type='submit' name='save' value='Save' /></td></tr>
        </form>
</body>
</html>