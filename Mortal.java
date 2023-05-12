public class Mortal extends Entity {

    public double health = 5;

    // Getter
    public double getHealth() {
        return health;
    }

    // Setter
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

    // Damage
    public double damage(double d) {
        setHealth(health - d);
        return 0;
    }
}
