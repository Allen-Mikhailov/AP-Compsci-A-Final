public class Rect {
    public int x1, x2;
    public int y1, y2;
    private Rect(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rect fromXYWH(int x, int y, int width, int height)
    {
        // Middle
        return new Rect(x, y, width, height);
    }

    public Rect fromXYXY(int x1, int y1, int x2, int y2)
    {
        return new Rect(x1, y1, x2, y2);
    }
}
