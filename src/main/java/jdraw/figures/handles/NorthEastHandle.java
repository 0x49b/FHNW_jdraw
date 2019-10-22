package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the NorthEast Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class NorthEastHandle extends AbstractHandle {
    public NorthEastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width , bounds.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        if (null == getCorner()) {
            return;
        }

        // TODO correct calcs
        getOwner().setBounds(
                getCorner(),
                new Point(
                        x,
                        y)

        );
    }

}
