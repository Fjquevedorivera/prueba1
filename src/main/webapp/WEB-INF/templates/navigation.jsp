<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="${sessionScope.usuarioLogin == null ? "/" : "/usuario/logout"}">Tienda Virtual</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="menu">
      <div class="navbar-nav">
	    <c:if test="${sessionScope.usuarioLogin == null}">
	        <a class="nav-link" aria-current="page" href="/usuario">Usuario</a>
	        <a class="nav-link" href="/producto/producto_admin">Producto</a>
		    <a class="nav-link" href="/venta">Venta</a>
		    <a class="nav-link" href="/categoria/categoria_admin">Categoría</a>
		</c:if>
		<c:if test="${sessionScope.usuarioLogin != null}">
	        <a class="nav-link" href="/producto">Productos</a>
	        <!-- <a class="nav-link" href="/producto">Categorias</a> -->
        </c:if>
      </div>
      <div class="navbar-nav">
      	<c:if test="${sessionScope.usuarioLogin != null}">
      		<!-- <a type="button" class="nav-link" data-bs-toggle="modal" data-bs-target="#exampleModal" href="/usuario/login">Carrito</a> -->
	        <li class="nav-item dropdown">
	          	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            	${sessionScope.usuarioLogin.getName()}
	          	</a>
	          	<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	            	<li><a type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#exampleModal" href="/usuario/login">Carro de compra</a></li>
	            	<li><hr class="dropdown-divider"></li>
	            	<li><a class="dropdown-item" href="/usuario/logout">Cerrar sesión</a></li>
	          	</ul>
	       	</li>
      	</c:if>
      	<c:if test="${sessionScope.usuarioLogin == null}">
		   	<a type="button" class="btn-nav btn btn-primary btn-sm" href="/usuario/login">Ingreso</a>
		   	<a type="button" class="btn-nav btn btn-success btn-sm" href="/usuario/registro">Registro</a>
	   	</c:if>
	  </div>
    </div>
  </div>
</nav>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Carro de compra</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      		<div class="table-responsive">
		      	<table class="table table-borderless table-sm">
					<thead>
				    	<tr>
				      	<th scope="col">#</th>
				      	<th scope="col">Nombre</th>
				      	<th scope="col">Descripción</th>
				      	<th scope="col">Cantidad</th>
				      	<th scope="col">Subtotal</th>
				      	<th scope="col-2">Acciones</th>
				    	</tr>
				  	</thead>
				  	<tbody>
				  		<c:forEach items="${sessionScope.carro}" var="producto_usuario" varStatus="loop">
				    		<tr>
					      		<th scope="row">${loop.index + 1}</th>
					      		<td>${producto_usuario.getProducto().getName()}</td>
					      		<td>${producto_usuario.getProducto().getBrand()}</td>
					      		<td>${producto_usuario.getQuantity_product()}</td>
					      		<td>${producto_usuario.getTotal_product()}</td>
			      		  		<td class="d-flex justify-content-center">
				      		  		<form:form method="post" action="/productousuario/eliminar">
				      		  			<input type="hidden" id="producto_id" name="producto_id" value="${producto_usuario.getProducto().getId()}">
										<input class="btn btn-danger btn-sm" type="submit" value="Eliminar">
				      		  		</form:form>
						  		</td>
				    		</tr>
				  		</c:forEach>
				  	</tbody>
				</table>
			</div>
      </div>
        <div class="modal-footer">
        	<div>
				<p class="h4">Total: ${sessionScope.precioTotal}</p>
      		</div>
     	</div>
      <div class="modal-footer">
		<div class="btn-group">
	        <button type="col button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
	        <button type="col button" class="btn btn-primary">Comprar</button>
      	</div>

      </div>
    </div>
  </div>
</div>

<style>

.btn-nav {
	margin: auto;
}

@media only screen and (max-width: 992px) {
	.btn-nav {
		margin: 0;
	}
}
</style>