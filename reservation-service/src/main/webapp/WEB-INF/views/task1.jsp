<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 카테고리 jsp.ver </h1>

<form id='myform' method="post" action="./task1/insert">
	카테고리 명 : <input type="text" name="name">
	<input type="submit" value="추가">
</form>

<table>
<c:forEach var="category" items="${categoryList}">
		<tr>
		   
		       <th>id</th> <td>${category.id}</td>
			   <th>name</th> <td>${category.name}</td>
			   <th>
				   <form method="post" action="./task1/update">
				   <input type="hidden" name="id" value= '${category.id}' />
				   	<input type="text" name="name" value= '${category.name}' />
				   	<input type="submit" value="수정">
				   </form> 
			   </th>
		   
		  	   <th>
		  	   	<form method="post" action="./task1/delete">
		  	   		<input type="hidden" name="id" value= '${category.id}' />
		  	 		<input type="submit" value="삭제">
		  	 	</form>	
		  	   </th>   
		</tr>
		
</c:forEach>
</table>


</body>
</html>