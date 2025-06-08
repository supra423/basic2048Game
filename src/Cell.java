import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    Cell () {
        this.setBackground(Color.decode("#636262"));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a"), 10));
        this.setPreferredSize(new Dimension(200, 200));
    }
}
