package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the East Handle of a Figure
 *
 * @author Florian Thiévent
 */
public class EastHandle extends AbstractHandle {
    private final Logger logger = LogManager.getLogger(EastHandle.class);

    public EastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = getOwner().getBounds();
        return new Point(bounds.x + bounds.width, bounds.y + bounds.height / 2);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
    }


    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {

        Rectangle bounds = getOwner().getBounds();

        logger.debug("X/Y: " + x + "/" + y + "  boundsx : "+bounds.x+"  width:" + bounds.width);

        getOwner().setBounds(
                new Point(bounds.x, bounds.y),
                new Point(bounds.x + bounds.width, bounds.y + bounds.height)
        );
    }

}
