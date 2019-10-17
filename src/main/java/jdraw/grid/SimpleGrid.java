package jdraw.grid;

import jdraw.framework.DrawGrid;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;

/**
 * Implements a SimpleGrid
 *
 * @author Florian Thiévent
 */
public class SimpleGrid implements DrawGrid {

    private final Logger logger = LogManager.getLogger(SimpleGrid.class);

    @Override
    public Point constrainPoint(Point p) {
        logger.debug("SimpleGrid:constraintPoint: " + p);
        return p;
    }

    @Override
    public int getStepX(boolean right) {
        logger.debug("SimpleGrid:getStepX:");
        return 1;
    }

    @Override
    public int getStepY(boolean down) {
        logger.debug("SimpleGrid:getStepY:");
        return 1;
    }

    @Override
    public void activate() {
        logger.debug("SimpleGrid:activate():");
    }

    @Override
    public void deactivate() {
        logger.debug("SimpleGrid:deactivate():");
    }

    @Override
    public void mouseDown() {
        logger.debug("SimpleGrid:mouseDown():");
    }

    @Override
    public void mouseUp() {
        logger.debug("SimpleGrid:mouseUp():");
    }
}
