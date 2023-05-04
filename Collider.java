import java.util.ArrayList;

public class Collider extends Component {

    private double radius = 50;
    private double weight = 1;
    private boolean movable = true;

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

    public static void frame()
    {
        for (Entity e1 : colliders)
        {
            for (Entity e2 : colliders)
            {
                if (e1 == e2)
                    continue;

                Collider c1 = (Collider) e1.GetComponent(Collider.class);
                Collider c2 = (Collider) e2.GetComponent(Collider.class);

                Vector2 dif = e2.pos.sub(e1.pos);
                double distance = dif.magnitude();
                double distanceDif = c1.radius + c2.radius - distance;

                if ( distanceDif > 0 )
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
