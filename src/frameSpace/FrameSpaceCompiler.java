package frameSpace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import parser.ParseException;
import parser.SimpleNode;
import parser.Space;
import recovery.ParseEOFException;

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
	
	private JPanel panelArvore;
	private JPanel panelConsole;
	
	private SimpleNode arvore;
	
	private Space compilador;
	
	private File arquivo;
	
	public boolean visivel = true;
	
	public FrameSpaceCompiler() {
		super();
	}
	
	public void initTela(JFrame tela) {
		
		tela.setSize(1088, 850);
		tela.setTitle("Space Compiler");
		
		JPanel panelButtons = new JPanel();
		JPanel panelCodigo = new JPanel();
		
		setPanelArvore(new JPanel());
		setPanelConsole(new JPanel());
		
		setTxCodigo(new JTextArea());
		setTxArvore(new JTextArea());
		setTxConsole(new JTextArea());
		
		getTxArvore().setEditable(false);
		getTxCodigo().setEditable(false);
		getTxConsole().setEditable(false);
		
		getTxCodigo().setFont(txCodigo.getFont().deriveFont(16f));
		
		JScrollPane scrollTextArea = new JScrollPane(getTxCodigo());
		JScrollPane scrollArvoreSintatica = new JScrollPane(getTxArvore());
		JScrollPane scrollConsole = new JScrollPane(getTxConsole());
		
		panelCodigo.setSize(540, 590);
		panelCodigo.setLayout(new BorderLayout());
		
		getPanelArvore().setSize(540, 590);
		getPanelArvore().setLocation(540, 0);
		getPanelArvore().setLayout(new BorderLayout());
		
		getPanelConsole().setSize(1080, 130);
		getPanelConsole().setLocation(0, 590);
		getPanelConsole().setLayout(new BorderLayout());
		getPanelConsole().setBackground(Color.black);
		
		panelButtons.setSize(1080, 100);
		panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
		panelButtons.setLocation(0, 720);
		panelButtons.setBackground(Color.darkGray);
		
		JLabel labelPrincipal = new JLabel();
		labelPrincipal.setText("Importe um arquivo da linguagem Space");
		labelPrincipal.setForeground(Color.WHITE);
		
		setBtPegarArquivo(new JButton("Escolher arquivo"));
		btPegarArquivo.addActionListener(this);
		
		setBtCompilar(new JButton("Compilar"));
		btCompilar.addActionListener(e -> compilarCodigo());
		
		panelCodigo.add(scrollTextArea);
		getPanelArvore().add(scrollArvoreSintatica);
		getPanelConsole().add(scrollConsole);
		
		panelButtons.add(btCompilar);
		panelButtons.add(btPegarArquivo);
	
		tela.setResizable(false);
		
		tela.add(panelCodigo);
		tela.add(getPanelArvore());
		tela.add(getPanelConsole());
		tela.add(panelButtons);

		tela.getContentPane().add(this, "Center");
		tela.setVisible(true);

	}

	@SuppressWarnings("static-access")
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
				
				if (compilador == null) {
					compilador = new Space(new FileReader(getArquivo()));
				}else {
					compilador.ReInit(new FileReader(getArquivo()));
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
	        setArvore(compilador.inicio());
	        	if (getArvore() != null) {
	        		txArvore.setText(""); 
	        		getArvore().dump(txArvore);
	        		compilador.consumeUntil(null, null, null, txConsole);
	        	}
	    }catch (ParseException e1) {
	        e1.printStackTrace();
	    } catch (ParseEOFException e) {
			e.printStackTrace();
		} 
	    finally {
	    	try {
				compilador.ReInit(new FileReader(getArquivo()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public void setOutputTextArea(JTextArea erroConsole) {
		setTxConsole(erroConsole);
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

	public JPanel getPanelArvore() {
		return panelArvore;
	}

	public void setPanelArvore(JPanel panelArvore) {
		this.panelArvore = panelArvore;
	}

	public JPanel getPanelConsole() {
		return panelConsole;
	}

	public void setPanelConsole(JPanel panelConsole) {
		this.panelConsole = panelConsole;
	}

	public JTextArea getTxConsole() {
		return txConsole;
	}

	public void setTxConsole(JTextArea txConsole) {
		this.txConsole = txConsole;
	}
}
