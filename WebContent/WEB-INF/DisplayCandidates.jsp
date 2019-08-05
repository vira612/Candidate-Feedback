<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Candidates</title>
</head>
<body>

  
    
    <c:if test="${user==null && admin==null}">
    
      <h3><a href="./Login">Login</a></h3>
      </c:if>
      
      
      <c:if test="${user != null || admin != null }">
      <h3><a href="./Logout">Logout</a></h3>
      </c:if>
      
    
   	  
   	  
    <table border='1'>
    <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Specialities</th>
    <th>Prsentation</th>
    <th>Rating</th>
    
    <c:choose>
    <c:when test="${user == null && admin!=null}">
    <th>Operation</th>
    </c:when>    
    </c:choose>
    
    
    <c:forEach items="${entry}" var="c"> 
    </tr>  
    <tr>
    <td>${c.getId()}</td>
 	<td><a href='DisplayFeedback?id=${c.getId()}'>${c.getName()}</a></td> 
    <td>${c.getSpeciality()}</td>
    <td>${c.getPresentation()}</td>
    <td>${c.avg_rate}</td>
    
    
    <c:choose>
    <c:when test="${user == null && admin!=null}">
    <td><a href='EditCandidates?id=${c.getId()}'>Edit</a></td>
    </c:when>    
    </c:choose>
    
    
    </tr>
    </c:forEach>
    </table>
    
    <c:choose>
    <c:when test="${user == null && admin!=null}">
    <p><a href='./AddCandidates'>Add Candidate</a></p>
    </c:when>    
    </c:choose>
    
</body>
</html>