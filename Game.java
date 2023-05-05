public class Game {
    private Engine engine;

    public Game(Engine e)
    {
        engine = e;

        Background background = new Background();

        Player player = new Player();

        Camera camera = new Camera(player);
        engine.SetCamera(camera);

        Enemy enemy = new Enemy(player, new Vector2(0, 0));

        e.AddEntity(background);
        e.AddEntity(player);
        e.AddEntity(camera);
        e.AddEntity(enemy);
    }

    public void Update()
    {
        Collider.handleCollisions();
    }
}
