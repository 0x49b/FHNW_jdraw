package jdraw.decorators;

import jdraw.framework.Figure;

import java.awt.*;

public class BorderDecorator extends AbstractDecorator {

    public BorderDecorator(Figure f){
        super(f);
    }

    @Override
    public void draw(Graphics g){

        System.out.printf("Add a border to the figure");

    }

}
