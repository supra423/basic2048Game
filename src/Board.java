import javax.swing.*;
import java.awt.*;


public class Board extends JPanel {
    Board() {

        this.setBackground(Color.decode("#a1a1a1"));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a"), 10));
        this.setLayout(new GridLayout(4, 4));
        this.setPreferredSize(new Dimension(800, 800));

    }
}