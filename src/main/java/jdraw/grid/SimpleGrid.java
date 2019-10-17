package jdraw.grid;

import jdraw.framework.DrawGrid;

import java.awt.*;

/**
 * Implements a SimpleGrid
 *
 * @author Florian Thiévent
 */
public class SimpleGrid implements DrawGrid {


    @Override
    public Point constrainPoint(Point p) {
        System.out.println("SimpleGrid:constraintPoint: " + p);
        return p;
    }

    @Override
    public int getStepX(boolean right) {
        System.out.println("SimpleGrid:getStepX:");
        return 1;
    }

    @Override
    public int getStepY(boolean down) {
        System.out.println("SimpleGrid:getStepY:");
        return 1;
    }

    @Override
    public void activate() {
        System.out.println("SimpleGrid:activate():");
    }

    @Override
    public void deactivate() {
        System.out.println("SimpleGrid:deactivate():");
    }

    @Override
    public void mouseDown() {
        System.out.println("SimpleGrid:mouseDown():");
    }

    @Override
    public void mouseUp() {
        System.out.println("SimpleGrid:mouseUp():");
    }
}
