package ThirdWeek;

import java.util.ArrayList;
import java.util.Arrays;


public class FastCollinearPoints {
    private final ArrayList<LineSegment> segmentsList;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Argument is null");
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("A point is null");
        }
        checkForDuplicatePoints(points);

        int n = points.length;
        segmentsList = new ArrayList<>();

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        for (int i = 0; i < n; i++) {
            Point p = sortedPoints[i];
            Point[] slopeOrderedPoints = sortedPoints.clone();
            Arrays.sort(slopeOrderedPoints, p.slopeOrder());

            int j = 1;
            while (j < n) {
                ArrayList<Point> collinear = new ArrayList<>();
                final double SLOPE = p.slopeTo(slopeOrderedPoints[j]);
                do {
                    collinear.add(slopeOrderedPoints[j++]);
                } while (j < n && p.slopeTo(slopeOrderedPoints[j]) == SLOPE);

                if (collinear.size() >= 3 && p.compareTo(collinear.get(0)) < 0) {
                    collinear.add(0, p);
                    Point min = collinear.get(0);
                    Point max = collinear.get(collinear.size() - 1);
                    segmentsList.add(new LineSegment(min, max));
                }
            }
        }
    }

    private void checkForDuplicatePoints(Point[] points) {
        Point[] copy = points.clone();
        Arrays.sort(copy);
        for (int i = 1; i < copy.length; i++) {
            if (copy[i].compareTo(copy[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }
    }

    public int numberOfSegments() {
        return segmentsList.size();
    }

    public LineSegment[] segments() {
        return segmentsList.toArray(new LineSegment[0]);
    }
}
