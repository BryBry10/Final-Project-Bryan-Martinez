import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyMain {
	public static void main(String[] args) throws IOException {

		Canvas canvas = new Canvas();
		canvas.requestFocus();

		// Add Type A GameObject
		Type_A_GameObject typeA = new Type_A_GameObject(100, 100);
		typeA.setVelocity(10);
		canvas.addGameObject(typeA);
		int indexA = 0;

		// Add Type B GameObject
		Type_B_GameObject typeB = new Type_B_GameObject(200, 200);
		typeB.setVelocity(10);
		canvas.addGameObject(typeB);
		int indexB = 1;

		// Add Type C GameObject
		Type_C_GameObject typeC = new Type_C_GameObject(300, 300);
		typeC.setVelocity(10);
		canvas.addGameObject(typeC);
		int indexC = 2;

		// Add Type D (User-controlled) GameObject
		Type_D_GameObject user = new Type_D_GameObject(400, 400);
		user.setVelocity(10);
		canvas.addKeyListener(user);
		canvas.addGameObject(user);
		int indexD = 3;
		

		//int selectedIndex = 3;
		//GameObject selectedObject = user;
		//KeyEvent e;
		
	
		}

	}

	

