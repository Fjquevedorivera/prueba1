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
			<h5 class="card-header">Iniciar	sesión</h5>
		  	<div class="card-body">
				<form class="p-4" method="POST" action="${pageContext.request.contextPath}/usuario/login">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label" for="username">Email: </label>
						<div class="col-sm-9">
							<input class="form-control" type="text" name="username"/> 
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-3 col-form-label" for="password">Contraseña: </label>
						<div class="col-sm-9">
							<input class="form-control" type="password" name="password"/>
						</div>
					</div>
					<div class="d-flex justify-content-center pt-4">
						<input class="btn btn-danger m-2" type="reset" value="Limpiar">
						<input class="btn btn-primary m-2" type="submit" value="Ingresar"> 
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>