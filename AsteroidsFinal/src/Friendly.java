import java.awt.Graphics;

/**
 * Interface for friendly entity - player ship
 */
public interface Friendly {
	public void tick();
	public void render(Graphics g);
}
