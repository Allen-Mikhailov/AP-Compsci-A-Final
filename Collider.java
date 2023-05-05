import java.util.ArrayList;

public class Collider extends Component {

    private double radius = 25;
    private double weight = 1;
    public boolean movable = true;

    private static ArrayList<Entity> colliders = new ArrayList<Entity>();

    public Collider(Entity e)
    {
        super(e);
        colliders.add(e);
    }
    public Collider(Entity e, double radius, double weight)
    {
        super(e);
        this.radius = radius;
        this.weight = weight;
        colliders.add(e);
    }

    public static void handleCollisions()
    {
        for (int i = 0; i < colliders.size(); i++)
        {
            Entity e1 = colliders.get(i);
            for (int j = i+1; j < colliders.size(); j++)
            {
                Entity e2 = colliders.get(j);

                Collider c1 = (Collider) e1.GetComponent(Collider.class);
                Collider c2 = (Collider) e2.GetComponent(Collider.class);

                Vector2 dif = e2.pos.sub(e1.pos);
                double distance = dif.magnitude();
                double distanceDif = c1.radius + c2.radius - distance;

                if ( distanceDif > 0)
                {
                    e1.OnEvent("Collider.Touch", e2);
                    e2.OnEvent("Collider.Touch", e1);
                    if (c2.movable && c1.movable || true)
                    {
                        double weightRatio = c1.weight/(c1.weight+c2.weight);

                        e1.pos = e1.pos.sub(dif.normalize().scale(distanceDif/2*(1-weightRatio)));
                        e2.pos = e2.pos.add(dif.normalize().scale(distanceDif/2*weightRatio));
                    }
                }
            }
        }
    }

    @Override
    public void update()
    {

    }

    @Override
    public void OnDestroy() {
        colliders.remove(parent);
    }
}
