<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page
	import="java.util.List,
	modelo.Coche,
	modelo.controladores.CocheControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de Coches" />
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
	<h1>Listado de Coches</h1>
	<table id="dtBasicExample" class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>Fabricante</th>
				<th>Bastidor</th>	
				<th>Modelo</th>	
				<th>Color</th>					
			</tr>
		</thead>
		<tbody >
			<%
				
			List<Coche> coches = CocheControlador.getControlador().findAllLimited(5, offset);
			for (Coche coche : coches) {
			%>
			<tr>
				<td><a
					href="fichaCoche.jsp?idCoche=<%=coche.getId()%>"> <%=coche.getFabricante()%>
				</a></td>
				
				<td><%=coche.getBastidor()%></td>
				<td><%=coche.getModelo()%></td>
				<td><%=coche.getColor()%></td>
								
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaCoche.jsp?idCoche=0'" />
		
		
<ul class="pagination justify-content-center">
	   <li class="page-item"><a class="page-link" href="?idPag=1">Primero</a></li>
    <%
	  int num = CocheControlador.getControlador().numRegistros();
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