package jdraw.test;

import jdraw.figures.Ellipse;
import jdraw.framework.Figure;

public class EllipseTest extends AbstractFigureTest {

    @Override
    protected Figure createFigure() {
        return new Ellipse(1,1,20,20);
    }
}
