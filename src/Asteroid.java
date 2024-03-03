import java.util.Random;

public class Asteroid implements MoveableObject {
    private int x, y;
    private AsteroidSize size;

    public Asteroid(AsteroidSize size) {
        this.size = size;
        Random rand = new Random();
        x = 1200;  // Initial position on the right side
        y = rand.nextInt(750);  // Random vertical position
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        x -= 3;  // Move towards the left
    }

    public AsteroidSize getSize() {
        return size;
    }
}
