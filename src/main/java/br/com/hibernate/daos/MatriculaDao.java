package br.com.hibernate.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.hibernate.daos.utils.JpaUtils;
import br.com.hibernate.models.Aluno;
import br.com.hibernate.models.AlunoTurma;
import br.com.hibernate.models.Turma;

public class MatriculaDao {
	
	private static EntityManager em = null;
	private static EntityTransaction tx = null;
	
	public static void inserir(Aluno aluno, Turma turma) {
		try {
			em = JpaUtils.getInstance();
			tx = em.getTransaction();
			AlunoTurma matricula = new AlunoTurma();
			matricula.setAluno(aluno);
			matricula.setTurma(turma);
			
			tx.begin();
			em.persist(matricula);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}

}
