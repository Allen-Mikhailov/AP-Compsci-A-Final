public class Mortal extends Entity {

    public double health = 5;

    public double getHealth() {
        return health;
    }

    public void setHealth(double newValue) {
        if (newValue <= 0)
        {
            health = 0;
            onDeath();
        } else {
            health = newValue;
        }   
    }

    public void onDeath()
    {
        Destroy();
    }

    public double damage(double d) {
        setHealth(health - d);
        return 0;
    }
}
