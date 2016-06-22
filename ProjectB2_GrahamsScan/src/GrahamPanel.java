import javax.swing.*;
import java.awt.*;

/**
 * Created by brandonbeckwith on 6/21/16.
 *
 */
public class GrahamPanel extends JPanel {

    private PointC[] allPoints;
    private PointC[] hull;
    private int minX, maxX, minY, maxY;

    private static final Color POINT_COLOR = Color.BLACK;
    private static final Color AXIS_COLOR = Color.RED;
    private static final Color LINE_COLOR = Color.BLUE;

    public GrahamPanel(GraphInfo info){
        //Used the GraphInfo to populate the variables used to make the graph
        this.allPoints = info.getPoints();
        this.hull = info.getHull();
        this.minX = info.getMinX();
        this.maxX = info.getMaxX();
        this.minY = info.getMinY();
        this.maxY = info.getMaxY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Calculate how big the border should be for the window
        int border = (int) (((this.getHeight() + this.getWidth()) / 2) * .05);

        //Calculate the ratio needed to properly place the points
        double ratioX = (this.getWidth() - (2 * border)) / (maxX - minX);
        double ratioY = (this.getHeight() - (2 * border)) / (maxY - minY);

        //Draw the x and y axis
        g.setColor(AXIS_COLOR);
        g.drawLine((int) ( ratioX * (border + Math.abs(minX))), 0,(int) ( ratioX * (border + Math.abs(minX))), this.getHeight());
        g.drawLine(0, (int) (ratioY * (border + Math.abs(minY))), this.getWidth(), (int) (ratioY * (border + Math.abs(minY))));

        //Draw the points
        g.setColor(POINT_COLOR);
        int circleSize = (int) (((this.getHeight() + this.getHeight()) / 2) * .005);

        for (PointC p: allPoints){
            g.fillOval((int) ( ratioX * (p.getX() + border + Math.abs(minX))), (int) (ratioY * (p.getY() + border + Math.abs(minY))), circleSize, circleSize);
        }

        //Draw the hull
        g.setColor(LINE_COLOR);
        for (int i=0; i < hull.length; i++){
            if (i == hull.length - 1){
                //Used to connect the last point to the first point
                g.drawLine((int) ( ratioX * (hull[0].getX() + border + Math.abs(minX))) , (int) ( ratioY * (hull[0].getY() + border + Math.abs(minY))) , (int) ( ratioX * (hull[i].getX() + border + Math.abs(minX))) , (int)( ratioY * (hull[i].getY() + border + Math.abs(minY))) );
            } else {
                g.drawLine((int) ( ratioX * (hull[i].getX() + border + Math.abs(minX))) , (int) ( ratioY * (hull[i].getY() + border + Math.abs(minY))) , (int) ( ratioX * (hull[i+1].getX() + border + Math.abs(minX))) , (int)( ratioY * (hull[i+1].getY() + border + Math.abs(minY))) );
            }
        }
    }
}
