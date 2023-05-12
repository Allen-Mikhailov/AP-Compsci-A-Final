public class Gun {
    // Properties
    public double fireRate;
    public double bulletSpeed;
    public double bulletSize;
    public double damage;
    public double recoil;

    // Fire rate timer
    public EngineTimer createTimer()
    {
        return new EngineTimer(fireRate, true);
    }

    // Firing
    public void Fire(Entity firedFrom, Vector2 pos)
    {
        SoundHandler.PlaySound("Bang");

        // Creating the bullet data
        Bullet.BulletData bulletData = new Bullet.BulletData();
        bulletData.damage = damage;
        bulletData.size = bulletSize;
        bulletData.speed = bulletSpeed;

        // Actually firing the bullet
        Vector2 mouseDir = Engine.engine.GetMouseDirection();
        Bullet newBullet = new Bullet(firedFrom, pos.add(mouseDir.scale(30)), mouseDir, bulletData);

        Engine.engine.AddEntity(newBullet);

        firedFrom.pos = firedFrom.pos.add(mouseDir.scale(-1*recoil));
    }
}
