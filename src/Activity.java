
/**
 * Activity that can be done by class.
 */
public class Activity implements Action {
    private Entity entity;
    private WorldModel world;
    private ImageStore imageStore;

    public Activity(
            Entity entity,
            WorldModel world,
            ImageStore imageStore)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    @Override
    public void executeAction(EventScheduler scheduler)
    {
        if(entity.getClass() == Sapling.class)
        {
            ((Sapling) entity).executeActivity(this.world,
                    this.imageStore, scheduler);
        }
        else if (entity.getClass() == Tree.class)
        {
            ((Tree) entity).executeActivity(this.world,
                    this.imageStore, scheduler);
        }
        else if (entity.getClass() == Fairy.class)
        {
            ((Fairy) entity).executeActivity(this.world,
                    this.imageStore, scheduler);
        }
        else if (entity.getClass() == DudeNotFull.class)
        {
            ((DudeNotFull) entity).executeActivity(this.world,
                    this.imageStore, scheduler);
        }
        else if (entity.getClass() == DudeFull.class)
        {
            ((DudeFull) entity).executeActivity(this.world,
                    this.imageStore, scheduler);
        }
        else if(entity.getClass() == PerryThePlatypus.class)
        {
            ((PerryThePlatypus) entity).executeActivity(this.world,
                    this.imageStore, scheduler);
//            ((PerryThePlatypus) entity).executeActivityHouseBite(this.world,
//                    this.imageStore, scheduler);
        }
        else if(entity.getClass() == MoleRat.class)
        {
            ((MoleRat) entity).executeActivity(this.world,
                    this.imageStore, scheduler);
        }
        else
        {
            throw new UnsupportedOperationException(String.format(
                    "executeActivityAction not supported for %s",
                    this.entity.getClass()));
        }

    }
}