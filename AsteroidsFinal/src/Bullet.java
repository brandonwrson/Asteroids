import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.awt.geom.*;

import javax.swing.*;

public class Bullet extends Object implements Friendly {
	Game g;

	/**
	 * constructor class
	 * @param x x pos
	 * @param y y position
	 * @param xVel velocity of bullet in x direction
	 * @param yVel velocity of bullet in y direction
	 * @param game game object
	 */
	public Bullet(double x, double y, double xVel, double yVel, Game game) {
		setX(x);
		setY(y);
		setxVel(xVel);
		setyVel(yVel);
		setType("Bullet");
		g = game;

	}

	/**
	 * draws the bullet object
	 * @param g
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		Ellipse2D ellipse = new Ellipse2D.Double(getX(), getY(), 10, 10);
		
		g2d.draw(ellipse);
		g2d.setColor(Color.GREEN);
		g2d.fill(ellipse);
		
	}

	/**
	 * game tick
	 */
	public void tick() {
		setX(getX() + getxVel());
		setY(getY() - getyVel());
		g.getC().intersects(this, g.getO());
	
	}

	/**
	 * draws a rectangle around bullet as hitbox
	 * @return
	 */
	@Override
	public Rectangle getBounds() {
		return (new Rectangle((int)getX(), (int)getY(), 10, 10));
	}
} 
