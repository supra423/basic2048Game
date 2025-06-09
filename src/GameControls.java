import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControls implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, undoPressed;
    private boolean lock = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        lock = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (lock) return;
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_U) {
            undoPressed = true;
        }
        lock = true;
    }
}

