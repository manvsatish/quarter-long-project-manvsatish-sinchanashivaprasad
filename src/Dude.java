import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Dude extends Move{
    private int resourceLimit;
    public Dude(String id,
                Point position,
                List<PImage> images,
                int actionPeriod,
                int animationPeriod,
                int resourceLimit) {
        super(id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit() {
        return resourceLimit;
    }

    @Override
    protected abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    @Override
    protected abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);

    public void transformMoleRat(WorldModel world, EventScheduler scheduler, ImageStore imageStore, Point pressed) {
        MoleRat moleRat = Factory.createMoleRat(Functions.MOLE_RAT_KEY, pressed,
                Functions.MOLE_RAT_ACTION_PERIOD,
                Functions.MOLE_RAT_ANIMATION_PERIOD,
                imageStore.getImageList(Functions.MOLE_RAT_KEY));

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(moleRat);
        moleRat.scheduleActions(scheduler, world, imageStore);
    }
}
