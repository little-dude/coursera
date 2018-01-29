// import java.util.Double;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;


public class PointSET {

    private final SET<Point2D> points;

    // construct an empty set of points 
    public PointSET() {
        points = new SET<>();
    }

    // is the set empty? 
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set 
    public int size() {
        return points.size();
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        points.add(p);
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all points to standard draw 
    public void draw() {
        for (Point2D p: points) {
            p.draw();
        }
    }

    private boolean inRectHV(Point2D p, RectHV rect) {
        return (p.x() >= rect.xmin()
                && p.x() <= rect.xmax()
                && p.y() >= rect.ymin()
                && p.y() <= rect.ymax());
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> pointsInRange = new ArrayList<>();
        for (Point2D p: points) {
            if (inRectHV(p, rect)) {
                pointsInRange.add(p);
            }
        }
        return pointsInRange;
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        Point2D nearest = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Point2D point: points) {
            double distance = p.distanceSquaredTo(point);
            if (distance < minDistance) {
                nearest = point;
                minDistance = distance;
            }
        }
        return nearest;
    }
}
