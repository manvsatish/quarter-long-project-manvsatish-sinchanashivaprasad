import processing.core.PImage;

import java.util.List;

public class MoleRatInator extends MovingEntity{
    public MoleRatInator(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }
}
