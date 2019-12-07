// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;

public class Ventana extends JFrame implements WindowListener{
	
	
	public Ventana() {
		super("Shoot em up");
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		this.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		this.add(new GamePanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
	}
	
	public static void main(String[] args) {
		Ventana ve=new Ventana();
		
	}


	public void windowActivated(WindowEvent arg0) {

		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
		System.exit(0);
	}


	public void windowClosing(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
		
	}


	

	
	
		
}
