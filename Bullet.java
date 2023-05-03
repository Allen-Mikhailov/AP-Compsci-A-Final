import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Entity implements Collider {
    Vector2 velocity;

    public Bullet(Vector2 pos, Vector2 velocity)
    {
        this.pos = pos;
        this.velocity = velocity;
    }

    @Override
    public void update() {
        pos = pos.add(velocity);

        if (Engine.engine.camera.pos.sub(pos).magnitude() > 5000)
            Destroy();
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        g.setColor(Color.black);
        g.fillOval(lx-3, ly-3, 6, 6);
    }

    @Override
    public void onTouch(Entity e) {
        
    }

    @Override
    public double radius() {
        return 6;
    }

    @Override
    public boolean isHard() {
        return false;
    }

    @Override
    public double weight() {
        return .1;
    }
}
