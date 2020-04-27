package org.gtap;

public interface Edit {

    public void undo();

    public void redo();

    public String getName();
}