import javax.swing.*;
import java.awt.*;
public class Window extends JFrame {

    public Window() {
        this.setTitle("2048");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setResizable(true);
        this.setPreferredSize(new Dimension(1080, 1080));
        this.getContentPane().setBackground(Color.decode("#424242"));

        GridBagConstraints gbc = new GridBagConstraints();

        new GameEngine(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(GameEngine.board, gbc);

        // Instruction panel and labels
        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(Color.decode("#313131"));
        instructionPanel.setBorder(BorderFactory.createBevelBorder(1));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;

        JLabel instructionLabel1 = new JLabel("<html>Use arrow keys to move.<br>Press U to undo.</html>");

        instructionLabel1.setForeground(Color.WHITE);
        instructionLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        instructionPanel.add(instructionLabel1);

        GridBagConstraints gbc2 = new GridBagConstraints();

        JPanel instructionPanel1 = new JPanel();
        instructionPanel1.setBackground(Color.decode("#313131"));
        instructionPanel1.setBorder(BorderFactory.createBevelBorder(1));
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(10, 10, 10, 10);
        gbc2.anchor = GridBagConstraints.WEST;

        JLabel instructionLabel2 = new JLabel("<html>Use arrow keys to move.<br>Press U to undo.</html>");

        instructionLabel2.setForeground(Color.WHITE);
        instructionLabel2.setFont(new Font("Arial", Font.BOLD, 18));
        instructionPanel1.add(instructionLabel2);

        this.add(instructionPanel, gbc);
        this.add(instructionPanel1, gbc2);
        this.pack();
    }
}
