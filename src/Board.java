import javax.swing.*;
import java.awt.*;


public class Board extends JPanel {
    final static int rows = 4;
    final static int  col = 4;

    public Board() {

        this.setBackground(Color.decode("#a1a1a1"));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a"), 10));
        this.setLayout(new GridLayout(4, 4));
        this.setPreferredSize(new Dimension(800, 800));

    }
    public void displayCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                this.add(new Cell());
            }
        }
    }
}