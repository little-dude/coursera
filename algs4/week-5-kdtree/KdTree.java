import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;


public class KdTree {

    private Node root;
    private int size;

    private static class Node {
        // the point
        private final Point2D p;
        // the axis-aligned rectangle corresponding to this node
        private final RectHV rect;
        // the left/bottom subtree
        private Node lb;
        // the right/top subtree
        private Node rt;

        public Node(Point2D point, RectHV rectangle) {
            p = point;
            rect = rectangle;
            lb = null;
            rt = null;
        }

        public Point2D point() {
            return p;
        }

        public double x() {
            return p.x();
        }

        public double y() {
            return p.y();
        }
    }

    // construct an empty set of points 
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty? 
    public boolean isEmpty() {
        return (size == 0);
    }

    // number of points in the set 
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            root = new Node(p, new RectHV(0, 0, 1, 1));
            size++;
        } else {
            insertV(p, root);
        }
    }

    private void insertH(Point2D p, Node tree) {
        if (p.equals(tree.point())) {
            return;
        }

        if (p.y() < tree.y()) {
            if (tree.lb == null) {
                tree.lb = new Node(p, new RectHV(
                            tree.rect.xmin(),
                            tree.rect.ymin(),
                            tree.rect.xmax(),
                            tree.y()));
                size++;
            } else {
                insertV(p, tree.lb);
            }
        } else {
            if (tree.rt == null) {
                tree.rt = new Node(p, new RectHV(
                            tree.rect.xmin(),
                            tree.y(),
                            tree.rect.xmax(),
                            tree.rect.ymax()));
                size++;
            } else {
                insertV(p, tree.rt);
            }
        }
    }

    private void insertV(Point2D p, Node tree) {
        if (p.equals(tree.point())) {
            return;
        }

        if (p.x() < tree.x()) {
            if (tree.lb == null) {
                tree.lb = new Node(p, new RectHV(
                            tree.rect.xmin(),
                            tree.rect.ymin(),
                            tree.x(),
                            tree.rect.ymax()));
                size++;
            } else {
                insertH(p, tree.lb);
            }
        } else {
            if (tree.rt == null) {
                tree.rt = new Node(p, new RectHV(
                            tree.x(),
                            tree.rect.ymin(),
                            tree.rect.xmax(),
                            tree.rect.ymax()));
                size++;
            } else {
                insertH(p, tree.rt);
            }
        }
    }

    // does the set contain point p? 
    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        int level = 0;
        Node tree = root;
        while (true) {
            if (tree == null) {
                return false;
            }
            if (tree.point().equals(p)) {
                return true;
            }
            if (level % 2 == 0) {
                if (p.x() < tree.x()) {
                    tree = tree.lb;
                } else {
                    tree = tree.rt;
                }
            } else {
                if (p.y() < tree.y()) {
                    tree = tree.lb;
                } else {
                    tree = tree.rt;
                }
            }
            level++;
        }
    }

    // draw all points to standard draw 
    public void draw() {
        drawV(root);
    }

    private void drawPoint(Node n) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(n.x(), n.y());
    }

    private void drawH(Node n) {
        if (n == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius();
        StdDraw.line(n.rect.xmin(), n.y(), n.rect.xmax(), n.y());
        drawPoint(n);
        drawV(n.lb);
        drawV(n.rt);
    }

    private void drawV(Node n) {
        if (n == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius();
        StdDraw.line(n.x(), n.rect.ymin(), n.x(), n.rect.ymax());
        drawPoint(n);
        drawH(n.lb);
        drawH(n.rt);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> pointsInRange = new ArrayList<>();
        range(rect, root, pointsInRange);
        return pointsInRange;
    }

    private void range(RectHV rect, Node tree, ArrayList<Point2D> points) {
        if (tree == null) {
            return;
        }
        if (rect.contains(tree.point())) {
            points.add(tree.point());
        }
        if (tree.lb != null && rect.intersects(tree.lb.rect)) {
            range(rect, tree.lb, points);
        }
        if (tree.rt != null && rect.intersects(tree.rt.rect)) {
            range(rect, tree.rt, points);
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return nearest(root, p, null, Double.POSITIVE_INFINITY);
    }

    private Point2D nearest(Node tree, Point2D p, Point2D min, double minDistance) {
        if (tree == null) {
            return null;
        }
        double distance = tree.point().distanceSquaredTo(p);
        if (distance < minDistance) {
            min = tree.point();
            minDistance = distance;
        }

        if (tree.lb == null && tree.rt == null) {
            return min;
        }

        double lbDistance = Double.POSITIVE_INFINITY;
        if (tree.lb != null) {
            lbDistance  = tree.lb.rect.distanceSquaredTo(p);
        }

        double rtDistance = Double.POSITIVE_INFINITY;
        if (tree.rt != null) {
            rtDistance  = tree.rt.rect.distanceSquaredTo(p);
        }

        Node first, second;
        if (rtDistance < minDistance && rtDistance <= lbDistance) {
            first = tree.rt;
            second = tree.lb;
        } else if (lbDistance < minDistance && lbDistance <= rtDistance) {
            first = tree.lb;
            second = tree.rt;
        } else {
            return min;
        }

        // get the min of the first sub-tree
        Point2D stMin = nearest(first, p, min, minDistance);
        if (stMin != min) {
            minDistance = stMin.distanceSquaredTo(p);
            min = stMin;
        }

        // if the distance between the point and the second sub-tree if lower
        // than the current min, explore it.
        if (second != null  && second.rect.distanceSquaredTo(p) < minDistance) {
            stMin = nearest(second, p, min, minDistance);
            if (stMin != min) {
                min = stMin;
            }
        }

        return min;
    }
}
