/******************************************************************************
 *  Compilation:  javac FastCollinearPoints.java
 *  Execution:    java FastCollinearPoints
 *  Dependencies: Point.java, LineSegment.java
 ******************************************************************************/


import java.util.Vector;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class FastCollinearPoints {
    private final Vector<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        segments = new Vector<>();
        Point[] arr = new Point[points.length];
        for (int j = 0; j < points.length; j++) {
            if (points[j] == null) {
                throw new IllegalArgumentException();
            }
            arr[j] = points[j];
        }

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Arrays.sort(arr, p.slopeOrder());
            if (p.compareTo(arr[1]) == 0) {
                // we know that p[0]==p, because
                // p.slopeTo(p)==Double.NEGATIVE_INFINITY. 
                // So if p[1]==p, we have a duplicate point
                throw new IllegalArgumentException();
            }

            int count = 1;
            double slope = p.slopeTo(arr[0]);
            for (int j = 1; j < points.length; j++) {
                double nextSlope = p.slopeTo(arr[j]);

                if (nextSlope == slope) {
                    count++;

                    if (j < points.length - 1) {
                        continue;
                    } else {
                        j++;
                    }
                }

                if (count >= 3) {
                    Point[] collinearPoints = new Point[count+1];
                    collinearPoints[0] = p;
                    for (int k = 0; k < count; k++) {
                        collinearPoints[k+1] = arr[j-1-k];
                    }

                    Point minPoint = min(collinearPoints);
                    Point maxPoint = max(collinearPoints);
                    if (p.compareTo(minPoint) == 0) {
                        // only add the segment if p is the min. that avoids
                        // duplicates without using extra memory or using a
                        // hash. see:
                        // https://www.coursera.org/learn/algorithms-part1/discussions/weeks/3/threads/YL_ngYEeEearKBKQJr-skQ
                        segments.add(new LineSegment(minPoint, maxPoint));
                    }
                }
                count = 1;
                slope = nextSlope;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        // see https://stackoverflow.com/a/7281807/1836144
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private Point min(Point[] points) {
        Point min = points[0];
        for (int i=1; i < points.length; i++) {
            Point p = points[i];
            if (min.compareTo(p) > 0) {
                min = p;
            }
        }
        return min;
    }

    private Point max(Point[] points) {
        Point max = points[0];
        for (int i=1; i < points.length; i++) {
            Point p = points[i];
            if (max.compareTo(p) < 0) {
                max = p;
            }
        }
        return max;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
