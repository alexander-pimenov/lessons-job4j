package ru.job4j.isp.task;

import java.util.HashSet;
import java.util.StringJoiner;

public class Points {
    private final HashSet<Point> points = new HashSet<>();

    public void add(Point point) {
        points.add(point);
    }

    public static double countDistance(Point point, double a, double b, double c) {
        return (a * point.getX() + b * point.getY() + c) / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    public Point immediatePoint(double a, double b, double c) {
        Point result = fartherPoint(a, b, c);
        double distance = Points.countDistance(result, a, b, c);
        for (Point point : points) {
            if (Points.countDistance(point, a, b, c) < distance) {
                result = point;
                distance = Points.countDistance(point, a, b, c);
            }
        }
        return result;
    }

    public Point fartherPoint(double a, double b, double c) {
        Point result = null;
        double distance = 0;
        for (Point point : points) {
            if (Points.countDistance(point, a, b, c) > distance) {
                result = point;
                distance = Points.countDistance(point, a, b, c);
            }
        }
        return result;
    }

    public void printAllPoints() {
        for (Point point : points) {
            System.out.println(point);
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Point point : points) {
            joiner.add(point.toString());
        }
        return "LinesPoints.Lines:\n" + joiner.toString();
    }
}
