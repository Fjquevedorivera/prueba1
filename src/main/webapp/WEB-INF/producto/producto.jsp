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
	<div class="card">
	  	<h5 class="card-header">Agregar producto</h5>
	  	<div class="card-body">
			<form:form class="form-group" method="POST" action="/producto/agregar" modelAttribute="producto">
				<div class="row">
					<form:label class="col-2 col-form-label" path="barcode">Código:</form:label>
					<div class="col-10">
						<form:input class="form-control" type="text" path="barcode"/> 
					</div>
				</div>
				<div class="row">
					<form:label class="col-2 col-form-label" path="name">Nombre:</form:label>
					<div class="col-10">
						<form:input class="form-control" type="text" path="name"/> 
					</div>
				</div>
				<div class="row">
					<form:label class="col-2 col-form-label" path="brand">Marca:</form:label>
					<div class="col-10">
						<form:input class="form-control" type="text" path="brand"/> 
					</div>
				</div>
				<div class="row">
					<form:label class="col-2 col-form-label" path="price">Precio:</form:label>
					<div class="col-10">
						<form:input class="form-control" type="text" path="price"/>
					</div>
				</div>
				<div class="d-flex justify-content-center">
					<input class="btn btn-danger m-2" type="reset" value="Limpiar">
					<input class="btn btn-primary m-2" type="submit" value="Insertar"> 
				</div>
			</form:form>
	  	</div>
	</div>

		
		<br>
		
		<h5 class="card-header">Listado de productos</h5>
		<table class="table">
			<thead>
		    	<tr>
		      	<th scope="col">#</th>
		      	<th scope="col">Código</th>
		      	<th scope="col">Nombre</th>
		      	<th scope="col">Marca</th>
		      	<th scope="col">Precio</th>
		      	<th scope="col-2">Acciones</th>
		    	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${listaProductos}" var="producto">
		    		<tr>
			      		<th scope="row">${producto.getId()}</th>
			      		<td>${producto.getBarcode()}</td>
			      		<td>${producto.getName()}</td>
			      		<td>${producto.getBrand()}</td>
			      		<td>${producto.getPrice()}</td>
	      		  		<td class="d-flex justify-content-center">
	      		  		<form method="" action="/producto/eliminar">
	      		  			<input type="hidden" name="id" value="${producto.getId()}">
							<input class="btn btn-danger btn-sm" type="submit" value="Eliminar">
	      		  		</form>
	      		  		<form method="" action="/producto/editar">
	      		  			<input type="hidden" name="id" value="${producto.getId()}">
							<input class="btn btn-primary btn-sm" type="submit" value="Editar">
						</form>
				  		</td>
		    		</tr>
		  		</c:forEach>
		  	</tbody>
		</table>
		
	</div>
	
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>