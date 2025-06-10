import javax.swing.*;
import java.awt.*;
// Only for the window
public class Window extends JFrame {
    public Window() {
        this.setTitle("2048");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setResizable(true);
        this.setPreferredSize(new Dimension(1400, 1000));
        this.getContentPane().setBackground(Color.decode("#424242"));


        this.pack();
    }

    public void add(GameEngine newGame) {

    }
}
