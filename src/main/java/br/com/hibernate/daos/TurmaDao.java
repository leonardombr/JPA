package br.com.hibernate.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.hibernate.daos.utils.JpaUtils;
import br.com.hibernate.models.Turma;

public class TurmaDao {
	
	private static EntityManager em = null;
	private static EntityTransaction tx = null;	

	public static List<Turma> all(){
		try {
			em = JpaUtils.getInstance();
			Query q = em.createQuery("SELECT turma FROM Turma turma");
			List<Turma> listaTurma = q.getResultList();
			return listaTurma;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}				
	}
	
	public static Turma getTurmaById(Long id) {
		try {
			em = JpaUtils.getInstance();
			em.getTransaction().begin();
			return em.find(Turma.class, id);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}		
	}
	
	public static void inserir(Turma turma) {
		try {
			em = JpaUtils.getInstance();
			tx = em.getTransaction();
			tx.begin();
			em.persist(turma);
			tx.commit();		
		} catch (Exception e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
		}finally {
			em.close();
		}
	}
	
	public static void atualizar(Turma turma) {
		try {
			em = JpaUtils.getInstance();
			tx = em.getTransaction();
			tx.begin();
			em.merge(turma);
			tx.commit();	
		} catch (Exception e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
		}finally {
			em.clear();
		}
	}
	
	public static void excluir(Turma turma) {
		try {
			em = JpaUtils.getInstance();
			tx = em.getTransaction();
			tx.begin();		
			em.remove(em.merge(turma));
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
