import javax.swing.*;
import java.awt.*;
// contains textures for each tile
public class Tile extends JPanel {
    static Dimension fixedSize = new Dimension(200, 200);
    private void cellConfiguration(String backgroundColor, String label, String labelColor, int labelSize) {
        this.setBackground(Color.decode(backgroundColor));
        final JLabel cellValue = new JLabel(label);
        cellValue.setForeground(Color.decode(labelColor));
        cellValue.setFont(new Font("Arial", Font.BOLD, labelSize));

        final GridBagConstraints gbc = new GridBagConstraints();
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
        final String stringValue = Integer.toString(value);
        this.setBorder(BorderFactory.createLineBorder(Color.decode("#7d7a7a"), 10));

        this.setPreferredSize(fixedSize);
        this.setMinimumSize(fixedSize);
        this.setMaximumSize(fixedSize);

        this.setLayout(new GridBagLayout());

        if (value == 2) {
            cellConfiguration("#efe5da", stringValue, "#766e64", 80);
        } else if (value == 4) {
            cellConfiguration("#ece1c8", stringValue, "#766e64", 80);
        } else if (value == 8) {
            cellConfiguration("#f3b178", stringValue, "#ffffff", 80);
        } else if (value == 16) {
            cellConfiguration("#f59563", stringValue, "#ffffff", 80);
        } else if (value == 32) {
            cellConfiguration("#f67c60", stringValue, "#ffffff", 79);
        } else if (value == 64) {
            cellConfiguration("#f65e3b", stringValue, "#ffffff", 80);
        } else if (value == 128) {
            cellConfiguration("#ecce73", stringValue, "#ffffff", 80);
        } else if (value == 256) {
            cellConfiguration("#eccd62", stringValue, "#ffffff", 80);
        } else if (value == 512) {
            cellConfiguration("#edc951", stringValue, "#ffffff", 80);
        } else if (value == 1024) {
            cellConfiguration("#edc43e", stringValue, "#ffffff", 60);
        } else if (value == 2048) {
            cellConfiguration("#ecc32d", stringValue, "#ffffff", 60);
        } else if (value > 2048 && value <= 8192) {
            cellConfiguration("#000000", stringValue, "#ffffff", 60);
        } else {
            cellConfiguration("#000000", stringValue, "#ffffff", 50);
        }
    }
}
