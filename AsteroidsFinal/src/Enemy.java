import java.awt.Graphics;

/**
 * Interface for enemy - asteroid entities
 */
public interface Enemy {
	public void tick();
	public void render(Graphics g);

}
