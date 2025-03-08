package core.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public final class KeyHandler implements KeyListener {
    /** Holds if up has been pressed. */
    private boolean upPressed;
    /** Holds if down has been pressed/ */
    private boolean downPressed;
    /** Holds if left has been pressed. */
    private boolean leftPressed;
    /** Holds if right has been pressed. */
    private boolean rightPressed;

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @apiNote This method will not be used!
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //Method to be left empty for now, no defined usage.
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            setUpPressed(true);
        }
        if (code == KeyEvent.VK_S) {
            setDownPressed(true);
        }
        if (code == KeyEvent.VK_A) {
            setLeftPressed(true);
        }
        if (code == KeyEvent.VK_D) {
            setRightPressed(true);
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            setUpPressed(false);
        }
        if (code == KeyEvent.VK_S) {
            setDownPressed(false);
        }
        if (code == KeyEvent.VK_A) {
            setLeftPressed(false);
        }
        if (code == KeyEvent.VK_D) {
            setRightPressed(false);
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyHandler that = (KeyHandler) o;
        return isUpPressed() == that.isUpPressed() && isDownPressed() == that.isDownPressed() && isLeftPressed() == that.isLeftPressed() && isRightPressed() == that.isRightPressed();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isUpPressed(), isDownPressed(), isLeftPressed(), isRightPressed());
    }

    @Override
    public String toString() {
        return "main.main.KeyHandler{" +
                "upPressed=" + upPressed +
                ", downPressed=" + downPressed +
                ", leftPressed=" + leftPressed +
                ", rightPressed=" + rightPressed +
                '}';
    }
}
