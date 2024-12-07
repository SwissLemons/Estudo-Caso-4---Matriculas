import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Main {
	public static ArrayList <Aluno> alunos;
	public static ArrayList <Curso> cursos;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		alunos = new ArrayList<>();
		cursos = new ArrayList<>();
		menu();
	}
	
	public static void menu() throws ClassNotFoundException, SQLException {
		JLabel labelOpcaoMenu = new JLabel("Selecione uma opção: ");
		JComboBox<String> opcaoMenu  = new JComboBox<String>();
		opcaoMenu.addItem("Cadastrar novo aluno");
		opcaoMenu.addItem("Cadastrar novo curso");
		opcaoMenu.addItem("Sair");
		
		//objeto do menu
		Object[] menu = {labelOpcaoMenu, opcaoMenu};
		JOptionPane.showMessageDialog(null, menu, "SCDAL 0.8.6",3);
		
		switch(opcaoMenu.getSelectedIndex()) {
		case 0:
			//aluno
			CadastroAlunos.cadastrarAluno();
			break;
		case 1:
			//curso
			CadastroCurso.cadastrarCurso();
			break;
		case 2:
			//sair
			Utils.sair();
			break;
		}
	}
}