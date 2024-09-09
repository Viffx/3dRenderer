package com.adrianlong.utils;

import com.adrianlong.Render;
import com.adrianlong.display.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    // Constructor
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Copy Constructor
    public Vector3(Vector3 vector) {
        this(vector.x, vector.y, vector.z);
    }

    // Conversion Constructor (2D Point to 3D, z defaults to 0)
    public Vector3(Point vector) {
        this(vector.x, vector.y, 0);
    }

    // Default Constructor
    public Vector3() {
        this(0, 0, 0);
    }

    // Create a new random Vector3
    public static Vector3 randomVector3(double max) {
        Random random = new Random();
        return new Vector3(random.nextDouble(max), random.nextDouble(max), random.nextDouble(max));
    }

    // Arrays
    public static Vector3[] convert(Point[] points) {
        List<Vector3> v3Points = new ArrayList<>();
        for (Point p : points) {
            v3Points.add(new Vector3(p));
        }
        return v3Points.toArray(new Vector3[0]);
    }

    // Math

    // Magnitude (Length of the vector)
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Normalize (returns a unit vector)
    public Vector3 normalize() {
        double mag = magnitude();
        if (mag == 0) {
            return new Vector3(0, 0, 0); // Return zero vector if magnitude is 0
        }
        return new Vector3(x / mag, y / mag, z / mag);
    }

    // Add another vector to this vector
    public Vector3 add(Vector3 v) {
        return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    // Subtract another vector from this vector
    public Vector3 subtract(Vector3 v) {
        return new Vector3(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    // Multiply the vector by a scalar
    public Vector3 multiply(double scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // Dot product of this vector and another vector
    public double dot(Vector3 v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    // Cross product of this vector and another vector
    public Vector3 cross(Vector3 v) {
        return new Vector3(
                this.y * v.z - this.z * v.y,
                this.z * v.x - this.x * v.z,
                this.x * v.y - this.y * v.x
        );
    }

    // Distance between two vectors
    public double distanceTo(Vector3 v) {
        double dx = this.x - v.x;
        double dy = this.y - v.y;
        double dz = this.z - v.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    // Rotate around the X-axis by angle theta (in radians)
    public void rotateX(double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        double newY = this.y * cosTheta - this.z * sinTheta;
        double newZ = this.y * sinTheta + this.z * cosTheta;
        this.y = newY;
        this.z = newZ;
    }

    // Rotate around the Y-axis by angle theta (in radians)
    public void rotateY(double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        double newX = this.x * cosTheta + this.z * sinTheta;
        double newZ = -this.x * sinTheta + this.z * cosTheta;
        this.x = newX;
        this.z = newZ;
    }

    // Rotate around the Z-axis by angle theta (in radians)
    public void rotateZ(double theta) {
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);
        double newX = this.x * cosTheta - this.y * sinTheta;
        double newY = this.x * sinTheta + this.y * cosTheta;
        this.x = newX;
        this.y = newY;
    }

    // Override toString for easy printing
    @Override
    public String toString() {
        return "Vector3(" + x + ", " + y + ", " + z + ")";
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
        Vector3 vector3 = (Vector3) obj;
        return Double.compare(vector3.x, x) == 0 &&
                Double.compare(vector3.y, y) == 0 &&
                Double.compare(vector3.z, z) == 0;
    }
}
