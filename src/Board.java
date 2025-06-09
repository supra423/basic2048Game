import javax.swing.*;
import java.awt.*;


public class Board extends JPanel {
    final static int rows = 4;
    final static int  cols = 4;

    public Board() {

        this.setBackground(Color.decode("#a1a1a1"));
        this.setLayout(new GridLayout(4, 4));
        this.setPreferredSize(new Dimension(800, 800));

    }

    public void boardUpdate(int[][] matrix) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0) {
                    this.add(new Cell(matrix[i][j]));
                } else {
                    this.add(new Cell());
                }
            }
        }
    }
    public void gameUpdate(int[][] matrix) {

        this.removeAll();

        this.boardUpdate(matrix);
        this.revalidate();
        this.repaint();
    }
}