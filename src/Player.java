// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Player {
	protected int 		xPos,
						yPos,
						velX,
						velY,
						r,
						score,
						hits,
						lives;
	protected Image 	sprite;
	protected String	bulletType;
	protected boolean	left,
						right,
						up,
						down,
						intangible,
						fire;
	protected long		fireTimer,
						fireDelay;
	protected Hurtbox 	hbp;
	protected Sounds 	explosion,
						bala,
						hit;
	
	
	public Player() {
		this.xPos=200;
		this.yPos=200;
		this.r=25;
		this.bulletType="basic";
		this.sprite=new ImageIcon("player.png").getImage();
		this.lives=3;
		this.left=false;
		this.right=false;
		this.up=false;
		this.down=false;
		this.fire=false;
		this.fireTimer=System.nanoTime();
		this.fireDelay=200;
		this.intangible=false;
		this.hits=0;
		this.explosion = new Sounds();
		this.bala = new Sounds();
		this.hit = new Sounds();
		
	}
	
	public void update() {
		
		if (this.hits==1) {
			this.bulletType="advanced";
		}
		
		if (left==true && right==false) {
			this.xPos+=-1;
		}
		if (right==true && left==false) {
			this.xPos+=1;
		}
		if (up==true && down==false) {
			this.yPos+=-1;
		}
		if (down==true && up==false) {
			this.yPos+=1;
		}
		
		
		if (this.xPos<this.r) {
			this.xPos=this.r;
		}
		if (this.yPos<this.r) {
			this.yPos=this.r;
		}
		if (GamePanel.WIDTH-this.r<this.xPos) {
			this.xPos=GamePanel.WIDTH-this.r;
		}
		if (GamePanel.HEIGHT-this.r<this.yPos) {
			this.yPos=GamePanel.HEIGHT-this.r;
		}
	
		
		if (fire) {
			long timer=(System.nanoTime()-fireTimer)/1300000;
			if (fireDelay<timer) {
				if (this.bulletType=="basic") {
					this.bala.audio("bala.wav");
					GamePanel.bullets.add(new Bullets(270,this.xPos+this.r,this.yPos, true));
					fireTimer=System.nanoTime();
				}
				
				if (this.bulletType=="advanced") {
					this.bala.audio("bala.wav");
					GamePanel.bullets.add(new Bullets(270,this.xPos+this.r,this.yPos, true));
					GamePanel.bullets.add(new Bullets(300,this.xPos+this.r,this.yPos, true));
					GamePanel.bullets.add(new Bullets(240,this.xPos+this.r,this.yPos, true));
					fireTimer=System.nanoTime();
				} 
				
			}
		}
		
		hbp=new Hurtbox(this.xPos-this.r,this.yPos-this.r,2*this.r,2*this.r);
		
	}
	

	
	
	
	public void setSprite(String a) {
		if (a=="Normal") {
			this.sprite=new ImageIcon("player.png").getImage();
		}
		if (a=="Left") {
			
		} 
		if (a=="Right") {
			
		}
		
		if (a=="PowerUp") {
			this.sprite=new ImageIcon("player2.png").getImage();
		}
	}
	
	public void destroyed(Hurtbox a) {
		if (this.hbp.hbx.intersects(a.hbx) && this.intangible==false && this.hits==0) {
			this.lives--;
			this.intangible=true;
			this.fire=false;
			this.down=false;
			this.up=false;
			this.right=false;
			this.left=false;
			GamePanel.acumEnemigos=0;
			GamePanel.bullets.clear();
			GamePanel.enemies.clear();
			this.sprite=new ImageIcon("Explosion.gif").getImage();
			this.explosion.audio("explosion.wav");
		}
		
		if (this.hbp.hbx.intersects(a.hbx) && this.intangible==false && this.hits==1){
			this.hits=0;
			this.setSprite("Normal");
			this.bulletType="basic";
			this.hit.audio("hit.wav");
		}
	}

	
	
}
