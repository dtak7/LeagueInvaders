import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	 Font titleFont;
	 Rocketship ship= new Rocketship(250,700,50,50);

	public GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont= new Font("Arial",Font.PLAIN,48);
	}

	void startGame() {
		timer.start();
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 500, 800);   
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("League Invaders", 100, 100);
		g.drawString("Press ENTER to Begin", 10, 200);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 800);   
		ship.draw(g);	
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 500, 800);  
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Game OVER", 100, 100);
		g.drawString("Press ENTER to Go Again", 10, 200);
	}

	void updateMenuState() {
		// currentState=0;
	}

	void updateGameState() {
	
	}

	void updateEndState() {
		// currentState=2;
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			currentState++;
			if(currentState > END_STATE){
                currentState = MENU_STATE;
        }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
