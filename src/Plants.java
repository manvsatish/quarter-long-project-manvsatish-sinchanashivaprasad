import com.sun.jdi.event.MonitorContendedEnteredEvent;
import processing.core.PImage;

import java.util.List;

public abstract class Plants extends AnimatedEntity{
    private int health;
    public Plants(String id, Point position, List<PImage> images, int actionPeriod, int animationPeriod, int health)
    {
        super(id, position, images, actionPeriod, animationPeriod);
        this.health = health;
    }
    public void setHealth(int health)
    {
        this.health += health;
    }

    public int getHealth()
    {
        return this.health;
    }

    protected abstract boolean transformPlant(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
    protected abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);
}