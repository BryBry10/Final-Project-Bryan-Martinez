import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Type_B_GameObject extends GameObject {

    // Constructor to set the starting position, direction, speed, and images
    public Type_B_GameObject(int x, int y, boolean userControlling) {
        super(x, y, userControlling); // Call the parent class constructor to set position and control
        setDirection(Direction.DOWN); // Start by moving down
        setVelocity(5); // Set the speed at which the object moves

        // Add images for all four possible directions (up, down, left, right)
        imageList = new LinkedList<>();
        imageList.add(new ImageIcon("images/Type_B_Up.png")); // Image for moving up
        imageList.add(new ImageIcon("images/Type_B_Down.png")); // Image for moving down
        imageList.add(new ImageIcon("images/Type_B_Left.png")); // Image for moving left
        imageList.add(new ImageIcon("images/Type_B_Right.png")); // Image for moving right
    }

    @Override
    public void move(Canvas c) {
        // Get the size of the object’s image and the canvas to check if it hits the boundaries
        Icon icon = getCurrentImage();
        int iconWidth = icon.getIconWidth(); // Width of the image
        int iconHeight = icon.getIconHeight(); // Height of the image
        int canvasWidth = (int) c.getSize().getWidth(); // Width of the canvas
        int canvasHeight = (int) c.getSize().getHeight(); // Height of the canvas

        // Move the object based on its direction and handle boundary collisions
        switch (getDirection()) {
        case Direction.DOWN:
            setY(getY() + getVelocity()); // Move down
            if (getY() + iconHeight > canvasHeight) { // If it goes beyond the bottom
                setY(canvasHeight - iconHeight); // Stop at the bottom of the canvas
                setDirection(Direction.RIGHT); // Change direction to move right
            }
            break;

        case Direction.RIGHT:
            setX(getX() + getVelocity()); // Move right
            if (getX() + iconWidth > canvasWidth) { // If it goes beyond the right side
                setX(canvasWidth - iconWidth); // Stop at the right side
                setDirection(Direction.UP); // Change direction to move up
            }
            break;

        case Direction.UP:
            setY(getY() - getVelocity()); // Move up
            if (getY() < 0) { // If it goes above the top
                setY(0); // Stop at the top
                setDirection(Direction.LEFT); // Change direction to move left
            }
            break;

        case Direction.LEFT:
            setX(getX() - getVelocity()); // Move left
            if (getX() < 0) { // If it goes beyond the left side
                setX(0); // Stop at the left side
                setDirection(Direction.DOWN); // Change direction to move down
            }
            break;

        default:
            break; // If no direction, do nothing
        }
    }

    @Override
    public void setImage() {
        // Set the correct image based on the current direction
        switch (getDirection()) {
        case Direction.UP:
            currentImage = 0; // Use the image for moving up
            break;
        case Direction.DOWN:
            currentImage = 1; // Use the image for moving down
            break;
        case Direction.LEFT:
            currentImage = 2; // Use the image for moving left
            break;
        case Direction.RIGHT:
            currentImage = 3; // Use the image for moving right
            break;
        default:
            break; // If no direction, do nothing
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
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                setDirection(Direction.RIGHT); // Move right if the right arrow key is pressed
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                setDirection(Direction.LEFT); // Move left if the left arrow key is pressed
            }
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
