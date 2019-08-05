<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Feedback</title>
</head>
<body>
       <c:if test="${user==null && admin==null}">
    
      <h3><a href="./Login">Login</a></h3>
      </c:if>
      
      
      <c:if test="${user != null || admin != null }">
      <h3><a href="./Logout">Logout</a></h3>
      </c:if>
      
     
     <c:forEach items="${feedback}" var="a"> 
      <table border='1'>
      
      <form action='EditFeedback?id=${id}' method='post'>
    
    <tr> 
    <th>Rating</th>
    <td>1 <input type='radio' name='rate' value='1' required/><input type='radio' name='rate' value='2' required /><input type='radio' name='rate' value='3' required/><input type='radio' name='rate' value='4' required/><input type='radio' name='rate' value='5'required/>5</td>
    </tr>
    
    <tr>
    <th>Name </th>
    <td><input type='text' name='name_f' value="${a.getName_f()}" required/> </td>
    </tr>
    
    <tr> 
    <th>Comments </th>
    <td><input type="text" name='comment' value="${a.getComment()}" required/> </td>
    </tr>
    
    <tr>
    <td colspan="2" rowspan="1"><input type='submit' name='edit' value='Edit' /> </td>
    </tr>
    

    </form>
    </table>
    </c:forEach>
</body>
</html>