import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObjects {

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	void update() {

	}
	void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	        g.fillRect(x, y, width, height);
	        System.out.println("hi");
	}
}
