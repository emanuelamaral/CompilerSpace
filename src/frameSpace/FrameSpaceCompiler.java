package frameSpace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
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

public class FrameSpaceCompiler extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 7834447818830595896L;

	private JButton btPegarArquivo;
	private JButton btCompilar;
	private JFileChooser fcEscolherArquivo;
	private String title;
	private JTextArea txCodigo;
	
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
		
//		txCodigo.setEditable(false);
		
		Font fonteTxCodigo = txCodigo.getFont();
		Font novaFonte = fonteTxCodigo.deriveFont(16f);
		
		txCodigo.setFont(novaFonte);
		
		JScrollPane scrollTextArea = new JScrollPane(txCodigo);
//		JScrollPane scrollArvoreSintatica = new JScro
		
		
		panelCodigo.setSize(540, 590);
		panelCodigo.setLayout(new BorderLayout());
		panelCodigo.setBackground(Color.GREEN);
		
		panelArvore.setSize(540, 590);
		panelArvore.setLocation(540, 0);
		
		panelButtons.setSize(1080, 100);
		panelButtons.setLayout(new GridBagLayout());
		panelButtons.setLocation(0, 590);
		panelButtons.setBackground(Color.darkGray);
		
		JLabel labelPrincipal = new JLabel();
		labelPrincipal.setText("Importe um arquivo da linguagem Space");
		labelPrincipal.setForeground(Color.WHITE);
		
		setBtPegarArquivo(new JButton("Escolher arquivo"));
		btPegarArquivo.addActionListener(this);
		
		setBtCompilar(new JButton("Compilar"));
		btCompilar.addActionListener(e -> compilarCodigo());
		
		panelCodigo.add(scrollTextArea);
		panelButtons.add(labelPrincipal);
		panelButtons.add(btPegarArquivo);
		panelButtons.add(btCompilar);
		
		
		
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
			
			File arquivo = fcEscolherArquivo.getSelectedFile();
			
			try {
				BufferedReader codigo = new BufferedReader(new FileReader(arquivo.toString()));
				while((texto = codigo.readLine()) != null) {
					resultado += texto + System.getProperty("line.separator");
				}

				
				txCodigo.setText(resultado);
				System.out.println(resultado);
				codigo.close();
				
				
			}catch(FileNotFoundException e1) {
				System.err.println("Arquivo não encontrado!");
			}catch(IOException e2) {
				System.err.println("Não foi possivel abrir o arquivo!");
			}
			
			setTxCodigo(new JTextArea());
			txCodigo.setText(arquivo.toString());
			 
			
		}
		else {
			System.out.println("Nenhum item selecionado!");
		}
		
	}
	
	public void compilarCodigo() {
		
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
}
