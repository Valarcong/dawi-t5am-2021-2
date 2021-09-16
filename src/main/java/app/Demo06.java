package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo06 {
	public static void main(String[] args) {

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em = fabrica.createEntityManager();
		
		String sql = "Select a from Usuario a";
		
		List<Usuario> lstUsuarios = em.createQuery(sql,Usuario.class).getResultList();
			for (Usuario u : lstUsuarios) {
				System.out.println("Siguiente empleado : "+ u.getNombre());
			}
			
			String sql2 = "Select a from Usuario a where a.tipo = :xtipo";
			
			TypedQuery<Usuario> query = em.createQuery(sql2, Usuario.class);
			query.setParameter("xtipo", 1);
			
			List<Usuario> lstUsuarios2 = query.getResultList();
				System.out.println("nro de usuarios :" + lstUsuarios2.size());
				for (Usuario u : lstUsuarios2) {
					System.out.println("Siguiente empleado : "+ u);
				}
		em.close();
		
		
	}
}
