import java.awt.Graphics;

public class Camera extends Entity {
    private Player player;
    public Camera(Player plr)
    {
        player = plr;
    }

    @Override
    public void update() {
        // pos = player.pos;
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        
    }
}
