public class Rocket implements MoveableObject {
    private int x, y;
    private static final int SPEED = 10;
    private boolean visible;

    public Rocket() {
        x = 50;
        y = 200;
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
