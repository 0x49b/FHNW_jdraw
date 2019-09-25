/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.List;
import java.util.stream.Collectors;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

/**
 * Removes a figure from the drawing model. This removal can be undone.
 * 
 * @author Christoph Denzler
 * 
 */
public class RemoveFigureCommand implements DrawCommand {
	private static final long serialVersionUID = 9121230304586234374L;

	/** The model from which to remove the figure. */
	private final DrawModel model;
	/** The figure to remove. */
	private final Figure figure;
	/** index of the figure in the model. */
	private final int index;

	/**
	 * To remove a figure we must know its identity and the model from which it
	 * should be removed.
	 * 
	 * @param model
	 *            the model from which to remove the figure.
	 * @param figure
	 *            the figure to remove.
	 * @throws IllegalArgumentException
	 *            if the figure is not contained in the model. This means, that
	 *            the RemoveFigureCommand instance must be created BEFORE the
	 *            figure is removed from the model.
	 */
	public RemoveFigureCommand(DrawModel model, Figure figure) {
		this.model = model;
		this.figure = figure;
		
		List<Figure> figures = model.getFigures().collect(Collectors.toList());
		index = figures.indexOf(figure);
		if (index == -1) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Remove the figure from the model. Does nothing if the figure was already
	 * removed.
	 */
	@Override
	public void redo() {
		model.removeFigure(figure);
	}

	/**
	 * Add a removed figure again the the model. <b>Note:</b> This
	 * implementation does not test if the figure is already contained in the
	 * model. It assumes that the model implements set semantics concerning
	 * addition of figures, i.e. the same figure can be added repeatedly without
	 * affecting the figure list of the model.
	 */
	@Override
	public void undo() {
		model.addFigure(figure);
		model.setFigureIndex(figure, index);
	}

}
