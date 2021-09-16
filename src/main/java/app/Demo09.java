package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo09 {

	public static void main(String[] args) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
			
		//Validar usuario segun Usuario y Clave --> usando Store procedures
		String sql2 = "{call usp_validaAcceso (:xusr, :xcla)}";
		
		//TypedQuery<Usuario> query = em.createQuery(sql2, Usuario.class);
		Query query = em.createNativeQuery(sql2,Usuario.class);
		
		query.setParameter("xusr", "U003@gmail.com");
		query.setParameter("xcla", "10003");
		
		Usuario u = null;
		try {
				u = (Usuario) query.getSingleResult();
		}catch (Exception e) {
			
		}
		if (u==null) {
			System.out.println("Codigo no existe");
			
		}else {
			System.out.println("Bienvenido : " + u.getNombre() );
		}
		
		em.close();
		
		
	}
}
