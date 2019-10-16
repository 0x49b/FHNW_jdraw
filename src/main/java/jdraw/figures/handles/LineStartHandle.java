package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the LineStart Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class LineStartHandle extends AbstractHandle {
    public LineStartHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();


        return new Point((int) getOwner().getBounds().getMinX(), (int) getOwner().getBounds().getMinY());
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
                new Point(
                        x,
                        y),
                getCorner()
        );
    }

}
