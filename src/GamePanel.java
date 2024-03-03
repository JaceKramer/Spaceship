import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private Rocket rocket;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Point> lasers;
    private Timer timer;

    public GamePanel() {
        rocket = new Rocket();
        asteroids = new ArrayList<>();
        lasers = new ArrayList<>();

        // Add keyboard listener
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    rocket.moveLeft();
                } else if (key == KeyEvent.VK_RIGHT) {
                    rocket.moveRight();
                } else if (key == KeyEvent.VK_UP) {
                    rocket.moveUp();
                } else if (key == KeyEvent.VK_DOWN) {
                    rocket.moveDown();
                } else if (key == KeyEvent.VK_SPACE) {
                    lasers.add(new Point(rocket.getX() + 50, rocket.getY() + 25));
                }
                repaint();
            }
        });
        setFocusable(true);

        // Add mouse listener for shooting laser
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                lasers.add(new Point(rocket.getX() + 50, rocket.getY() + 25));
                repaint();
            }
        });

        // Set up the timer with a lambda function
        timer = new Timer(20, (ActionEvent e) -> {
            rocket.move();
            for (Asteroid asteroid : asteroids) {
                asteroid.move();
            }
            for (Point laser : lasers) {
                laser.x += 15;  // Move laser to the right
            }
            checkCollisions();
            repaint();
        });
        timer.start();

        // Generate initial asteroids
        generateAsteroids();
    }

    private void generateAsteroids() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            AsteroidSize size = AsteroidSize.values()[rand.nextInt(AsteroidSize.values().length)];
            asteroids.add(new Asteroid(size));
        }
    }

    private void checkCollisions() {
        // Check for collisions between lasers and asteroids
        for (int i = 0; i < lasers.size(); i++) {
            Point laser = lasers.get(i);
            for (int j = 0; j < asteroids.size(); j++) {
                Asteroid asteroid = asteroids.get(j);
                if (laser.x >= asteroid.getX() && laser.x <= asteroid.getX() + 50 &&
                        laser.y >= asteroid.getY() && laser.y <= asteroid.getY() + 50) {
                    lasers.remove(i);
                    asteroids.remove(j);
                    i--;
                    break;
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw rocket
        String rockot = "khaledski.png";
        ImageIcon rockIt = new ImageIcon(rockot);
        Image rockItImage = rockIt.getImage();
        Image rock = rockItImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon rocky = new ImageIcon(rock);

        String yu = "shark.PNG";
        ImageIcon kari = new ImageIcon(yu);
        Image yukar = kari.getImage();
        Image bae = yukar.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon yukari = new ImageIcon(bae);

        String yip = "shark.PNG";
        ImageIcon ee = new ImageIcon(yip);
        Image yuee = ee.getImage();
        Image yipee = yuee.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        ImageIcon yukayipe = new ImageIcon(yipee);

        String win = "shark.PNG";
        ImageIcon ter = new ImageIcon(win);
        Image snow = ter.getImage();
        Image wnter = snow.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon winter = new ImageIcon(wnter);


        g.drawImage(rocky.getImage(), rocket.getX(), rocket.getY(), null);

        // Draw asteroids
        for (Asteroid asteroid : asteroids) {
            Image imagePath;
            switch (asteroid.getSize()) {
                case SMALL:
                    imagePath = yukari.getImage();
                    break;
                case MEDIUM:
                    imagePath = yukayipe.getImage();
                    break;
                case LARGE:
                default:
                    imagePath = winter.getImage();
                    break;
            }
            g.drawImage(imagePath, asteroid.getX(), asteroid.getY(), null);
        }

        // Draw lasers
        for (Point laser : lasers) {
            g.setColor(Color.RED);
            g.fillRect(laser.x, laser.y, 10, 5);
        }
    }
}
