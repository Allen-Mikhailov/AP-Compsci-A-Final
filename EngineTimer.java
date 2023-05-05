public class EngineTimer {

    private double next;
    private double length;

    public EngineTimer(double length)
    {
        this.length = length;
        reset();
    }

    public boolean isReady()
    {
        return Engine.GameTime >= next;
    }

    public void reset()
    {
        next = Engine.GameTime + length;
    }
}
