package org.gtap;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GCanvas extends JPanel implements MouseListener, MouseMotionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    ArrayList<Node> nodeList;
    Node selected;

    int numNodes = 0;

    public GCanvas() {

        nodeList = new ArrayList<Node>();

        setBorder(BorderFactory.createLineBorder(Color.black));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    private void moveNode(Node n, int x, int y) {
        n.setX(x);
        n.setY(y);
        repaint();
    }

    private void selectNode(int x, int y) {
        selected = null;
        for (Node n : nodeList) {
            if (n.contains(x, y)) {
                selected = n;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (Node n : nodeList) {
            n.paint(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        nodeList.add(new Node(e.getX(), e.getY()));
        numNodes++;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.selectNode(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

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

        if (selected != null) {
            this.moveNode(selected, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}