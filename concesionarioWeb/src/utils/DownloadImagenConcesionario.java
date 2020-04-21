package utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Concesionario;
import modelo.controladores.ConcesionarioControlador;




/**
 * Servlet implementation class PrimerServlet
 */
@WebServlet(description = "Download imagen almacenada en BBDD", urlPatterns = { "/utils/DownloadImagenConcesionario" })
public class DownloadImagenConcesionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadImagenConcesionario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean imagenEncontradaYDevuelta = false;
		
		
		Concesionario concesionario = null;
		if (request.getParameter("idConcesionario") != null) { // compruebo si el par�metro est� en la petici�n web
			String strIdConcesionario = request.getParameter("idConcesionario");
			try {
				concesionario = (Concesionario) ConcesionarioControlador.getControlador().find(Integer.parseInt(strIdConcesionario));
				
				
				if (concesionario.getImagen() != null && concesionario.getImagen().length > 0) {
					
					response.setContentType("image/jpeg");
					// Establezco la longitud del fichero, para que el explorador cliente pueda conocer este dato
					response.setContentLength(concesionario.getImagen().length);
					
					InputStream inputStream = new ByteArrayInputStream(concesionario.getImagen()); // Convierto el array de bytes en un objeto InputStream
					OutputStream outStream = response.getOutputStream(); // Un stream que permite pasar la respuesta del Servlet en forma secuencial
					
					byte[] bufferDeLectura = new byte[4096];
					int bytesRead = -1; // Comienza la lectura del fichero y su paso al objeto response del Servlet 
	                while ((bytesRead = inputStream.read(bufferDeLectura)) != -1) {
	                    outStream.write(bufferDeLectura, 0, bytesRead);
	                }
	                inputStream.close();
	                outStream.close();
	                
	                imagenEncontradaYDevuelta = true;
				}
				
			} catch (Exception e) {
				System.out.println("Concesionario no encontrado para el id: " + strIdConcesionario);
			}
			
			
			if (!imagenEncontradaYDevuelta) {
				response.setContentType("text/html");
				response.getWriter().append("No se encuentra la imagen pedida");
			}
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
