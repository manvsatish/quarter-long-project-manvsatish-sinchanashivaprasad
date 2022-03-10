import processing.core.PImage;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class Move extends AnimatedEntity{
    public Move(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }
    protected abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);

    /**
     * Modified method which uses the AStarPathingStrategy to determine the
     * path of the dudes and the fairy.
     * @param world
     * @param destPos
     * @return
     */
    public Point nextPosition(WorldModel world, Point destPos)
    {
        PathingStrategy aStarPath = new AStarPathingStrategy();
        List<Point> points = aStarPath.computePath(getPosition(), destPos,
                canPassThrough(world), withinReach(), PathingStrategy.CARDINAL_NEIGHBORS);
        if (points.isEmpty()) {
            return getPosition();
        }
        return points.get(1);
    }

    /**
     * Determines whether a path is complete when
     * a point is reached that is withinReach of the end point.
     * @return BiPredicate whether path is complete
     */
    private static BiPredicate<Point, Point> withinReach()
    {
        return Point::adjacent;
    }

    /**
     * Determines if a given point can be traversed
     * (i.e., is both a valid position in the world and a location to
     * which the traveler can move)
     * @param world
     * @return Predicate whether can pass through
     */
    private static Predicate<Point> canPassThrough(WorldModel world)
    {
        return point -> !world.isOccupied(point) && world.withinBounds(point);
    }

}