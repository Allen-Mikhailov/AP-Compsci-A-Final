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

    private Gun[] guns;
    private EngineTimer[] gunTimers;
    private Gun currentGun;
    private EngineTimer bulletTimer;

    private void switchGun(int index)
    {
        this.currentGun = guns[index];
        bulletTimer= gunTimers[index];
    }

    public Player()
    {
        EventHandler events = new EventHandler(this, new String[] 
            {"Key.Pressed", "Key.Released", "Mouse.Pressed", "Mouse.Released"});
        AddComponent(events);

        Collider newCollider = new Collider(this);
        AddComponent(newCollider);

        // PointLight light = new PointLight(this);
        // this.AddComponent(light);

        direction = Vector2.zero;

        guns = new Gun[2];
        gunTimers = new EngineTimer[2];
        
        Gun basicGun = new Gun();
        basicGun.fireRate = .1;
        basicGun.bulletSpeed = 20;
        basicGun.bulletSize = 6;
        basicGun.damage = 1;
        basicGun.recoil = 4;
        gunTimers[0] = basicGun.createTimer();
        guns[0] = basicGun;

        Gun bigGun = new Gun();
        bigGun.fireRate = 1;
        bigGun.bulletSpeed = 10;
        bigGun.bulletSize = 20;
        bigGun.damage = 5;
        bigGun.recoil = 20;
        gunTimers[1] = bigGun.createTimer();
        guns[1] = bigGun;

        switchGun(0);

        // Guns
        
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

        // Switch guns
        if (Character.isDigit((char) e.getKeyCode()))
        {
            int index = Integer.parseInt(keychar)-1;

            if (index > -1 && index < guns.length)
                switchGun(index);
            return;
        }

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

    private void Fire()
    {
        currentGun.Fire(this, pos.add(Engine.engine.GetMouseDirection().scale(30)));
        bulletTimer.reset();
    }

    @Override
    public void OnEvent(String event, Object eventObj) {
        int button;
        switch (event)
        {
            case "Key.Pressed":
                keyevent((KeyEvent) eventObj, "Pressed");
                break;
            case "Key.Released":
                keyevent((KeyEvent) eventObj, "Released");
                break;
            case "Mouse.Pressed":
                button = ((MouseEvent) eventObj).getButton();
                if (button == 1)
                    heldDown = true;
                break;

            case "Mouse.Released":
                button = ((MouseEvent) eventObj).getButton();
                if (button == 1)
                    heldDown = false;
                break;
        }
    }

    @Override
    public void update() {
        pos = pos.add(direction.scale(speed));

        if (bulletTimer.isReady() && heldDown)
        {
            Fire();
        }
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
}
