import processing.core.PImage;

import java.util.List;

public class Doofenschmirtz extends MovingEntity{

    public Doofenschmirtz(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }
}
