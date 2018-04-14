import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship ship;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	long enemyTimer = 0;
	int enemySpawnTime = 1;

	ObjectManager(Rocketship ship) {
		this.ship = ship;

	}

	void update() {
		ship.update();
		for (Projectile p : projectiles) {
			System.out.println(p);
			p.update();
		}
		for (Alien a : aliens) {
			System.out.println(a);
			a.update();

		}
	}

	void draw(Graphics g) {
		for (Projectile p : projectiles) {
			System.out.println(p);
			p.draw(g);
		}
		for (Alien a : aliens) {
			System.out.println(a);
			a.draw(g);
		}
	}

	void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	void addAlien(Alien a) {
		aliens.add(a);
	}

	void checkCollision() {
		for (Alien a : aliens) {
			if (ship.collisionBox.intersects(a.collisionBox)) {
				ship.isAlive = false;

			}

			for (Projectile p : projectiles) {
				if (a.collisionBox.intersects(p.collisionBox)) {
					a.isAlive = false;
				}
			}
		}
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Alien(new Random().nextInt(500), 0, 50, 50));

			enemyTimer = System.currentTimeMillis();
		}
	}

	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).isAlive==false) {
			aliens.remove(aliens.get(i));
		}
	}
}
}