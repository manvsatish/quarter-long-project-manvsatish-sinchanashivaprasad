import processing.core.PImage;

import java.util.List;

/**
 * Entities that are animated extend this abstract class.
 */
public abstract class AnimatedEntity extends MovingEntity {
    private int actionPeriod;
    public AnimatedEntity(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod)
    {
        super(id, position, images, animationPeriod);
        this.actionPeriod = actionPeriod;
    }

    public int getActionPeriod()
    {
        return this.actionPeriod;
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                Functions.createActivityAction(this, world, imageStore),
                this.actionPeriod);
        super.scheduleActions(scheduler, world, imageStore);
    }


    protected abstract void executeActivity (WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}