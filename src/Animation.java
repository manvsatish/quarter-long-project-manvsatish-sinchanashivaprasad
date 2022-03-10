/**
 * Animation that can be done by class.
 */
public class Animation implements Action {
    private MovingEntity entity;
    private int repeatCount;

    public Animation(
            MovingEntity entity,
            int repeatCount)
    {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    @Override
    public void executeAction(EventScheduler scheduler)
    {
        entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity,
                    Functions.createAnimationAction(entity,
                            Math.max(this.repeatCount - 1,
                                    0)),
                    entity.getAnimationPeriod());
        }
    }
}