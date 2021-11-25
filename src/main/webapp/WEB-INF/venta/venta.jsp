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
	  		<h5 class="card-header">Generar Venta</h5>
		  	<div class="card-body">
				<form:form class="form-group" method="POST" action="/venta/agregar" modelAttribute="venta">
					<div class="row">
						<form:label class="col-2 col-form-label" path="date">Fecha:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="date"/> 
						</div>
					</div>
					<div class="row">
						<form:label class="col-2 col-form-label" path="total">Total:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="total"/> 
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
		
	  	<h5 class="card-header">Listado de ventas</h5>
		<table class="table">
			<thead>
		    	<tr>
		      	<th scope="col">#</th>
		      	<th scope="col">Fecha</th>
		      	<th scope="col">Total</th>
		      	<th scope="col">Acciones</th>
		    	</tr>
		  	</thead>
		  	<tbody>
		  		<c:forEach items="${listaVentas}" var="venta">
		    		<tr>
			      		<th scope="row">${venta.getId()}</th>
			      		<td>${venta.getDate()}</td>
			      		<td>${venta.getTotal()}</td>
	      		  		<td class="d-flex justify-content-center">
		      		  		<form method="" action="/venta/eliminar">
		      		  			<input type="hidden" name="id" value="${venta.getId()}">
								<input class="btn btn-danger btn-sm" type="submit" value="Eliminar">
		      		  		</form>
		      		  		<form method="" action="/venta/editar">
		      		  			<input type="hidden" name="id" value="${venta.getId()}">
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