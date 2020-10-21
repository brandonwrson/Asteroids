import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Lives {
	int x;
	int y;
	Image img;

	/**
	 * constructor
	 * @param x
	 * @param y
	 */
	public Lives (int x, int y) {
		this.x = x;
		this.y = y;
		img = Toolkit.getDefaultToolkit().getImage("heart2.png");
	}

	/**
	 * draws image
	 * @param g
	 */
	public void render(Graphics g) {
		ImageIcon t = new ImageIcon(img);
		g.drawImage(img, x, y, 30, 30, null);
		 
	}
	public void destroy() {
		img = null;
	}
	
}
