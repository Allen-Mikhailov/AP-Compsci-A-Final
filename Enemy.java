import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Mortal {
    private Entity target;

    private double size = 50;
    private double speed = 2;

    public Enemy(Mortal target, Vector2 pos) {
        this.target = target;
        this.pos = pos;

        Collider newCollider = new Collider(this);
        this.AddComponent(newCollider);
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        g.setColor(Color.green);
        g.fillOval((int)(lx-size/2), (int)(ly-size/2), (int)(size), (int)(size));
    }

    @Override
    public void update() {
        Vector2 dir = target.pos.sub(pos).normalize();

        pos = pos.add(dir.scale(speed));
    }
}
