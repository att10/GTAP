package org.gtap;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.Graphics;

/*
 * Drawable Node Object.
 */

public class Node {

    ArrayList<Node> neighbors;
    ArrayList<Edge> edges;

    private int xPos, yPos;
    private int radius = 10, diameter = radius * 2;

    public Node(int x, int y) {
        neighbors = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
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

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    public void removeNeighbor(Node n) {
        neighbors.remove(n);
    }

    public boolean hasNeighbor(Node n) {

        for (Node node : neighbors) {
            if (node == n) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(xPos - radius, yPos - radius, diameter, diameter);
        g.setColor(Color.GRAY);
        g.drawOval(xPos - radius, yPos - radius, diameter, diameter);
    }

    public String toString() {
        return "Node";
    }
}