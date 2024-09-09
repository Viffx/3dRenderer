package com.adrianlong.display;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Display extends JFrame {
    public List<Drawable> shapes = new ArrayList<>();
    public Display(String name, Dimension d) {
        d.width += 16;
        d.height += 39;
        this.setName(name);
        this.setSize(d);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new PaintPanel());
        this.setVisible(true);
    }
    public void draw(Drawable shape) {
        shapes.add(shape);
        repaint();
    }
    public void erase(Drawable shape) {
        shapes.remove(shape);
        repaint();
    }
    public void erase(int i) {
        shapes.remove(i);
        repaint();
    }
    public void eraseAll() {
        shapes = new ArrayList<>();
        repaint();
    }
    class PaintPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            super.setBackground(Color.BLACK);
            // draw all shapes
            try {
                for (Drawable shape : shapes) {
                    shape.draw(g);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
