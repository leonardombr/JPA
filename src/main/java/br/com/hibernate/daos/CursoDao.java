package br.com.hibernate.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.hibernate.daos.utils.JpaUtils;
import br.com.hibernate.models.Curso;

public class CursoDao {
	
	private static EntityManager em = null;
	private static EntityTransaction tx = null;	

	public static List<Curso> all() {
		try {
			em = JpaUtils.getInstance().getEm();
			Query q = em.createQuery("SELECT curso FROM Curso curso");
			List<Curso> listaCursos = q.getResultList();
			return listaCursos;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}		
	}
	
	public static Curso getCursoById(Long id) {
		try {
			em = JpaUtils.getInstance().getEm();
			return em.find(Curso.class, id);		 			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}		
	}
	
	public static void inserir(Curso curso) {
		try {
			em = JpaUtils.getInstance().getEm();
			tx = em.getTransaction();
			tx.begin();
			em.persist(curso);
			tx.commit();	
		} catch (Exception e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
		}finally {
			em.close();
		}
	}
	
	public static void atualizar(Curso curso) {
		try {
			em = JpaUtils.getInstance().getEm();
			tx = em.getTransaction();
			tx.begin();
			em.merge(curso);
			tx.commit();	
		} catch (Exception e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
		}finally {
			em.close();
		}
	}
	
	public static void excluir(Curso curso) {
		try {
			em = JpaUtils.getInstance().getEm();
			tx = em.getTransaction();
			tx.begin();		
			em.remove(em.merge(curso));
			tx.commit();			
		} catch (Exception e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
		}finally {
			em.close();
		}
	}
}
