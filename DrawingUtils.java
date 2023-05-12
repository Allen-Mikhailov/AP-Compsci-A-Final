import java.awt.Color;
import java.awt.Graphics;

public class DrawingUtils {

    // Rounds an int
    public static int roundedInt(double d)
    {
        return (int) Math.round(d);
    }

    // Drawing a circle from the center point
    public static void DrawCircle(Graphics g, Vector2 pos, double diameter, Color color)
    {
        g.setColor(color);
        g.fillOval(
            roundedInt(pos.x-diameter/2), 
            roundedInt(pos.y-diameter/2), 
            roundedInt(diameter), 
            roundedInt(diameter)
        );
    }
}
