package frameSpace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FrameSpaceCompiler extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 7834447818830595896L;

	private JButton btPegarArquivo;
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
		JTextArea txCodigo = new JTextArea();
		
		txCodigo.setEditable(false);
		
		panelArvore.setSize(540, 900);
		panelArvore.setBackground(Color.BLUE);
		panelArvore.setLocation(540, 0);
		
		panelCodigo.setSize(540, 900);
		panelCodigo.setBackground(Color.GREEN);
		
		panelButtons.setSize(1080, 100);
		panelButtons.setLayout(new GridBagLayout());
		panelButtons.setLocation(0, 590);
		panelButtons.setBackground(Color.darkGray);
		
		JLabel labelPrincipal = new JLabel();
		labelPrincipal.setText("Importe um arquivo da linguagem Space");
		
		setIr(new JButton("Escolher arquivo"));
		btPegarArquivo.addActionListener(this);
		
		panelButtons.add(labelPrincipal);
		panelButtons.add(btPegarArquivo);
		
		tela.add(panelButtons);
		tela.add(panelCodigo);
		tela.add(panelArvore);
		
		tela.getContentPane().add(this, "Center");
		tela.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		setEscolher(new JFileChooser());
		
		fcEscolherArquivo.setCurrentDirectory(new java.io.File("."));
		fcEscolherArquivo.setDialogTitle(title);
		fcEscolherArquivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		fcEscolherArquivo.setAcceptAllFileFilterUsed(false);
		
		if(fcEscolherArquivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			
			File arquivo = fcEscolherArquivo.getSelectedFile();
//			File arquivo = fcEscolherArquivo.getSel
			
			
//			setTxCodigo(arquivo.toString()); 
			
			
		}
		else {
			System.out.println("Nenhum item selecionado!");
		}
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}

	public JButton getIr() {
		return btPegarArquivo;
	}


	public void setIr(JButton ir) {
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
	

}
