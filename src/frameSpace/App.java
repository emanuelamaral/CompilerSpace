package frameSpace;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {
		JFrame test = new JFrame("");
		FrameSpaceCompiler tela = new FrameSpaceCompiler();
		
		test.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			}
		);
		
		tela.initTela(test);
		
	}

}
