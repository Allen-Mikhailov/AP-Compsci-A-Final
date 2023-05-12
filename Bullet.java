import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Entity  {
    // Properties
    Vector2 velocity;
    Entity FiredFrom;
    boolean ended = false;
    BulletData data;

    
    // Class for the data of a bullet
    public static class BulletData {
        public double speed, damage, size;
    }

    public Bullet(Entity FiredFrom, Vector2 pos, Vector2 direction, BulletData data)
    {
        // Setting properties
        this.pos = pos;
        this.velocity = direction.scale(data.speed);
        this.FiredFrom = FiredFrom;

        // Collider
        Collider newCollider = new Collider(this, data.size, .1);
        newCollider.movable = false;
        this.AddComponent(newCollider);
        this.data = data;
    }

    @Override
    public void update() {
        // Moving
        pos = pos.add(velocity);

        // If to far away then destory
        if (Engine.engine.camera.pos.sub(pos).magnitude() > 5000)
            Destroy();
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        // Getting the local position
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        // Drawing
        g.setColor(Color.black);
        DrawingUtils.DrawCircle(g, new Vector2(lx, ly), data.size, Color.black);
    }

    @Override
    public void OnEvent(String event, Object eventObj) {
        switch (event)
        {
            case "Collider.Touch":
                if (ended) {return;}

                if (eventObj instanceof Mortal && eventObj != FiredFrom)
                {
                    // Doing damage
                    Mortal m = (Mortal) eventObj;
                    m.damage(data.damage);
                    ended = true;

                    // Knockback
                    m.pos = m.pos.add(velocity.normalize().scale(5));

                    // Particle
                    Particle particle = new Particle("Explosion", pos, .04, .25);
                    Engine.engine.AddEntity(particle);

                    Destroy();
                }
        }
    }
}
