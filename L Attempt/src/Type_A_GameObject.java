import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Type_A_GameObject extends GameObject {

    // Constructor to set the starting position, direction, speed, and images
    public Type_A_GameObject(int x, int y, boolean userControlling) {
        super(x, y, userControlling); // Call the parent class constructor to set position and control
        setDirection(Direction.UP); // Start by moving up
        setVelocity(5); // Set the speed at which the object moves

        // Create a list of images for animation (one for moving up, one for moving down)
        imageList = new LinkedList<>();
        imageList.add(new ImageIcon("images/Type_A_Up.png")); // Image for moving up
        imageList.add(new ImageIcon("images/Type_A_Down.png")); // Image for moving down
    }

    @Override
    public void move(Canvas c) {
        // Get the height of the object’s image and the canvas to check for boundaries
        Icon icon = getCurrentImage();
        int iconHeight = icon.getIconHeight(); // Height of the image
        int canvasHeight = (int) c.getSize().getHeight(); // Height of the canvas

        // Move the object based on its direction
        if (getDirection() == Direction.UP) {
            setY(getY() - getVelocity()); // Move up by decreasing y position
            if (getY() < 0) { // If the object goes above the canvas
                setY(0); // Stop at the top of the canvas
                setDirection(Direction.DOWN); // Change direction to move down
            }
        } else if (getDirection() == Direction.DOWN) {
            setY(getY() + getVelocity()); // Move down by increasing y position
            if (getY() + iconHeight > canvasHeight) { // If the object goes below the canvas
                setY(canvasHeight - iconHeight); // Stop at the bottom of the canvas
                setDirection(Direction.UP); // Change direction to move up
            }
        }
    }

    // This method sets the current image based on the direction of movement
    public void setImage() {
        // Choose the correct image depending on whether the object is moving up or down
        switch (getDirection()) {
        case Direction.UP:
            currentImage = 0; // Use the image for moving up
            break;
        case Direction.DOWN:
            currentImage = 1; // Use the image for moving down
            break;
        default:
            break; // If no direction is set, do nothing
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // This method isn't used right now, so it’s empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // If the user is controlling the object, change its direction based on key press
        if (userControlling) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setDirection(Direction.UP); // Move up if the up arrow key is pressed
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setDirection(Direction.DOWN); // Move down if the down arrow key is pressed
            }
        }
    }
}
