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
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import parser.ParseException;
import parser.SimpleNode;
import parser.Space;

public class FrameSpaceCompiler extends JPanel implements ActionListener{
	
	/*Classe interna que escreve no textArea(txConsole) toda a saída que vai para o console*/
	private static class CustomOutputStream extends OutputStream {
	    private JTextArea textArea;
	    
	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	    }
	    
	    
	    
	    @Override
	    public void write(int b) throws IOException {
	        textArea.append(String.valueOf((char) b));
	        textArea.setCaretPosition(textArea.getDocument().getLength());
	    }
	}

    private static final long serialVersionUID = 7834447818830595896L;

    private JButton btPegarArquivo;
    private JButton btCompilar;
    private JButton btArvoreConsole;

    private JFileChooser fcEscolherArquivo;

    private String title;

    private JTextArea txCodigo;
    private JTextArea txArvore;
    private JTextArea txConsole;
    private JTextArea txNumeroLinhas;

    private JPanel panelArvore;
    private JPanel panelConsole;

    private SimpleNode arvore;

    private Space compilador;

    private File arquivo;

    private JScrollPane scrollTextArea;
    private JScrollPane scrollNumeroLinhas;
    
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
        setTxNumeroLinhas(new JTextArea());

        getTxArvore().setEditable(false);
        getTxCodigo().setEditable(false);
        getTxConsole().setEditable(false);

        getTxCodigo().setFont(getTxCodigo().getFont().deriveFont(16f));

        panelCodigo.setSize(540, 590);
        panelCodigo.setLayout(new BorderLayout());

        getPanelArvore().setSize(540, 590);
        getPanelArvore().setLocation(540, 0);
        getPanelArvore().setLayout(new BorderLayout());

        getPanelConsole().setSize(1080, 130);
        getPanelConsole().setLocation(0, 590);
        getPanelConsole().setLayout(new BorderLayout());
        getPanelConsole().setBackground(Color.BLACK);

        panelButtons.setSize(1080, 100);
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panelButtons.setLocation(0, 720);
        panelButtons.setBackground(Color.DARK_GRAY);

        getTxNumeroLinhas().setFont(getTxCodigo().getFont().deriveFont(16f));
        getTxNumeroLinhas().setBackground(Color.LIGHT_GRAY);
        getTxNumeroLinhas().setEditable(false);
        
        JLabel labelPrincipal = new JLabel();
        labelPrincipal.setText("Importe um arquivo da linguagem Space");
        labelPrincipal.setForeground(Color.WHITE);

        setBtPegarArquivo(new JButton("Escolher arquivo"));
        getBtPegarArquivo().addActionListener(this);

        setBtCompilar(new JButton("Compilar"));
        getBtCompilar().addActionListener(e -> compilarCodigo());

        setScrollTextArea(new JScrollPane(getTxCodigo()));
        setScrollNumeroLinhas(new JScrollPane(getTxNumeroLinhas()));
        getScrollNumeroLinhas().setPreferredSize(new Dimension(27, 0));

        JScrollPane scrollArvoreSintatica = new JScrollPane(getTxArvore());
        JScrollPane scrollConsole = new JScrollPane(getTxConsole());

        getScrollNumeroLinhas().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        getScrollNumeroLinhas().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        

        panelCodigo.add(getScrollTextArea());
        panelCodigo.add(getScrollNumeroLinhas(), BorderLayout.WEST);

        getScrollTextArea().getVerticalScrollBar().addAdjustmentListener(e -> {
            int value = getScrollTextArea().getVerticalScrollBar().getValue();
            getScrollNumeroLinhas().getVerticalScrollBar().setValue(value);
        });

        getPanelArvore().add(scrollArvoreSintatica);
        getPanelConsole().add(scrollConsole);

        panelButtons.add(getBtCompilar());
        panelButtons.add(getBtPegarArquivo());

        tela.setResizable(false);

        tela.add(panelCodigo);
        tela.add(getPanelArvore());
        tela.add(getPanelConsole());
        tela.add(panelButtons);

        tela.getContentPane().add(this, BorderLayout.CENTER);
        updateLinhas();
        
        tela.setVisible(true);
        setOutputTextArea(getTxConsole());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String texto = "";
        String resultado = "";
        setFcEscolherArquivo(new JFileChooser());

        getFcEscolherArquivo().setCurrentDirectory(new java.io.File("."));
        getFcEscolherArquivo().setDialogTitle(title);
        getFcEscolherArquivo().setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        getFcEscolherArquivo().setAcceptAllFileFilterUsed(false);

        if (getFcEscolherArquivo().showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            setArquivo(getFcEscolherArquivo().getSelectedFile());

            try {
                BufferedReader codigo = new BufferedReader(new FileReader(getArquivo().toString()));
                while ((texto = codigo.readLine()) != null) {
                    resultado += texto + System.getProperty("line.separator");
                }

                getTxCodigo().setText(resultado);
                updateLinhas();
                codigo.close();

            } catch (FileNotFoundException e1) {
                System.err.println("Arquivo não encontrado!");
            } catch (IOException e2) {
                System.err.println("Não foi possível abrir o arquivo!");
            }
        } else {
            System.out.println("Nenhum item selecionado!");
        }

    }

    public void compilarCodigo() {
        limparArvore();
        limparConsole();
        
        try {
            if (compilador == null) {
                compilador = new Space(new FileReader(getArquivo()));
            } else {
                Space.ReInit(new FileReader(getArquivo()));
            }

            setArvore(Space.inicio());
            getArvore().dump(txArvore);
        } catch (ParseException ex) {
        	ex.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateLinhas() {
        int linhas = getTxCodigo().getLineCount();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= linhas; i++) {
            sb.append(i).append("\n");
        }
        getTxNumeroLinhas().setText(sb.toString());
        getTxNumeroLinhas().setCaretPosition(0);

    }
    
    public void setOutputTextArea(JTextArea outputTextArea) {
        PrintStream printStream = new PrintStream(new CustomOutputStream(outputTextArea));
        System.setOut(printStream);
    }
    
    private void limparArvore() {
    	getTxArvore().setText("");
    }
    
    private void limparConsole() {
    	getTxConsole().setText("");
    }

    @Override
	public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    public JButton getBtPegarArquivo() {
        return btPegarArquivo;
    }

    public void setBtPegarArquivo(JButton btPegarArquivo) {
        this.btPegarArquivo = btPegarArquivo;
    }

    public JFileChooser getFcEscolherArquivo() {
        return fcEscolherArquivo;
    }

    public void setFcEscolherArquivo(JFileChooser fcEscolherArquivo) {
        this.fcEscolherArquivo = fcEscolherArquivo;
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

    public void setTxCodigo(JTextArea txCodigo) {
        this.txCodigo = txCodigo;
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

    public JTextArea getTxNumeroLinhas() {
        return txNumeroLinhas;
    }

    public void setTxNumeroLinhas(JTextArea txNumeroLinhas) {
        this.txNumeroLinhas = txNumeroLinhas;
    }

    public JScrollPane getScrollTextArea() {
        return scrollTextArea;
    }

    public void setScrollTextArea(JScrollPane scrollTextArea) {
        this.scrollTextArea = scrollTextArea;
    }

    public JScrollPane getScrollNumeroLinhas() {
        return scrollNumeroLinhas;
    }

    public void setScrollNumeroLinhas(JScrollPane scrollNumeroLinhas) {
        this.scrollNumeroLinhas = scrollNumeroLinhas;
    }

    public Space getCompilador() {
        return compilador;
    }

    public void setCompilador(Space compilador) {
        this.compilador = compilador;
    }

    public static void main(String[] args) {
        JFrame tela = new JFrame();

        FrameSpaceCompiler frame = new FrameSpaceCompiler();
        frame.initTela(tela);

    }

}
