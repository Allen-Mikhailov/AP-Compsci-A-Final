import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler extends Component {

    private static HashMap<String, ArrayList<EventHandler>> eventConnections 
        = new HashMap<String, ArrayList<EventHandler>>();

    public static void AddConnection(String event, EventHandler handler)
    {
        if (!eventConnections.containsKey(event))
            eventConnections.put(event, new ArrayList<EventHandler>());

        eventConnections.get(event).add(handler);
    }

    public static void RemoveConnection(String event, EventHandler handler)
    {
        eventConnections.get(event).remove(handler);
    }

    public EventHandler(Entity e, String[] events)
    {
        super(e);

        for (String event : events)
        {
            AddConnection(event, this);
        }
    }

    @Override
    public void OnDestroy() {
        // TODO Auto-generated method stub
        super.OnDestroy();
    }
}
