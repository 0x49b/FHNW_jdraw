package jdraw.grid;

import jdraw.framework.DrawGrid;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;

public class GridSquare implements DrawGrid {
    private final Logger logger = LogManager.getLogger(GridSquare.class);
    private int gridSize;


    public GridSquare(int size) {
        gridSize = size;
    }

    @Override
    public Point constrainPoint(Point p) {
        return new Point(
                ((int) p.getX() + 10) / gridSize * gridSize,
                ((int) p.getY() + 10) / gridSize * gridSize
        );
    }

    @Override
    public int getStepX(boolean right) {
        return gridSize;
    }

    @Override
    public int getStepY(boolean down) {
        return gridSize;
    }

    @Override
    public void activate() {
        logger.debug("activated GridSquare with Size of " + gridSize);
    }

    @Override
    public void deactivate() {
        logger.debug("deactivated GridSquare with Size of " + gridSize);
    }

    @Override
    public void mouseDown() {

    }

    @Override
    public void mouseUp() {

    }
}
