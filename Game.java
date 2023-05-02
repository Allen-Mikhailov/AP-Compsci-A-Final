import javax.swing.plaf.basic.BasicLabelUI;

public class Game {
    private Engine engine;

    public Game(Engine e)
    {
        engine = e;

        Background background = new Background();

        Player player = new Player();

        Camera camera = new Camera(player);
        engine.SetCamera(camera);

        e.AddEntity(background);
        e.AddEntity(player);
        e.AddEntity(camera);
    }
}
