package com.adrianlong.display;

import com.adrianlong.utils.Vector2;
import com.adrianlong.utils.Vector3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends Drawable {
    public List<Vector2> points = new ArrayList<>();

    public Polygon(Vector2... points) {
        this.points.addAll(List.of(points));
    }
    public Polygon(List<Vector2> points) {
        this.points.addAll(points);
    }
    Polygon(Point... points) {
        Vector2[] vpoints = Vector2.convert(points);
        this.points.addAll(List.of(vpoints));
    }
    public void addPoint(Vector2 point) {
        points.add(point);
    }
    public int[] getPoints(String xy) {
        int size = this.points.size();
        int[] points = new int[size];
        if (xy.equalsIgnoreCase("x")) {
            for (int i = 0; i < size; i++) {
                points[i] = (int) this.points.get(i).x;
            }
        } else {
            for (int i = 0; i < size; i++) {
                points[i] = (int) this.points.get(i).y;
            }
        }
        return points;
    }
    @Override
    public void draw(Graphics g) {
        java.awt.Polygon polygon = new java.awt.Polygon(
                getPoints("x"),
                getPoints("y"),
                points.size()
        );
        g.setColor(color);
        if (filled) {
            g.fillPolygon(polygon);
            return;
        }
        g.drawPolygon(polygon);
    }

    @Override
    public boolean isAt(double x, double y) {
        return false;
    }

    @Override
    public void move(double x, double y) {

    }

    @Override
    public void moveTo(double x, double y) {

    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (Vector2 point : points) {
            text.append(point);
        }
        return text.toString();
    }
}
