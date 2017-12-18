package br.com.hibernate.daos.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	
	private static String NOME_PERSISTENCE_UNIT = "jpa";
	private static EntityManager em;
	
	private JpaUtils() {
		
	}

	public static EntityManager getInstance() {
		if(em == null) {
			EntityManagerFactory defaultEmf = Persistence.createEntityManagerFactory(NOME_PERSISTENCE_UNIT);
			em = defaultEmf.createEntityManager();
		}
		return em;
	}
}
