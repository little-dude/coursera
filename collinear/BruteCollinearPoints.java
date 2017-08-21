/******************************************************************************
 *  Compilation:  javac BruteCollinearPoints.java
 *  Execution:    java BruteCollinearPoints
 *  Dependencies: Point.java, LineSegment.java
 ******************************************************************************/

import java.util.Vector;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {

    private final Vector<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        segments = new Vector<>();
        for (int i = 0; i < points.length-3; i++) {
            Point p1 = points[i];
            if (p1 == null) {
                throw new IllegalArgumentException();
            }

            second:
            for (int j = i+1; j < points.length-2; j++) {
                Point p2 = points[j];
                if (p2 == null || isSame(p1, p2)) {
                    throw new IllegalArgumentException();
                }
                double slope = p1.slopeTo(p2);

                for (int k = j+1; k < points.length-1; k++) {
                    Point p3 = points[k];
                    if (p3 == null || isSame(p1, p3) || isSame(p2, p3)) {
                        throw new IllegalArgumentException();
                    }
                    if (p1.slopeTo(p3) != slope) {
                        continue;
                    }

                    for (int l = k+1; l < points.length; l++) {
                        Point p4 = points[l];
                        if (p4 == null || isSame(p1, p4) || isSame(p2, p4) || isSame(p3, p4)) {
                            throw new IllegalArgumentException();
                        }
                        if (p1.slopeTo(p4) != slope) {
                            continue;
                        }
                        Point[] arr = {p1, p2, p3, p4};
                        segments.add(new LineSegment(min(arr), max(arr)));
                        // since we know from the sepcs that we don't have more
                        // than 4 points on the same line, we can break out of
                        // the fourth and third loop when we find a segment.
                        // That saves a few iterations and avoids duplicates.
                        break second;
                    }
                }
            }
        }
    }

    private boolean isSame(Point a, Point b) {
        return (a.compareTo(b) == 0);
    }

    private Point min(Point[] points) {
        Point min = points[0];
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (min.compareTo(p) > 0) {
                min = p;
            }
        }
        return min;
    }

    private Point max(Point[] points) {
        Point max = points[0];
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (max.compareTo(p) < 0) {
                max = p;
            }
        }
        return max;
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        // see https://stackoverflow.com/a/7281807/1836144
        return segments.toArray(new LineSegment[segments.size()]);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
