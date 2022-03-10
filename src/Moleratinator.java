import processing.core.PImage;

import java.util.List;

public class Moleratinator extends MovingEntity{
    public Moleratinator(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }
}
