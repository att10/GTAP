package org.gtap;

import java.util.ArrayList;

/*
 * Simple Graph Object
 * 
 * Uses adjacency list graph structure.
 */

public class SimpleGraph implements Graph {

    ArrayList<Node> nodes;
    ArrayList<Edge> edges;

    public SimpleGraph() {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public boolean addNode(Node n) {

        if (nodes.contains(n)) {
            return false;
        }

        return nodes.add(n);
    }

    public boolean removeNode(Node n) {
        for (Edge e : n.getEdges()) {
            edges.remove(e);
        }

        for (Node neighbor : n.getNeighbors()) {
            neighbor.removeNeighbor(n);
        }
        return nodes.remove(n);
    }

    public boolean addEdge(Edge e) {

        Node node1 = e.getNode1();
        Node node2 = e.getNode2();

        if (edges.contains(e)) {
            return false;
        } else if (!nodes.contains(node1) && !nodes.contains(node2)) {
            return false;
        }

        return edges.add(e);
    }

    public boolean removeEdge(Edge e) {

        if (!edges.contains(e)) {
            return false;
        }

        Node node1 = e.getNode1();
        Node node2 = e.getNode2();

        node1.removeNeighbor(node2);

        // case when we are still drawing the edge
        // node 2 not assigned yet
        if (node2 != null) {
            node2.removeNeighbor(node1);
        }

        return edges.remove(e);
    }

}