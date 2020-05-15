<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page
	import="java.util.List,
	modelo.Concesionario,
	modelo.controladores.ConcesionarioControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de concesionario" />
</jsp:include>

	<%!
			public int getOffset(String param){
				int offset = Integer.parseInt(param);
				if(offset > 1){
					return 5 * offset;
				}
				else{
					return 0;
				}
				
			}
		%>
		<%! private int offset, paginationIndex; %>
		<% offset = getOffset(request.getParameter("idPag"));
			paginationIndex = Integer.parseInt(request.getParameter("idPag"));
		%>

<div class="container">
	<h1>Listado de concesionarios</h1>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>Cif</th>
				<th>Nombre</th>
				<th>Localidad</th>				
			</tr>
		</thead>
		<tbody >
			<%
				
			List<Concesionario> concesionarios = ConcesionarioControlador.getControlador().findAllLimited(5, offset );
			for (Concesionario concesionario : concesionarios) {
			%>
			<tr>
				<td><a
					href="fichaConcesionario.jsp?idConcesionario=<%=concesionario.getId()%>"> <%=concesionario.getCif()%>
				</a></td>
				
				<td><%=concesionario.getNombre()%></td>
				<td><%=concesionario.getLocalidad()%></td>				
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaConcesionario.jsp?idConcesionario=0'" />


<ul class="pagination justify-content-center">
	   <li class="page-item"><a class="page-link" href="?idPag=1">Primero</a></li>
    <%
	  int num = ConcesionarioControlador.getControlador().numRegistros();
	  double size = Math.ceil(num / 5);
	  if (paginationIndex > 1){
	  %> 
		  <li class="page-item"><a class="page-link" href="?idPag=<%= paginationIndex-1 %>" ><%= paginationIndex-1 %></a></li>
	  <%
	  }
	  %>
	  <li class="page-item active"><a class="page-link" href="?idPag=<%= paginationIndex %>" ><%= paginationIndex %></a></li>
	  <%if (paginationIndex < size) { %>
	  <li class="page-item"><a class="page-link" href="?idPag=<%= paginationIndex+1 %>" ><%= paginationIndex+1 %></a></li>
	  <%} %>
	  <li class="page-item"><a class="page-link" href="?idPag=<%= Math.round(size) %>">Ultimo</a></li>
  </ul>
</div>



<%@ include file="pie.jsp"%>