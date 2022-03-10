/**
 * A* data structure has three components: distance from the start,
 * the heuristic distance, and the total distance. Implemented
 * in the form of a node.
 */
public class AStarNode {
    private int g; /* distance from the start */
    private int h; /* the heuristic distance */
    private int f; /* the total distance */
    private AStarNode pNode; /* parent node */
    private Point position; /* position of the node */

    public AStarNode(int g, int h, int f, Point position, AStarNode pNode)
    {
        this.g = g;
        this.h = h;
        this.f = f;
        this.pNode = pNode;
        this.position = position;
    }

    public int getG()
    {
        return this.g;
    }

    public int getF()
    {
        return this.f;
    }

    public void setPosition(Point p)
    {
        this.position = p;
    }

    public Point getPosition()
    {
        return position;
    }

    public AStarNode getPNode()
    {
        return pNode;
    }

    public String toString()
    {
        return "("+ this.position.getX() + ", " + this.position.getY() + ")";
    }

}