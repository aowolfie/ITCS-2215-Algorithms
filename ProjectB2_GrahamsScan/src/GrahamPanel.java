import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by brandonbeckwith on 6/21/16.
 *
 */
public class GrahamPanel extends JPanel {

    private PointC[] allPoints;
    private PointC[] hull;
    private int minX, maxX, minY, maxY;
    private GraphInfo info;

    private static final Color POINT_COLOR = Color.BLACK;
    private static final Color AXIS_COLOR = Color.RED;
    private static final Color LINE_COLOR = Color.BLUE;

    public GrahamPanel(GraphInfo info){
        this.info = info;
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

        int border = (int) (((this.getHeight() + this.getWidth()) / 2) * .05);

        int canvasWidth = this.getWidth() - (2 * border);
        int canvasHeight = this.getHeight() - (2 * border);


        /* removed for now because it doesn't align correctly!
        //Draw our axis
        g.setColor(AXIS_COLOR);
        g.drawLine(canvasWidth/2, border, canvasWidth/2, canvasHeight);
        g.drawLine(border, canvasHeight/2, canvasWidth, canvasHeight/2);
        */

        double ratioX = canvasWidth / (maxX - minX);
        double ratioY = canvasHeight / (maxY - minY);

        g.setColor(POINT_COLOR);
        int circleSize = (int) (((this.getHeight() + this.getHeight()) / 2) * .005);

        for (PointC p: allPoints){
            g.fillOval((int) ( ratioX * (p.getX() + border + Math.abs(minX))), (int) (ratioY * (p.getY() + border + Math.abs(minY))), circleSize, circleSize);
        }

        g.setColor(LINE_COLOR);
        for (int i=0; i < hull.length; i++){
            if (i == hull.length - 1){
                g.drawLine((int) ( ratioX * (hull[0].getX() + border + Math.abs(minX))) , (int) ( ratioY * (hull[0].getY() + border + Math.abs(minY))) , (int) ( ratioX * (hull[i].getX() + border + Math.abs(minX))) , (int)( ratioY * (hull[i].getY() + border + Math.abs(minY))) );
            } else {
                g.drawLine((int) ( ratioX * (hull[i].getX() + border + Math.abs(minX))) , (int) ( ratioY * (hull[i].getY() + border + Math.abs(minY))) , (int) ( ratioX * (hull[i+1].getX() + border + Math.abs(minX))) , (int)( ratioY * (hull[i+1].getY() + border + Math.abs(minY))) );
            }
        }
    }
}
