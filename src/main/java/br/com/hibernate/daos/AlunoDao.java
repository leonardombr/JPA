package br.com.hibernate.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.hibernate.daos.utils.JpaUtils;
import br.com.hibernate.models.Aluno;

public class AlunoDao {
	
	private static EntityManager em = null;
	private static EntityTransaction tx = null;	

	public static List<Aluno> all(){
		try {
			em = JpaUtils.getInstance();
			Query q = em.createQuery("SELECT aluno FROM Aluno aluno");
			List<Aluno> listaAlunos = q.getResultList();
			return listaAlunos;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();	
		}		
	}
	
	public static Aluno getAlunoById(Long id){
		try {
			em = JpaUtils.getInstance();		
			return em.find(Aluno.class, (long)id);					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
		}		
	}
	
	public static void inserir(Aluno aluno) {
		try {
			em = JpaUtils.getInstance();
			tx = em.getTransaction();
			tx.begin();
			em.persist(aluno);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
		}finally {
			em.close();	
		}		
	}
	
	public static void atualizar(Aluno aluno) {
		try {
			em = JpaUtils.getInstance();
			tx = em.getTransaction();
			tx.begin();
			em.merge(aluno);
			tx.commit();	
		} catch (Exception e) {
			e.printStackTrace();
			if(tx.isActive())
				tx.rollback();
		}finally {
			em.close();	
		}		
	}
	
	public static void excluir(Aluno aluno) {
		try {
			em = JpaUtils.getInstance();
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.merge(aluno));
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
