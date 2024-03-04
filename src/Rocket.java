public class Rocket implements MoveableObject {
    private int x, y;
    private static final int SPEED = 10;
    private boolean visible;
    private int swit;

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


    public void move(int swit) {
            switch(swit) {
                case 1:
                    x -= SPEED;
                    if (x < 0) {x = 0;}
                    break;
                case 2:
                    x += SPEED;
                    if (x + 100 > 1200) {x = 1100;}
                    break;
                case 3:
                    y -= SPEED;
                    if (y < 0) {y = 0;}
                    break;
                case 4:
                    y += SPEED;
                    if (y + 100 > 750) {y = 650;}
                    break;
                case 5:
                    break;
            }
    }
}
