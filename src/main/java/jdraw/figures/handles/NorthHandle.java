package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the North Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class NorthHandle extends AbstractHandle {
    public NorthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width / 2, bounds.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(r.x, y),
                             new Point(r.x + r.width, r.y + r.height));
        if (y > r.y+r.height && r.height == 0) {
            //getOwner().swapVertical();
        }
    }

}
