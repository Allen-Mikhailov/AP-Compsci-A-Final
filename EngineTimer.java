public class EngineTimer {
    // Properties
    private double next;
    private double length;

    // Timer
    public EngineTimer(double length)
    {
        this.length = length;
        reset();
    }

    // Main Constructor
    public EngineTimer(double length, boolean startReady)
    {
        this.length = length;

        if (!startReady)
            reset();
    }

    // Checking if the timer is ready
    public boolean isReady()
    {
        return Engine.GameTime >= next;
    }

    // Resetting the timer
    public void reset()
    {
        next = Engine.GameTime + length;
    }
}
