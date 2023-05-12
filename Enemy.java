import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Mortal {

    // Properties
    private Entity target;

    private double size = 50;
    private double speed = 2;
    private double rotation = Math.random()*Math.PI*2;
    private double rotationSpeed = .025;

    public Enemy(Mortal target, Vector2 pos) {
        this.target = target;
        this.pos = pos;

        // Creating a collider
        Collider newCollider = new Collider(this);
        this.AddComponent(newCollider);
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        // Local position
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        Vector2 vectorPos = new Vector2(lx, ly);

        // Drawing the main body
        DrawingUtils.DrawCircle(g, vectorPos, (int) size+4, Color.black);

        Color midColor = new Color(.8f*(float)(5-health)/5, .8f*(float)health/5, 0);
        DrawingUtils.DrawCircle(g, vectorPos, (int) size, midColor);

        // Eyes
        Vector2 normal = target.pos.sub(pos).normalize();
        double angle = Math.atan2(normal.y, normal.x);

        // Eye variables
        double eyeDistance = 10;
        double eyesWidth = 20;
        double eyeSize = 10;
        double pupileSize = 5;

        // Position vectors
        Vector2 eyeCenter = vectorPos.add(Vector2.polar(eyeDistance, rotation));
        Vector2 leftEyeCenter = eyeCenter.add(Vector2.polar(eyesWidth/2, rotation + Math.PI/2));
        Vector2 rightEyeCenter = eyeCenter.add(Vector2.polar(eyesWidth/2, rotation - Math.PI/2));

        Vector2 leftPupal = leftEyeCenter.add(Vector2.polar(eyeSize/2-pupileSize/2, angle));
        Vector2 rightPupal = rightEyeCenter.add(Vector2.polar(eyeSize/2-pupileSize/2, angle));

        // Border of the eye
        DrawingUtils.DrawCircle(g, leftEyeCenter, (int) eyeSize+2, Color.black);
        DrawingUtils.DrawCircle(g, rightEyeCenter, (int) eyeSize+2, Color.black);

        // Center of Eye
        DrawingUtils.DrawCircle(g, leftEyeCenter, (int) eyeSize, Color.white);
        DrawingUtils.DrawCircle(g, rightEyeCenter, (int) eyeSize, Color.white);

        // Pupals
        DrawingUtils.DrawCircle(g, leftPupal, (int) pupileSize, Color.black);
        DrawingUtils.DrawCircle(g, rightPupal, (int) pupileSize, Color.black);
    }

    public double getSign(double value)
    {
        // Gets the sign of a double (1/-1)
        if (value == 0)
            return 1;
        return value/Math.abs(value);
    }

    @Override
    public void update() {
        // Getting direction to the target
        Vector2 dir = target.pos.sub(pos).normalize();

        final double Pi2 = Math.PI*2;

        // Moving
        pos = pos.add(dir.scale(speed));

        // Direction calculations
        Vector2 normal = target.pos.sub(pos).normalize();
        double angle = Math.atan2(normal.y, normal.x);

        double rawDif = Math.abs(angle - rotation);
        double direction = getSign(angle-rotation);
        if (rawDif > Math.PI)
        {
            rawDif = Pi2-rawDif;
            direction *= -1;
        }
            
        double dif = Math.min(rotationSpeed, rawDif);
        rotation = (Math.PI*2+ rotation+dif*direction)%(Math.PI*2);

    }

    @Override
    public void onDeath() {
        // On death it explodes
        Particle particle = new Particle("Explosion", pos, .08, .5);
        Engine.engine.AddEntity(particle);

        super.onDeath();
    }
}
