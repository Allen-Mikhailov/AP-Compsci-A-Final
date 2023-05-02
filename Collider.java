public interface Collider {
    public double radius();
    public boolean isHard();
    public void onTouch(Entity e);
}
