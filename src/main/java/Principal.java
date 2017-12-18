import br.com.hibernate.daos.AlunoDao;
import br.com.hibernate.daos.MatriculaDao;
import br.com.hibernate.daos.TurmaDao;
import br.com.hibernate.models.Aluno;

public class Principal {
	
	public static String NOME_PERSISTENCE_UNIT = "jpa";

	public static void main(String[] args) {
		Aluno aluno = new Aluno();		
		aluno = AlunoDao.getAlunoById((long) 6);
		
		aluno.setIdade(06);
		
		AlunoDao.atualizar(aluno);
		
	}
}

