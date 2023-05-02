import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class Entity {
    public static int count = 0;

    public Vector2 pos;

    public String[] events = {};

    private Rect getBounds()
    {return Rect.empty;}

    public Entity()
    {
        pos = new Vector2();
        count++;
    }

    public void update()
    {

    }

    public void render(Graphics g, Vector2 cameraPos)
    {
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        g.setColor(Color.red);
        g.fillRect(lx - 25, ly - 25, 50, 50);
    }

    public void OnEvent(String event, Object eventObj)
    {

    }
}
