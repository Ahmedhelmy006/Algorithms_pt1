package ThirdWeek;
import java.util.Arrays;
import java.util.ArrayList;


public class BruteCollinearPoints {
    private int numOfSegments;
    private LineSegment[] segments;
    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if(points == null){
            throw new NullPointerException("There is no Point");
        }
        for(Point p: points){
            if(p == null){
                throw new NullPointerException("There is no Point");
            }
        }
        int numOfPoints = points.length;
        Point[] newPoints = points.clone();
        Arrays.sort(newPoints);

        for(int i = 0; i < numOfPoints - 1; i++){
            if(newPoints[i].compareTo(newPoints[i + 1]) == 0){
                throw new IllegalArgumentException("Duplicates are Found");
            }
        }
        // I would never commit such a crime unless I am asked, which is exactly what happened. Excuse the mess.
        for (int i = 0; i < numOfPoints; i++) {
            for (int j = i+1; j < numOfPoints; j++) {
                for (int k = j +1; k < numOfPoints; k++) {
                    Point p = newPoints[i], q = newPoints[j], r = newPoints[k];
                    double PQ = p.slopeTo(q);
                    double PR = p.slopeTo(r);
                    if(PQ == PR){
                        for (int l = k +1; l < numOfPoints; l++) {
                            Point s = newPoints[l];
                            double PS = p.slopeTo(s);
                            if (PQ == PS) {
                                LineSegment segment = new LineSegment(p, s);
                                segmentsList.add(segment);
                                numOfSegments++;
                            }
                            
                        }
                    }
                }
            }
        }
        segments = segmentsList.toArray(new LineSegment[numOfSegments]);

    }

    public int numberOfSegments() {
        return numOfSegments;
    }

    public LineSegment[] segments() {
        return segments;
    }
}