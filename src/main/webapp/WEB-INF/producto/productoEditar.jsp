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
		  	<h5 class="card-header">Editar producto</h5>
		  	<div class="card-body">
				<form:form class="form-group" method="POST" action="/producto/actualizar" modelAttribute="producto">
					<form:input type="hidden" path="id"/> 
					<input type="hidden" name="_method" value="put">
					<div class="row">
						<form:label class="col-2 col-form-label" path="name">Nombre:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="name"/> 
						</div>
		
						<form:label class="col-2 col-form-label" path="brand">Marca:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="brand"/> 
						</div>
		
						<form:label class="col-2 col-form-label" path="price">Precio:</form:label>
						<div class="col-10">
							<form:input class="form-control" type="text" path="price"/>
						</div>
						
						<div class="form-group row">
							<form:label class="col-sm-2 col-form-label" path="categorias">Productos:</form:label>
							<form:select class="form-select" path="categorias" multiple="true" aria-label="Seleccionar categorias">
								<option disabled>Seleccione Categorias</option>
								<c:forEach items="${listaCategorias}" var="categoria">
									<c:choose>
									<c:when test="${producto.getCategorias().contains(categoria)}">
										<form:option value="${categoria.getId()}" selected="true">
												${categoria.getName()}
										</form:option>
									</c:when>
									<c:when test="${!producto.getCategorias().contains(producto)}">
										<form:option value="${categoria.getId()}">
												${categoria.getName()} 
										</form:option>
									</c:when>
									</c:choose>
								</c:forEach>
							</form:select>
						</div>
			
						<div class="d-flex justify-content-center">
							<input class="btn btn-danger m-2" type="reset" value="Limpiar">
							<input class="btn btn-primary m-2" type="submit" value="Actualizar"> 
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>