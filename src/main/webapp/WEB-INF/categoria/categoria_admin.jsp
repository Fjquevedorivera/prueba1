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
	  	<h5 class="card-header">Agregar categoría</h5>
	  	<div class="card-body">
			<form:form class="p-4" method="POST" action="/categoria/agregar" modelAttribute="categoria">
				<div class="form-group row">
					<form:label class="col-sm-2 col-form-label" path="name">Nombre:</form:label>
					<div class="col-sm-10">
						<form:input class="form-control" type="text" path="name"/> 
					</div>
				</div>
				<div class="form-group row">
					<form:label class="col-sm-2 col-form-label" path="description">Descripción:</form:label>
					<div class="col-sm-10">
						<form:input class="form-control" type="text" path="description"/> 
					</div>
				</div>
				<div class="form-group row">
					<form:label class="col-sm-2 col-form-label" path="productos">Productos:</form:label>
					<form:select class="form-select" path="productos" multiple="true" aria-label="Seleccionar productos">
						<option disabled>Seleccione Productos</option>
						<c:forEach items="${listaProductos}" var="producto">
							<form:option value="${producto.getId()}">
								${producto.getName()} ${producto.getBrand()}
							</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="d-flex justify-content-center pt-4">
					<input class="btn btn-danger m-2" type="reset" value="Limpiar">
					<input class="btn btn-primary m-2" type="submit" value="Insertar"> 
				</div>
			</form:form>
	  	</div>
	</div>

		
		<br>
		
		<h5 class="card-header">Listado de categorias</h5>
		<table class="table">
			<thead>
		    	<tr>
		      	<th scope="col">#</th>
		      	<th scope="col">Nombre</th>
		      	<th scope="col">Descripción</th>
		      	<th scope="col-2">Acciones</th>
		    	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${listaCategorias}" var="categoria">
		    		<tr>
			      		<th scope="row">${categoria.getId()}</th>
			      		<td>${categoria.getName()}</td>
			      		<td>${categoria.getDescription()}</td>
	      		  		<td class="d-flex justify-content-center">
	      		  		<form method="" action="/categoria/eliminar">
	      		  			<input type="hidden" name="id" value="${categoria.getId()}">
							<input class="btn btn-danger btn-sm" type="submit" value="Eliminar">
	      		  		</form>
	      		  		<form method="" action="/categoria/editar">
	      		  			<input type="hidden" name="id" value="${categoria.getId()}">
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