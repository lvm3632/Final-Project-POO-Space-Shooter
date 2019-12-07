// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Powerup {
	protected int 		xPos,
						yPos,
						hitboxSizeX,
						hitboxSizeY;
	protected Image 	sprite;
	protected Hurtbox	hbm;
	
	public Powerup() {
		this.sprite=new ImageIcon("player.png").getImage();
		Random rand=new Random();
		int n=rand.nextInt(700);
		this.yPos=n;
		this.xPos=1280;
		this.hitboxSizeX=40;
		this.hitboxSizeY=40;
		
	}
	
	public void powerUpdate() {
		this.hbm=new Hurtbox(this.xPos-20,this.yPos-20,40,40);
		this.xPos--;

		
	}
	
	public boolean pickup(Hurtbox a) {
		if (this.hbm.hbx.intersects(a.hbx)){
			return true;
		}else {
			return false;
		}
	}
}
