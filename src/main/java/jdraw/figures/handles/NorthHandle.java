package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the North Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class NorthHandle implements FigureHandle {

    Figure owner;

    public NorthHandle(Figure figure) {
        owner = figure;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public Point getLocation() {
        return owner.getBounds().getLocation();
    }


    @Override
    public void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x + (owner.getBounds().width / 2) - 3, loc.y + (owner.getBounds().height / 2) - 3, 6, 6);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - 3, loc.y - 3, 6, 6);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }
}