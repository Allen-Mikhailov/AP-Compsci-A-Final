public class Game {
    private Engine engine;

    public Game(Engine e)
    {
        engine = e;

        Player player = new Player();

        Camera camera = new Camera(player);
        engine.SetCamera(camera);

        e.AddEntity(player);
        e.AddEntity(camera);
    }
}
