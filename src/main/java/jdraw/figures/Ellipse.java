
/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.figures.handles.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents Ellipse in JDraw.
 *
 * @author Florian Thiévent
 */
public class Ellipse extends AbstractFigure {

    private static final long serialVersionUID = 9120181044386552132L;

    private CopyOnWriteArrayList<FigureListener> listeners = new CopyOnWriteArrayList<>();

    /**
     * Using java.awt.Ellipse2D to represent te ellipse
     */
    private final Ellipse2D ellipse;

    /**
     * Create a new Ellipse2D of the given dimension.
     *
     * @param x the x-coordinate (top left)
     * @param y the y-coordinate (top left)
     * @param w the ellipse's width
     * @param h the ellipse's height
     */
    public Ellipse(int x, int y, int w, int h) {
        ellipse = new Ellipse2D.Double(x, y, w, h);
    }

    /**
     * Draw the ellipse to the given graphic context.
     *
     * @param g the graphics context to use for drawing.Will be cast to Graphics2D
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; g2.setBackground(Color.WHITE); g2.setColor(Color.BLACK); g2.draw(ellipse);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        ellipse.setFrameFromDiagonal(origin, corner); notifyListeners(new FigureEvent(this));
    }

    @Override
    public void move(int dx, int dy) {
        ellipse.setFrame(ellipse.getX() + dx, ellipse.getY() + dy, ellipse.getWidth(), ellipse.getHeight());
        if (dx != 0 || dy != 0) {
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return ellipse.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return ellipse.getBounds();
    }

    /**
     * Returns a list of 8 handles for this Rectangle.
     *
     * @return all handles that are attached to the targeted figure.
     * @see Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {

        List<FigureHandle> handles = new LinkedList<>();

        handles.add(new NorthEastHandle(this));
        handles.add(new NorthHandle(this));
        handles.add(new NorthWestHandle(this));
        handles.add(new EastHandle(this));
        handles.add(new SouthEastHandle(this));
        handles.add(new SouthHandle(this));
        handles.add(new SouthWestHandle(this));
        handles.add(new WestHandle(this));

        return handles;
    }

}
