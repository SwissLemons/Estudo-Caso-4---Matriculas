import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CadastroAlunos {
	public static String comboBoxCursos;
	private static JTextField tfNome;
	private static JTextField tfCPF;
	private static JTextField tfTelefone;
	private static JDatePickerImpl datePicker;
	private static JComboBox<String> comboBox;
	private static JTable table;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @wbp.parser.entryPoint
	 */
	public static void cadastrarAluno() throws ClassNotFoundException, SQLException {
		
		//Frame
		JFrame telaCadastro = new JFrame();
		telaCadastro.setResizable(false);
		telaCadastro.setTitle("Cadastro de Aluno");
		telaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaCadastro.setSize(990, 406);
		telaCadastro.getContentPane().setLayout(null);
		telaCadastro.setLocationRelativeTo(null);
		
		//TF Nome
		tfNome = new JTextField();
		tfNome.setBounds(10, 36, 186, 20);
		telaCadastro.getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		//lbl Nome
		JLabel lblNome = new JLabel("Nome do aluno: ");
		lblNome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNome.setBounds(10, 11, 186, 14);
		telaCadastro.getContentPane().add(lblNome);
        
		//TF DatePicker
        UtilDateModel model = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		SpringLayout springLayout = (SpringLayout) datePicker.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(),0,SpringLayout.WEST,datePicker);
		datePicker.setBounds(10, 92, 186, 30);
		model.setSelected(true);
		telaCadastro.getContentPane().add(datePicker);
        
		//lbl DataNascimento
		JLabel lblDataNascimento = new JLabel("Data de nascimento: ");
		lblDataNascimento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataNascimento.setBounds(10, 67, 186, 14);
		telaCadastro.getContentPane().add(lblDataNascimento);

		//TF CPF
		tfCPF = new JTextField();
		tfCPF.setColumns(10);
		tfCPF.setBounds(10, 158, 186, 20);
		telaCadastro.getContentPane().add(tfCPF);
		
		//lbl CPF
		JLabel lblCPF = new JLabel("CPF: ");
		lblCPF.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCPF.setBounds(10, 133, 186, 14);
		telaCadastro.getContentPane().add(lblCPF);
		
		//TF Telefone
		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(10, 214, 186, 20);
		telaCadastro.getContentPane().add(tfTelefone);
		
		//lbl Telefone
		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefone.setBounds(10, 189, 186, 14);
		telaCadastro.getContentPane().add(lblTelefone);
		
		//TF Curso
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"- Selecione -"}));
		comboBox.setBounds(10,270, 186, 22);
		telaCadastro.getContentPane().add(comboBox);
		
		// Consultar cursos do banco de dados e adicionar ao JComboBox
	    ConexaoMysql conexao = new ConexaoMysql();
	    try {
	        ArrayList<Curso> cursos = conexao.consultarCurso();
	        for (Curso curso : cursos) {
	            comboBox.addItem(curso.getNomeCurso());
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao carregar cursos do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
		//lbl Curso
		JLabel lblCurso = new JLabel("Curso Atrelado:");
		lblCurso.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCurso.setBounds(10, 245, 186, 14);
		telaCadastro.getContentPane().add(lblCurso);
		
		//botao Confirmar
		JButton btnContinue = new JButton("Confirmar cadastro");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//valida e processa os dados inseridos
				try {
					validaAluno(telaCadastro);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnContinue.setBounds(10, 303, 186, 23);
		telaCadastro.getContentPane().add(btnContinue);
		
		//botao alterar dados 
		JButton btnContinue_1 = new JButton("Alterar da tabela");
		btnContinue_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
					//verificar se uma linha foi selecionada:
					if(table.getSelectedRow() != -1){
						DefaultTableModel modelo = (DefaultTableModel) table.getModel();
						ConexaoMysql conexao = new ConexaoMysql();
						Aluno aluno = new Aluno();
						
						//pega os novos valores dos campos de entrada
						String novoNome = tfNome.getText();
						Date dataSelecionada = (Date) datePicker.getModel().getValue();
		                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		                String novaDataNascimento = sdf.format(dataSelecionada);
		                String novoCPF = tfCPF.getText();
		                String novoTelefone = tfTelefone.getText();
		                String novoCurso = comboBox.getSelectedItem().toString();
						
		                // Atualiza os valores do aluno
		                aluno.setMatricula(Integer.parseInt(modelo.getValueAt(table.getSelectedRow(), 0).toString()));
		                aluno.setNome(novoNome);
		                aluno.setDataNascimento(novaDataNascimento);
		                aluno.setCpf(novoCPF);
		                aluno.setTelefone(novoTelefone);
		                aluno.setCursoAtual(novoCurso);
		                
		                // Atualiza a tabela
		                modelo.setValueAt(novoNome, table.getSelectedRow(), 1);
		                modelo.setValueAt(novaDataNascimento, table.getSelectedRow(), 2);
		                modelo.setValueAt(novoCPF, table.getSelectedRow(), 3);
		                modelo.setValueAt(novoTelefone, table.getSelectedRow(), 4);
		                modelo.setValueAt(novoCurso, table.getSelectedRow(), 5);
						
		                // Atualiza o banco de dados
		                conexao.alterarAluno(aluno);
		                atualizarTabela();
					} else {
						JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para atualizar.", "Erro", JOptionPane.ERROR_MESSAGE);
			        }
				atualizarTabela();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnContinue_1.setBounds(390, 337, 174, 23);
		telaCadastro.getContentPane().add(btnContinue_1);
		
		//botao voltar ao menu
		JButton btnContinue_2 = new JButton("Voltar ao menu");
		btnContinue_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    telaCadastro.dispose();
				try {
					Main.menu();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnContinue_2.setBounds(10, 337, 186, 23);
		telaCadastro.getContentPane().add(btnContinue_2);
		
		//botao excluir cadastro 
		JButton btnContinue_1_1 = new JButton("Excluir da tabela");
		btnContinue_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//excluir uma linha da tabela:
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					if(table.getSelectedRow() != -1) {
						ConexaoMysql conexao = new ConexaoMysql();
						Aluno aluno = new Aluno();
						
						String matricula = modelo.getValueAt(table.getSelectedRow(),0).toString();
						aluno.setMatricula(Integer.parseInt(matricula));
						try {
							conexao.excluir(aluno);
							atualizarTabela();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					atualizarTabela();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnContinue_1_1.setBounds(790, 337, 174, 23);
		telaCadastro.getContentPane().add(btnContinue_1_1);
		
		//scroll pane da tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 11, 758, 315);
		telaCadastro.getContentPane().add(scrollPane);
		
		//table da tabela
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnContinue_1_2 = new JButton("limpar campos de dados");
		btnContinue_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnContinue_1_2.setBounds(206, 337, 174, 23);
		telaCadastro.getContentPane().add(btnContinue_1_2);
		
		//atualiza a tabela de alunos
        atualizarTabela();
		
		//visibilidade da tela
        telaCadastro.setVisible(true);        
	}
	
	public static void validaAluno(JFrame telaCadastro) throws ClassNotFoundException, SQLException{
		//dataPicker e suas formatações
	    Date dataSelecionada = (Date) datePicker.getModel().getValue();
		// Formate a data para o formato desejado
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = sdf.format(dataSelecionada);
		
	    // Verificação do nome
	    String nome = tfNome.getText().replaceAll("[^a-zA-Z\\s]", ""); // Remover caracteres não alfabeticos
	    if (nome.isEmpty() || nome.length() < 3) {
	        JOptionPane.showMessageDialog(null, "Por favor, insira o nome do aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // verificação da data
	    if(dataSelecionada == null || dataFormatada == null) {
	    	JOptionPane.showMessageDialog(null, "Por favor, insira uma data de nascimento valida.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Verificação do CPF
	    String cpf = tfCPF.getText().replaceAll("[^0-9]", ""); // Remover caracteres não numéricos
	    if (cpf.length() != 11) {
	        JOptionPane.showMessageDialog(null, "Por favor, insira um CPF válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Verificação do telefone
	    String telefoneStr = tfTelefone.getText().replaceAll("[^0-9]", ""); // Remover caracteres não numéricos
	    if (telefoneStr.length() < 10 || telefoneStr.length() >11) {
	        JOptionPane.showMessageDialog(null, "Por favor, insira um telefone válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Verificação do curso selecionado (pode ser nulo entao ta show)
	    String cursoSelecionado = (String) comboBox.getSelectedItem();
	    //if (cursoSelecionado == null || cursoSelecionado.equals("- Selecione -")) {
        //	JOptionPane.showMessageDialog(null, "Por favor, selecione um curso.", "Erro", JOptionPane.ERROR_MESSAGE);
        //	return;
	    //}
	    
	    // Criar um novo objeto Aluno com os dados fornecidos
	    Aluno novoAluno = new Aluno();
	    novoAluno.setNome(tfNome.getText());
	    novoAluno.setDataNascimento(dataFormatada);
	    novoAluno.setCpf(tfCPF.getText());
	    novoAluno.setTelefone(telefoneStr);
	    novoAluno.setCursoAtual(cursoSelecionado); // Setar o curso selecionado

		ConexaoMysql conexao = new ConexaoMysql();
		try {
			conexao.salvar(novoAluno);
			atualizarTabela();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    // Adicionar o novo aluno à lista de alunos na classe Main
	    Main.alunos.add(novoAluno);
	    // Exibir mensagem de confirmação
	    JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    
	    atualizarTabela();
	}
	
	public static void atualizarTabela() throws ClassNotFoundException, SQLException {
		ConexaoMysql conexao = new ConexaoMysql();
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();		
		// Define the column names for the table model
	    modelo.setColumnIdentifiers(new Object[]{"Matricula", "Nome", "Data de Nascimento", "CPF", "Telefone", "Curso"});
		modelo.setRowCount(0);//exclui todas as linhas da tabekla
		
		//adicionar os objetos do banco de dados na tabela da tela:
		for (Aluno aluno : conexao.consultar()) {
	        modelo.addRow(new Object[] {
		        aluno.getMatricula(), 
		        aluno.getNome(), 
		        aluno.getDataNascimento(), 
		        aluno.getCpf(), 
	            aluno.getTelefone(), 
	            aluno.getCursoAtual()
	        });
		}
		table.setModel(modelo);
	}
}