// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends Player {
	protected int 		xPos,
						yPos,
						hitboxSizeX,
						hitboxSizeY,
						hitsCounter,
						tiempoExplosion,
						enemyType,
						upOrDown,
						pointsValue;
	protected Random	rand;
	protected Hurtbox 	hbe;
	
	public Enemy() {
		super();
		this.sprite=new ImageIcon("enemy.png").getImage();
		this.hitsCounter=2;
		this.pointsValue=50;
		this.rand=new Random();
		int n=this.rand.nextInt(700);
		this.yPos=n;
		this.xPos=1280;
		this.hitboxSizeX=40;
		this.hitboxSizeY=40;
		this.hbe=new Hurtbox(this.xPos,this.yPos,40,40);
		this.intangible=false;
		this.left=true;
		n=this.rand.nextInt(3);
		this.enemyType=n;
		if (this.enemyType==2) {
			this.xPos=1200;
			n=this.rand.nextInt(700);
			this.yPos=n;
			this.xPos=1280;
			this.sprite=new ImageIcon("enemy3.png").getImage();
		}
		if (this.enemyType==1) {
			this.sprite=new ImageIcon("enemy2.png").getImage();
		}
		
	}
	
	
	public void enemyUpdate() {
		
		if (left==true ) {
			this.xPos+=-1;
		}
		if (right==true) {
			this.xPos+=1;
		}
		if (up==true) {
			this.yPos+=-1;
		}
		if (down==true) {
			this.yPos+=1;
		}
		
		if ( this.enemyType==0) {
			if (this.xPos<-40) {
				this.xPos=1280;
			}
				
		}
		
		if ( this.enemyType==1) {
			if (this.xPos<-40) {
				this.xPos=1280;
			}
			
			if (this.yPos<-100 || 800<this.yPos) {
				int n=this.rand.nextInt(700);
				this.yPos=n;
				this.xPos=1280;
				n=this.rand.nextInt(2);
				this.upOrDown=n;
			}
			
			
				if (this.upOrDown==0) {
					this.up=true;
					this.down=false;
				}else {
					this.down=true;
					this.up=false;
					
				}
				
		}
		
		if (this.enemyType==2) {
		
			if (this.xPos<800) {
				this.xPos=800;
				this.up=false;
				this.left=false;
				this.down=false;
				this.right=false;
				
				int n=rand.nextInt(5);
				switch(n) {
				case 0:
					this.up=true;
					break;
				case 1:
					this.up=true;
					this.right=true;
					break;
				case 2:
					this.right=true;
					break;
				case 3:
					this.down=true;
					this.right=true;
					break;
				case 4:
					this.down=true;
					break;
				}
			}
			
			if (this.yPos>600) {
				this.yPos=600;
				this.up=false;
				this.left=false;
				this.down=false;
				this.right=false;
				
				int n=rand.nextInt(5);
				switch(n) {
				case 0:
					this.left=true;
					break;
				case 1:
					this.up=true;
					this.left=true;
					break;
				case 2:
					this.up=true;
					break;
				case 3:
					this.up=true;
					this.right=true;
					break;
				case 4:
					this.right=true;
					break;
				
				}
				
			}
			
			if (this.yPos<100) {
				this.yPos=100;
				this.up=false;
				this.left=false;
				this.down=false;
				this.right=false;
				
				int n=rand.nextInt(5);
				switch(n) {
				case 0:
					this.left=true;
					break;
				case 1:
					this.down=true;
					this.left=true;
					break;
				case 2:
					this.down=true;
					break;
				case 3:
					this.down=true;
					this.right=true;
					break;
				case 4:
					this.right=true;
					break;
				
				}
				
			}
			
			if (1200<this.xPos ) {
				this.xPos=1200;
				this.up=false;
				this.down=false;
				this.right=false;
				this.left=true;
			}
		
			
		}
		
		int n=this.rand.nextInt(4000);
		if (n<=10) {
			if (this.bulletType=="basic" && this.intangible==false) {
				GamePanel.bullets.add(new Bullets(90,this.xPos-20,this.yPos+5, false));
				
			}
		}
			
			
		
			this.hbe=new Hurtbox(this.xPos,this.yPos,40,40);
			
		
	}
	
	public void draw(Graphics2D g,Enemy e) {
		g.setColor(Color.RED);
		g.drawImage(this.sprite, this.xPos,this.yPos, this.hitboxSizeX,this.hitboxSizeY,null);
	}
	
	public void dead() {
		this.hitsCounter++;
		}
		
		
	

}
