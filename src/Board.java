import javax.swing.*;
import java.awt.*;


public class Board extends JPanel {
    final private static int boardRows = GameEngine.matrixRows;
    final private static int boardCols = GameEngine.matrixCols;
    final public static int boardHeight = 800;
    final public static int boardWidth = 800;

    public Board() {
        this.setBackground(Color.decode("#424242"));
        this.setLayout(new GridLayout(boardRows, boardCols));
        this.setPreferredSize(new Dimension(boardHeight, boardWidth));
    }

    public void tileUpdate(int[][] matrix) {
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardCols; j++) {
                if (matrix[i][j] != 0) {
                    this.add(new Tile(matrix[i][j]));
                } else {
                    this.add(new Tile());
                }
            }
        }
    }
    public void boardUpdate(int[][] matrix) {

        this.removeAll();

        this.tileUpdate(matrix);
        this.revalidate();
        this.repaint();
    }
}