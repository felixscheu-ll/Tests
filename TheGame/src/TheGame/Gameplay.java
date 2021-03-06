package TheGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

	static int level= 1; // game level
	private boolean play= false;
	private int shipX=40; // X position of the spaceship
	private int shipY=700; // Y position of the spaceship
	private Timer timer;
	private int delay = 8;
	Meteors meteors = new Meteors();
	StarryBackground starbackground = new StarryBackground();
	
	public Gameplay() {
		addKeyListener (this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer (delay, this);
		timer.start();
	}
	
	// the game is drawn
	public void paint(Graphics g) {
		//black background
		g.setColor(Color.black);
		g.fillRect(1,1, Main.fX,Main.fY);
		
		//make it starry
		starbackground.drawstars((Graphics2D) g);

		//display Level
		g.setColor(Color.WHITE);
		g.setFont(new Font ("serif", Font.BOLD, 40));
		g.drawString("Level: " + level, 30, 40);
		
		//the moon
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(1200, 70, 150, 150);
		
		 //meteors   
		meteors.drawMeteors((Graphics2D) g);
		
		//spaceship
		g.setColor(Color.gray);
		g.fillRoundRect(shipX, shipY, 30, 50, 10, 10);
		
		//for loop in which each meteors position is compared with the spaceship's
		for (int i =0; i<meteors.metXY.length; i++) {
			for (int k=0; k<meteors.metXY[i].length; k+=2 ) {
				if (new Rectangle(shipX, shipY, 25, 45).intersects(new Rectangle(meteors.metXY[i][k], meteors.metXY[i][k+1],30,30))) {
					// if their position is the same, game over
					g.setColor(Color.RED);
					g.setFont(new Font ("serif", Font.BOLD, 60));
					g.drawString("Game Over!", 550, 400);
					g.setFont(new Font ("serif", Font.PLAIN, 40));
					g.drawString("Press Enter to restart", 525, 480);
					shipX=40;
					shipY=700;
					level=1;
					timer.stop();			
					play=false;	
				}
			}
		}
		//if statement to check if spaceship reaches the moon
		if (new Rectangle(shipX, shipY, 30, 50).intersects(new Rectangle(1220, 70, 100, 100))) {
			//6 levels to reach the moon
			if (level>6) {
				g.setColor(Color.black);
				g.fillRect(1,1, Main.fX, Main.fY);
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font ("serif", Font.BOLD, 60));
				g.drawString("You reached the moon!", 450, 400);
				timer.stop();
				play=false;
			} 
			// if level<=6, then the spaceship returns to starting position and level increases.
			shipX=40;
			shipY=700;
			level++;
			meteors = new Meteors();
		}
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {
		//gravity always pulls spaceship downwards. If it reaches bottom, it just stays there
			if (shipY>=740) {
			shipY=740;
			}
			else shipY++;
		
		//make meteors fall (if statement checks if they have fallen. If yes, they return to the top)
			for (int i =0; i<meteors.metXY.length; i++) {
				for (int k=0; k<meteors.metXY[i].length; k++ )
					if (k%2!=0) {
						if (meteors.metXY[i][k]>780) {
						meteors.metXY[i][k]=0;
						}
						meteors.metXY[i][k]+=5;
					}
				}
			}
	repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	// avoid spaceship going out of frame bounds && movement configuration
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				timer.restart();
				play=true;
				meteors= new Meteors();
	}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if (shipX >= Main.fX-50) {
				shipX = Main.fX-50;
			}
			else moveRight();
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			if (shipX <= 0) {
				shipX = 0;
			}
			else moveLeft();
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			if (shipY <= 0) {
				shipY = 0;
			 }
			else moveUp();
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			if (shipY >= Main.fY) {
				shipY = Main.fY;
			}
			else moveDown();
		}
	}
	
	//movement methods
	public void moveRight() {
		if	(play=true) shipX+=20;
	}
	public void moveLeft() {
		if	(play=true) shipX-=20;
	}
	public void moveUp() {
		if	(play=true) shipY-=20;
	}
	public void moveDown() {
		if	(play=true) shipY+=20;
	}
	@Override
	public void keyReleased(KeyEvent e) {	
	}

}
