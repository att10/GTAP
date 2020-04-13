package org.gtap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GCanvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    SimpleGraph graph;
    GUndoManager undoManager;
    Node currentNode;
    Edge currentEdge;

    int numNodes = 0;

    public GCanvas() {

        graph = new SimpleGraph();
        undoManager = new GUndoManager(graph);
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
    }

    private void moveNode(Node n, int x, int y) {
        n.setX(x);
        n.setY(y);

        ArrayList<Edge> edges = n.getEdges();
        for (Edge e : edges) {
            e.nodeMoved(n);
        }

        repaint();
    }

    private Node selectNode(int x, int y) {
        Node current = null;
        for (Node n : graph.getNodes()) {
            if (n.contains(x, y)) {
                current = n;
            }
        }
        return current;
    }

    public void removeNode(Node n) {
        graph.removeNode(n);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        for (Edge e : graph.getEdges()) {
            e.paint(g2);
        }
        for (Node n : graph.getNodes()) {
            n.paint(g2);
        }

        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            currentNode = new Node(e.getX(), e.getY());
            graph.addNode(currentNode);
            undoManager.add(new GraphElement(currentNode, null));
            currentNode = null;
            numNodes++;
            repaint();
        }

        // FOR TESTING
        // System.out.println(nodeList);
        // System.out.println(edgeList);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentNode = this.selectNode(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e) && currentNode != null) {
            currentEdge = new Edge(currentNode, e.getX(), e.getY());
            graph.addEdge(currentEdge);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (SwingUtilities.isRightMouseButton(e)) {

            // get the second node the edge will be attached to
            Node endNode = selectNode(e.getX(), e.getY());

            // check if it is valid, if not remove, if yes add
            if (endNode == null || currentNode.hasNeighbor(endNode) || currentNode == endNode) {
                graph.removeEdge(currentEdge);
            } else {
                currentEdge.setNode2(endNode);
                undoManager.add(new GraphElement(null, currentEdge));
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

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_Z) && (e.isControlDown())) {
            undoManager.undo();
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}