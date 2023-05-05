import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Player extends Mortal {

    private Vector2 direction;

    private double speed = 4;

    private boolean Up;
    private boolean Down;
    private boolean Left;
    private boolean Right;

    private boolean heldDown = false;

    public Player()
    {
        EventHandler events = new EventHandler(this, new String[] 
            {"Key.Pressed", "Key.Released", "Mouse.Pressed", "Mouse.Released"});
        AddComponent(events);

        Collider newCollider = new Collider(this);
        AddComponent(newCollider);

        direction = Vector2.zero;
    }

    private void UpdateDirection()
    {
        Vector2 dir = Vector2.zero;

        if (Up)
            dir = dir.sub(Vector2.up);
        if (Down)
            dir = dir.sub(Vector2.down);
        if (Left)
            dir = dir.add(Vector2.left);
        if (Right)
            dir = dir.add(Vector2.right);    
            
        direction = dir.normalize();
    }

    private void keyevent(KeyEvent e, String type)
    {
        String keychar = ""+ (char) e.getKeyCode();
        switch (keychar)
        {
            case "W":
                if (type.equals("Pressed"))
                    Up = true;
                else if (type.equals("Released"))
                    Up = false;
                UpdateDirection();
                break;

            case "S":
                if (type.equals("Pressed"))
                    Down = true;
                else if (type.equals("Released"))
                    Down = false;
                UpdateDirection();
                break;

            case "A":
                if (type.equals("Pressed"))
                    Left = true;
                else if (type.equals("Released"))
                    Left = false;
                UpdateDirection();
                break;

            case "D":
                if (type.equals("Pressed"))
                    Right = true;
                else if (type.equals("Released"))
                    Right = false;
                UpdateDirection();
                break;
        }
    }

    private void Fire(MouseEvent e)
    {
        Vector2 mouseDir = Engine.engine.GetMouseDirection();
        Bullet newBullet = new Bullet(pos.add(mouseDir.scale(25)), mouseDir.scale(20));

        Engine.engine.AddEntity(newBullet);
    }

    @Override
    public void OnEvent(String event, Object eventObj) {
        switch (event)
        {
            case "Key.Pressed":
                keyevent((KeyEvent) eventObj, "Pressed");
                break;
            case "Key.Released":
                keyevent((KeyEvent) eventObj, "Released");
                break;
            case "Mouse.Pressed":
                Fire((MouseEvent) eventObj);
                heldDown = true;
                break;

            case "Mouse.Released":
                heldDown = false;
                break;
        }
    }

    @Override
    public void update() {
        pos = pos.add(direction.scale(speed));
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        Vector2 mouseDir = Engine.engine.GetMouseDirection();

        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        g.setColor(Color.black);
        g.fillOval(lx-25, ly-25, 50, 50);

        g.drawLine(lx, ly, lx+(int)(mouseDir.x*40), ly+(int)(mouseDir.y*40));
    }

    // @Override
    // public double radius() {
    //     return 5;
    // }

    // @Override
    // public void onTouch(Entity e) {
    //     // System.out.println("Touched");
    // }

    // @Override
    // public boolean isHard() {
    //     return true;
    // }
    // @Override
    // public double weight() {
    //     return 1;
    // }
}
