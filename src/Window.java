import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window() {
        this.setTitle("2048");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        this.setPreferredSize(new Dimension(1200, 1000));
        this.getContentPane().setBackground(Color.decode("#424242"));

        this.pack();

//        this.add(board);
    }
}
