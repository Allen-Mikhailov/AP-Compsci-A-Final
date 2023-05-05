public class Game {
    private Engine engine;

    final double spawnTime = .25;

    Player player;
    Camera camera;

    EngineTimer enemySpawns;

    public Game(Engine e)
    {
        engine = e;

        Background background = new Background();

        player = new Player();

        camera = new Camera(player);
        engine.SetCamera(camera);

        enemySpawns = new EngineTimer(spawnTime);


        e.AddEntity(background);
        e.AddEntity(player);
        e.AddEntity(camera);
    }

    public void Update()
    {
        Collider.handleCollisions();

        if (enemySpawns.isReady())
        {
            Enemy enemy = new Enemy(player, new Vector2(0, 0));
            engine.AddEntity(enemy);
            enemySpawns.reset();
        }
    }
}
