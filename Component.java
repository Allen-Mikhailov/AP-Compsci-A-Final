import java.awt.Graphics;

public class Component
{
    // Properties
    Entity parent;
    public Component(Entity parent)
    {
        this.parent = parent;
    }

    // Fired every frame before the render
    public void update()
    {
        
    }

    // rRender layer before most objects
    public void prerender(Graphics g, Vector2 cameraPos)
    {
        
    }

    // Render layer for objects
    public void render(Graphics g, Vector2 cameraPos)
    {
        
    }

    // Render layer for effects
    public void postrender(Graphics g, Vector2 cameraPos)
    {
        
    }

    // On the destruction of this component or object
    public void OnDestroy()
    {
        
    }
}