import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	 public static BufferedImage alienImg;
     public static BufferedImage rocketImg;
     public static BufferedImage bulletImg;
     public static BufferedImage spaceImg;
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Rocketship ship = new Rocketship(250, 700, 50, 50);
	ObjectManager manager = new ObjectManager(ship);

	public GamePanel() {
		try {

            alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
            rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
            bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
            spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
    } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

    }
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
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
	     g.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.frameWidth, LeagueInvaders.frameHeight, null);
		ship.draw(g);
		manager.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 500, 800);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER", 100, 100);
		g.drawString("Press ENTER", 100, 200);
		g.drawString("to start again", 100, 300);
		g.drawString("score " +Integer.toString(manager.score), 100, 400);
	
	}

	void updateMenuState() {
		// currentState=0;
	}

	
	void updateGameState() {
		manager.update();
		manager.manageEnemies();
		manager.checkCollision();
		if (!ship.isAlive) {
			currentState = END_STATE;
		} else {
			manager.purgeObjects();
		}
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
		
		  if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			ship.x += 10;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			ship.x -= 10;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			ship.y -= 10;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			ship.y += 10;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			manager.addProjectile(new Projectile(ship.x + 20, ship.y + 20, 10, 10));
		}
		else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState++;
			if (currentState > END_STATE) {
				ship = new Rocketship(250, 700, 50, 50);
				manager = new ObjectManager(ship);
				currentState = MENU_STATE;	
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
