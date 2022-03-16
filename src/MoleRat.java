import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Optional<Entity> moleTarget =
                world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(House.class)));

        if (moleTarget.isPresent()) {
            Point tgtPos = moleTarget.get().getPosition();

            if (moveTo(world, moleTarget.get(), scheduler)) {
                HouseBite houseBite = (HouseBite) Factory.createHouseBite(Functions.HOUSEBITE_KEY, tgtPos,
                        imageStore.getImageList(Functions.HOUSEBITE_KEY));
                world.addEntity(houseBite);
            }
        }
        scheduler.scheduleEvent(this,
                Functions.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
    }

    @Override
    protected boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (this.getPosition().adjacent(target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }
                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }
}