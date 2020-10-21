import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class PlayerShip extends Object implements Friendly {
	Image img1;
	ImageIcon t;
	private Game game;

	/**
	 * constructor
	 * @param g
	 */
	public PlayerShip(Game g) {
		setX(500);
		setY(300);
		setAngle(0);
		img1 = Toolkit.getDefaultToolkit().getImage("ship.png");
		t = new ImageIcon(img1);
		setImage(img1);
		setType("Ship");
		game = g;
		
	}

	/**
	 * draws player ship
	 * @param g
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform orig = g2d.getTransform();
		AffineTransform at = (AffineTransform) orig.clone();
		at.rotate(Math.toRadians(getAngle()), getX() + 32, getY() + 32);
		g2d.setTransform(at);
		g2d.drawImage(getImage(), at.getTranslateInstance(getX(),getY()), null);		
		g2d.setTransform(orig);
		
	}

	/**
	 * game tick
	 *
	 */
	public void tick() {
		setX(getX() + getxVel());
		setY(getY() - getyVel());
		setAngle(getAngle() + getdTheta());
		if (getX() <= 0 + 30) 
			setX(30);
		if (getX() >= 900 - 30)
			setX(880);
		if (getY() <= 0 + 30)
			setY(30);
		if (getY() >= 700 - 30)
			setY(670);
		game.getC().intersects(this, game.getO());
		
	}

	/**
	 * press shift to warp and teleport to a new location
	 */
	public void warp() {
		setX((Math.random()*850) + 25);
		setY((Math.random()*650) + 25);
	}

	/**
	 * hitbox
	 * @return
	 */
	@Override

	public Rectangle getBounds() {
		return (new Rectangle((int)getX(), (int)getY(), 64, 64));
	}
	public void die() {
		game.loseLife();
		setX(500);
		setY(300);
		game.getO().clear();
		setAngle(0);
		
	}			
	
}
