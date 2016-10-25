package ru.stqa.pft.sandbox;

/**
 * Created by Александр on 25.10.2016.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
    public double distance(Point p) {
      return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
    }

}
