import javax.swing.*;
import java.awt.*;
public class Window extends JFrame {
    public Window() {
        this.setTitle("2048");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setResizable(true);
        this.setPreferredSize(new Dimension(1400, 1000));
        this.getContentPane().setBackground(Color.decode("#424242"));

        GridBagConstraints gbc = new GridBagConstraints();

        // Instruction label
        JLabel instructionLabel1 = new JLabel("Use arrow keys to move.");

        instructionLabel1.setForeground(Color.WHITE);
        instructionLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(instructionLabel1, gbc);

        JLabel instructionLabel2 = new JLabel("Press U to undo.");

        instructionLabel2.setForeground(Color.WHITE);
        instructionLabel2.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(instructionLabel2, gbc);

        this.pack();
    }

    public void add(GameEngine newGame) {

    }
}
