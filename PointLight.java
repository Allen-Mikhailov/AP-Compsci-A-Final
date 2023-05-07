import java.awt.*;

public class PointLight extends Component {
    private PointLightData d;


    public static class PointLightData
    {
        public double minIntensity = .0;
        public double maxIntensity = .1;
        public double folds = 20;
        public double diameter = 500;
        public Color color = new Color(255, 170, 51);
    }

    public void setData(PointLightData data)
    {
        this.d = data;
    }

    public PointLight(Entity e)
    {
        super(e);
        setData(new PointLightData());
    }

    public PointLight(Entity e, PointLightData data)
    {
        super(e);
        setData(data);
    }

    public void prerender(Graphics g, Vector2 cameraPos)
    {
        Vector2 localPos = parent.pos.sub(cameraPos);
        // System.out.println(localPos);

        double alpha = (d.maxIntensity-d.minIntensity)*255;


        DrawingUtils.DrawCircle(g, localPos, d.diameter, new Color(
            d.color.getRed(), // r
            d.color.getGreen(), // g
            d.color.getBlue(), // b
            DrawingUtils.roundedInt(d.minIntensity*255)
        ));

        for (int i = 1; i < d.folds; i++)
        {
            DrawingUtils.DrawCircle(g, localPos, d.diameter*((double)i/d.folds), new Color(
                d.color.getRed(), // r
                d.color.getGreen(), // g
                d.color.getBlue(), // b
                DrawingUtils.roundedInt(alpha * (Math.pow((double)i/d.folds, 2)) - Math.pow((double)(i+1)/d.folds, 2))
            ));
        }
    }
}
