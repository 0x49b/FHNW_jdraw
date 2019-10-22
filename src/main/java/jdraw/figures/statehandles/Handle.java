package jdraw.figures.statehandles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Handle implements FigureHandle {

    private HandleState state;
    public Handle(HandleState state){
        this.state = state;
    }

    public void setState(HandleState state){
        this.state = state;
    }

    public HandleState getState(){
        return this.state;
    }

    @Override
    public Figure getOwner() {
        return null;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public Cursor getCursor() {
        return null;
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {

    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {

    }
}
