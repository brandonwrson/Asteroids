import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Asteroid extends Object implements Enemy {
	Image i;
	ImageIcon t;
	private int temp;
	private int temp2;
	private double tempAngle;
	private final int LARGE = 1;
	private final int MEDIUM = 2;
	private final int SMALL = 3;
	private int size;
	Game game;

	/**
	 * creates random asteroids, constructor class
	 * @param g
	 * @param sizeOverload
	 * @param overloadX
	 * @param overloadY
	 * @param overloadxVel
	 * @param overloadyVel
	 * @param overLoad
	 */
	public Asteroid(Game g, int sizeOverload, double overloadX, double overloadY, 
			double overloadxVel, double overloadyVel, boolean overLoad) {
		if (!overLoad) {
			temp = (int)(Math.random() * 2) + 1; // variables to randomly initalize size
			temp2 = (int)(Math.random() * 4);
			if (temp2 == 0) {
				setX(-5);
				setY(900*Math.random());
				setxVel(temp*Math.cos(Math.toRadians(Math.random()*90)));
				setyVel(temp*Math.sin(Math.toRadians((Math.random()*180) - 90)));
				setSize(LARGE);

			} else if (temp2 == 1) {
				setX(750);
				setY(900*Math.random());
				setxVel(temp*Math.cos(Math.toRadians((Math.random()*90) + 90)));
				setyVel(temp*Math.sin(Math.toRadians((Math.random()*180) - 90)));
				setSize(MEDIUM);

			} else if (temp2 == 2) {
				setY(-50);
				setX(700*Math.random());
				setxVel(temp*Math.cos(Math.toRadians((Math.random()*180))));
				setyVel(temp*Math.sin(Math.toRadians((Math.random()*90))));
				setSize(SMALL);
			} else if (temp2 == 3) {
				setY(950);
				setX(700*Math.random());
				setxVel(temp*Math.cos(Math.toRadians((Math.random()*180))));
				setyVel(temp*Math.sin(Math.toRadians((Math.random()*90) - 90)));	
				setSize(MEDIUM);
			}
			if (size == LARGE) {
				setWidth(200);
				setHeight(125);
				setI("asteroidLarge.png");
			}
			if (size == MEDIUM) {
				setI("asteroidMedium.png");
				setWidth(100);
				setHeight(63);
			}
			if (size == SMALL) {
				setI("asteroidSmall.png");
				setWidth(50);
				setHeight(32);
			}
		} else {
			if (sizeOverload == LARGE) {
				size = sizeOverload;
				setWidth(200);
				setHeight(125);
				setI("asteroidLarge.png");
			}
			if (sizeOverload == MEDIUM) {
				size = sizeOverload;
				setI("asteroidMedium.png");
				setWidth(100);
				setHeight(63);
			}
			if (sizeOverload == SMALL) {
				size = sizeOverload;
				setI("asteroidSmall.png");
				setWidth(50);
				setHeight(32);
			}
			setX(overloadX);
			setY(overloadY);
			setxVel(overloadxVel);
			setyVel(overloadyVel);
		}

		t = new ImageIcon(i);
		setType("Asteroid");
		game = g;
	}

	/**
	 * draws the asteroids
	 * @param g
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform orig = g2d.getTransform();
		AffineTransform at = (AffineTransform) orig.clone();
		at.rotate(Math.toRadians(getAngle()), getX() + getWidth()/2, getY() + getHeight()/2);
		g2d.setTransform(at);
		g2d.drawImage(i, at.getTranslateInstance(getX(),getY()), null);		
		g2d.setTransform(orig);
	}

	/**
	 * game tick
	 */
	public void tick() {
		setX(getX() + getxVel());
		setY(getY() + getyVel());

	}

	/**
	 * controls the splitting mechanism, creates new smaller asteroids at the location of destroyed asteroids
	 */
	public void split() {
		
		if (getSize() < 3 && getSize() > 0) {
			game.getC().addObject(new Asteroid(game, getSize() + 1, getX(), 
					getY(), getxVel(), getyVel(), true));
			game.getC().addObject(new Asteroid(game, getSize() + 1, getX(), 
					getY(), getxVel() + 1, getyVel(), true));
		}		
		game.increaseScore(getSize() * 1000);

	}

	/**
	 * hitbox for asteroids
	 * @return
	 */
	@Override
	public Rectangle getBounds() {
		return (new Rectangle((int)getX(), (int)getY(), getWidth(), getHeight()));
	}
	public void setI(String t) {
		i = Toolkit.getDefaultToolkit().getImage(t);
	}
	public void setSize(int p) {
		size = p;
	}
	public int getSize() {
		return size;
	}


}
