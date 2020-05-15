<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page
	import="java.util.List,
	java.util.Date,
	java.text.SimpleDateFormat,
	modelo.Venta,
	modelo.controladores.VentaControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de Ventas" />
</jsp:include>

<%!
SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
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
	<h1>Listado de Ventas</h1>
	<table id="dtBasicExample" class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>Cliente</th>
				<th>Concesionario</th>	
				<th>Coche</th>	
				<th>Fecha</th>
				<th>Precio Venta</th>					
			</tr>
		</thead>
		<tbody >
			<%
				
			List<Venta> ventas = VentaControlador.getControlador().findAllLimited(5, offset);
			for (Venta venta : ventas) {
			%>
			<tr>
				<td><a
					href="fichaVenta.jsp?idVenta=<%=venta.getId()%>"> <%=venta.getCliente()%>
				</a></td>
				
				<td><%=venta.getConcesionario()%></td>
				<td><%=venta.getCoche()%></td>
				<td><%=((venta.getFecha() != null)? sdfFecha.format(venta.getFecha()) : "")%></td>
				<td><%=venta.getPrecioVenta()%></td>
								
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaVenta.jsp?idVenta=0'" />
		
		
<ul class="pagination justify-content-center">
	   <li class="page-item"><a class="page-link" href="?idPag=1">Primero</a></li>
    <%
	  int num = VentaControlador.getControlador().numRegistros();
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