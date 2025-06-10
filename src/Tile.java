import javax.swing.*;
import java.awt.*;
// contains textures for each tile
public class Tile extends JPanel {
    static Dimension fixedSize = new Dimension(200, 200);
    private void cellConfiguration(String backgroundColor, String label, String labelColor, int labelSize) {
        this.setBackground(Color.decode(backgroundColor));
        JLabel cellValue = new JLabel(label);
        cellValue.setForeground(Color.decode(labelColor));
        cellValue.setFont(new Font("Arial", Font.BOLD, labelSize));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;



        this.add(cellValue, gbc);
    }

    public Tile() {
        this.setBackground(Color.decode("#636262"));
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a"), 10));

        this.setPreferredSize(fixedSize);
        this.setMinimumSize(fixedSize);
        this.setMaximumSize(fixedSize);

        this.setLayout(new GridBagLayout());
    }
    public Tile(int value) {
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a"), 10));

        this.setPreferredSize(fixedSize);
        this.setMinimumSize(fixedSize);
        this.setMaximumSize(fixedSize);

        this.setLayout(new GridBagLayout());

        if (value == 2) {
            cellConfiguration("#efe5da", "2", "#766e64", 80);
        } else if (value == 4) {
            cellConfiguration("#ece1c8", "4", "#766e64", 80);
        } else if (value == 8) {
            cellConfiguration("#f3b178", "8", "#ffffff", 80);
        } else if (value == 16) {
            cellConfiguration("#f59563", "16", "#ffffff", 80);
        } else if (value == 32) {
            cellConfiguration("#f67c60", "32", "#ffffff", 80);
        } else if (value == 64) {
            cellConfiguration("#f65e3b", "64", "#ffffff", 80);
        } else if (value == 128) {
            cellConfiguration("#ecce73", "128", "#ffffff", 80);
        } else if (value == 256) {
            cellConfiguration("#eccd62", "256", "#ffffff", 80);
        } else if (value == 512) {
            cellConfiguration("#edc951", "512", "#ffffff", 80);
        } else if (value == 1024) {
            cellConfiguration("#edc43e", "1024", "#ffffff", 60);
        } else if (value == 2048) {
            cellConfiguration("#ecc32d", "2048", "#ffffff", 60);
        } else if (value == 4096) {
            cellConfiguration("#000000", "4096", "#ffffff", 60);
        } else if (value == 8192) {
            cellConfiguration("#000000", "8192", "#ffffff", 60);
        } else if (value == 16384) {
            cellConfiguration("#000000", "16384", "#ffffff", 60);
        }
    }
}
