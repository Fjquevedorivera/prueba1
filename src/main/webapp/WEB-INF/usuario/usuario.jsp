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
			<h5 class="card-header">Crear Usuario</h5>
		  	<div class="card-body">
				<form:form class="form-group" method="POST" action="/usuario/agregar" modelAttribute="usuario">
					<div class="row">
						<form:label class="col-2 col-form-label" path="name">Nombre:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="name"/> 
						</div>
					</div>
					<div class="row">
						<form:label class="col-2 col-form-label" path="last_name">Apellido:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="last_name"/> 
						</div>
					</div>
					<div class="row">
						<form:label class="col-2 col-form-label" path="email">Email:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="email"/> 
						</div>
					</div>
					<div class="row">
						<form:label class="col-2 col-form-label" path="password">Password:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="password"/>
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
		
		<h5 class="card-header">Listado de usuarios</h5>
		<table class="table">
			<thead>
		    	<tr>
		      	<th scope="col">#</th>
		      	<th scope="col">Nombre</th>
		      	<th scope="col">Apellido</th>
		      	<th scope="col">Email</th>
		      	<th scope="col-2">Acciones</th>
		    	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${listaUsuarios}" var="usuario">
		    		<tr>
			      		<th scope="row">${usuario.getId()}</th>
			      		<td>${usuario.getName()}</td>
			      		<td>${usuario.getLast_name()}</td>
			      		<td>${usuario.getEmail()}</td>
	      		  		<td class="d-flex justify-content-center">
	      		  		<form method="" action="/usuario/eliminar">
	      		  			<input type="hidden" name="id" value="${usuario.getId()}">
							<input class="btn btn-danger btn-sm" type="submit" value="Eliminar">
	      		  		</form>
	      		  		<form method="" action="/usuario/editar">
	      		  			<input type="hidden" name="id" value="${usuario.getId()}">
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