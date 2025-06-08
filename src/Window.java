import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    Board board = new Board();
    final int rows = 4;
    final int  col = 4;
    public Window() {
        this.setTitle("2048");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        this.setPreferredSize(new Dimension(1200, 1000));
        this.getContentPane().setBackground(Color.decode("#424242"));

        this.pack();

        this.add(board);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                board.add(new Cell());
            }
        }
    }
}
