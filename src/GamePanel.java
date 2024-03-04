import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private Rocket rocket;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Point> lasers;
    private Timer timer;
    private int score;
    private int highScore;
    private boolean gameOver;

    public GamePanel() {
        rocket = new Rocket();
        asteroids = new ArrayList<>();
        lasers = new ArrayList<>();
        score = 0;
        highScore = readHighScore();
        gameOver = false;

        // Add keyboard listener for moving the rocket based on what key is pressed
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!gameOver) {
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
                    } else if (key == KeyEvent.VK_W) {
                        rocket.moveUp();
                    } else if (key == KeyEvent.VK_A) {
                        rocket.moveLeft();
                    } else if (key == KeyEvent.VK_S) {
                        rocket.moveDown();
                    } else if (key == KeyEvent.VK_D) {
                        rocket.moveRight();
                    }
                    repaint();
                }
            }
        });
        setFocusable(true);

       this.addMouseListener(new LaserListener());

        // Add mouse listener for shooting laser
        /*addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!gameOver){
                    lasers.add(new Point(rocket.getX() + 50, rocket.getY() + 25));
                    repaint();
                }
            }
        });*/

        // Set up the timer with a lambda function to run the game
        timer = new Timer(20, (ActionEvent e) -> {
            if (!gameOver) {
                rocket.move();
                for (Asteroid asteroid : asteroids) {
                    asteroid.move();
                }
                for (Point laser : lasers) {
                    laser.x += 15;
                }
                spawnAsteroids();
                checkCollisions();
                repaint();
            }
        });
        timer.start();

        // Generate 5 initial asteroids
        generateAsteroids();
    }

    private class LaserListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            if (!gameOver){
                lasers.add(new Point(rocket.getX() + 50, rocket.getY() + 25));
                repaint();
            }
        }
        public void mousePressed(MouseEvent e) {}public void mouseReleased(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}public void mouseExited(MouseEvent e) {}
    }

    // Generate 5 initial asteroids
    private void generateAsteroids() {
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            AsteroidSize size = AsteroidSize.values()[rand.nextInt(AsteroidSize.values().length)];
            asteroids.add(new Asteroid(size));
        }
    }

    // Generates asteroids in groups of 1 or 2
    private void spawnAsteroids(){
        Random rand = new Random();
        if (rand.nextDouble() < 0.02) {
            AsteroidSize size = AsteroidSize.values()[rand.nextInt(AsteroidSize.values().length)];
            asteroids.add(new Asteroid(size));
        }

        if (rand.nextDouble() < 0.01) {
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
                        laser.y >= asteroid.getY() && laser.y <= asteroid.getY() + 75) {
                    lasers.remove(i);
                    asteroids.remove(j);
                    score++;
                    i--;
                    break;
                }
            }
        }

        // Checks for collision between the rocket and asteroids
        for (Asteroid asteroid : asteroids) {
            if (rocket.getX() < asteroid.getX() + 150 &&
                    rocket.getX() + 100 > asteroid.getX() &&
                    rocket.getY() < asteroid.getY() + 75 &&
                    rocket.getY() + 100 > asteroid.getY()) {
                rocket.setVisible(false);
                gameOver = true;
                updateHighScore();
                timer.stop();  // Stops the game when the rocket is hit
            }
        }
    }

    // Updates high score
    private void updateHighScore() {
        if (score > highScore) {
            highScore = score;
            writeHighScore();
        }
    }

    // Checks current high score
    private int readHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;  // Default to 0 if there's an issue reading the file
    }

    // Writes new high score
    private void writeHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"))) {
            writer.write(Integer.toString(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Resize all images
        String rockot = "spaceship.png";
        ImageIcon rockIt = new ImageIcon(rockot);
        Image rockItImage = rockIt.getImage();
        Image rock = rockItImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon rocky = new ImageIcon(rock);

        String sml = "meteor.PNG";
        ImageIcon smal = new ImageIcon(sml);
        Image smll = smal.getImage();
        Image small = smll.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        ImageIcon smallmeteor = new ImageIcon(small);

        String mid = "meteor.PNG";
        ImageIcon medi = new ImageIcon(mid);
        Image medum = medi.getImage();
        Image medium = medum.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        ImageIcon mediummeteor = new ImageIcon(medium);

        String lrg = "meteor.PNG";
        ImageIcon larg = new ImageIcon(lrg);
        Image lrge = larg.getImage();
        Image large = lrge.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon largemeteor = new ImageIcon(large);

        // Draw rocket
        if (rocket.isVisible()) {
            g.drawImage(rocky.getImage(), rocket.getX(), rocket.getY(), null);
        }

        // Draw asteroids
        for (Asteroid asteroid : asteroids) {
            Image imagePath;
            switch (asteroid.getSize()) {
                case SMALL:
                    imagePath = smallmeteor.getImage();
                    break;
                case MEDIUM:
                    imagePath = mediummeteor.getImage();
                    break;
                case LARGE:
                default:
                    imagePath = largemeteor.getImage();
                    break;
            }
            g.drawImage(imagePath, asteroid.getX(), asteroid.getY(), null);
        }

        // Draw lasers
        for (Point laser : lasers) {
            g.setColor(Color.RED);
            g.fillRect(laser.x, laser.y, 10, 5);
        }

        // Displays text when game is finished
        if (gameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Game Over", getWidth() / 2 - 60, getHeight() / 2 - 20);
            g.drawString("  Final Score: " + score, getWidth() / 2 - 90, getHeight() / 2 + 20);
            g.drawString("  High Score: " + highScore, getWidth() / 2 - 90, getHeight() / 2 + 60);
        }
    }
}

