package org.gtap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GCanvas extends JPanel implements MouseListener, MouseMotionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ArrayList<Node> nodeList;
    ArrayList<Edge> edgeList;
    Node currentNode;
    Edge currentEdge;

    int numNodes = 0;

    public GCanvas() {

        nodeList = new ArrayList<Node>();
        edgeList = new ArrayList<Edge>();
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void moveNode(Node n, int x, int y) {
        n.setX(x);
        n.setY(y);
        repaint();
    }

    private Node selectNode(int x, int y) {
        Node current = null;
        for (Node n : nodeList) {
            if (n.contains(x, y)) {
                current = n;
            }
        }
        return current;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (Node n : nodeList) {
            n.paint(g);
        }

        for (Edge e : edgeList) {
            e.paint(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            nodeList.add(new Node(e.getX(), e.getY()));
            numNodes++;
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentNode = this.selectNode(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e) && currentNode != null) {
            currentEdge = new Edge(currentNode, e.getX(), e.getY());
            edgeList.add(currentEdge);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            Node endNode = selectNode(e.getX(), e.getY());
            if (endNode != null) {
                currentEdge.setNode2(endNode);
            } else {
                edgeList.remove(currentEdge);
                currentEdge = null;
            }
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && currentNode != null) {
            this.moveNode(currentNode, e.getX(), e.getY());
        } else if (SwingUtilities.isRightMouseButton(e) && currentNode != null) {
            currentEdge.setX2(e.getX());
            currentEdge.setY2(e.getY());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}