package com.adrianlong.display;

import java.awt.*;

public abstract class Drawable {
    Color color;
    boolean filled;
    public Drawable setColor(Color color) {
        this.color = color;
        return this;
    }
    public Drawable toggleFill() {
        filled = !filled;
        return this;
    }
    public Drawable noFill() {
        filled = false;
        return this;
    }
    public Drawable fill() {
        filled = true;
        return this;
    }
    public abstract void draw(Graphics g);
    public abstract boolean isAt(double x, double y);
    public abstract void move(double x, double y);
    public abstract void moveTo(double x, double y);
}
