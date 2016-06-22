import java.awt.*;

/**
 * Created by brandonbeckwith on 6/20/16.
 *
 * Just extended Point and implemented the comparable interface
 */
public class PointC extends Point implements Comparable<PointC> {

    //Used to store the slope for the compareTo method
    private double slope = 0;

    //Call the parent constructor method
    public PointC(int x, int y){
        super(x,y);
    }

    /**
     * Using this point as the reference compares p1 and p2.
     * p1 with either be inline with (parallel,
     * to the left of (counterclockwise),
     * or to the right of (clockwise) p2
     *
     * @param p1 The point that we use as the reference line
     * @param p2 The point that we want to find the relative position of
     * @return 1 for clockwise, -1 for counter clockwise, 0 for parallel.
     */
    public int orientation(PointC p1, PointC p2){
        double xShift = this.getX() * -1;
        double yShift = this.getY() * -1;
        double val1 = (p1.getY() + yShift)* (p2.getX() + xShift);
        double val2 = (p1.getX() + xShift) * (p2.getY() + yShift);

        if (val1 > val2){
            return -1; //Counter clockwise
        } else if (val1 < val2){
            return 1; //Clockwise
        } else {
            return 0; // Parallel
        }
    }

    public int compareTo(PointC point) {
        if (this.getSlope() > point.getSlope() ){
            return 1;
        } else if (this.getSlope() < point.getSlope()){
            return -1;
        }
        return 0;
    }

    /**
     * Calculates, stores and returns the slope
     * @param point
     * @return
     */
    public double calculateSlope(PointC point){
        this.slope = ((point.getY() - this.getY())/(point.getX() - this.getX()));
        return this.slope;
    }

    public double getSlope(){
        return this.slope;
    }
}
