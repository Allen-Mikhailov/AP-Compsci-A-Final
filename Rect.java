public class Rect {
    public double x1, x2;
    public double y1, y2;

    public static Rect empty = new Rect(0, 0, 0, 0);

    private Rect(double x1, double y1, double x2, double y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rect fromXYWH(double x, double y, double width, double height)
    {
        // Middle
        return new Rect(x-width/2, y-height/2, x+width/2, y+height/2);
    }

    public Rect fromVectors(Vector2 a, Vector2 b)
    {
        return new Rect(a.x, a.y, b.x, b.y);
    }

    public Rect fromXYXY(double x1, double y1, double x2, double y2)
    {
        return new Rect(x1, y1, x2, y2);
    }

    public double getWidth()
    {
        return x2-x1;
    }

    public double getHeight()
    {
        return y2-y1;
    }
}
