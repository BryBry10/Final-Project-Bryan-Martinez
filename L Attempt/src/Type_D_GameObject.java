import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

//GAME OBJECT OF TYPE D REQUIRES A KEY LISTENER
public class Type_D_GameObject extends GameObject implements KeyListener {

	public Type_D_GameObject(int x, int y, boolean userControlling) {
		super(x, y, userControlling);
		setDirection(Direction.NONE);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_D_Up.png"));
		imageList.add(new ImageIcon("images/Type_D_Down.png"));
		imageList.add(new ImageIcon("images/Type_D_Left.png"));
		imageList.add(new ImageIcon("images/Type_D_Right.png"));

	}

	public void move(Canvas c) {
		Icon icon = getCurrentImage();

		int iconHeight = icon.getIconHeight();
		int iconWidth = icon.getIconWidth();
		int canvasHeight = (int) c.getSize().getHeight();
		int canvasWidth = (int) c.getSize().getWidth();

		// MOVE BLUE GAME OBJECT
		switch (getDirection()) {
		case Direction.UP:
			setY(getY() - getVelocity());
			if (getY() < 0) {
				setY(0);
			}
			break;
		case Direction.DOWN:
			setY(getY() + getVelocity());
			if (getY() + iconHeight > canvasHeight) {
				setY((int) (canvasHeight - iconHeight));
			}
			break;
		case Direction.LEFT:
			setX(getX() + getVelocity());
			if (getX() + iconWidth > canvasWidth) {
				setX((int) (canvasWidth - iconWidth));
			}
			break;
		case Direction.RIGHT:
			setX(getX() - getVelocity());
			if (getX() < 0) {
				setX(0);
			}
			break;
		default:
			break;
		}

	}

	// SPECIFY THE IMAGE TO DISPLAY
	// USED FOR ANIMATION
	public void setImage() {
		switch (getDirection()) {
		case Direction.NONE:
			break;
		case Direction.UP:
			currentImage = 0;
			break;
		case Direction.DOWN:
			currentImage = 1;
			break;
		case Direction.LEFT:
			currentImage = 2;
			break;
		case Direction.RIGHT:
			currentImage = 3;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {

		// SHOULD ONLY WORK IF THE OBJECT IS IN CONTROL
		if (userControlling) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setDirection(Direction.UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setDirection(Direction.DOWN);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.RIGHT);
			}

		}

	}

}
