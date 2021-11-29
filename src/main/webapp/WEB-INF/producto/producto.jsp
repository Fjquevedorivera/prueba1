<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../templates/navigation.jsp" %>
	<div class="container">

		<h5 class="card-header">Listado de productos</h5>
		<table class="table">
			<thead>
		    	<tr>
		      	<th scope="col">#</th>
		      	<th scope="col">Nombre</th>
		      	<th scope="col">Marca</th>
		      	<th scope="col">Precio</th>
		      	<th scope="col">Cantidad</th>
		      	<th scope="col">Acciones</th>
		    	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${listaProductos}" var="producto">
		    		<tr>
			      		<th scope="row">${producto.getId()}</th>
			      		<td>${producto.getName()}</td>
			      		<td>${producto.getBrand()}</td>
			      		<td>${producto.getPrice()}</td>
	      		  		<form:form method="POST" action="/productousuario" var="producto_usuario">
	      		  			<td>
	      		  				<input class="form-control form-control-sm text-end" id="quantity_product" name="quantity_product" value="" placeholder="Ingrese cantidad de productos">
		      		  			<input type="hidden" id="producto" name="producto" value="${producto.getId()}">
		      		  			<input type="hidden" id="usuario" name="usuario" value="${sessionScope.usuarioLogin.getId()}">
	      		  			</td>
		      		  		<td class="d-flex justify-content-center">	
								<input class="btn btn-success btn-sm" type="submit" value="Agregar al carro">
		      		  		</td>
	      		  		</form:form>
		    		</tr>
		  		</c:forEach>
		  	</tbody>
		</table>
		
	</div>
	
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>