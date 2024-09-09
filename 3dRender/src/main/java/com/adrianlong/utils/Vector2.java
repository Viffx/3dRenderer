package com.adrianlong.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Vector2 {
    public double x;
    public double y;

    // Constructor
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Copying Constructor
    public Vector2(Vector2 vector) {
        this(vector.x, vector.y);
    }
    // Conversion Constructor
    public Vector2(Point vector) {
        this(vector.x, vector.y);
    }
    public Vector2(Dimension vector) {
        this(vector.width, vector.height);
    }
    // Default Constructor
    public Vector2() {
        this(0, 0);
    }

    // Create a new random vector
    public static Vector2 randomVector2(double max) {
        Random random = new Random();
        return new Vector2(random.nextDouble(max),random.nextDouble(max));
    }

    // Arrays
    public static Vector2[] convert(Point[] points) {
        List<Vector2> v2Points = new ArrayList<>();
        for (Point p : points) {
            v2Points.add(new Vector2(p));
        }
        return v2Points.toArray(new Vector2[0]);
    }

    // Math

    // Magnitude (Length of the vector)
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Normalize (returns a unit vector)
    public Vector2 normalize() {
        double mag = magnitude();
        if (mag == 0) {
            return new Vector2(0, 0); // Return zero vector if magnitude is 0
        }
        return new Vector2(x / mag, y / mag);
    }

    // Add another vector to this vector
    public Vector2 add(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    // Subtract another vector from this vector
    public Vector2 subtract(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    // Multiply the vector by a scalar
    public Vector2 multiply(double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    // Dot product of this vector and another vector
    public double dot(Vector2 v) {
        return this.x * v.x + this.y * v.y;
    }

    // Distance between two vectors
    public double distanceTo(Vector2 v) {
        double dx = this.x - v.x;
        double dy = this.y - v.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Override toString for easy printing
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")\n";
    }

    // Equality check
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector2 vector2 = (Vector2) obj;
        return Double.compare(vector2.x, x) == 0 &&
                Double.compare(vector2.y, y) == 0;
    }
}
