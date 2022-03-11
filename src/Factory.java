import processing.core.PImage;

import java.util.List;

public class Factory {
    public static Entity createHouse(
            String id, Point position, List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Entity createObstacle(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Obstacle(id, position, images,
                animationPeriod);
    }

    public static Entity createTree(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new Tree(id, position, images, actionPeriod, animationPeriod, health);
    }

    public static Entity createStump(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Stump(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static Entity createSapling(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Sapling(id, position, images,
                Functions.SAPLING_ACTION_ANIMATION_PERIOD, Functions.SAPLING_ACTION_ANIMATION_PERIOD, 0, Functions.SAPLING_HEALTH_LIMIT);
    }

    public static Entity createFairy(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Fairy(id, position, images,
                actionPeriod, animationPeriod);
    }

    // need resource count, though it always starts at 0
    public static Entity createDudeNotFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images)
    {
        return new DudeNotFull(id, position, images, resourceLimit, 0,
                actionPeriod, animationPeriod);
    }

    // don't technically need resource count ... full
    public static Entity createDudeFull(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int resourceLimit,
            List<PImage> images) {
        return new DudeFull(id, position, images, resourceLimit,
                actionPeriod, animationPeriod);
    }

    public static PerryThePlatypus createPerry(String id,
                                               Point position,
                                               int actionPeriod,
                                               int animationPeriod,
                                               List<PImage> images)
    {
        return new PerryThePlatypus(id, position, images,
                actionPeriod, animationPeriod);
    }

    public static MoleRat createMoleRat(String id,
                                               Point position,
                                               int actionPeriod,
                                               int animationPeriod,
                                               List<PImage> images)
    {
        return new MoleRat( id, position, images,
                actionPeriod, animationPeriod);
    }


    public static Entity createDoofenshmirtz(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Doofenschmirtz(id, position, images,
                animationPeriod);
    }

    public static Entity createMoleRatInator(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new MoleRatInator(id, position, images,
                animationPeriod);
    }

}
