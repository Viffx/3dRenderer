package com.adrianlong;

import com.adrianlong.display.Drawable;
import com.adrianlong.display.Polygon;
import com.adrianlong.utils.Vector2;
import com.adrianlong.utils.Vector3;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Render extends Drawable {
    public Vector3 cameraPos = new Vector3(0, -5, 0);  // Camera at origin
    private Vector3 cameraTarget = new Vector3(0, 0, 1);  // Looking along Z-axis
    private final Vector3 up = new Vector3(0, 1, 0);  // Y-axis is up

    private double fov = Math.toRadians(15);  // 90 degrees field of view
    private double aspectRatio = 16.0 / 9.0;  // Widescreen ratio
    private double near = 0.1;
    private double far = 100;

    private String[] fileLines;
    public List<Vector3> model = new ArrayList<>();
    private List<Integer[]> connections = new ArrayList<>();
    public List<Polygon> shapes = new ArrayList<>();

    Render(Vector3 cameraPos, Vector3 cameraTarget) {
        this.cameraPos = cameraPos;
        this.cameraTarget = cameraTarget;
    }
    public Render() {

    }
    public void rotateModelX(double theta) {
        model.forEach(vector3 -> {
            vector3.rotateX(theta);
        });
    }
    public void rotateModelY(double theta) {
        model.forEach(vector3 -> {
            vector3.rotateY(theta);
        });
    }
    public void rotateModelZ(double theta) {
        model.forEach(vector3 -> {
            vector3.rotateZ(theta);
        });
    }

    public Render readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        fileLines = lines.toArray(new String[0]);
        return this;
    }
    public Render parse() {
        for (String line : fileLines) {
            String[] components = line.split(" ");
            switch (components[0]) {
                case "v" -> {
                    model.add(
                            new Vector3(
                                    Double.parseDouble(components[1]),
                                    Double.parseDouble(components[2]),
                                    Double.parseDouble(components[3])
                            )
                    );
                }
                case "f" -> {
                    Integer[] polygon = new Integer[components.length - 1];
                    for (int i = 1; i < components.length; i++) {
                        String[] data = components[i].split("/");
                        polygon[i-1] = Integer.parseInt(data[0]) - 1;
                    }
                    connections.add(polygon);
                }
            }
        }
        return this;
    }
    public Render run(Dimension windowSize, double scale) {
        shapes = new ArrayList<>();
        for (Integer[] pointNums : connections) {
            List<Vector3> v3points = new ArrayList<>();
            for (Integer pointNum : pointNums) {
                v3points.add(this.model.get(pointNum));
            }
            List<Vector2> v2Points = new ArrayList<>();
            for (Vector3 vector3 : v3points) {
                Vector2 projectedPoint = this.project(vector3).multiply(scale);
                projectedPoint.y *= 0.56;
                Vector2 targetPoint = projectedPoint.add(new Vector2(windowSize).multiply(0.5));
                v2Points.add(targetPoint);
            }
            Polygon polygon = new Polygon(v2Points);
            shapes.add(polygon);
        }
        return this;
    }
    public Vector2 project(Vector3 point) {
        Vector3 forward = cameraTarget.subtract(cameraPos).normalize();
        Vector3 right = up.cross(forward).normalize();
        Vector3 adjustedUp = forward.cross(right).normalize();

        // Transform point from world space to camera space
        Vector3 translated = point.subtract(cameraPos);
        Vector3 cam = new Vector3(
                translated.dot(right),
                translated.dot(adjustedUp),
                translated.dot(forward)
        );

        // Perspective projection calculation
        double fovRad = 1.0 / Math.tan(fov / 2.0);
        Vector3 projection = new Vector3(
                cam.x * fovRad / aspectRatio,
                cam.y * fovRad,
                cam.z * ((far + near) / (near - far)) + ((2 * far * near) / (near - far))
        );

        // Normalize by perspective division
        if (cam.z != 0) {
            projection.x /= cam.z;
            projection.y /= cam.z;
        }

        // Return 2D projected point
        return new Vector2(projection.x, projection.y);
    }

    @Override
    public void draw(Graphics g) {
        for (Polygon shape : shapes) {
            shape.draw(g);
        }
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
        text.append("Points:\n\n");
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < model.size(); i++) {
            StringBuilder line = new StringBuilder();
            line.append(i).append(" ").append(model.get(i)).append("\n");
            lines.add(line.toString());
            text.append(lines.get(i));
        }
        text.append("\nPolygonBuilders:\n\n");

        for (int i = 0; i < connections.size(); i++) {
            StringBuilder point = new StringBuilder();
            for (Integer Int : connections.get(i)) {
                text.append(Int).append(" ");
                point.append(lines.get(Int));
            }
            text.append("\n").append(point).append("\n");
        }

        text.append("Polygons\n\n");

        for (Polygon polygon : shapes) {
            text.append(polygon).append("\n");
        }
        return text.toString();
    }
}
