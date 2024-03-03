import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel displayPanel = new JPanel();*/
        //displayPanel.add(new TeamPanel());
        //displayPanel.add(new ShapeIconPanel());
        /*displayPanel.add(new KeyBoardPanel());
        displayPanel.add(new MousePanel());

        testFrame.getContentPane().add(displayPanel);
        testFrame.pack();
        testFrame.setVisible(true);*/

        JFrame frame = new JFrame("Space Game");
        GamePanel gamePanel = new GamePanel();
        gamePanel.setBackground(Color.cyan);
        frame.add(gamePanel);
        frame.setSize(1200, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}