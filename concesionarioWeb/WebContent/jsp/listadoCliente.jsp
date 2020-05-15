<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page
	import="java.util.List,
	java.util.Date,
	java.text.SimpleDateFormat,
	modelo.Cliente,
	modelo.controladores.ClienteControlador"%>

<jsp:include page="cabecera.jsp" flush="true">
	<jsp:param name="tituloDePagina" value="Listado de Clientes" />
</jsp:include>

<%!
SimpleDateFormat sdfFechaNac = new SimpleDateFormat("dd/MM/yyyy");
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
	<h1>Listado de Clientes</h1>
	<table id="dtBasicExample" class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>DNI/NIE</th>
				<th>Nombre</th>	
				<th>Apellidos</th>
				<th>Localidad</th>
				<th>Fecha Nacimiento</th>
				<th>Activo</th>						
			</tr>
		</thead>
		<tbody >
			<%
				
			List<Cliente> clientes = ClienteControlador.getControlador().findAllLimited(5, offset);
			for (Cliente cliente : clientes) {
			%>
			<tr>
				<td><a
					href="fichaCliente.jsp?idCliente=<%=cliente.getId()%>"> <%=cliente.getDniNie()%>
				</a></td>
				
				<td><%=cliente.getNombre()%></td>
				<td><%=cliente.getApellidos()%></td>
				<td><%=cliente.getLocalidad()%></td>
				<td><%=((cliente.getFechaNac() != null)? sdfFechaNac.format(cliente.getFechaNac()) : "")%></td>
				<td align="center"><div>								
						<input name="activo" class="form-check-input" type="checkbox" id="activo" checked="<%=true%>" />								
					</div></td>
								
			</tr>
			<%
				}
			// Al finalizar de exponer la lista de profesores termino la tabla y cierro el fichero HTML
			%>
		</tbody>
	</table>
	<p />
	<input type="submit" class="btn btn-primary" name="nuevo" value="Nuevo"
		onclick="window.location='fichaCliente.jsp?idCliente=0'" />
		
		
<ul class="pagination justify-content-center">
	   <li class="page-item"><a class="page-link" href="?idPag=1">Primero</a></li>
    <%
	  int num = ClienteControlador.getControlador().numRegistros();
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