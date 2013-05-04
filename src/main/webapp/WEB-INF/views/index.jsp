<%@ page import="ie.cit.aolivera.* "%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<h1>Shopping list comparison (Spring NVC+DB)</h1>
	<a href="j_spring_security_logout">LOGOUT: <security:authentication property="principal.username"/></a>
	<h2>Shopping list:</h2>
	
	<c:forEach items="${shoppings}" var="shopping" varStatus="row">
	${shopping.id} - ${shopping.shop } - ${shopping.product } - ${shopping.price } - ${shopping.bestPrice }" 
		<form method=post>
			<input name="_method" value="delete" type="hidden"	>
			<input name="shoppingId" value=${shopping.id } type="hidden"	>
			<input type ="submit" value="Delete"> 
		</form>
		<form method=post>
			<input name="_method" value="put" type="hidden"	>
			<input name="shoppingId" value=${shopping.id } type="hidden"	>
			<input type ="submit" value="Update"> 
		</form>
	</c:forEach>
	
	<form  method="post">
		Shop <input type="text" name="shop">
		Product <input type="text" name="product">
		Price<input name="price"><input type="submit" title="Add Shopping">
	</form>
</body>
</html>