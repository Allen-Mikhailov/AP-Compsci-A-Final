public interface Collider {
    public double radius();
    public double weight();
    public boolean isHard();
    public void onTouch(Entity e);
}
