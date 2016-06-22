/**
 * Created by brandonbeckwith on 6/21/16.
 *
 * A helper class used to pass information to the graph, not normally needed for the algorithm
 */
public class GraphInfo {


    private PointC[] hull, points;
    private int minX, maxX, minY, maxY;


    public PointC[] getHull() {
        return hull;
    }

    public void setHull(PointC[] hull) {
        this.hull = hull;
    }

    public PointC[] getPoints() {
        return points;
    }

    public void setPoints(PointC[] points) {
        this.points = points;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = (int) minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = (int) maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = (int) minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = (int) maxY;
    }
}
