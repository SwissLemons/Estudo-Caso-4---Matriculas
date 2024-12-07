import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexaoMysql {
	//usuario senha e url para conexao
	private String usuario = "root";
	private String senha = "root";
	private String url = "jdbc:mysql://localhost:3306/escola_legal";
	private Connection idConexao;
	
	//conecta ao mysql
	public void conectar() throws ClassNotFoundException, SQLException {
		//carregar driver(biblioteca)
		Class.forName("com.mysql.jdbc.Driver");
		//fazer a conecção:
		this.idConexao = DriverManager.getConnection(url, usuario, senha);
		//testar coneção:
		if(this.idConexao != null) {
			System.out.println("Conectado.");
		}
	}
	//desconecta para evitar uso exessivo de recursos
	public void desConectar() throws SQLException {
		if(this.idConexao != null) {
			System.out.println("Desconectado.");
			this.idConexao.close();
		}
	}
	//salvar na lista de alunos
	public void salvar(Aluno aluno) throws ClassNotFoundException, SQLException {
		this.conectar();
		String sql = "insert into aluno (nome,dataNascimento,cpf,telefone, cursoAtual) values (?,?,?,?,?)";
		PreparedStatement comando = this.idConexao.prepareStatement(sql);
		//inserir os valores no comando SQL:
		comando.setString(1, aluno.getNome());
		comando.setString(2, aluno.getDataNascimento());
		comando.setString(3, aluno.getCpf());
		comando.setString(4, aluno.getTelefone());
		comando.setString(5, aluno.getCursoAtual());
		//executar:
		comando.execute();
		//fecha a coneção do banco:
		this.desConectar();
	}
	//salvar na lista de cursos
	public void salvarCurso(Curso curso) throws ClassNotFoundException, SQLException {
		this.conectar();
		String sqlC = "insert into curso (nomeCurso,dataInicio,dataTermino,professor) values (?,?,?,?)";
		PreparedStatement comandoC = this.idConexao.prepareStatement(sqlC);
		//inserir os valores no comando SQL:
		comandoC.setString(1, curso.getNomeCurso());
		comandoC.setString(2, curso.getDataInicio());
		comandoC.setString(3, curso.getDataTermino());
		comandoC.setString(4, curso.getProfessor());
		//executar:
		comandoC.execute();
		//fecha a coneção do banco:
		this.desConectar();
	}
	//mostrar lista alunos
	public ArrayList<Aluno> consultar() throws ClassNotFoundException, SQLException {
		this.conectar();
		String sql = "select matricula, nome, dataNascimento, cpf, telefone, cursoAtual  from aluno";
		//criar o comando:
		PreparedStatement comando = this.idConexao.prepareStatement(sql);
	    ResultSet dadosAluno = comando.executeQuery(); //consulta: SELECT
		//cria uma lista:
		ArrayList<Aluno> listaAlunos = new ArrayList();
		while(dadosAluno.next()) {//enquanto houver registros
			Aluno aluno = new Aluno(); //cada linha da tabela
			aluno.setMatricula(dadosAluno.getInt(1));
			aluno.setNome(dadosAluno.getString(2));
			aluno.setDataNascimento(dadosAluno.getString(3));
			aluno.setCpf(dadosAluno.getString(4));
			aluno.setTelefone(dadosAluno.getString(5));
	        aluno.setCursoAtual(dadosAluno.getString(6)); // Incluindo o curso
			listaAlunos.add(aluno);
		}// preencheu o Arraylist com os registros
		this.desConectar();
		return listaAlunos;
	}
	//mostrar lista cursos
	public ArrayList<Curso> consultarCurso() throws ClassNotFoundException, SQLException {
		this.conectar();
		String sql = "select idCurso, nomeCurso, dataInicio, dataTermino, professor from curso";
		//criar o comando:
		PreparedStatement comando = this.idConexao.prepareStatement(sql);
		ResultSet dadosCurso = comando.executeQuery(); //consulta: SELECT
		//cria uma lista:
		ArrayList<Curso> listaCursos = new ArrayList();
		while(dadosCurso.next()) {//enquanto houver registros
			Curso curso = new Curso();
			curso.setIdCurso(dadosCurso.getInt(1));
			curso.setNomeCurso(dadosCurso.getString(2));
			curso.setDataInicio(dadosCurso.getString(3));
			curso.setDataTermino(dadosCurso.getString(4));
			curso.setProfessor(dadosCurso.getString(5));
			//aluno.setCurso(curso.setNomeCurso(dados.getString(6)));
			listaCursos.add(curso);
		}// preencheu o Arraylist com os registros
		this.desConectar();
		return listaCursos;
	}
	//alterar aluno:
	public void alterarAluno(Aluno aluno) throws ClassNotFoundException, SQLException {
		this.conectar();
		String sqlAlterar = "update aluno set nome=?, dataNascimento=?, cpf=?, telefone=?, cursoAtual=? where matricula=?";
		PreparedStatement comandoAlterar = this.idConexao.prepareStatement(sqlAlterar);
		comandoAlterar.setString(1, aluno.getNome());
		comandoAlterar.setString(2, aluno.getDataNascimento());
		comandoAlterar.setString(3, aluno.getCpf());
		comandoAlterar.setString(4, aluno.getTelefone());
		comandoAlterar.setString(5, aluno.getCursoAtual());
		comandoAlterar.setInt(6, aluno.getMatricula());
		comandoAlterar.executeUpdate();
		this.desConectar();
	}
	//alterar curso:
	public void alterarCurso(Curso curso) throws ClassNotFoundException, SQLException {
		this.conectar();
		String sql = "update curso set nomeCurso=?, dataInicio=?, dataTermino=?, professor=? where idCurso=?";
		PreparedStatement comando = this.idConexao.prepareStatement(sql);
		comando.setString(1, curso.getNomeCurso());
		comando.setString(2, curso.getDataInicio());
		comando.setString(3, curso.getDataTermino());
		comando.setString(4, curso.getProfessor());
		comando.setInt(5, curso.getIdCurso());
		comando.executeUpdate();
		this.desConectar();
	}
	//excluir aluno:
	public void excluir(Aluno aluno) throws ClassNotFoundException, SQLException {
		this.conectar();
		String sql = "delete from aluno where matricula=?";
		PreparedStatement comando = this.idConexao.prepareStatement(sql);
		comando.setInt(1, aluno.getMatricula());	
		comando.executeUpdate();
		this.desConectar();
	}
	//excluir curso:
	public void excluirCurso(Curso curso) throws ClassNotFoundException, SQLException {
		this.conectar();
		String sql = "delete from curso where idCurso=?";
		PreparedStatement comando = this.idConexao.prepareStatement(sql);
		comando.setInt(1, curso.getIdCurso());	
		comando.executeUpdate();
		this.desConectar();
	}	
}