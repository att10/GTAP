
package org.gtap;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;

public class Node {

    private int xPos, yPos; // Coordinates of center (ideally)
    private int width = 20, height = 20;

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean contains(int x, int y) {
        return new Ellipse2D.Double(xPos, yPos, width, height).contains(new Point(x, y));
    }

    public void moveTo(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(xPos, yPos, width, height);
    }

}