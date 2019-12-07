// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class StartApp extends JFrame implements ActionListener {

	JFrame MenuFrame = new JFrame("Game Menu");
	JButton btnPlay, btnHighScore, btnExit;
	Music background_music = new Music();
	ScoreFunctions load = new ScoreFunctions();

	public StartApp() throws IOException {

		GUI();

	}

	public void GUI() {

		background_music.setFile("musica.wav");
		background_music.play();
		load.loadScore();
		MenuFrame.setSize(1280, 720);
		MenuFrame.setLocation(0, 0);
		MenuFrame.setVisible(true);

		MenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageBackground b = new ImageBackground("fondo_score.jpg");

		this.btnPlay = new JButton("Start Game");
		this.btnPlay.setEnabled(true);
		this.btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.btnPlay.setToolTipText("Start Game");
		this.btnPlay.setBackground(new Color(0, 180, 255));
		this.btnPlay.setSize(150, 50);
		this.btnPlay.setLocation(540, 200);
		this.btnPlay.addActionListener(this);
		MenuFrame.add(this.btnPlay);

		this.btnHighScore = new JButton("High Scores");
		this.btnHighScore.setEnabled(true);
		this.btnHighScore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.btnHighScore.setToolTipText("Start Game");
		this.btnHighScore.setBackground(new Color(0, 180, 255));
		this.btnHighScore.setSize(150, 50);
		this.btnHighScore.setLocation(540, 260);
		this.btnHighScore.addActionListener(this);
		MenuFrame.add(this.btnHighScore);

		this.btnExit = new JButton("Exit");
		this.btnExit.setEnabled(true);
		this.btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.btnExit.setToolTipText("Start Game");
		this.btnExit.setBackground(new Color(0, 180, 255));
		this.btnExit.setSize(150, 50);
		this.btnExit.setLocation(540, 320);
		this.btnExit.addActionListener(this);
		MenuFrame.add(this.btnExit);

		MenuFrame.add(b);

		btnHighScore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // TODO Auto-generated
				
				ScoreGUI test1 = new ScoreGUI();
				//System.out.println("Check high score");

			}

		});

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // TODO Auto-generated
				//System.out.println("Exit");
				System.exit(0);
			}
		});

		btnPlay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				Ventana jugar = new Ventana();
				MenuFrame.dispose();
				//System.out.println("Play");
			}

		});

	}

	public static void main(String[] args) throws IOException {
		StartApp inicio = new StartApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
