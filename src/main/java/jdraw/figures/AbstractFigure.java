package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Florian Thiévent
 */
abstract class AbstractFigure implements Figure {

    private CopyOnWriteArrayList<FigureListener> figureListeners = new CopyOnWriteArrayList<>();

    /**
     * Add listener to figureListener
     *
     * @param listener the figure listener.
     */
    @Override
    public void addFigureListener(FigureListener listener) {
        figureListeners.add(listener);
    }

    /**
     * Rmove listener from figureListener
     *
     * @param listener the figure listener.
     */
    @Override
    public void removeFigureListener(FigureListener listener) {
        figureListeners.remove(listener);
    }

    /**
     * Notify All Listeners in figureListeners
     */
    protected void notifyListeners(FigureEvent e) {
        figureListeners.forEach(fl -> fl.figureChanged(e));
    }

    @Override
    public Figure clone() {
        return null;
    }
}
