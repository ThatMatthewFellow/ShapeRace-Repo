import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.event.*;

// Shape Race -- ThatMatthewFellow

// This program creates three shapes: a red rectangle, a green circle, and...
// a blue rectangle (since there aren't any other shapes to be made).
// It also creates a vertical line that declares when to end the race.
// The objective of this program to race three shapes, and when one of them...
// crosses the finish line, declare a winner, and stop racing.
// To move the shapes, there are various buttons:
// These buttons include "move 10" which moves a randomly picked shape by 10 pixels.
// There's also the "move 100" button which moves a randomly picked shape by 100 pixels.
// Then the "Instant Results" button which runs a while loop until a shape wins the race,
// therefore ending it instantly and declaring a victor.
// Finally there's the "Reset" button that moves all shapes back to X = 100/the starting value.
// Based on which of the button's is clicked, different events happen to affect the shapes.
// The shapes are allowed to move until they reach X = 800 which ends the race, 
// and display the winner on the screen with text, it also allows the user to press the...
// reset button to make another race happen, or close the program if they're done.
// As the shapes race, the leading shape shape is made bigger to make it...
// even easier to track the winner.
// The buttons are set up to work with each other, so the user can press...
// "move 10" and "move 100" in the same race, and still get a winner.
public class ShapeRace{
    public static void main(String[] args){
	// Stuff you have to do to start graphical programing.  For now, just 
	// Put it in and trust that it works
	EventQueue.invokeLater(() ->
			       {
			// Create the frame object
			ButtonFrame b = new ButtonFrame();
		    // Let the frame kill our program when we close it
		    b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    // Show the frame
		    b.setVisible(true);
			       }
	    );
    }
}

// Uses ButtonFrame to add some buttons to the program
class ButtonFrame extends JFrame {
    private JPanel buttonPanel;
    private DrawComponent d;
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 900;

    public ButtonFrame() {
		// Screen size info
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		// Set the size to default
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// Adds a new DrawComponent to allow for usage later
        d = new DrawComponent();
        add(d);
        pack();

		// New JPanel object
        buttonPanel = new JPanel();

        // Create buttons
		// Calls functions below to make buttons that do different things
		// All buttons are set to yellow in their creation functions
        makeIndividual("Move 10");
		makeHundred("Move 100");
		makeInstant("Instant Results");
		makeReset("Reset");

		// Add the buttons
        add(buttonPanel);
    }

