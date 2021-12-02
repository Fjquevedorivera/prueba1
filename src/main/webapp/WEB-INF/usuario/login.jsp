<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="../templates/header.jsp" %>
<title>Insert title here</title>
</head>
<body>
	
	<%@ include file="../templates/navigation.jsp" %>
	<div class="container">
		<div class="card">
			<h5 class="card-header">Ingresar Usuario</h5>
		  	<div class="card-body">
				<form:form class="p-4" method="POST" action="/usuario/iniciar" modelAttribute="usuario">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<div class="form-group row">
						<form:label class="col-sm-2 col-form-label" path="email">Email:</form:label>
						<div class="col-sm-10">
							<form:input class="form-control" type="text" path="email"/> 
						</div>
					</div>
					<div class="form-group row">
						<form:label class="col-sm-2 col-form-label" path="password">Password:</form:label>
						<div class="col-sm-10">
							<form:input class="form-control" type="password" path="password"/>
						</div>
					</div>

					<div class="d-flex justify-content-center pt-4">
						<input class="btn btn-danger m-2" type="reset" value="Limpiar">
						<input class="btn btn-primary m-2" type="submit" value="Ingresar"> 
					</div>
						
				</form:form>
				<c:if test="${error != null}">
					<div class="alert alert-danger mt-4 p-2" role="alert">
						${error}
					</div>
				</c:if>
			</div>
		</div>
	</div>
	
</body>
<%@ include file="../templates/footer.jsp" %>
</html>