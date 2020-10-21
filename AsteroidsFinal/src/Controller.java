import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	private LinkedList<Object> o = new LinkedList<Object>();
	private LinkedList<Friendly> f = new LinkedList<Friendly>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();
	private LinkedList<Lives> l  = new LinkedList<Lives>();
	Object temp;
	Lives tempLife;

	/**
	 * class constructor
	 */
	public Controller() {
		for (int i = 0; i < 3; i ++) {
			l.add(new Lives(10 + 30*i, 40));
		}
	}

	/**
	 * game tick
	 */
	public void tick() {
		for (int i = 0; i < o.size(); i++) {
			temp = o.get(i);
			temp.tick();

			if(temp.getY() > 950 || temp.getY() < -50)
				removeObject(temp);
			if (temp.getX() < -50 || temp.getX() > 950)
				removeObject(temp);
		}		
	}

	/**
	 * method for drawing object
	 * @param g
	 */
	public void render(Graphics g) {
		for (int i = 0; i < o.size(); i++) {
			temp = o.get(i);
			temp.render(g);
		}
		for (int i = 0; i < l.size(); i++) {
			tempLife = l.get(i);
			tempLife.render(g);
		}
	}
	public void addObject(Object t) {
		o.add(t);
	}
	public void removeObject(Object t) {
		o.remove(t);
	}

	/**
	 * checks if an object o (bullet) intersects with any of the set of linked objects (asteroids)
	 * @param o
	 * @param t
	 */
	public void intersects(Object o, LinkedList<Object> t) {
		for (int i = 0; i < t.size(); i++) {
			if (o.getType().equals("Bullet") && t.get(i).getType().equals("Asteroid") ||
					o.getType().equals("Asteroid") && t.get(i).getType().equals("Bullet")) {
				if (o.getBounds().intersects(t.get(i).getBounds())) {		
					removeObject(o);
					if (i != t.size()) {
						if (t.get(i).getType().equals("Asteroid"))
							((Asteroid) t.get(i)).split();
						removeObject(t.get(i));
					}
					
					return;
				}
			}
			if (o.getType().equals("Ship") && t.get(i).getType().equals("Asteroid")) {
				if (o.getBounds().intersects(t.get(i).getBounds())) {
					removeObject(t.get(i));
					((PlayerShip) o).die();
				}
			}
				
		}
	}

	public LinkedList<Object> getO() {
		return o;
	}

	/**
	 * clears all asteroids off the screen
	 */
	public void clear() {
		o = new LinkedList<Object>();
	}

	/**
	 * removes the asteroid hit
	 */
	public void die() {
		l.remove(l.getLast());
	}
}
