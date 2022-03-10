import java.util.*;
import processing.core.PImage;

public abstract class MovingEntity extends Entity{
    private int animationPeriod;

    public MovingEntity(String id, Point position, List<PImage> images, int animationPeriod)
    {
        super(id, position, images, 0);
        this.animationPeriod = animationPeriod;
    }
    public int getAnimationPeriod()
    {
        return this.animationPeriod;
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Functions.createAnimationAction(this, 0),
                getAnimationPeriod());
    }
}
