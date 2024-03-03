public class Rocket implements MoveableObject {
    private int x, y;
    private static final int SPEED = 5;

    public Rocket() {
        x = 50;
        y = 200;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveLeft() {
        x -= SPEED;
    }
    public void moveUp() {
        y -= SPEED;
    }
    public void moveDown() {
        y += SPEED;
    }
    public void moveRight() {
        x += SPEED;
    }

    public void move() {

    }
}
