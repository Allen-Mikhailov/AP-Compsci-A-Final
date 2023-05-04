import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Entity {
    public static int count = 0;

    public Vector2 pos;

    ArrayList<Component> components;

    public String[] events = {};

    public Entity()
    {
        pos = new Vector2();
        components = new ArrayList<Component>();
        count++;
    }

    public void update()
    {

    }

    public void AddComponent(Component component)
    {
        components.add(component);
    }

    public Component GetComponent(Class T)
    {
        for (Component component : components)
        {
            if (component.getClass() == T)
            {
                return component;
            }
        }
        return null;
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

    public void Destroy()
    {
        Engine.engine.removalQueue.add(this);
    }
}
