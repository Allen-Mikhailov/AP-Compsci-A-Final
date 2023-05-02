
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Engine {
    public int frame = 0;

    public int screenWidth, screenHeight;

    public Vector2 mousePos;

    public static Engine engine;
    private ArrayList<Entity> entities;
    private HashMap<String, ArrayList<Entity>> eventConnections;

    private long last_time;
    private double FPS;

    public ArrayList<Entity> removalQueue;


    public Game game;

    public Entity camera;

    public void SetCamera(Entity e)
    {
        camera = e;
    }

    public Engine()
    {
        engine = this;
        entities = new ArrayList<Entity>();
        eventConnections = new HashMap<String, ArrayList<Entity>>();

        game = new Game(this);
        mousePos = Vector2.zero;

        removalQueue = new ArrayList<Entity>();

        last_time = System.nanoTime();
    
    }

    private void AddConnection(String event, Entity entity)
    {
        if (!eventConnections.containsKey(event))
            eventConnections.put(event, new ArrayList<Entity>());

        eventConnections.get(event).add(entity);
    }

    public void AddEntity(Entity e)
    {
        entities.add(e);
        for (String event : e.events)
        {
            AddConnection(event, e);
        }
    }

    public void RemoveEntity(Entity e)
    {
        entities.remove(e);
        for (String event : e.events)
        {
            eventConnections.get(event).remove(e);
        }
    }

    public Vector2 GetMouseDirection()
    {
        return new Vector2(
            mousePos.x-screenWidth/2, 
            mousePos.y-screenHeight/2
        ).normalize();
    }

    public void FireEvent(String event, Object eventObject)
    {
        if (!eventConnections.containsKey(event))
            return;

        for (Entity entity : eventConnections.get(event))
        {
            entity.OnEvent(event, eventObject);
        }
    }

    private void DrawDebug(Graphics g)
    {
        g.setColor(Color.BLACK);

        // Frames
		g.drawString("Frame: "+frame + " FPS: "+Math.floor(FPS), 0, 10);
        g.drawString("Entities: "+entities.size(), 0, 20);
    }

    public void DrawFrame(Graphics g)
    {
        long time = System.nanoTime();
        double delta_time =  ((double) (time - last_time) / (1000000 * 1000));
        last_time = time;

        FPS = 1/delta_time;

        g.setColor(Color.WHITE);
		g.fillRect(0,0,800,800);

        Vector2 cameraPos = Vector2.sub(camera.pos, new Vector2(screenWidth/2, screenHeight/2));

        for (Entity entity : entities)
        {
            entity.update();
        }

        for (Entity entity : entities)
        {
            entity.render(g, cameraPos);
        }

        for (Entity entity : removalQueue)
        {
            RemoveEntity(entity);
        }

        removalQueue = new ArrayList<Entity>();
		
        DrawDebug(g);
        frame++;
    }

    // Input
    public void MouseInput(String event, MouseEvent e)
    {FireEvent(event, e);}

    public void KeyBoardInput(String event, KeyEvent e)
    {FireEvent(event, e);}
}
