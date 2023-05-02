
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game
{
	public static void main(String...args)
	{
		JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
		MyPanelb m = new MyPanelb();
		j.setSize(m.getSize());
		j.add(m); //adds the panel to the frame so that the picture will be drawn
			      //use setContentPane() sometimes works better then just add b/c of greater efficiency.

		// Input
		j.addKeyListener(m);
	    j.addMouseListener(m);

		j.setVisible(true); //allows the frame to be shown.

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
	}

}

class MyPanelb extends JPanel implements ActionListener, MouseListener, KeyListener
{
	
	private Timer time;
	private Engine engine = new Engine();
	
	MyPanelb()
	{
		time = new Timer(15, this); //sets delay to 15 millis and calls the actionPerformed of this class.
		setSize(800, 800);
		setVisible(true); //it's like calling the repaint method.
		time.start();
	
	}
	
	public void paintComponent(Graphics g)
	{engine.DrawFrame(g);}
	
	public void actionPerformed(ActionEvent e)
	{repaint();}

	public void mouseClicked(MouseEvent e)
	{engine.MouseInput("Clicked", e.getX(), e.getY());}

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}


	public void keyPressed(KeyEvent e)
	{engine.KeyBoardInput("Pressed", e.getKeyCode());}

	public void keyReleased(KeyEvent e)	{}
	public void keyTyped(KeyEvent e){}

	
}