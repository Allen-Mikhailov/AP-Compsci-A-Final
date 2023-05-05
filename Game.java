public class Game {
    private Engine engine;

    final double spawnTime = 2.25;

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
        Collider.handleCollisions();
        Collider.handleCollisions();

        if (enemySpawns.isReady())
        {
            Vector2 pos = player.pos.add(Vector2.polar(500, Engine.GameTime*3));


            Enemy enemy = new Enemy(player, pos);
            engine.AddEntity(enemy);
            enemySpawns.reset();
        }
    }
}
