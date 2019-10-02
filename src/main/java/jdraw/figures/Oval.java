/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Oval implements Figure {
    private static final long serialVersionUID = 9120181044386552132L;

    private CopyOnWriteArrayList<FigureListener> listeners = new CopyOnWriteArrayList<>();


    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    //private final Rectangle rectangle;
    private final Ellipse2D oval;

    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Oval(int x, int y, int w, int h) {
        //rectangle = new Rectangle(x, y, w, h);
        oval = new Ellipse2D.Double(x,y,w,h);
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.RED);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.draw(oval);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        rectangle.setFrameFromDiagonal(origin, corner);
        notifyListeners(new FigureEvent(this));
    }

    @Override
    public void move(int dx, int dy) {
        rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
        if (dx != 0 || dy != 0) {
            notifyListeners(new FigureEvent(this));
        }
    }

    // Todo reimplement
    @Override
    public boolean contains(int x, int y) {
        return oval.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return oval.getBounds();
    }

    /**
     * Returns a list of 8 handles for this Rectangle.
     *
     * @return all handles that are attached to the targeted figure.
     * @see Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        listeners.add(listener);

    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Figure clone() {
        return null;
    }

    /**
     * Notify all registered Listeners
     */
    private void notifyListeners(FigureEvent e) {
        listeners.forEach(fl -> fl.figureChanged(e));
    }

}
