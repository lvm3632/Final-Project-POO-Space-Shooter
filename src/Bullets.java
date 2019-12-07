// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullets {

	double 		xPos,
				yPos,
				hitboxSizeX,
				hitboxSizeY,
				dx,
				dy,
				rad,
				acum,
				speed;
	boolean		allied;
	Image		sprite;
	
	public Bullets(double angle, int x, int y, boolean allied) { //Normales
		this.xPos=x;
		this.yPos=y;
		this.hitboxSizeX=20;
		this.hitboxSizeY=20;
		rad=Math.toRadians(angle);
		speed=2;
		dx=-1*Math.sin(rad)*speed;
		dy=-1*Math.cos(rad)*speed;
		this.acum=0;
		this.allied=allied;
		if (this.allied==true) {
			this.sprite=new ImageIcon("BalaAzul.png").getImage();
			this.yPos=this.yPos-10;
		}else {
			this.sprite=new ImageIcon("BalaRoja.png").getImage();
			
		}
		
	}
	
	
	
	
	public boolean update() {
		this.xPos+=dx;
		this.yPos+=dy;
		
		if ((this.xPos<-this.hitboxSizeX)|| (GamePanel.WIDTH+this.hitboxSizeX<this.xPos) 
			|| this.yPos<this.hitboxSizeY || (GamePanel.HEIGHT+this.hitboxSizeY<this.yPos)) {
			return true;
		}
		
		return false;
	}
	
	public void draw(Graphics2D g,Player player) {
			g.setColor(Color.RED);
			g.drawImage(this.sprite,(int)(this.xPos),(int)(this.yPos),(int)(this.hitboxSizeX),(int)(this.hitboxSizeY), null);		
		
		
	}
	
}
