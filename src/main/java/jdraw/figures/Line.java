
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
import java.awt.geom.Line2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents Ellipse in JDraw.
 *
 * @author Florian Thiévent
 */
public class Line extends AbstractFigure {

    private static final long serialVersionUID = 9120181044386552132L;

    private CopyOnWriteArrayList<FigureListener> listeners = new CopyOnWriteArrayList<>();

    /**
     * Using java.awt.Ellipse2D to represent te ellipse
     */
    private Line2D line;

    /**
     * Create a new Ellipse2D of the given dimension.
     *
     * @param x1 the x1-coordinate (top left)
     * @param y1 the y1-coordinate (top left)
     * @param x2 the ellipse's width
     * @param y2 the ellipse's height
     */
    public Line(int x1, int y1, int x2, int y2) {
        line = new Line2D.Double(x1, y1, x2, y2);
    }

    /**
     * Draw the ellipse to the given graphic context.
     *
     * @param g the graphics context to use for drawing.Will be cast to Graphics2D
     */
    @Override
    public void draw(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        line = new Line2D.Double(origin.x, origin.y, corner.x, corner.y);
        notifyListeners(new FigureEvent(this));
    }

    @Override
    public void move(int dx, int dy) {
        line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);

        if (dx != 0 || dy != 0) {
            notifyListeners(new FigureEvent(this));
        }
    }

    // Todo reimplement
    @Override
    public boolean contains(int x, int y) {
        return line.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    /**
     * Returns a list of 2 handles for this Line.
     *
     * @return all handles that are attached to the targeted figure.
     * @see Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

}
