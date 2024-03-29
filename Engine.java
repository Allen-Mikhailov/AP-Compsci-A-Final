
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

    public boolean started;

    public static Engine engine;
    private ArrayList<Entity> entities;
    public ArrayList<Entity> addQueue;
    public ArrayList<Entity> removalQueue;

    private long last_time;
    public static double FPS;
    public static double delta_time;

    public static double GameTime;


    public Game game;

    // Camera stuff
    public Entity camera;
    public void SetCamera(Entity e)
    {
        camera = e;
    }

    public Engine()
    {
        // Setting up engine
        started = false;
        engine = this;
        entities = new ArrayList<Entity>();

        game = new Game(this);
        mousePos = Vector2.zero;

        GameTime = 0;


        // Queues
        addQueue = new ArrayList<Entity>();
        removalQueue = new ArrayList<Entity>();

        // For FPS and time
        last_time = System.nanoTime();
        
        started = true;
    }

    // Actually adding 
    public void RawAddEntity(Entity e)
    {
        entities.add(e);
    }

    // Adding an entity
    public void AddEntity(Entity e)
    {
        if (started)
            addQueue.add(e);
        else
            RawAddEntity(e);
    }

    // Removing an entity
    public void RemoveEntity(Entity e)
    {
        entities.remove(e);
    }

    // Gets the direction of the mouse
    public Vector2 GetMouseDirection()
    {
        return new Vector2(
            mousePos.x-screenWidth/2, 
            mousePos.y-screenHeight/2
        ).normalize();
    }

    // round to place
    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    // Drawing tthe debug stats
    private void DrawDebug(Graphics g)
    {
        // Basic stats
        g.setColor(Color.BLACK);
        String[] debugs = new String[] {
            "Time: "+roundAvoid(GameTime, 2),
            "Frame: "+frame,
            "FPS: "+roundAvoid(FPS, 2),
            "Entities: "+entities.size() + " : "
        };

        int spacing = 12;
        int index = 0;

        for (int i = 0; i < debugs.length; i++)
        {
            g.drawString(debugs[i], 0, (index+1)*spacing);
            index++;
        }

        // Drawing the list of entities
        for (int i = 0; i < entities.size(); i++)
        {
            String name = entities.get(i).getClass().getName();

            // Counting consecutive
            int count = 1;
            for (int j = i+1; j < entities.size(); j++)
            {
                if (name.equals(entities.get(j).getClass().getName()))
                    count++;
            }

            if (count > 1)
                name+= " x"+count;

            // Drawing an incrementing
            g.drawString(name, 0, screenHeight/2+ (index+1)*spacing);
            i+=count-1;
            index++;
        }
    }

    // Running through all of the queues
    private void clearQueues()
    {
        for (Entity entity : addQueue)
        {
            RawAddEntity(entity);
        }

        for (Entity entity : removalQueue)
        {
            RemoveEntity(entity);
        }

        addQueue = new ArrayList<Entity>();
        removalQueue = new ArrayList<Entity>();
    }

    public void DrawFrame(Graphics g)
    {
        // Frame rate stuff
        long time = System.nanoTime();
        delta_time =  ((double) (time - last_time) / (1000000 * 1000));
        last_time = time;

        GameTime += delta_time;

        FPS = 1/delta_time;

        g.setColor(Color.WHITE);
		g.fillRect(0,0,800,800);

        // Getting camera position
        Vector2 cameraPos = Vector2.sub(camera.pos, new Vector2(screenWidth/2, screenHeight/2));

        clearQueues();

        for (Entity entity : entities)
        {
            entity.update();
            for (Component component : entity.components)
            {
                component.update();
            }
        }

        game.Update();

        clearQueues();

        // pre render
        for (Entity entity : entities)
        {
            entity.prerender(g, cameraPos);
            for (Component component : entity.components)
            {
                component.prerender(g, cameraPos);
            }
        }

        // normal render
        for (Entity entity : entities)
        {
            entity.render(g, cameraPos);
            for (Component component : entity.components)
            {
                component.render(g, cameraPos);
            }
        }

        // Post render
        for (Entity entity : entities)
        {
            entity.postrender(g, cameraPos);
            for (Component component : entity.components)
            {
                component.postrender(g, cameraPos);
            }
        }

        clearQueues();
		
        DrawDebug(g);
        frame++;
    }

    // Input
    public void MouseInput(String event, MouseEvent e)
    {EventHandler.FireEvent(event, e);}

    public void KeyBoardInput(String event, KeyEvent e)
    {EventHandler.FireEvent(event, e);}
}
