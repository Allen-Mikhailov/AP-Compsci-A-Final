import java.awt.Graphics;

public class Component
{
    Entity parent;
    public Component(Entity parent)
    {
        this.parent = parent;
    }

    public void update()
    {
        
    }

    public void prerender(Graphics g, Vector2 cameraPos)
    {
        
    }

    public void render(Graphics g, Vector2 cameraPos)
    {
        
    }

    public void postrender(Graphics g, Vector2 cameraPos)
    {
        
    }

    public void OnDestroy()
    {
        
    }
}