	// Individual movement button maker
	// The action detailed in this button is:
	// As long as none of the X cordinates for the shapes is 800,
	// then generate a random number (0-2), and call a function that causes a shape to move...
	// based on that random number.
	// Once one of the shapes crosses 800 on the X-axis, it prints out which of the shapes did it,
	// and declares a victor, while also stopping the shapes from continuing to move.
    public void makeIndividual(String name) {
        JButton button = new JButton(name);
        buttonPanel.add(button);
		button.setBackground(Color.YELLOW);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				if(d.getRect1() < 800 && d.getCirc() < 800 && d.getRect2() < 800){
             	    Random random = new Random();
             	    int choice = random.nextInt(3);
             	    int moveAmount = 10;
              		if (choice == 0) {
              	    	d.moveRect1(moveAmount);
              		} else if (choice == 1) {
              	      	d.moveCircle(moveAmount);
               	 	} else if (choice == 2) {
               	     	d.moveRect2(moveAmount);
               	 	}
					d.currentWinner();
				}
				if(d.getRect1() >= 800){
					d.setWords("Red rectangle won!");
					System.out.println("Red rectangle won!");
				}
				else if(d.getCirc() >= 800){
					d.setWords("Green circle won!");
					System.out.println("Green circle won!");
				}
				else if(d.getRect2() >= 800){
					d.setWords("Blue rectangle won!");
					System.out.println("Blue rectangle won!");
				}
            }
        });
    }

	// Instant result button maker
	// The action detailed in this button is:
	// The raceEnd() boolean function from DrawComponent is placed in a while loop,
	// as a parameter, and runs a loop for moving a shape until one of them,
	// reaches X = 800.
	// In the loop, a random number (0-2) is generated, and depending on the number,
	// it moves that corresponding shape by 10.
	public void makeInstant(String name) {
        JButton button = new JButton(name);
        buttonPanel.add(button);
		button.setBackground(Color.YELLOW);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				while(d.raceEnd() == false){
             	    Random random = new Random();
             	    int choice = random.nextInt(3);
             	    int moveAmount = 10;
              		if (choice == 0) {
              	    	d.moveRect1(moveAmount);
              		} else if (choice == 1) {
              	      	d.moveCircle(moveAmount);
               	 	} else if (choice == 2) {
               	     	d.moveRect2(moveAmount);
               	 	}
					d.currentWinner();				
				}
            }
        });
    }

	// Reset button maker
	// The action detailed in this button is:
	// Reset all the shapes to their starting position by calling functions...
	// From DrawComponents that take them back to X = 100.
	// This allows for shapes to race again without restarting the program.
	// It also resets their sizes, and the winner text on the screen.
	public void makeReset(String name) {
        JButton button = new JButton(name);
        buttonPanel.add(button);
		button.setBackground(Color.YELLOW);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				d.setRect1(100);
				d.setCirc(100);
				d.setRect2(100);
				d.currentWinner();
				d.setWords("");
            }
        });
    }

	// Move 100 button maker
	// The action detailed in this button is:
	// Very similar to the move individual button, except the shapes move...
	// by 100 pixels each time to make races a little quicker
	public void makeHundred(String name) {
        JButton button = new JButton(name);
        buttonPanel.add(button);
		button.setBackground(Color.YELLOW);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				if(d.getRect1() < 800 && d.getCirc() < 800 && d.getRect2() < 800){
             	    Random random = new Random();
             	    int choice = random.nextInt(3);
             	    int moveAmount = 100;
              		if (choice == 0) {
              	    	d.moveRect1(moveAmount);
              		} else if (choice == 1) {
              	      	d.moveCircle(moveAmount);
               	 	} else if (choice == 2) {
               	     	d.moveRect2(moveAmount);
               	 	}
					d.currentWinner();
				}
				if(d.getRect1() >= 800){
					d.setWords("Red rectangle won!");
					System.out.println("Red rectangle won!");
				}
				else if(d.getCirc() >= 800){
					d.setWords("Green circle won!");
					System.out.println("Green circle won!");
				}
				else if(d.getRect2() >= 800){
					d.setWords("Blue rectangle won!");
					System.out.println("Blue rectangle won!");
				}
            }
        });
    }
}

// Contains a rectangles, circle, and line
// Holds various programs related to the shapes that are drawn.
class DrawComponent extends JComponent{
	// Default width/height, and the Shapes's width/height
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 900;
	private static final int SHAPE_WIDTH = 25;
	private static final int SHAPE_HEIGHT = 25;
	// Sets the string words to nothings, and uses it later
	private String words = "";
	// Sets each shape's X value independently for manipulation later
	private int rect1X = 100;
	private int circX = 100;
	private int rect2X = 100;
	// Sets each shape's width and height separately for later usage
	// Rectangle 1's Width and height
	private int rect1W = 25;
	private int rect1H = 25;
	// Circle's Width and height
	private int circW = 25;
	private int circH = 25;
	// Rectangle 2's Width and height
	private int rect2W = 25;
	private int rect2H = 25;

	// paintComponent function for drawing the shapes
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

		// Defines points in which the line will go between
		Point2D p1 = new Point2D.Double(800,50);
		Point2D p2 = new Point2D.Double(800,850);

		// Draw the line that will end the race
		Line2D line = new Line2D.Double(p1,p2);
		g2.draw(line);

        // Draws a rectangle
        Rectangle2D rect1 = new Rectangle2D.Double(rect1X, 100, rect1W, rect1H);
		// Make the rectangle red
		g2.setPaint(Color.RED);
		g2.fill(rect1);

