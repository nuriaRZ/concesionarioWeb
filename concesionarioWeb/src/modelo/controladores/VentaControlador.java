package modelo.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Venta;
import modelo.controladores.Controlador;




public class VentaControlador extends Controlador {

	private static VentaControlador controlador = null;

	public VentaControlador () {
		super(Venta.class, "VentaDeCoches");
	}
	
	/**
	 * 
	 * @return
	 */
	public static VentaControlador getControlador () {
		if (controlador == null) {
			controlador = new VentaControlador();
		}
		return controlador;
	}

	/**
	 *  
	 */
	public Venta find (int id) {
		return (Venta) super.find(id);
	}

	
	/**
	 * 
	 * @return
	 */
	public Venta findFirst () {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v order by v.id", Venta.class);
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public Venta findLast () {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v order by v.id desc", Venta.class);
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public Venta findNext (Venta v) {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v where v.id > :idActual order by v.id", Venta.class);
			q.setParameter("idActual", v.getId());
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public Venta findPrevious (Venta v) {
		try {
			EntityManager em = getEntityManagerFactory().createEntityManager();
			Query q = em.createQuery("SELECT v FROM Venta v where v.id < :idActual order by v.id desc", Venta.class);
			q.setParameter("idActual", v.getId());
			Venta resultado = (Venta) q.setMaxResults(1).getSingleResult();
			em.close();
			return resultado;
		}
		catch (NoResultException nrEx) {
			return null;
		}
	}

	
	
	
	public List<Venta> findAll () {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		Query q = em.createQuery("SELECT v FROM Venta v", Venta.class);
		List<Venta> resultado = (List<Venta>) q.getResultList();
		em.close();
		return resultado;
	}
	

	
	public static String toString (Venta v) {
		return "Id: " + v.getId() + " - IdCliente: " + v.getCliente() +
				" - IdConcesionario: " + v.getConcesionario() + " - IdCoche: " + v.getCoche()+
				" - Fecha: " + v.getFecha() + " - PrecioVenta: " + v.getPrecioVenta(); 
	}

	
	
}
