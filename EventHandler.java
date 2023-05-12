import java.util.ArrayList;
import java.util.HashMap;

public class EventHandler extends Component {

    // Arraylist of all event connections
    private static HashMap<String, ArrayList<EventHandler>> eventConnections 
        = new HashMap<String, ArrayList<EventHandler>>();

    // Adds a connection to the array
    public static void AddConnection(String event, EventHandler handler)
    {
        if (!eventConnections.containsKey(event))
            eventConnections.put(event, new ArrayList<EventHandler>());

        eventConnections.get(event).add(handler);
    }

    // removes a connection
    public static void RemoveConnection(String event, EventHandler handler)
    {
        eventConnections.get(event).remove(handler);
    }

    // Handles firing of all of the connections
    public static void FireEvent(String event, Object eventObj)
    {
        if (eventConnections.get(event) != null)
        {
            for (EventHandler handler : eventConnections.get(event))
            {
                handler.parent.OnEvent(event, eventObj);
            }
        }
    }

    // Main constructor
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
        
        super.OnDestroy();
    }
}
