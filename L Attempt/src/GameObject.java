import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.Icon;

// This is the general GameObject class that all objects in the game will use.
public abstract class GameObject implements KeyListener {
    // Every game object has a position (x, y), a speed (velocity), and a direction it moves in.
    private int x;
    private int y;
    private int velocity;
    private int direction;

    // Each object can have multiple images (sprites) it can use.
    protected List<Icon> imageList;
    protected int currentImage; // This keeps track of which image to show.

    // This tracks if the user is controlling this object.
    protected boolean userControlling;

    // These keep track of the previous state (direction and image) before the user took control.
    protected int previousDirection;
    protected int previousImage;

    // When creating a GameObject, set its position, speed, and if the user is controlling it.
    public GameObject(int x, int y, boolean userControlling) {
        this.x = x;
        this.y = y;
        velocity = 0; // Initial velocity is 0.
        currentImage = 0; // Start with the first image.

        this.userControlling = userControlling; // Set if the user is controlling the object.
    }

    // This function is used to draw the object on the screen using its current image.
    public void draw(Component c, Graphics g) {
        imageList.get(currentImage).paintIcon(c, g, x, y); // Draw the current image at the object's position.
    }

    // Getters and setters for position (x, y) and velocity.
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity; // Set the speed of the object.
    }

    public int getVelocity() {
        return velocity; // Get the current speed of the object.
    }

    public int getDirection() {
        return direction; // Get t
    }
        
    public void setDirection(int direction) {
    	this.direction = direction;
    }
    
}
