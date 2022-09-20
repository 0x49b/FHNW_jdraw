package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the NorthWest Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class NorthWestHandle extends AbstractHandle {

    public NorthWestHandle(Figure figure) {
        super(figure);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x, bounds.y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }


    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(x,y), new Point(r.x+r.width, r.y+r.height));

    }

}
