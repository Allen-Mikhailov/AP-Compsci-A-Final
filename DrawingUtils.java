import java.awt.Color;
import java.awt.Graphics;

public class DrawingUtils {

    public static int roundedInt(double d)
    {
        return (int) Math.round(d);
    }

    public static void DrawCircle(Graphics g, Vector2 pos, double diameter, Color color)
    {
        g.fillOval(
            roundedInt(pos.x-diameter/2), 
            roundedInt(pos.y-diameter/2), 
            roundedInt(diameter), 
            roundedInt(diameter)
        );
    }
}
