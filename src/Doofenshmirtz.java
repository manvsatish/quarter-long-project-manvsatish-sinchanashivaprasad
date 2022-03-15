import processing.core.PImage;

import java.util.List;

public class Doofenshmirtz extends MovingEntity{

    public Doofenshmirtz(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }
}