		// Draws a circle (ellipse)
		Ellipse2D circ = new Ellipse2D.Double(circX, 400, circW, circH);
		// Make the circle green
		g2.setPaint(Color.GREEN);
		g2.fill(circ);

		// Draws a third shape (rectangle)
		Rectangle2D rect2 = new Rectangle2D.Double(rect2X, 700, rect2W, rect2H);
		// Make the second rectangle blue
		g2.setPaint(Color.BLUE);
		g2.fill(rect2);

		// Draws a blank string of characters on the screen, and makes it black
		g2.setPaint(Color.BLACK);
		g2.drawString(words, 400, 75);
	}
	
	// A function relating to Screen size that makes sure it sets correctly
	public Dimension getPreferredSize(){
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

	// Set the words of the drawString object
	public void setWords(String s){
		words = s;
	}

	// Move functions
	// Move the red rectangle by an amount entered
	public void moveRect1(int amount){
        rect1X += amount;
        repaint();
    }

	// Move the green circle
	// Move the green circle by an amount entered
    public void moveCircle(int amount){
        circX += amount;
        repaint();
    }

	// Move the blue rectangle
	// Move the blue rectangle by an amount entered
	public void moveRect2(int amount){
		rect2X += amount;
		repaint();
	}

	// Set functions
	// Useful for the reset function
	// Set the red rectangle's X position
	public void setRect1(int amount){
		rect1X = amount;
		repaint();
	}

	// Set the green triangle's X position
	public void setCirc(int amount){
		circX = amount;
		repaint();
	}

	// Set the blue rectangle's X position
	public void setRect2(int amount){
		rect2X = amount;
		repaint();
	}

	// Get functions
	// Get the red rectangle's X position
	public int getRect1(){
		return rect1X;
	}

	// Get the green triangle's X position
	public int getCirc(){
		return circX;
	}

	// Get the blue rectangle's X position
	public int getRect2(){
		return rect2X;
	}

	// A boolean function to see when the race is over, display a message...
	// declaring who won, and update the string object to output text.
	public boolean raceEnd(){
		if(rect1X == 800){
			System.out.println("Red rectangle won!");
			words = "Red rectangle won!";
			return true;
		}
		else if(circX == 800){
			System.out.println("Green circle won!");
			words = "Green circle won!";
			return true;
		}
		else if(rect2X == 800){
			System.out.println("Blue rectangle won!");
			words = "Blue rectangle won!";
			return true;
		}
		else{
			return false;
		}
	}

	// A void function that changes the size of a shape depending on if it's winning.
	// It compares the X coordinate for each shape to see which is in the lead,
	// and based on that, it makes the shape that's in the lead bigger.
	// If all shapes are tied in X-value, then they're tied, and none are winning.
	public void currentWinner(){
		// All shapes are tied
		// All sizes are the same
		if(rect1X == circX && circX == rect2X){
			circH = 25;
			circW = 25;
			rect1H = 25;
			rect1W = 25;
			rect2H = 25;
			rect2W = 25;
		}
		// Red rectangle's winning
		// Red rectangle's size is bigger
		else if(rect1X > circX && rect1X > rect2X){
			rect1H = 50;
			rect1W = 50;
			circH = 25;
			circW = 25;
			rect2H = 25;
			rect2W = 25;
		}
		// Green circle's winning
		// Green circle's size is bigger
  		else if(circX > rect1X && circX > rect2X){
			circH = 50;
			circW = 50;
			rect1H = 25;
			rect1W = 25;
			rect2H = 25;
			rect2W = 25;
  		}
		// Blue rectangle's winning
		// Blue rectangle's size is bigger
  		else if(rect2X > rect1X && rect2X > circX){
			rect2H = 50;
			rect2W = 50;
			circH = 25;
			circW = 25;
			rect1H = 25;
			rect1W = 25;
  		}
	}
}