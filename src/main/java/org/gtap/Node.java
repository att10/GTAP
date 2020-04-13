package org.gtap;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.Graphics2D;

/**
 * Drawable Node Object.
 */

public class Node {

    ArrayList<Node> neighbors; // list of adjacent nodes
    ArrayList<Edge> edges; // list of adjacent edges

    private int xPos, yPos; // center of the node 
    private int radius = 15, diameter = radius * 2; // size

    /**
     * Constructor.
    * 
    * @param x x position of the node.
    * @param y y position of the node.
    */
    public Node(int x, int y) {
        neighbors = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        xPos = x;
        yPos = y;
    }

    /**
    * Set the x position of the node. 
    *
    * @param x new x position
    */
    public void setX(int x) {
        xPos = x;
    }

    /**
    * Set the y position of the node. 
    *
    * @param y: new y position.
    */
    public void setY(int y) {
        yPos = y;
    }

    /**
    * Get the x position of the node. 
    *
    * @return x position of node.
    */
    public int getX() {
        return xPos;
    }

    /**
    * Get the y position of the node. 
    *
    * @return y position of node.
    */
    public int getY() {
        return yPos;
    }

    /**
    * Get the position at the center of the node. 
    *
    * @return position at the center of the node.
    */
    public Point getCenter() {
        return new Point(xPos, yPos);
    }

    /**
    * Get the width of the node. 
    *
    * @return width of the node.
    */
    public int getWidth() {
        return diameter;
    }

    /**
    * Get the height of the node. 
    *
    * @return height of the node.
    */
    public int getHeight() {
        return diameter;
    }

    /**
    * Check whether the node contains the point (x, y).
    * 
    * @param x the x value of the point we are checking.
    * @param y the y value of the point we are checking.
    * @return true if (x, y) is contained in the node.
    */
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

    public void paint(Graphics2D g) {
        
        g.setColor(Color.WHITE);
        g.fillOval(xPos - radius, yPos - radius, diameter, diameter);
        g.setColor(Color.GRAY);
        g.drawOval(xPos - radius, yPos - radius, diameter, diameter);
    }

    public String toString() {
        return "Node";
    }
}