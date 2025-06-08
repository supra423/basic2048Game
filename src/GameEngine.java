import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameEngine implements ActionListener {
    private final GameControls keyH = new GameControls();

    public GameEngine(JFrame frame) {
        frame.addKeyListener(keyH);
        Timer timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (keyH.upPressed) {
            keyH.upPressed = false;
        } else if (keyH.downPressed) {
            keyH.downPressed = false;
        } else if (keyH.leftPressed) {
            keyH.leftPressed = false;
        } else if (keyH.rightPressed) {
            keyH.rightPressed = false;
        }
    }
}
