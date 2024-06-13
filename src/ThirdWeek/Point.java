package ThirdWeek;


import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if(that == null){
            throw new IllegalArgumentException("There is no Point");
        }

        double nominator = y - that.y;
        double denominator = x - that.x;

        if(x == that.x && y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }
        else if(denominator == 0.0){
            return Double.POSITIVE_INFINITY;
        }
        else if(nominator == 0.0){
            return 0.0;
        }
        return nominator / denominator;
    }

    public int compareTo( Point that){
        if(that == null){
            throw new IllegalArgumentException("There is no Point to Compare");
        }
        if(y > that.y){
            return 1;
        }
        else if(y < that.y){
            return -1;
        }
        else {
            return Integer.compare(x, that.x);
        }
    }

    public Comparator<Point> slopeOrder() {
        return (o1, o2) -> {
            double s1 = slopeTo(o1);
            double s2 = slopeTo(o2);
            return Double.compare(s1, s2);
        };
    }


    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
