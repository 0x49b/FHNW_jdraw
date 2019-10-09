/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.std;

import jdraw.framework.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 *
 * @author TODO Florian Thiévent
 */
public class StdDrawModel implements DrawModel {

    private final LinkedList<Figure> figures = new LinkedList<>();
    private CopyOnWriteArrayList<DrawModelListener> listeners = new CopyOnWriteArrayList<>();
    private FigureListener figureListener = e -> notifyListener(e.getFigure(), DrawModelEvent.Type.DRAWING_CHANGED);


    /**
     * Notify all Listeners about a change
     *
     * @param f    figure
     * @param type eventtype
     */
    private void notifyListener(Figure f, DrawModelEvent.Type type) {
        DrawModelEvent dme = new DrawModelEvent(this, f, type);
        listeners.forEach(e -> e.modelChanged(dme));
        // XXX das listeners.forEach führt zu einer CME wenn sich ein Listener während der Notifikation an- oder abmeldet.
    }


    @Override
    public void addFigure(Figure f) {
        if (figures.contains(f)) return;
        figures.add(f);
        f.addFigureListener(figureListener);
        notifyListener(f, DrawModelEvent.Type.FIGURE_ADDED);
    }

    @Override
    public Stream<Figure> getFigures() {
        return figures.stream();
    }

    @Override
    public void removeFigure(Figure f) {

        if (!figures.contains(f)) return;

        f.removeFigureListener(figureListener);
        figures.remove(f);
        notifyListener(f, DrawModelEvent.Type.FIGURE_REMOVED);
    }

    @Override
    public void addModelChangeListener(DrawModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeModelChangeListener(DrawModelListener listener) {

        listeners.remove(listener);
    }

    /**
     * The draw command handler. Initialized here with a dummy implementation.
     */
    // TODO initialize with your implementation of the undo/redo-assignment.
    private DrawCommandHandler handler = new EmptyDrawCommandHandler();

    /**
     * Retrieve the draw command handler in use.
     *
     * @return the draw command handler.
     */
    @Override
    public DrawCommandHandler getDrawCommandHandler() {
        return handler;
    }


    @Override
    public void setFigureIndex(Figure f, int index) {
        if (!figures.contains(f)) {
            throw new IllegalArgumentException("Figure cannot be found in list");
        }

        if (index > figures.size() - 1 || index < 0) {
            throw new IndexOutOfBoundsException("Index is not in figures");
        } else {
            figures.remove(f);
            figures.add(index, f);
            notifyListener(f, DrawModelEvent.Type.DRAWING_CHANGED);
        }
    }


    @Override
    public void removeAllFigures() {
        figures.forEach(f -> f.removeFigureListener(figureListener));
        figures.clear();
        notifyListener(null, DrawModelEvent.Type.DRAWING_CLEARED);
    }

}
