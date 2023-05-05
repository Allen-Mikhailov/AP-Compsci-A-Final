import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Entity  {
    Vector2 velocity;
    Entity FiredFrom;
    boolean ended = false;

    public Bullet(Entity FiredFrom, Vector2 pos, Vector2 velocity)
    {
        this.pos = pos;
        this.velocity = velocity;
        this.FiredFrom = FiredFrom;

        Collider newCollider = new Collider(this, 6, .1);
        newCollider.movable = false;
        this.AddComponent(newCollider);
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
    public void OnEvent(String event, Object eventObj) {
        switch (event)
        {
            case "Collider.Touch":
                if (ended) {return;}

                if (eventObj instanceof Mortal && eventObj != FiredFrom)
                {
                    Mortal m = (Mortal) eventObj;
                    m.damage(1);
                    ended = true;
                    Destroy();
                }
        }
    }
}
