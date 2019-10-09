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
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Rect extends AbstractFigure {

    private static final long serialVersionUID = 9120181044386552132L;
    private CopyOnWriteArrayList<FigureListener> listeners = new CopyOnWriteArrayList<>();


    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    private final Rectangle rectangle;

    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Rect(int x, int y, int w, int h) {
        rectangle = new Rectangle(x, y, w, h);
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
        g.setColor(Color.BLACK);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        rectangle.setFrameFromDiagonal(origin, corner);
        notifyListeners(new FigureEvent(this)); // XXX nur falls es wirklcih eine Änderung war.
    }

    @Override
    public void move(int dx, int dy) {
        rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
        if (dx != 0 || dy != 0) {
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return rectangle.getBounds();
    }

    /**
     * Returns a list of 8 handles for this Rectangle.
     *
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

}
