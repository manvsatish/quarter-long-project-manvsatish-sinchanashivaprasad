import java.util.Comparator;

/**
 * Implements interface Event
 */
public final class EventComparator implements Comparator<Event>
{
    public int compare(Event lft, Event rht) {
        return (int)(lft.getTime() - rht.getTime());
    }
}
