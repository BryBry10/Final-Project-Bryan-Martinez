import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener {
    // This is the serial number. It's like a unique ID for the program.
    private static final long serialVersionUID = 1L;

    // We need a window (frame) and a timer to make things happen in the game.
    private JFrame frame;
    private Timer gameLoopTimer;

    // This list will hold all the game objects (like characters or things in the game).
    private List<GameObject> gameObjectList;
    private int highlighted = 0; // This keeps track of which object is selected.

    public Canvas() {
        // First, create a list to hold all the game objects that will show up on the screen.
        gameObjectList = new LinkedList<GameObject>();

        // Next, create a window (frame) for the game to be displayed in.
        frame = new JFrame("Animation Canvas");
        frame.setSize(800, 800); // Set the window size to 800x800 pixels.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed.
        frame.add(this); // Add this canvas to the window.

        // Create a timer that will update the game every 25 milliseconds (this makes the game "run").
        gameLoopTimer = new Timer(25, this);
        gameLoopTimer.start(); // Start the timer.

        setFocusTraversalKeysEnabled(false); // Disable automatic focus movement.
        addKeyListener(this); // Add a listener to capture keyboard events.

        // Make the window visible so the user can see it.
        frame.setVisible(true);
    }

    /**
     * This function adds a new game object (like a character or item) to the game.
     */
    public synchronized void addGameObject(GameObject sprite) {
        gameObjectList.add(sprite); // Add the new object to the list.
    }

    /**
     * This function is used to draw all the game objects on the screen.
     */
    public synchronized void paint(Graphics g) {
        for (int i = 0; i < gameObjectList.size(); i++) {
            GameObject currentObject = gameObjectList.get(i);
            currentObject.draw(this, g); // Draw the current object on the screen.

            if (i == highlighted) {
                g.setColor(Color.RED); // If it's the highlighted object, draw a red border around it.
                g.drawRect(currentObject.getX(), currentObject.getY(), currentObject.getCurrentImage().getIconWidth(),
                        currentObject.getCurrentImage().getIconHeight());
            }
        }
    }

    // ****************************************************
    // This method is part of ActionListener. It's called when the timer goes off.
    public synchronized void actionPerformed(ActionEvent e) {
        // Go through all the game objects and move them. Then, update their images.
        for (GameObject gameObject : gameObjectList) {
            gameObject.move(this); // Move the object.
            gameObject.setImage(); // Update its image (for animation or state change).
        }
        repaint(); // Redraw everything on the screen.
    }

    // ****************************************************
    // These methods are part of KeyListener. They are used to handle keyboard input.

    public void keyTyped(KeyEvent e) {
        // This is empty because we don’t need it in this game.
    }

    public void keyPressed(KeyEvent e) {
        // This is empty because we don’t need it in this game.
    }

    public void keyReleased(KeyEvent e) {
        // This happens when a key is released.
        // We are checking if the Tab key was pressed to switch between highlighted objects.
        if (e.getKeyCode() == KeyEvent.VK_TAB) {
            GameObject selectedObject = gameObjectList.get(highlighted);
            selectedObject.setUserControlling(false); // Stop controlling the current object.

            // Now, move to the next object in the list.
            highlighted = highlighted + 1; // Go to the next object.

            // If we've reached the end of the list, go back to the first object.
            if (highlighted == gameObjectList.size()) {
                highlighted = 0;
            }
            selectedObject = gameObjectList.get(highlighted);

            selectedObject.setUserControlling(true); // Start controlling the new object.
            // We don't need to change velocity here, so we removed that part.
        }
    }
}
