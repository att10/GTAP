package org.gtap;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Edge {

    Node node1, node2;
    double x1, x2, y1, y2;

    public Edge(Node n1, Node n2) {
        Point startPoint = n1.getCenter();
        Point endPoint = n1.getCenter();
        x1 = startPoint.getX();
        y1 = startPoint.getY();
        x2 = endPoint.getX();
        y2 = endPoint.getY();

        node1 = n1;
        node2 = n2;

        node1.addEdge(this);
        node2.addEdge(this);
    }

    public Edge(Node n1, int x, int y) {
        Point startPoint = n1.getCenter();
        x1 = startPoint.getX();
        y1 = startPoint.getY();
        x2 = x;
        y2 = y;

        node1 = n1;
        node1.addEdge(this);
    }

    public void setNode2(Node n2) {

        node2 = n2;

        node1.addNeighbor(node2);
        node2.addEdge(this);

        x2 = n2.getX();
        y2 = n2.getY();
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void nodeMoved(Node n) {
        if (n == node1) {
            x1 = n.getX();
            y1 = n.getY();
        } else {
            x2 = n.getX();
            y2 = n.getY();
        }
    }

    public void setX2(int x) {
        x2 = x;
    }

    public void setY2(int y) {
        y2 = y;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Double(x1, y1, x2, y2);
        g2.draw(lin);
    }

    public String toString() {
        return "Edge";
    }
}