// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import javax.swing.JFrame;
import javax.swing.JInternalFrame;



@SuppressWarnings("serial")
public class ScoreGUI extends JInternalFrame {

	public JFrame ScoreGUI;
	public ScoreFunctions hs;

	public ScoreGUI() {
		super("HiScores", 
				false, //resizable
				true, //closable
				false, //maximizable
				true);//iconifiable     

		
		
		ScoreGUI = new JFrame("High Score Table");
	
		ScoreGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//ScoreGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		hs = new ScoreFunctions();
		(ScoreGUI.getContentPane()).add(hs);

		ScoreGUI.pack();
		ScoreGUI.setFocusable(true);
		ScoreGUI.setVisible(true);
		
		ScoreGUI.setLocation(300, 100);
	

		
		
		
		
	}
}