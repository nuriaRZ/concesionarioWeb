package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Concesionario;
import modelo.controladores.ConcesionarioControlador;



/**
 * Servlet implementation class ListadoConcesionario
 */
@WebServlet(description = "Listado de Concesionario", urlPatterns = {"/ListadoConcesionario"})
public class ListadoConcesionario extends SuperTipoServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoConcesionario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(this.getCabeceraHTML("Listado de Concesionario"));
		response.getWriter().append("\r\n" + 
				"<body>\r\n" + 
				"<h1>Listado de Concesionarios</h1>\r\n" + 
				"<table width=\"95%\" border=\"1\">\r\n" + 
				"  <tr>\r\n" + 
				"    <th scope=\"col\">CIF</th>\r\n" + 
				"    <th scope=\"col\">Nombre</th>\r\n" + 
				"    <th scope=\"col\">Localidad</th>\r\n" + 
				"  </tr>\r\n");
		
		List<Concesionario> concesionarios = ConcesionarioControlador.getControlador().findAll();
		
		for (Concesionario c : concesionarios) {
			response.getWriter().append("" +
					"  <tr>\r\n" + 
					"    <td><a href=\"FichaConcesionario?idConcesionario=" + c.getId() + "\">" + c.getCif() + "</a></td>\r\n" + 
					"    <td>" + c.getNombre() + "</td>\r\n" + 
					"    <td>" + c.getLocalidad() + "</td>\r\n" + 
					"  </tr>\r\n");
		}
		
		response.getWriter().append(""+
				"</table>\r\n" +
				"<p/><input type=\"submit\"  name=\"nuevo\" value=\"Nuevo\"  onclick=\"window.location='FichaConcesionario?idConcesionario=0'\"/>" +
				 
				"");
		response.getWriter().append(this.getPieHTML());
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
