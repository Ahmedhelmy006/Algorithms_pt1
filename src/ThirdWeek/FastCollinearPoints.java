package ThirdWeek;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private int numOfSegments;
    private LineSegment[] segments;
    private final ArrayList<LineSegment> segmentsList = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if(points == null){
            throw new NullPointerException("There is no Point");
        }
        for(Point p: points){
            if(p == null){
                throw new NullPointerException("There is no Point");
            }
        }

        Point[] newPoints = points.clone();
        Arrays.sort(newPoints);
        int numOfPoints = newPoints.length;
        for(int i = 0; i < numOfPoints - 1; i++){
            if(newPoints[i].compareTo(newPoints[i + 1]) == 0){
                throw new IllegalArgumentException("Duplicates are Found");
            }
        }
        for(int i=0; i< numOfPoints; i++){
            for(int j = i +1; j < numOfPoints; j++){

            }
        }

    }
    public int numberOfSegments(){

        return 0;
    }
    public LineSegment[] segments(){

        return new LineSegment[0];
    }
}