package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Concesionario;
import modelo.controladores.ConcesionarioControlador;
import utils.FormularioIncorrectoRecibidoException;
import utils.RequestUtils;

/**
 * Servlet implementation class FichaConcesionario
 */
@WebServlet(name = "FichaConcesionario", urlPatterns = {"/FichaConcesionario"})
public class FichaConcesionario extends SuperTipoServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FichaConcesionario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> hashMap = RequestUtils.requestToHashMap(request);
		Concesionario concesionario = new Concesionario();
		
		try {
			int idConcesionario = RequestUtils.getIntParameterFromHashMap(hashMap,"idConcesionario");
			if(idConcesionario != 0) {
				concesionario = (Concesionario)ConcesionarioControlador.getControlador().find(idConcesionario);
				
			}
		}catch (Exception e) { 
			e.printStackTrace();
		}
		
		if (concesionario == null) {
			concesionario = new Concesionario();
		}
		
		if (concesionario.getCif() == null) concesionario.setCif("");
		if (concesionario.getNombre() == null) concesionario.setNombre("");
		if (concesionario.getLocalidad() == null) concesionario.setLocalidad("");   
		
		// Variable con mensaje de información al usuario sobre alguna acción requerida
			String mensajeAlUsuario = "";
			//ELIMINAR
			if(RequestUtils.getStringParameterFromHashMap(hashMap, "eliminar") != null) {
				//intento eliminar el registro
				try {
					ConcesionarioControlador.getControlador().remove(concesionario);
					response.sendRedirect(request.getContextPath() + "listadoConcesionario");
				}catch (Exception ex) {
					mensajeAlUsuario = "Imposible eliminar. Es posible que existan restricciones";
				}
			}
			
			//GUARDAR
			
			if (RequestUtils.getStringParameterFromHashMap(hashMap, "guardar") != null) {
				//obtengo todos los datos y los guardo en la BBDD
				try {
					concesionario.setCif(RequestUtils.getStringParameterFromHashMap(hashMap, "cif"));
					concesionario.setNombre(RequestUtils.getStringParameterFromHashMap(hashMap, "nombre"));
					concesionario.setLocalidad(RequestUtils.getStringParameterFromHashMap(hashMap, "localidad"));
					byte[] imagen = RequestUtils.getByteArrayFromHashMap(hashMap, "ficheroImagen");
					if (imagen != null && imagen.length > 0) concesionario.setImagen(imagen);
					
					ConcesionarioControlador.getControlador().save(concesionario);
					mensajeAlUsuario = "Guardado correctamente";
				}catch(Exception ex) {
					throw new ServletException(ex);
				}
			}
			
			// Ahora muestro la pantalla de respuesta al usuario
			
			response.getWriter().append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
					"<head>\r\n" + 
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\r\n" + 
					"<title>Ficha Concesionario</title>\r\n" + 
					"</head>\r\n" + 
					"	<script>\r\n" + 
					"	function validateForm() {\r\n" + 
					"	  var x = document.forms[\"form1\"][\"cif\"].value;\r\n" + 
					"	  var x2 = document.forms[\"form1\"][\"nombre\"].value;\r\n" +
					"	  var x3 = document.forms[\"form1\"][\"localidad\"].value;\r\n" +
					"	  if (x == \"\") {\r\n" + 
					"	    alert(\"El campo Cif debe ser rellenado\");\r\n" + 
					"	    return false;\r\n" + 
					"	  }\r\n" + 
					"	  if (x2 == \"\") {\r\n" + 
					"	    alert(\"El campo Nombre debe ser rellenado\");\r\n" + 
					"	    return false;\r\n" + 
					"	  }\r\n" +
					"	  if (x3 == \"\") {\r\n" + 
					"	    alert(\"El campo Localidad debe ser rellenado\");\r\n" + 
					"	    return false;\r\n" + 
					"	  }\r\n" +
					"	}\r\n" + 
					"	</script>"+
					"\r\n" + 
					"<body " +((mensajeAlUsuario != null && mensajeAlUsuario != "")? "onLoad=\"alert('" + mensajeAlUsuario + "');\"" : "")  + " >\r\n" + 
					"<h1>Ficha de Concesionario</h1>\r\n" + 
					"<a href=\"ListadoConcesionario\">Ir al listado de Concesionarios</a>" +
					"<form id=\"form1\" name=\"form1\" method=\"post\" action=\"FichaConcesionario\" onsubmit=\"return validateForm()\" enctype=\"multipart/form-data\">\r\n" +
					" <img src=\"utils/DownloadImagenConcesionario?idConcesionario=" +  concesionario.getId()  + "\" width='100px' height='100px'/>" +
					" <input type=\"hidden\" name=\"idConcesionario\" value=\"" + concesionario.getId()  + "\"\\>" +
					"  <p>\r\n" + 
					"    <label for=\"ficheroImagen\">Imagen:</label>\r\n" + 
					"    <input name=\"ficheroImagen\" type=\"file\" id=\"ficheroImagen\" />\r\n" + 
					"  </p>\r\n" +
					"  <p>\r\n" + 
					"    <label for=\"cif\">Cif:</label>\r\n" + 
					"    <input name=\"cif\" type=\"text\" id=\"cif\"  value=\"" + concesionario.getCif() + "\" />\r\n" + 
					"  </p>\r\n" + 
					"  <p>\r\n" + 
					"    <label for=\"nombre\">Nombre:</label>\r\n" + 
					"    <input name=\"nombre\" type=\"text\" id=\"nombre\" value=\"" + concesionario.getNombre() + "\" />\r\n" + 
					"  </p>\r\n" + 
					"  <p>\r\n" + 
					"    <label for=\"localidad\">Localidad:</label>\r\n" + 
					"    <input name=\"localidad\" type=\"text\" id=\"localidad\" value='" + concesionario.getLocalidad() + "'/>\r\n" + 
					"  </p>\r\n" + 
					"  <p>\r\n" + 
					"    <input type=\"submit\" name=\"guardar\" value=\"Guardar\" />\r\n" + 
					"    <input type=\"submit\" name=\"eliminar\" value=\"Eliminar\" />\r\n" + 
					"  </p>\r\n" + 
					"</form>" 
					 ); 
			response.getWriter().append(this.getPieHTML());
					
					
					
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	
	

}
