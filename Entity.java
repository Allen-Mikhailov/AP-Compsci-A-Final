import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

public class Entity {
    public static int count = 0;

    // Properties
    public Vector2 pos;
    ArrayList<Component> components;
    public String[] events = {};

    public Entity()
    {
        // Set up
        pos = new Vector2();
        components = new ArrayList<Component>();
        count++;
    }

    // Called Every Update
    public void update()
    {

    }

    // Adds a component to the entity
    public void AddComponent(Component component)
    {
        components.add(component);
    }

    // Gets the component
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

    // Called before object renders
    public void prerender(Graphics g, Vector2 cameraPos)
    {
        
    }

    // Main render Layer
    public void render(Graphics g, Vector2 cameraPos)
    {
        // local position
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);
    }

    // Layer for visiual effects
    public void postrender(Graphics g, Vector2 cameraPos)
    {
        
    }

    // Called when an even happens
    public void OnEvent(String event, Object eventObj)
    {

    }

    // Called on destruction of the object
    public void Destroy()
    {
        Engine.engine.removalQueue.add(this);
        for (Component comp : components)
        {
            comp.OnDestroy();
        }
    }
}
