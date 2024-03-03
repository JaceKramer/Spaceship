import java.util.Random;

public class Asteroid implements MoveableObject {
    private int x, y;
    private AsteroidSize size;
    private boolean visible;

    public Asteroid(AsteroidSize size) {
        this.size = size;
        Random rand = new Random();
        x = 1200;  // Initial position on the right side
        y = rand.nextInt(650);  // Random vertical position
        visible = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }


    public void move() {
        if(size == AsteroidSize.SMALL)
        {
            x -= 10;
        }
        else if (size == AsteroidSize.MEDIUM) {
            x -= 7;
        }
        else if (size == AsteroidSize.LARGE) {
            x -= 3;  // Move towards the left
        }
    }

    public AsteroidSize getSize() {
        return size;
    }
}
