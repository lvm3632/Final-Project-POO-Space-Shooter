// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable,KeyListener{
	
	public static int WIDTH=1280;
	public static int HEIGHT=720;
	
	protected Image 	fondo,
							fondo_game_over, 
							fondo_vidas_restantes;;
	protected Player 	player;
	protected Thread 	thread;
	protected boolean 		running,
							pause;
	protected BufferedImage image;
	protected Graphics2D g;
	protected Sounds 	explosion,
						pauseSound;
	protected static ArrayList<Bullets> bullets;
	protected static ArrayList<Powerup> power;
	protected static ArrayList<Enemy> 	enemies;
	protected static int 				acumMuerte=0,
										acumPower=0,
										acumEnemigos=0;
	private double score = 0;
	private boolean comprobador=false;
	int contador= 0;
	public GamePanel(){
		super();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.setFocusable(true);
												
		
		this.fondo=new ImageIcon("Fondo.jpg").getImage();	//Get BKG
		this.player=new Player();	
		GamePanel.bullets=new ArrayList<Bullets>();
		GamePanel.power=new ArrayList<Powerup>();
		GamePanel.enemies= new ArrayList<Enemy>();
		this.explosion = new Sounds();
		this.pauseSound = new Sounds();
												
		this.addKeyListener(this);
		
		
	}
	
	public void setScore(double score) {
		this.score = score;
		
	}
	public double getScore() {
		return this.score;
	}
	
	public void puntuaciones() {
		
		if(this.player.lives < 0) {
			comprobador = true;
			if(comprobador && contador == 0) {
			
				Scores player_name = new Scores();
				String name = JOptionPane.showInputDialog(null, "Nombre del jugador: ");
				player_name.setName(name);
					
				Scores player = new Scores(player_name.name(), this.score);	
				ScoreFunctions guardar_scores = new ScoreFunctions();
	
				guardar_scores.loadScore();
				guardar_scores.add(player);
				try {
					guardar_scores.saveScore();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.setFocusable(false);
				ScoreGUI ver = new ScoreGUI();
				
				
			}
		}
		
		contador++;
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread==null) {
			thread=new Thread(this);
			thread.start();
		}
	}


	public void run() {
		long startTime;
		long timeMilis;
		long waitTime;
		long totalTime=0;
		int targetTime=1000/60;
		
		image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D)image.getGraphics();
		running=true;
		pause=false;
		
		
	
		while(running) {
			if (!pause) {
				this.requestFocus();
				startTime=System.currentTimeMillis();
					
				update();
				render();
				draw();
				
				timeMilis=(System.nanoTime()-startTime)/1000000;
				waitTime=targetTime-timeMilis;
				
				try {
					Thread.sleep(waitTime);
				}catch(Exception e) {
					
				}
				
				totalTime+=System.nanoTime()-startTime;
			}else {
				this.requestFocus();
			}
					
		}
		
	}
	

	public void update() {				//All game procedures
		this.player.update();
		
		for (int i=0;i<GamePanel.enemies.size();i++) {
			GamePanel.enemies.get(i).enemyUpdate();
		}
		
		if (this.enemies.size()<10 && this.player.intangible==false) {
			acumEnemigos++;
			if (150<acumEnemigos ) {
				GamePanel.enemies.add(new Enemy());
				acumEnemigos=0;
			}
		}
		
		if (this.power.size()<1 && this.player.intangible==false ) {
			acumPower++;
			if (200<acumPower ) {
				GamePanel.power.add(new Powerup());
				acumPower=0;
				
			}
		}
		
		if (power.isEmpty()) {
			
		}else {
			for (int i=0;i<GamePanel.power.size();i++) {
				power.get(i).powerUpdate();
				try {
					
					if (power.get(i).xPos<0) {
						power.remove(i);
						return;
					}
					
					if (power.get(i).hbm.hbx.intersects(this.player.hbp.hbx)) {
						this.player.bulletType="advanced";
						this.player.hits=1;
						this.player.setSprite("PowerUp");
						power.remove(i);
						return;
						
					}
					
					
				}catch(Exception e) {
					
				}
			}
		}
		
		//System.out.println(this.player.hits);
		
		
		for (int i=0;i<GamePanel.bullets.size();i++) {
			boolean remove=bullets.get(i).update();
			if (remove) {
				bullets.remove(i);
				i--;
			}
		}
		
		
		for (int i=0;i<GamePanel.bullets.size();i++) {
			Bullets bullet=bullets.get(i);
			int x=(int) bullet.xPos;
			int y=(int) bullet.yPos;
			Hurtbox hbb= new Hurtbox(x,y,20,20);
			if (bullet.allied==false) {
				int hits=this.player.hits;
				this.player.destroyed(hbb);
				if (this.player.hits<hits) {
					bullets.remove(i);
				}
			}
			
			
		}
		
		if (enemies.isEmpty()) {
			
		}else {
			for (int j=0;j<GamePanel.enemies.size();j++) {
				for (int i=0;i<GamePanel.bullets.size();i++) {
							
						if (bullets.get(i)==null) {
							return;
						}else {
							Hurtbox hbb= new Hurtbox((int)bullets.get(i).xPos,(int)bullets.get(i).yPos,20,20);
							if (bullets.get(i).allied==true && hbb.hbx.intersects(enemies.get(j).hbe.hbx)) {
								GamePanel.bullets.remove(i);
								i++;
								enemies.get(j).dead();
								
								
								return;
							}
						}
					
					if (3<=enemies.get(j).hitsCounter) {
						
						enemies.get(j).intangible=true;
						enemies.get(j).down=false;
						enemies.get(j).up=false;
						enemies.get(j).right=false;
						enemies.get(j).left=false;
						enemies.get(j).sprite=new ImageIcon("Explosion.gif").getImage();
						enemies.get(j).tiempoExplosion++;
						
						if (1000<=enemies.get(j).tiempoExplosion) {
							if (enemies.get(j).enemyType==0) {
								this.score = 20 + this.score;
								this.setScore(this.score);
							}
							
							if (enemies.get(j).enemyType==1) {
								this.score = 30 + this.score;
								this.setScore(this.score);
							}
							
							if (enemies.get(j).enemyType==2) {
								this.score = 40 + this.score;
								this.setScore(this.score);
							}
							enemies.remove(j);
						}
					}	
						
					if (enemies.isEmpty()) {
						return;
					}
					try {
						if (enemies.get(j)==null ) {
							return;
						}
					}catch (Exception e) {
						return;
					}
					
			
				}
				if (enemies.isEmpty()) {
					return;
				}
			}
		}
		
		if (enemies.isEmpty()) {
			
		}else {
			for (int j=0;j<GamePanel.enemies.size();j++) {
				this.player.destroyed(enemies.get(j).hbe);
			}
		}
		

		if (2000<acumMuerte) {
			
				this.player.xPos=200;
				this.player.yPos=200;
				this.player.setSprite("Normal");
				this.player.bulletType="basic";
				this.player.hits=0;
				this.player.intangible=false;
				acumMuerte=0;
				acumEnemigos=0;
				
		}
		

	}
	
	public void render() {				//Game graphics related
		if(this.player.lives<0) {
			this.fondo_game_over = new ImageIcon("game_over.jpg").getImage();
			g.drawImage(this.fondo_game_over, 0, 0, this.getWidth(), this.getHeight(), this);
			g.setFont(new Font("Monospaced", Font.BOLD, 200));
			g.setColor(Color.WHITE);
			g.drawString(Double.toString(this.score), 400, 400);
			
			puntuaciones();		
		}
		
		if (0<=this.player.lives && this.player.intangible==false){
			g.drawImage(this.fondo,0,0,null);	//Normal render
			g.drawImage(this.player.sprite, this.player.xPos-this.player.r, this.player.yPos-this.player.r, 2*this.player.r , 2*this.player.r ,null);
			g.setFont(new Font("Monospaced", Font.BOLD, 18));
			g.setColor(Color.WHITE);
			g.drawString("Vidas: "+ this.player.lives, 1130, 500);
			g.drawString("Score: " + this.getScore(), 1130, 530);
			
			for (int i=0;i<bullets.size();i++) {
				 bullets.get(i).draw(g,this.player);
			}	
			
			for (int i=0;i<GamePanel.enemies.size();i++) {
				GamePanel.enemies.get(i).draw(g, GamePanel.enemies.get(i));
			}
			
			if (power.isEmpty()) {
				
			}else {
				g.drawImage(power.get(0).sprite,power.get(0).xPos,power.get(0).yPos,40,40,null);
			}
		}
		
		if (0<=this.player.lives && this.player.intangible==true){
			g.setColor(Color.BLACK); 			
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(this.player.sprite, this.player.xPos-this.player.r, this.player.yPos-this.player.r, 2*this.player.r , 2*this.player.r ,null);
			g.setColor(Color.WHITE);
			g.drawString("Moristes", 500, 400);
			
			this.fondo_vidas_restantes = new ImageIcon("vidas_restantes.jpg").getImage();
			g.drawImage(this.fondo_vidas_restantes, 0, 0, this.getWidth(), this.getHeight(), this);
			
			g.setFont(new Font("Monospaced", Font.BOLD, 200));
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(this.player.lives), 600, 500);
			acumMuerte++;
			acumMuerte++;
		}
		
	
		
	}
	
	public void draw() {
		Graphics g2=this.getGraphics();
			g2.drawImage(image,0 ,0,null);
	}

	public void keyPressed(KeyEvent e) { //Comenzar a mover al jugador
		if (this.player.lives<0 || this.player.intangible==true) {
			
		}else {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				this.player.up=true;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				this.player.down=true;
		}          
		
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				this.player.left=true;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				this.player.right=true;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			this.player.fire=true;
		}
		
		}
		
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			if (this.pause==false) {
				this.pauseSound.audio("pause.wav");
				this.pause=true;
			}else {
				this.pause=false;
				this.pauseSound.audio("pause.wav");
			}
		}
		
			
		}

	public void keyReleased(KeyEvent e) {	//Terminar el movimiento del jugador
		if (this.player.lives<0 || this.player.intangible==true) {
			
		}else {
			if (e.getKeyCode()==KeyEvent.VK_UP) {   
				this.player.velY=0;
				this.player.up=false;
				return;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				this.player.velY=0;
				this.player.down=false;
				return;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				this.player.velX=0;	
				this.player.left=false;
				return;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				this.player.velX=0;
				this.player.right=false;
				return;
			}
			
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				this.player.fire=false;
				
			}
			
			
		}
		
		
	}

	
	public void keyTyped(KeyEvent arg0) {

	}
}
