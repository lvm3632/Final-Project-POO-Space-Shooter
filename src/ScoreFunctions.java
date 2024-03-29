// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;


@SuppressWarnings("serial")
public class ScoreFunctions extends JComponent {


	private static final int numHiScore = 100; // number of high score to be shown
	private static final Scores nullScore = new Scores(null, 999999.99);

	private Scores[] hiScores;
	private int size;
	private boolean sorted;
	private Image fondo_score;

	public ScoreFunctions() {

		// size of the screen
		int xdim = 1280;
		int ydim = 720;

		this.setSize(xdim, ydim);
		this.setPreferredSize(new Dimension(xdim, ydim));
		this.setFocusable(true);
		
		hiScores = new Scores[numHiScore + 1];
		size = 0;
		sorted = false;

		for (int i = 0; i < numHiScore + 1; i++) {
			hiScores[i] = nullScore;
		}
	}

	public void clear() {
		size = 0;
		sorted = false;
		for (int i = 0; i < numHiScore + 1; i++) {
			hiScores[i] = nullScore;
		}
	}

	// add High Score to the end
	public void add(Scores hs) {
		hiScores[size] = hs;
		if (size < numHiScore) {
			size++;
		}
		sorted = false;
	}

	public void sort() {
		if (!sorted) {
			Arrays.sort(hiScores);
			sorted = true;
		}
	}

	// insert a new High Score
	public void newHiScore(String name, Double score) {
		Scores nScore = new Scores(name, score);
		add(nScore);
		sort();
	
	}

	// check a score if it is a new High Score
	public boolean isHiScore(Double score) {
		sort();
		Double bestScore = hiScores[numHiScore - 1].score(); // must beat the last entry
		return (score < bestScore);
	}

	// load high score from text
	public void loadScore() {

		try {
			String line;
			clear();
			BufferedReader reader = new BufferedReader(new FileReader("hiscore.txt"));
			while ((line = reader.readLine()) != null) {

				String[] str = new String[2];
				str = line.split(";");
				String name = str[0];
				Double score = Double.parseDouble(str[1]);
				Scores hiscore = new Scores(name, score);
				add(hiscore);
				
			}
			sort();
			
		} catch (FileNotFoundException e) {
			System.out.println("No High Score found! (File not found)");
		} catch (IOException e) {
			System.out.println("No High Score found! (IOException)");
		}
	
	}

	// save high score to text
	public void saveScore() throws IOException {

		try {
			PrintWriter pr = new PrintWriter("hiscore.txt");
			for (int i = 0; (!hiScores[i].equals(nullScore)) && (i < numHiScore); i++) {
				pr.println(hiScores[i].name() + ";" + hiScores[i].score());
			}
			pr.close();

		} catch (FileNotFoundException e) {
			File file = new File("hiscore.txt");
			if (file.createNewFile()) {
				System.out.println("\"hiscore.txt\" is created");
				saveScore();
			} else {
				System.out.println("High Score Table failed to be created!");
			}
		}
	}

	// display the high score table
	@Override
	public void paintComponent(Graphics g) {
		
		
		this.fondo_score = new ImageIcon("fondo_menu.jpg").getImage();
		g.drawImage(this.fondo_score, 0, 0, this.getWidth(), this.getHeight(), this);
		int xName = 275;
		int xScore = 875;
		int y = 165;

		loadScore();
	
		g.setColor(Color.GREEN);
		g.setFont(new Font("Monospaced", Font.BOLD, 72));
		g.drawString("High Scores", 400, 70);
		
		
		g.setColor(Color.GREEN);
		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		int contador=0;
		for(int i=0;i<numHiScore;i++) {
			if(!hiScores[i].equals(nullScore)) {
				contador++;
			}
		}
		contador = contador - 1;
		for (int i = 0; (!hiScores[i].equals(nullScore)) && (i < 10); i++) {
			//System.out.println("" + hiScores[i]);
			g.drawString(hiScores[contador-i].name(), xName, y);
			g.drawString(hiScores[contador-i].score() + "", xScore, y);
			g.drawString((i+1) + ".", 200, y);
			y += 40;
		}
		
		
		
		

	}

	// print high score on console
	// for testing purpose
	/*public void printScore() {
		for (int i = 0; (!hiScores[i].equals(nullScore)) && (i < numHiScore); i++) {
			System.out.println(hiScores[i]);
		}
	}*/

}