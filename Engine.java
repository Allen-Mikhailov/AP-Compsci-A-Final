
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Engine {
    public int frame = 0;
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public void AddEntity(Entity e)
    {
        entities.add(e);
        for (String input : e.inputs)
        {

        }
    }

    private void DrawDebug(Graphics g)
    {
        g.setColor(Color.BLACK);

        // Frames
		g.drawString("Frame: "+frame, 0, 10);
        g.drawString("Entities: "+entities.size(), 0, 20);
    }

    public void DrawFrame(Graphics g)
    {
        g.setColor(Color.WHITE);
		g.fillRect(0,0,800,800);
		
        DrawDebug(g);
        frame++;
    }

    // Input
    public void MouseInput(String action, int x, int y)
    {

    }

    public void KeyBoardInput(String action, int keycode)
    {
        char input = (char) keycode;
    }
}
