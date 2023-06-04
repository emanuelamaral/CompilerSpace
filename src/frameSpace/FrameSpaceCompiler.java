package frameSpace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import frameSpaceExtra.BordaNumerada;
import parser.ParseException;
import parser.SimpleNode;
import parser.Space;

//import parser.ParseException;

public class FrameSpaceCompiler extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 7834447818830595896L;

	private JButton btPegarArquivo;
	private JButton btCompilar;
	private JButton btArvoreConsole;
	
	private JFileChooser fcEscolherArquivo;
	
	private String title;
	
	private JTextArea txCodigo;
	private JTextArea txArvore;
	private JTextArea txConsole;
	
	private SimpleNode arvore;
	
	private Space compilador;
	
	private File arquivo;
	
	public FrameSpaceCompiler() {
		super();
	}
	
	public void initTela(JFrame tela) {
		
		tela.setSize(1080, 720);
		tela.setTitle("Space Compiler");
		
		JPanel panelButtons = new JPanel();
		JPanel panelCodigo = new JPanel();
		JPanel panelArvore = new JPanel();
		
		setTxCodigo(new JTextArea());
		setTxArvore(new JTextArea());
		
		txArvore.setEditable(false);
		
		txCodigo.setFont(txCodigo.getFont().deriveFont(16f));
		
		JScrollPane scrollTextArea = new JScrollPane(txCodigo);
		JScrollPane scrollArvoreSintatica = new JScrollPane(txArvore);
		
		panelCodigo.setSize(540, 590);
		panelCodigo.setLayout(new BorderLayout());
		
		panelArvore.setSize(540, 590);
		panelArvore.setLocation(540, 0);
		panelArvore.setLayout(new BorderLayout());
		
		panelButtons.setSize(1080, 100);
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
		panelButtons.setLocation(0, 590);
		panelButtons.setBackground(Color.darkGray);
		
		JLabel labelPrincipal = new JLabel();
		labelPrincipal.setText("Importe um arquivo da linguagem Space");
		labelPrincipal.setForeground(Color.WHITE);
		
		setBtPegarArquivo(new JButton("Escolher arquivo"));
		btPegarArquivo.addActionListener(this);
		
		setBtCompilar(new JButton("Compilar"));
		btCompilar.addActionListener(e -> compilarCodigo());
		
		setBtArvoreConsole(new JButton("Mostrar Console"));
		btArvoreConsole.addActionListener(e -> trocarVisualizacao());
		
		panelCodigo.add(scrollTextArea);
		
		panelArvore.add(scrollArvoreSintatica);
		
		panelButtons.add(btCompilar);
		panelButtons.add(btPegarArquivo);
		panelButtons.add(btArvoreConsole);
	
		
		tela.setResizable(false);
		
		tela.add(panelButtons);
		tela.add(panelCodigo);
		tela.add(panelArvore);
		
		tela.getContentPane().add(this, "Center");
		tela.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String texto = "";
		String resultado = "";
		setEscolher(new JFileChooser());
		
		fcEscolherArquivo.setCurrentDirectory(new java.io.File("."));
		fcEscolherArquivo.setDialogTitle(title);
		fcEscolherArquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		fcEscolherArquivo.setAcceptAllFileFilterUsed(false);
		
		if(fcEscolherArquivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			
			setArquivo(fcEscolherArquivo.getSelectedFile());
			
			try {
				BufferedReader codigo = new BufferedReader(new FileReader(getArquivo().toString()));
				while((texto = codigo.readLine()) != null) {
					resultado += texto + System.getProperty("line.separator");
				}
				
				txCodigo.setText(resultado);
				codigo.close();
				
			}catch(FileNotFoundException e1) {
				System.err.println("Arquivo não encontrado!");
			}catch(IOException e2) {
				System.err.println("Não foi possivel abrir o arquivo!");
			}
			
		}
		else {
			System.out.println("Nenhum item selecionado!");
		}
		
	}
	
	@SuppressWarnings("static-access")
	public void compilarCodigo() {
	    System.out.println("Aqui será gerada a árvore sintática");
	    System.out.println(getTxCodigo().getText());

	    try {
	        compilador = new Space(new FileReader(getArquivo()));
	        setArvore(compilador.inicio());
	        if (getArvore() != null) {
	            txArvore.setText(""); 
	            getArvore().dump(txArvore);
	        }
	    } catch (ParseException e1) {
	        e1.printStackTrace();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	public void trocarVisualizacao() {
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	public JButton getBtPegarArquivo() {
		return btPegarArquivo;
	}


	public void setBtPegarArquivo(JButton ir) {
		this.btPegarArquivo = ir;
	}


	public JFileChooser getEscolher() {
		return fcEscolherArquivo;
	}


	public void setEscolher(JFileChooser escolher) {
		this.fcEscolherArquivo = escolher;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public JTextArea getTxCodigo() {
		return txCodigo;
	}


	public void setTxCodigo(JTextArea arquivo) {
		this.txCodigo = arquivo;
	}

	public JButton getBtCompilar() {
		return btCompilar;
	}

	public void setBtCompilar(JButton btCompilar) {
		this.btCompilar = btCompilar;
	}

	public JButton getBtArvoreConsole() {
		return btArvoreConsole;
	}

	public void setBtArvoreConsole(JButton btArvoreConsole) {
		this.btArvoreConsole = btArvoreConsole;
	}

	public SimpleNode getArvore() {
		return arvore;
	}

	public void setArvore(SimpleNode arvore) {
		this.arvore = arvore;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}
	
	public JTextArea getTxArvore() {
		return txArvore;
	}

	public void setTxArvore(JTextArea txArvore) {
		this.txArvore = txArvore;
	}
}
