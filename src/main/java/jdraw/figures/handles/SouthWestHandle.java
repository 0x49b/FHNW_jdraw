package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the SouthWest Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class SouthWestHandle extends AbstractHandle {
    public SouthWestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x, bounds.y + bounds.height);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        if (null == getCorner()) {
            return;
        }

        //TODO correct implementation
        getOwner().setBounds(
                new Point(
                        getCorner().x - getOwner().getBounds().width,
                        y
                ),
                getCorner()
        );
    }

}
