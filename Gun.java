public class Gun {
    public double fireRate;
    public double bulletSpeed;
    public double bulletSize;
    public double damage;

    public EngineTimer createTimer()
    {
        return new EngineTimer(fireRate, true);
    }

    public void Fire(Entity firedFrom, Vector2 pos)
    {
        Bullet.BulletData bulletData = new Bullet.BulletData();
        bulletData.damage = damage;
        bulletData.size = bulletSize;
        bulletData.speed = bulletSpeed;

        Vector2 mouseDir = Engine.engine.GetMouseDirection();
        Bullet newBullet = new Bullet(firedFrom, pos.add(mouseDir.scale(30)), mouseDir, bulletData);

        Engine.engine.AddEntity(newBullet);
    }
}
