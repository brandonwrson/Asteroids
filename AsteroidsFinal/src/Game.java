import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;


public class Game extends JFrame implements Runnable {
	public static int gameWidth = 700;
	public static int gameHeight = 900;
	public static int points = 0;
	public int score = 0;
	public int height = this.getSize().height;
	public int width = this.getSize().width;
	private Thread thread;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_RGB);
	private PlayerShip playerShip;
	private Controller c;
	private boolean isShooting = false;
	private boolean isWarping = false;
	private int lives = 3;
	private LinkedList<Object> o;

	/**
	 * constructor class, initalizes all objects
	 */
	public Game() {
		this.setSize(gameHeight, gameWidth);
		this.setTitle("Java Asteroids");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.centerWindow();
		playerShip = new PlayerShip(this);
		this.setFocusable(true);
		this.setVisible(true);
		c = new Controller();
		addKeyListener(new GameListener(this));
		o = c.getO();

	}

	/**
	 * starts the game tick
	 */
	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * controls game end scenario
	 */
	private synchronized void stop() {
		System.out.println("GAMEOVER - FINAL SCORE: " + score );
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.exit(1);

	}

	/**
	 * main game loop
	 */
	public void run() {
		long last = System.nanoTime();
		final double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - last) / ns;
			last = now;
			if (delta >= 1) {
				tick();
				delta--;
			}
			render();
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;


				c.addObject(new Asteroid(this, 0, 0.0, 0.0, 0.0, 0.0, false));
			}
			if (lives <= 0) {
				c.clear();
				render();
				running = false;
			}
		}

		stop();
	}

	/**
	 * game tick
	 */
	private void tick() {
		c.tick();
		playerShip.tick();
	}

	/**
	 * draws game
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		playerShip.render(g);
		c.render(g);

		g.dispose();
		bs.show();
	}

	/**
	 * main
	 * @param args
	 */

	public static void main(String[] args) {
		Game g = new Game();
		g.start();

	}


	/**
	 * listens for user inputs
	 * @param e
	 */

	public void keyPressed (KeyEvent e) {
		int p = e.getKeyCode();
		if (p == KeyEvent.VK_SPACE && !isShooting) {
			isShooting = true;
			fire();
		}
		if (p == KeyEvent.VK_W) {
			moveUp(true);
		}
		if (p == KeyEvent.VK_D) {
			rotateRight(true);
		}
		if (p == KeyEvent.VK_A) {
			rotateLeft(true);
		}
		if (p == KeyEvent.VK_SHIFT) {
			hyperShift();
			isWarping = true;
		}
	}

	/**
	 * listens for user inputs
	 * @param e
	 */
	public void keyReleased (KeyEvent e) {
		int p = e.getKeyCode();
		if (p == KeyEvent.VK_W) {
			moveUp(false);
		}
		if (p == KeyEvent.VK_A) {
			rotateLeft(false);
		}
		if (p == KeyEvent.VK_D) {
			rotateRight(false);
		}
		if (p == KeyEvent.VK_SPACE) {
			isShooting = false;
		}
		if (p == KeyEvent.VK_SHIFT) {
			isShooting = false;
		}
	}

	/**
	 * methods to control key movements
	 */

	public void fire() {
		c.addObject(new Bullet(playerShip.getX() + playerShip.getWidth()/2,
				playerShip.getY() - playerShip.getHeight()/2,
				8*Math.sin(Math.toRadians(playerShip.getAngle())),
				8*Math.cos(Math.toRadians(playerShip.getAngle())), this));
	}

	public void moveUp(boolean t) {
		if (t) {
			playerShip.setxVel(3*Math.sin(Math.toRadians(playerShip.getAngle())));
			playerShip.setyVel(3*Math.cos(Math.toRadians(playerShip.getAngle())));
			return;
		}
		playerShip.setxVel(0);
		playerShip.setyVel(0);

	}
	public void rotateRight(boolean t) {
		if (t) {
			playerShip.setdTheta(4);
			return;
		}
		playerShip.setdTheta(0);

	}
	public void rotateLeft(boolean t) {
		if (t) {
			playerShip.setdTheta(-4);
			return;
		}
		playerShip.setdTheta(0);
	}
	public void hyperShift() {
		playerShip.warp();
	}

	/**
	 * draws the game window
	 */

	private void centerWindow() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = this.getSize().width;
		int height = this.getSize().height;
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		this.setLocation(x, y);
	}


	//Getters/Setters
	public LinkedList<Object> getO() {
		return o;
	}
	public Controller getC() {
		return c;
	}
	public void setC(Controller c) {
		this.c = c;
	}
	public void loseLife() {
		lives--;
		c.die();
	}
	public void increaseScore(int t) {
		System.out.println("+ " + t);
		score += t;
	}
	public int getLives() {
		return lives;
	}


}
