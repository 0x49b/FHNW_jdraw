package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractHandle implements FigureHandle {

    private final Logger logger = LogManager.getLogger(AbstractHandle.class);
    private final static int HANDLE_SIZE = 6;
    private Figure owner;
    private Point corner;
    protected Point opposingPoint;
    public Point startPoint = new Point();
    public Point interStart= new Point();
    public Point interEnd= new Point();
    public Point endPoint= new Point();

    public AbstractHandle(Figure owner) {
        this.owner = owner;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public void draw(Graphics g) {
        Point loc = getTopLeftPoint();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x, loc.y, HANDLE_SIZE, HANDLE_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x, loc.y, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public boolean contains(int x, int y) {
        return new Rectangle(
                (int) getTopLeftPoint().getX(),
                (int) getTopLeftPoint().getY(),
                HANDLE_SIZE,
                HANDLE_SIZE
        ).contains(x, y);
    }

    private Point getTopLeftPoint() {
        Point loc = getLocation();

        return new Point(loc.x - HANDLE_SIZE / 2, loc.y - HANDLE_SIZE / 2);
    }

    private Point getBottomRightPoint() {
        Point loc = getLocation();

        return new Point(loc.x + HANDLE_SIZE / 2, loc.y + HANDLE_SIZE / 2);
    }

    /**
     * Set the Origin Point of the Handle
     *
     * @param corner Point
     */
    protected void setCorner(Point corner) {
        this.corner = corner;
    }


    protected Point getCorner() {
        return corner;
    }



    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {

        // Set Start Point of Interaction
        startPoint.setLocation(x, y);
        interStart = startPoint;

        Rectangle bounds = getOwner().getBounds();
        setCorner(new Point(
                bounds.x + bounds.width,
                bounds.y + bounds.height
        ));
    }


    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        endPoint = new Point(x, y);
        interEnd = endPoint;
        interactionChanged(interStart, interEnd);
        interStart = endPoint;
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        endPoint.setLocation(x,y);
        interEnd = endPoint;
        interactionFinished(startPoint, endPoint);

        corner = null;
    }

    public void interactionFinished(Point startPoint, Point endPoint) {


    }

    public void interactionChanged(Point startPoint, Point endPoint) {

    }
}
