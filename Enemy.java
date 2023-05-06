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

    public void DrawCircle(Graphics g, Vector2 position, int d)
    {
        int x =  (int) Math.round(position.x);
        int y  = (int) Math.round(position.y);
        g.fillOval(x-d/2, y-d/2, d, d);
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        Vector2 vectorPos = new Vector2(lx, ly);

        g.setColor(Color.black);
        DrawCircle(g, vectorPos, (int) size+4);


        g.setColor(new Color((float)(5-health)/5, (float)health/5, 0));
        DrawCircle(g, vectorPos, (int) size);

        // Eyes
        Vector2 normal = target.pos.sub(pos).normalize();
        double angle = Math.atan2(normal.y, normal.x);

        double eyeDistance = 10;
        double eyesWidth = 20;
        double eyeSize = 10;
        double pupileSize = 5;

        Vector2 eyeCenter = vectorPos.add(Vector2.polar(eyeDistance, angle));
        Vector2 leftEyeCenter = eyeCenter.add(Vector2.polar(eyesWidth/2, angle + Math.PI/2));
        Vector2 rightEyeCenter = eyeCenter.add(Vector2.polar(eyesWidth/2, angle - Math.PI/2));

        Vector2 leftPupal = leftEyeCenter.add(Vector2.polar(eyeSize/2-pupileSize/2, angle));
        Vector2 rightPupal = rightEyeCenter.add(Vector2.polar(eyeSize/2-pupileSize/2, angle));

        g.setColor(Color.black);
        DrawCircle(g, leftEyeCenter, (int) eyeSize+2);
        DrawCircle(g, rightEyeCenter, (int) eyeSize+2);

        g.setColor(Color.white);
        DrawCircle(g, leftEyeCenter, (int) eyeSize);
        DrawCircle(g, rightEyeCenter, (int) eyeSize);

        g.setColor(Color.black);
        DrawCircle(g, leftPupal, (int) pupileSize);
        DrawCircle(g, rightPupal, (int) pupileSize);
    }

    @Override
    public void update() {
        Vector2 dir = target.pos.sub(pos).normalize();

        pos = pos.add(dir.scale(speed));
    }

    @Override
    public void onDeath() {
        
        Particle particle = new Particle("Explosion", pos, .08, .5);
        Engine.engine.AddEntity(particle);

        super.onDeath();
    }
}
