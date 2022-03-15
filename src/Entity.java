import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public abstract class Entity
{
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    public Entity(String id, Point position, List<PImage> images, int imageIndex)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }

    public String getId()
    {
        return this.id;
    }

    PImage getCurrentImage(Object entity)
    {
        if (entity instanceof Background) {
            return images.get(imageIndex);
        }
        else if (entity instanceof Entity) {
            return images.get(imageIndex);
        }
        else {
            throw new UnsupportedOperationException(
                    String.format("getCurrentImage not supported for %s",
                            this));
        }
    }

    public Point getPosition()
    {
        return this.position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public void nextImage()
    {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public List<PImage> getImages()
    {
        return this.images;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public void setImages(List<PImage> images) {
        this.images = images;
    }


}