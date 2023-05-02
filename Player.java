import java.awt.event.KeyEvent;

public class Player extends Entity {

    private Vector2 direction;

    private boolean Up;
    private boolean Down;
    private boolean Left;
    private boolean Right;

    public Player()
    {
        events = new String[] {"Key.Pressed"};
    }

    private void UpdateDirection()
    {
        direction = new Vector2();
    }

    @Override
    public void OnEvent(String event, Object eventObj) {
        switch (event)
        {
            case "Key.Pressed":
                KeyEvent key = (KeyEvent) eventObj;
                char keychar = (char) key.getKeyCode();
                break;
        }
    }
}
