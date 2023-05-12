import java.awt.Graphics;

public class Camera extends Entity {

    // Properties
    private Player player;
    public Camera(Player plr)
    {
        player = plr;
    }

    @Override
    public void update() {
        // Moving to the player position
        pos = pos.add((player.pos.sub(pos)).scale(.1));
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        
    }
}
