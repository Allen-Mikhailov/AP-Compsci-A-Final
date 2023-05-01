
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class Game
{
	public static void main(String...args)
	{
		JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
		MyPanelb m = new MyPanelb();
		j.setSize(m.getSize());
		j.add(m); //adds the panel to the frame so that the picture will be drawn
			      //use setContentPane() sometimes works better then just add b/c of greater efficiency.

		j.setVisible(true); //allows the frame to be shown.

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
	}

}

class MyPanelb extends JPanel implements ActionListener
{
	
	private Timer time;
	private int frame = 0;
	
	MyPanelb()
	{
		time = new Timer(15, this); //sets delay to 15 millis and calls the actionPerformed of this class.
		setSize(800, 800);
		setVisible(true); //it's like calling the repaint method.
		time.start();
	
	}
	
	public void paintComponent(Graphics g)
	{

		g.setColor(Color.WHITE);
		g.fillRect(0,0,800,800);
		
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(frame), 10, 10);

		frame++;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
			
			
		repaint();  // call to paintComponent
	}
	
}