package org.gtap;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;

public class Node {

    private int xPos, yPos; // Coordinates of center (ideally)
    private int radius = 10, diameter = radius * 2;

    public Node(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public Point getCenter() {
        return new Point(xPos, yPos);
    }

    public int getWidth() {
        return diameter;
    }

    public int getHeight() {
        return diameter;
    }

    public boolean contains(int x, int y) {
        return new Ellipse2D.Double(xPos - radius, yPos - radius, diameter, diameter).contains(new Point(x, y));
    }

    public void moveTo(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(xPos - radius, yPos - radius, diameter, diameter);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(xPos - radius, yPos - radius, diameter, diameter);
    }

}