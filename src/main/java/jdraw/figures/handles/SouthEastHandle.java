package jdraw.figures.handles;

import jdraw.framework.Figure;

import java.awt.*;

/**
 * Represents the SouthEast Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class SouthEastHandle extends AbstractHandle {
    public SouthEastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public void interactionChanged(Point startPoint, Point endPoint) {
        Rectangle bounds = getOwner().getBounds();
        getOwner().setBounds(
                new Point(bounds.x, bounds.y),
                new Point(endPoint.x, endPoint.y)
        );
    }

}
