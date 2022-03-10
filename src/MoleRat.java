import processing.core.PImage;

import java.util.List;

public class MoleRat extends Move{

    public MoleRat(String id,
                   Point position,
                   List<PImage> images,
                   int actionPeriod,
                   int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }

    @Override
    protected boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        return false;
    }
}
