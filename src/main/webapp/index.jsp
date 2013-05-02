<%@ page import="ie.cit.aolivera.* "%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="repo" class="ie.cit.aolivera.ShoppingRepo" scope="application"/>
<jsp:useBean id="shopping" class="ie.cit.aolivera.Shopping" scope="page"></jsp:useBean>

<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<h1>Shopping list comparison</h1>
	<h2>Shopping list:</h2>
	
	<c:forEach items="${shoppings}" var="shopping" varStatus="row">
	${row.count} - ${shopping.shop } - ${shopping.product } - ${shopping.price } - ${shopping.bestPrice }" 
		<form method=post>
			<input name="_method" value="delete" type="hidden"	>
			<input name="shoppingId" value=${row.count } type="hidden"	>
			<input type ="submit" value="Delete"> 
		
		</form>
		<form method=post>
			<input name="_method" value="put" type="hidden"	>
			<input name="shoppingId" value=${row.count } type="hidden"	>
			<input type ="submit" value="Update"> 
		
		</form>
		
		
	</c:forEach>
	
	
	<form  method="post">
		Shop <input type="text" name="shop">
		Product <input type="text" name="product">
		Price<input name="price"><input type="submit" title="Create">
		
	</form>
	
	
<!--	<form  method="get">
		Shop <input type="text" name="shop">
		Product <input type="text" name="product">
		Price<input name="price"><input type="submit" title="Create">
		
	</form>
-->
</body>





</html>