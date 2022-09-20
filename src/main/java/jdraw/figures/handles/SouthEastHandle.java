package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

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
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        //TODO Make it correct
        Rectangle r = getOwner().getBounds();
        getOwner().setBounds(new Point(r.x,r.y),
                             new Point(x,y));
        if (x < r.x+r.width && r.width == 0) {
            //getOwner().swapHorizontal();
        }
        if (y < r.y+r.height && r.height == 0) {
            //getOwner().swapVertical();
        }
    }


}
