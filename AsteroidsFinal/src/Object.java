import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Object {
	/**
	 * generic object class to contain all game entities
	 */
	private double x;
	private double y;
	private int width;
	private int height;
	private double angle;
	private double xVel;
	private double yVel;
	private double dTheta;
	private Image img1;
	private String type;
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public void setImage(Image img1) {
		this.img1 = img1;
	}
	public Image getImage() {
		return img1;
	}
	public double getxVel() {
		return xVel;
	}
	public void setxVel(double xVel) {
		this.xVel = xVel;
	}
	public double getyVel() {
		return yVel;
	}
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void changeAngle(double t) {

		this.angle += t;
	}
	public double getdTheta() {
		return dTheta;
	}
	public void setdTheta(double dTheta) {
		this.dTheta = dTheta;
	}
	public abstract Rectangle getBounds();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
