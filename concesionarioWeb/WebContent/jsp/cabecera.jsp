<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  	<% String tituloDePagina = request.getParameter("tituloDePagina"); %>
<title><%= (tituloDePagina != null)? tituloDePagina : "Sin título" %></title>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark justify-content-center">
		<a class="nav-link" href=listadoConcesionario.jsp?idPag=1>Concesionarios</a>
		<a class="nav-link" href=listadoFabricante.jsp?idPag=1>Fabricantes</a>
		<a class="nav-link" href=listadoCoche.jsp?idPag=1>Coches</a>
		<a class="nav-link" href=listadoCliente.jsp?idPag=1>Clientes</a>
		<a class="nav-link" href=listadoVenta.jsp?idPag=1>Ventas</a>
	</nav>
	<p>
	
