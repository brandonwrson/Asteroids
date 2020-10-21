import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * reads user inputs
 */
public class GameListener extends KeyAdapter {
	Game g;
	public GameListener(Game g) {
		this.g = g;		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		g.keyPressed(e);	
	}
	public void keyReleased(KeyEvent e) {
		g.keyReleased(e);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
