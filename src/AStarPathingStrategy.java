import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.*;

/**
 * given class
 */
class AStarPathingStrategy implements PathingStrategy
{
    /**
     * Method which computes the path of the entity. Implements
     * the A* Pathing Strategy which uses the total distance, heuristic
     * distance and the distance from the start to calculate the
     * path for the entity to take.
     * @param start
     * @param end
     * @param canPassThrough
     * @param withinReach
     * @param potentialNeighbors
     * @return
     */
    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {
        /* closed list implemented using a hash map */
        /* constant time access */
        Map<Point, AStarNode> closeM =
                new HashMap<Point, AStarNode>();
        Map<Point, AStarNode> openM = new HashMap<Point, AStarNode>();
        /* open list, uses a priority queue */
        /* sorts it for you */
        Queue<AStarNode> open =
                new PriorityQueue<AStarNode>(Comparator.comparingInt(AStarNode::getF));
        open.add(new AStarNode(0, Functions.distanceSquared(start, end),
                Functions.distanceSquared(start, end), start, null)); // adds the starting node

        ArrayList<Point> path = new ArrayList<>(); /* the computed path */

        while (!(open.isEmpty()))
        {
            AStarNode currentNode = open.remove();
            if (withinReach.test(currentNode.getPosition(), end))
            {
                return pathComputation(path, currentNode);
            }
            /* using streams to build a list of neighbors (filter, collect) */
            List<Point> streamedNeighbors = potentialNeighbors.apply(currentNode.getPosition())
                    .filter(canPassThrough).filter(p -> !p.equals(start)
                    && !p.equals(end)).collect(Collectors.toList());
            for (Point node : streamedNeighbors)
            {
                if (!closeM.containsKey(node))
                {
                    int incG = 1 + currentNode.getG();
                    if (openM.containsKey(node))
                    {
                        if (incG < openM.get(node).getG())
                        {
                            AStarNode nextNode = new AStarNode(incG, Functions.distanceSquared(node, end),
                                    Functions.distanceSquared(node, end) + incG,
                                    node, currentNode);
                            open.add(nextNode);
                            open.remove(openM.get(node));
                            openM.replace(node, nextNode);
                        }
                    }
                    else
                    {
                        AStarNode neighbor = new AStarNode(1 + currentNode.getG(),
                                Functions.distanceSquared(node, end),
                                1 + currentNode.getG() + Functions.distanceSquared(node, end),
                                node, currentNode);
                        open.add(neighbor);
                        openM.put(node, neighbor);
                    }
                }
                closeM.put(currentNode.getPosition(), currentNode);
            }
        }
        return path;
    }

    /**
     * recursive function to return the computed path.
     * @param path
     * @param node
     * @return
     */
    public List<Point> pathComputation(List<Point> path, AStarNode node)
    {
        path.add(node.getPosition());
        if(node.getPNode() == null)
        {
            Collections.reverse(path);
            return path;
        }
        return pathComputation(path, node.getPNode());
    }
}