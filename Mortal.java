public class Mortal extends Entity {

    public double health = 5;

    public double getHealth() {
        return health;
    }

    public void setHealth(double newValue) {
        health = newValue;
    }

    public double damage(double d) {
        setHealth(health - d);
        return 0;
    }
}
