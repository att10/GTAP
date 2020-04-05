package org.gtap;

import java.util.LinkedList;

public class GUndoManager {

    LinkedList<GraphElement> undoStack;
    SimpleGraph graph;

    public GUndoManager(SimpleGraph g) {
        undoStack = new LinkedList<GraphElement>();
        graph = g;
    }

    public void add(GraphElement e) {
        undoStack.push(e);
    }

    public void undo() {

        if (!undoStack.isEmpty()) {
            GraphElement top = undoStack.pop();
            if (top.node != null) {
                graph.removeNode(top.node);
            } else {
                graph.removeEdge(top.edge);
            }
            top = null;
        }
    }

}