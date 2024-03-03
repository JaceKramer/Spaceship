import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MousePanel extends JPanel{
    private final int PANEL_WIDTH = 500,
                PANEL_HEIGHT = 500,
                RIGHT = PANEL_WIDTH,
                LEFT = 0,
                TOP = 0,
                BOTTOM = PANEL_HEIGHT,
                MARGIN = 10;
    public boolean mousePressed = false;


    public MousePanel() {

        this.addMouseListener(new LaserListener());
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.PINK);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mousePressed) {
            int startLaserY = PANEL_HEIGHT / 2,
                    startLaserX = LEFT + MARGIN,
                    endLaserY = PANEL_HEIGHT / 2,
                    endLaserX = RIGHT - MARGIN;
            g.setColor(Color.BLACK);
            g.drawLine(startLaserX, startLaserY - 1, endLaserX, endLaserY - 1);
            g.drawLine(startLaserX, startLaserY + 1, endLaserX, endLaserY + 1);
            g.setColor(Color.orange);
            g.drawLine(startLaserX, startLaserY, endLaserX, endLaserY);
        }
    }

    private class LaserListener implements MouseListener {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
                repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
                repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
    }

    private class NotListening implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }
}
