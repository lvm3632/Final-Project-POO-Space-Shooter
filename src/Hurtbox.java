// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Rectangle;

public class Hurtbox {
	Rectangle hbx;
	
	public Hurtbox(int xPos, int yPos, int hitboxSizeX, int hitboxSizeY) {
		this.hbx=new Rectangle(xPos,yPos,hitboxSizeX,hitboxSizeY);
		
	}
	
}
