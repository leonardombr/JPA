package br.com.hibernate.daos.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	
	private static String NOME_PERSISTENCE_UNIT = "jpa";
	private static JpaUtils instancia;
	private EntityManagerFactory factory;
	
	private JpaUtils() {
		factory = Persistence.createEntityManagerFactory(NOME_PERSISTENCE_UNIT);
	}

	public static JpaUtils getInstance() {
		if (instancia == null){
			instancia = new JpaUtils();
		}
		return instancia;
	}
	
	public EntityManager getEm(){
		return this.factory.createEntityManager();
	}
	
}
