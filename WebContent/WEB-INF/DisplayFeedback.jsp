<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Feedbacks</title>
</head>
<body>

 	 <c:if test="${user==null && admin==null}">
    
      <h3><a href="./Login">Login</a></h3>
      </c:if>
      
      
      <c:if test="${user != null || admin != null }">
      <h3><a href="./Logout">Logout</a></h3>
      </c:if>
      
      
	<h3><a href="./DisplayCandidates">Back to Candidates</a></h3>

 	<table border='1'>
    <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Specialities</th>
    <th>Prsentation</th>
    <th>Rating</th>
    

     
    </tr>  
    <tr>
    <td>${entry.getId()}</td>
    <td>${entry.getName()}</td>
    <td>${entry.getSpeciality()}</td>
    <td>${entry.getPresentation()}</td>
    <td>${entry.avg_rate}</td>
    
    </tr>
    
    </table>
    
    <h3>Comments:</h3>
    
    <table border='1'>
    <c:forEach items="${feedback}" var="a"> 
    
    <tr>
    <td>Rating ${a.getRate()}  </td>
    <td align="right">Posted by ${a.getName_f()} on ${a.getDate()} </td>
    </tr>
    <tr>
    <td colspan="2" rowspan="1">${a.getComment()} </td>
    </tr>
    
    	<c:if test="${flag == true }">
       <c:if test="${user == a.getName_f() || admin == a.getName_f()}">
       
      <tr><td colspan="2" rowspan="1"><a href="./EditFeedback?id=${entry.getId()}">EditFeedback</a></td></tr>
      </c:if>
   
    </c:if>
    
    
    </c:forEach>
    </table>
    
    
   
      
      
    
    <c:if test="${user!=null || admin!=null}">
    
   
    <c:if test="${flag == false }">
    
    <h3>Please give you feedback:</h3>
    
    <table border='1'>
    <form action='DisplayFeedback?id=${entry.getId()}' method='post'>
    
    <tr> 
    <th>Rating</th>
    <td>1 <input type='radio' name='rate' value='1' required/><input type='radio' name='rate' value='2' required /><input type='radio' name='rate' value='3' required/><input type='radio' name='rate' value='4' required/><input type='radio' name='rate' value='5'required/>5</td>
    </tr>
    
    <tr>
    <th>Name </th>
    <td><input type='text' name='name_f' value="${user}" required/> </td>
    </tr>
    
    <tr> 
    <th>Comments </th>
    <td><input type="text" name='comment' required/> </td>
    </tr>
    
    <tr>
    <td colspan="2" rowspan="1"><input type='submit' name='submit' value='submit' /> </td>
    </tr>

    </form>
    </table>
    </c:if>
    </c:if>
    
    
    
</body>
</html>