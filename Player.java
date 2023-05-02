import java.awt.event.KeyEvent;

public class Player extends Entity {

    private Vector2 direction;

    private double speed = 4;

    private boolean Up;
    private boolean Down;
    private boolean Left;
    private boolean Right;

    public Player()
    {
        events = new String[] {"Key.Pressed", "Key.Released"};
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
        System.out.println(keychar);
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
        }
    }

    @Override
    public void update() {
        pos = pos.add(direction.scale(speed));
    }
}
