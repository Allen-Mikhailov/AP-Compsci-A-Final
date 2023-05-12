public class Game {
    private Engine engine;

    // Enemy spawn time
    final double spawnTime = 1.75;

    // Nessesary Entities
    Player player;
    Camera camera;
    EngineTimer enemySpawns;

    public Game(Engine e)
    {
        engine = e;

        // Loading assets
        AssetLoader.LoadImage("Explosion", "./Assets/Explosion.png");
        SoundHandler.LoadSound("Bang", "./Assets/Bang.wav");

        // Creating main entities
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
        // Collisions
        Collider.handleCollisions();
        Collider.handleCollisions();
        Collider.handleCollisions();

        // Enemy spawning
        if (enemySpawns.isReady())
        {
            Vector2 pos = player.pos.add(Vector2.polar(500, Engine.GameTime*3));


            Enemy enemy = new Enemy(player, pos);
            engine.AddEntity(enemy);
            enemySpawns.reset();
        }
    }
}
