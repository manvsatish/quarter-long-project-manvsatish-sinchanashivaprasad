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

    public void transformMoleRat(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        int[] x = {-1, 0, 0, 1};
        int[] y = {0, -1, 1, 0};
        for(int i = 0; i < x.length; i++)
        {
            Point p = new Point(Math.abs(this.getPosition().getX()) + x[i], Math.abs(this.getPosition().getY() + y[i]));
//            testing purposes
//            System.out.println(p.getX() + " " + p.getY());
            if(world.getBackgroundCell(p).getId().equals(Functions.INATOR_KEY))
            {
                MoleRat moleRat = new MoleRat(Functions.MOLE_RAT_KEY,
                        this.getPosition(),
                        imageStore.getImageList(Functions.MOLE_RAT_KEY),
                        Functions.MOLE_RAT_ACTION_PERIOD,
                        Functions.MOLE_RAT_ANIMATION_PERIOD);

                world.removeEntity(this);
                scheduler.unscheduleAllEvents(this);

                world.addEntity(moleRat);
                moleRat.scheduleActions(scheduler, world, imageStore);
            }
        }
    }
}
