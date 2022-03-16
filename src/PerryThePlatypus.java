import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PerryThePlatypus extends Move{
    public PerryThePlatypus(String id,
                            Point position,
                            List<PImage> images,
                            int actionPeriod,
                            int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> perryTarget =
                world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(MoleRat.class,
                        HouseBite.class, MajorMonogram.class)));

        if (perryTarget.isPresent()) {
            Point tgtPos = perryTarget.get().getPosition();

            if (moveTo(world, perryTarget.get(), scheduler)) {
                if(perryTarget.get().getClass() == HouseBite.class)
                {
                    House house = (House) Factory.createHouse(Functions.HOUSE_KEY,
                            tgtPos, imageStore.getImageList(Functions.HOUSE_KEY));
                    world.addEntity(house);
                }
                else if(perryTarget.get().getClass() == MoleRat.class)
                {
                    DudeNotFull dude = (DudeNotFull) Factory.createDudeNotFull("dude not full" + this.getId(),
                            tgtPos, Functions.DUDE_ACTION_PERIOD, Functions.DUDE_ANIMATION_PERIOD,
                            5, imageStore.getImageList(Functions.DUDE_KEY));

                    world.addEntity(dude);
                    dude.scheduleActions(scheduler, world, imageStore);
                }
                else
                {
                    MajorMonogram mm = Factory.createMajorMonogram(Functions.MM_KEY,
                            tgtPos, imageStore.getImageList(Functions.MM_KEY));
                    world.addEntity(mm);
                }
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
