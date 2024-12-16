import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Type_C_GameObject extends GameObject {

    // Constructor to initialize the starting position, direction, speed, and images
    public Type_C_GameObject(int x, int y, boolean userControlling) {
        super(x, y, userControlling); // Calls the parent class constructor to set position and control
        setDirection(Direction.RIGHT); // Start by moving to the right
        setVelocity(5); // Set the speed at which the object moves

        // Add images for the animation (left and right movement)
        imageList = new LinkedList<>();
        imageList.add(new ImageIcon("images/Type_C_Left.png")); // Image for moving left
        imageList.add(new ImageIcon("images/Type_C_Right.png")); // Image for moving right
    }

    @Override
    public void move(Canvas c) {
        // Get the width of the object's image and the width of the canvas for boundary checks
        Icon icon = getCurrentImage();
        int iconWidth = icon.getIconWidth(); // Get the width of the image
        int canvasWidth = (int) c.getSize().getWidth(); // Get the width of the canvas

        // Handle horizontal movement, making the object bounce back and forth
        if (getDirection() == Direction.RIGHT) {
            setX(getX() + getVelocity()); // Move right
            if (getX() + iconWidth > canvasWidth) { // Check if the object hits the right boundary
                setX(canvasWidth - iconWidth); // Stop at the right boundary
                setDirection(Direction.LEFT); // Change direction to left (bounce)
            }
        } else if (getDirection() == Direction.LEFT) {
            setX(getX() - getVelocity()); // Move left
            if (getX() < 0) { // Check if the object hits the left boundary
                setX(0); // Stop at the left boundary
                setDirection(Direction.RIGHT); // Change direction to right (bounce)
            }
        }
    }

    @Override
    public void setImage() {
        // Update the current image based on the direction the object is moving
        switch (getDirection()) {
        case Direction.LEFT:
            currentImage = 0; // Use the image for moving left
            break;
        case Direction.RIGHT:
            currentImage = 1; // Use the image for moving right
            break;
        default:
            break; // If no direction, do nothing
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method isn't used right now, so it's empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // If the user is controlling the object, change direction based on key press
        if (userControlling) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                setDirection(Direction.RIGHT); // Move right if the right arrow key is pressed
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                setDirection(Direction.LEFT); // Move left if the left arrow key is pressed
            }
        }
    }
}
