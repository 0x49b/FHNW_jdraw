package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the South Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class SouthHandle extends AbstractHandle {
    public SouthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
    }

    /*@Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();

        getOwner().setBounds(new Point(r.x, y),
                new Point(r.x + r.width, r.y));

        if (y < r.y) {
            // todo implement a swap method --> State Pattern?!?
        }
    }*/

    @Override
    public void interactionChanged(Point startPoint, Point endPoint) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                new Point(bounds.x, bounds.y),
                new Point(endPoint.x + bounds.width, endPoint.y)
        );
    }

}
