import java.awt.*;

public class Particle extends Entity {
    private Image image;
    private int width, height;
    private EngineTimer timer;

    private void construct(Image img, Vector2 pos, double scale, double lifetime)
    {
        this.pos = pos;
        image = img;
        width  = (int) Math.round( image.getWidth(null)  * scale );
        height = (int) Math.round( image.getHeight(null) * scale );

        timer = new EngineTimer(lifetime);
    }

    public Particle(Image img, Vector2 pos, double scale, double lifetime)
    {   
        construct(img, pos, scale, lifetime);
    }

    public Particle(String img, Vector2 pos, double scale, double lifetime)
    {   
        image = AssetLoader.loadedImages.get(img);
        construct(image, pos, scale, lifetime);
    }

    @Override
    public void update() {
        if (timer.isReady())
            Destroy();
    }

    @Override
    public void render(Graphics g, Vector2 cameraPos) {
        int lx = (int) (pos.x-cameraPos.x);
        int ly = (int) (pos.y-cameraPos.y);

        g.drawImage(image, lx - width/2, ly - height/2, width, height, null);
    }
}
