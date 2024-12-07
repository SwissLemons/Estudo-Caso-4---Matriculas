import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CadastroCurso {
	private static JTextField tfNomeCurso;
	private static JTextField tfProfessor;
	private static JDatePickerImpl dpInicio;
	private static JDatePickerImpl dpTermino;
	private static JTable tableCurso;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @wbp.parser.entryPoint
	 */
	public static void cadastrarCurso() throws ClassNotFoundException, SQLException {
		
		//JFrame
		JFrame telaCadastroCurso = new JFrame();
		telaCadastroCurso.setResizable(false);
		telaCadastroCurso.setTitle("Cadastro de Curso");
		telaCadastroCurso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaCadastroCurso.setSize(890, 360);
		telaCadastroCurso.getContentPane().setLayout(null);
		telaCadastroCurso.setLocationRelativeTo(null);
		
		//lbl Curso
		JLabel lblNewLabel = new JLabel("Nome do curso:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 186, 14);
		telaCadastroCurso.getContentPane().add(lblNewLabel);
		
		//TF Curso
		tfNomeCurso = new JTextField();
		tfNomeCurso.setBounds(10, 36, 186, 20);
		telaCadastroCurso.getContentPane().add(tfNomeCurso);
		tfNomeCurso.setColumns(10);
		
		//lbl Data de inicio
		JLabel lblNewLabel_1 = new JLabel("Data de inicio do curso:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setBounds(10, 67, 186, 14);
		telaCadastroCurso.getContentPane().add(lblNewLabel_1);
		
		//TF Data de inicio
        UtilDateModel model = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
		dpInicio = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		SpringLayout springLayout = (SpringLayout) dpInicio.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, dpInicio.getJFormattedTextField(),0,SpringLayout.WEST,dpInicio);
		dpInicio.setBounds(10, 92, 186, 30);
		model.setSelected(true);
		telaCadastroCurso.getContentPane().add(dpInicio);
		
		//lbl Data de termino
		JLabel lblNewLabel_2 = new JLabel("Data de termino do curso:");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 133, 186, 14);
		telaCadastroCurso.getContentPane().add(lblNewLabel_2);
		
		//TF Data de termino
        UtilDateModel model1 = new UtilDateModel();
		Properties properties1 = new Properties();
		properties1.put("text.today", "Today");
		properties1.put("text.month", "Month");
		properties1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, properties1);
		dpTermino = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
		SpringLayout springLayout1 = (SpringLayout) dpTermino.getLayout();
		springLayout1.putConstraint(SpringLayout.WEST, dpTermino.getJFormattedTextField(),0,SpringLayout.WEST,dpTermino);
		dpTermino.setBounds(10, 156, 186, 30);
		model1.setSelected(true);
		telaCadastroCurso.getContentPane().add(dpTermino);
		
		//lbl Professor
		JLabel lblNewLabel_3 = new JLabel("Professor associado:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_3.setBounds(10, 197, 186, 14);
		telaCadastroCurso.getContentPane().add(lblNewLabel_3);
		
		//TF Professor
		tfProfessor = new JTextField();
		tfProfessor.setColumns(10);
		tfProfessor.setBounds(10, 222, 186, 20);
		telaCadastroCurso.getContentPane().add(tfProfessor);
		
		//Botao Confirmar
		JButton btnConfirmar = new JButton("Confirmar Curso");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					validaCurso(telaCadastroCurso);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnConfirmar.setBounds(10, 253, 186, 23);
		telaCadastroCurso.getContentPane().add(btnConfirmar);
		
		//Botao voltar ao menu
		JButton btnCancelar = new JButton("Voltar ao menu");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaCadastroCurso.dispose();
				try {
					Main.menu();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCancelar.setBounds(10, 287, 186, 23);
		telaCadastroCurso.getContentPane().add(btnCancelar);
		
		//Botao alterar da tabela
		JButton btnAlterarDaTabela = new JButton("Alterar da tabela");
		btnAlterarDaTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
					//verificar se uma linha foi selecionada:
					if(tableCurso.getSelectedRow() != -1){
						DefaultTableModel modelo = (DefaultTableModel) tableCurso.getModel();
						ConexaoMysql conexao = new ConexaoMysql();
						Curso curso = new Curso();
						
						//pega os novos valores dos campos de entrada
						String novoNomeCurso = tfNomeCurso.getText();
						Date dataInicioSelecionada = (Date) dpInicio.getModel().getValue();
		                SimpleDateFormat sdfI = new SimpleDateFormat("dd/MM/yyyy");
		                String novaDataInicio = sdfI.format(dataInicioSelecionada);
						Date dataTerminoSelecionada = (Date) dpInicio.getModel().getValue();
		                SimpleDateFormat sdfT = new SimpleDateFormat("dd/MM/yyyy");
		                String novaDataTermino = sdfT.format(dataTerminoSelecionada);
		                String novoProfessor = tfProfessor.getText();
						
		                // Atualiza os valores do curso
		                curso.setIdCurso(Integer.parseInt(modelo.getValueAt(tableCurso.getSelectedRow(), 0).toString()));
		                curso.setNomeCurso(novoNomeCurso);
		                curso.setDataInicio(novaDataInicio);
		                curso.setDataTermino(novaDataTermino);
		                curso.setProfessor(novoProfessor);
		                
		                // Atualiza a tabela
		                modelo.setValueAt(novoNomeCurso, tableCurso.getSelectedRow(), 1);
		                modelo.setValueAt(novaDataInicio, tableCurso.getSelectedRow(), 2);
		                modelo.setValueAt(novaDataTermino, tableCurso.getSelectedRow(), 3);
		                modelo.setValueAt(novoProfessor, tableCurso.getSelectedRow(), 4);
						
		                // Atualiza o banco de dados
		                conexao.alterarCurso(curso);
		                atualizarTabelaCurso();
					} else {
						JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para atualizar.", "Erro", JOptionPane.ERROR_MESSAGE);
			        }
				atualizarTabelaCurso();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnAlterarDaTabela.setBounds(206, 287, 186, 23);
		telaCadastroCurso.getContentPane().add(btnAlterarDaTabela);
		
		//Botao excluir da tabela
		JButton btnExcluirDaTabela = new JButton("Excluir da tabela");
		btnExcluirDaTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//excluir uma linha da tabela:
					DefaultTableModel modelo = (DefaultTableModel) tableCurso.getModel();
					if(tableCurso.getSelectedRow() != -1) {
						ConexaoMysql conexao = new ConexaoMysql();
						Curso curso = new Curso();
						
						String matricula = modelo.getValueAt(tableCurso.getSelectedRow(),0).toString();
						curso.setIdCurso(Integer.parseInt(matricula));
						try {
							conexao.excluirCurso(curso);
							atualizarTabelaCurso();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					atualizarTabelaCurso();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExcluirDaTabela.setBounds(678, 287, 186, 23);
		telaCadastroCurso.getContentPane().add(btnExcluirDaTabela);
		
		//Scroll pane da tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 10, 658, 265);
		telaCadastroCurso.getContentPane().add(scrollPane);
		
		//table da tabela
		tableCurso = new JTable();
		scrollPane.setViewportView(tableCurso);
		
		JButton btnLimparCamposDe = new JButton("Limpar campos de dados");
		btnLimparCamposDe.setBounds(402, 287, 186, 23);
		telaCadastroCurso.getContentPane().add(btnLimparCamposDe);
		
		//atualiza a tabela de cursos
		atualizarTabelaCurso();
		
		//visibilidade da tela
        telaCadastroCurso.setVisible(true);
	}
	
	public static void validaCurso(JFrame telaCadastroCurso) throws ClassNotFoundException, SQLException {
		//dataPicker e suas formatações
	    Date dataInicioSelecionada = (Date) dpInicio.getModel().getValue();
	    Date dataTerminoSelecionada = (Date) dpTermino.getModel().getValue();

		// Formate a data para o formato desejado
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicioFormatada = sdf.format(dataInicioSelecionada);
		String dataTerminoFormatada = sdf.format(dataTerminoSelecionada);
		
		// Verificação do curso
	    String curso = tfNomeCurso.getText().replaceAll("[^a-z]", ""); // Remover caracteres não alfabeticos
	    if (curso.isEmpty() || curso.length() < 3) {
	        JOptionPane.showMessageDialog(null, "Por favor, insira o nome do curso.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // verificação da data de inicio
	    if(dataInicioSelecionada == null || dataInicioFormatada == null) {
	    	JOptionPane.showMessageDialog(null, "Por favor, insira uma data de inicio do curso valida.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // verificação da data de termino
	    if(dataTerminoSelecionada == null || dataTerminoFormatada == null) {
	    	JOptionPane.showMessageDialog(null, "Por favor, insira uma data de termino do curso valida.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Verificação do professor
	    String professor = tfProfessor.getText().replaceAll("[^a-z]", ""); // Remover caracteres não alfabeticos
	    if (professor.isEmpty() || professor.length() < 3) {
	        JOptionPane.showMessageDialog(null, "Por favor, insira o nome do professor responsavel.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    // Criar um novo objeto Curso com os dados fornecidos
	    Curso novoCurso = new Curso();
	    novoCurso.setNomeCurso(tfNomeCurso.getText());
	    novoCurso.setDataInicio(dataInicioFormatada);
	    novoCurso.setDataTermino(dataTerminoFormatada);
	    novoCurso.setProfessor(tfProfessor.getText());
	    
	    ConexaoMysql conexao = new ConexaoMysql();
		try {
			conexao.salvarCurso(novoCurso);
			atualizarTabelaCurso();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    // Adicionar o novo aluno à lista de alunos na classe Main
	    Main.cursos.add(novoCurso);
	    // Exibir mensagem de confirmação
	    JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	    
	    atualizarTabelaCurso();
	}	
	
	public static void atualizarTabelaCurso() throws ClassNotFoundException, SQLException {
		ConexaoMysql conexaoCurso = new ConexaoMysql();
		DefaultTableModel modeloCurso = (DefaultTableModel) tableCurso.getModel();
		// Define the column names for the table model
		modeloCurso.setColumnIdentifiers(new Object[]{"Id", "Nome do Curso", "Data de Inicio", "Data de Termino", "Professor"});
		modeloCurso.setRowCount(0);//exclui todas as linhas da tabekla
		
		//adicionar os objetos do banco de dados na tabela da tela:
		for (Curso curso : conexaoCurso.consultarCurso()) {
			modeloCurso.addRow(new Object[] {
		    	curso.getIdCurso(), 
		        curso.getNomeCurso(), 
		        curso.getDataInicio(), 
		        curso.getDataTermino(), 
		        curso.getProfessor()
			});
		}
		tableCurso.setModel(modeloCurso);
	}
}