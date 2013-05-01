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
	<jsp:setProperty property="*" name="shopping"/>
	<c:if test= "${shopping.shop !=null}">
			<%
			repo.addShopping(shopping);
			%>
	</c:if>
	
	<c:forEach items="${repo.shoppings}" var="shopping" varStatus="row">
	${row.count} - ${shopping.shop } - ${shopping.product } - ${shopping.price } - ${shopping.bestPrice }" <br>
	
	</c:forEach>
	<form  method="get">
		<input name="shop"><input type="submit" title="Create">
	</form>

</body>





</html>