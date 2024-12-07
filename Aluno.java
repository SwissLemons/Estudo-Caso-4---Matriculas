public class Aluno {
	private int matricula;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String telefone;
	private String cursoAtual;
	
	public String getCursoAtual() {
        return cursoAtual;
    }
    public void setCursoAtual(String cursoAtual) {
        this.cursoAtual = cursoAtual;
    }
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataFormatada) {
		this.dataNascimento = dataFormatada;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefoneStr) {
		this.telefone = telefoneStr;
	}
}