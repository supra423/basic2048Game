import javax.swing.*;
import java.awt.*;
// contains textures for each tile
public class Tile extends JPanel {

    final private static int tileHeight = Board.boardWidth / GameEngine.rows;
    final private static int tileWidth = Board.boardWidth / GameEngine.cols;
    final private static Dimension fixedSize = new Dimension(tileWidth, tileHeight);


    private void cellConfiguration(String backgroundColor, String label, String labelColor, int labelSize) {
        this.setBackground(Color.decode(backgroundColor));
        final JLabel cellValue = new JLabel(label);
        cellValue.setForeground(Color.decode(labelColor));
        cellValue.setFont(new Font("Arial", Font.BOLD, labelSize));

        this.add(cellValue);
    }

    public Tile() {
        this.setBackground(Color.decode("#636262"));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a")));

        this.setPreferredSize(fixedSize);
        this.setMinimumSize(fixedSize);
        this.setMaximumSize(fixedSize);

        this.setLayout(new GridBagLayout());
    }
    public Tile(int value) {
        final String stringValue = Integer.toString(value);
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a")));

        this.setPreferredSize(fixedSize);
        this.setMinimumSize(fixedSize);
        this.setMaximumSize(fixedSize);

        this.setLayout(new GridBagLayout());

        // autoscaling , because tileHeight is equal to tileWidth, we can just use
        // either of them, in this context, I'll be using height.
        final int largeFont = (int) (tileHeight * 0.4);
        final int mediumFont = (int) (tileHeight * 0.3);
        final int smallFont = (int) (tileHeight * 0.25);
        if (value == 2) {
            cellConfiguration("#efe5da", stringValue, "#766e64", largeFont);
        } else if (value == 4) {
            cellConfiguration("#ece1c8", stringValue, "#766e64", largeFont);
        } else if (value == 8) {
            cellConfiguration("#f3b178", stringValue, "#ffffff", largeFont);
        } else if (value == 16) {
            cellConfiguration("#f59563", stringValue, "#ffffff", largeFont);
        } else if (value == 32) {
            cellConfiguration("#f67c60", stringValue, "#ffffff", largeFont);
        } else if (value == 64) {
            cellConfiguration("#f65e3b", stringValue, "#ffffff", largeFont);
        } else if (value == 128) {
            cellConfiguration("#ecce73", stringValue, "#ffffff", largeFont);
        } else if (value == 256) {
            cellConfiguration("#eccd62", stringValue, "#ffffff", largeFont);
        } else if (value == 512) {
            cellConfiguration("#edc951", stringValue, "#ffffff", largeFont);
        } else if (value == 1024) {
            cellConfiguration("#edc43e", stringValue, "#ffffff", mediumFont);
        } else if (value == 2048) {
            cellConfiguration("#ecc32d", stringValue, "#ffffff", mediumFont);
        } else if (value > 2048 && value <= 8192) {
            cellConfiguration("#000000", stringValue, "#ffffff", mediumFont);
        } else {
            cellConfiguration("#000000", stringValue, "#ffffff", smallFont);
        }
    }
}
