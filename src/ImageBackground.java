// Proyecto Realizado por: Gerardo Reyes (01635286) y Michel Lujano (A01636172) -Shoot 'Em Up-.

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;


public class ImageBackground extends Canvas{
	
	Image img;
	
	public ImageBackground(String imagen) {
		super();
		img = Toolkit.getDefaultToolkit().getImage(imagen);
	}
	
	public void paint(Graphics g) {
		
		int width = getSize().width,
			height = getSize().height;
	
		
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, 0,0, width, height, this);
		
	}
	
	
}