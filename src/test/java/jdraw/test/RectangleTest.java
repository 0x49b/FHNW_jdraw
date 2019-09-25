package jdraw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jdraw.figures.Rect;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

public class RectangleTest {

	private Rect f;
	private int cnt;

	@BeforeEach
	public void setUp() {
		f = new Rect(1, 1, 20, 10);
		cnt = 0;
	}

	@Test
	public void testNotification1() {
		FigureListener l = new TestListener();
		f.addFigureListener(l);
		f.move(1, 1);
		assertTrue(cnt == 1, "figureChanged must be called on a registered listener");
		f.removeFigureListener(l);
		f.move(2, 2);
		assertTrue(cnt == 1, "figureChanged must not be called on disconnected listener");
	}

	@Test
	public void testNotification2() {
		f.addFigureListener(new TestListener());
		f.move(0, 0);
		assertTrue(cnt == 0, "Listener was called even if state does not change");
	}

	@Test
	final public void testMultiListeners() {
		f.addFigureListener(new TestListener());
		f.addFigureListener(new TestListener());
		int c = cnt;
		f.move(3, 3);
		assertTrue(cnt == c + 2, "multiple listeners are not supported");
	}

	@Test
	final public void testRemoveListener() {
		f.addFigureListener(new TestListener());
		f.addFigureListener(new RemoveListener(f));
		f.addFigureListener(new TestListener());
		f.addFigureListener(new TestListener());
		f.move(4, 4);
	}
	
	@Test
	final public void testCycle() {
		Figure f1 = f;
		Figure f2 = new Rect(10, 10, 10, 10);
		f1.addFigureListener(new UpdateListener(f2));
		f2.addFigureListener(new UpdateListener(f1));
		
		f2.move(5, 5);
		assertEquals(f1.getBounds().getLocation(), f2.getBounds().getLocation(), "Position of the two figures must be equal");
		assertEquals(15, f1.getBounds().x, "Figures must both be at position x=15");
		assertEquals(15, f1.getBounds().y, "Figures must both be at position y=15");

		f1.move(5, 5);
		assertEquals(f1.getBounds().getLocation(), f2.getBounds().getLocation(), "Position of the two figures must be equal");
		assertEquals(20, f1.getBounds().x, "Figures must both be at position x=20");
		assertEquals(20, f1.getBounds().y, "Figures must both be at position y=20");
	}

	class TestListener implements FigureListener {
		@Override
		public void figureChanged(FigureEvent e) {
			assertTrue(e.getSource() == f);
			cnt++;
		}
	}

	class RemoveListener implements FigureListener {
		private final Figure f;

		RemoveListener(Figure f) {
			this.f = f;
		}

		@Override
		public void figureChanged(FigureEvent e) {
			f.removeFigureListener(this);
		}
	}

	class UpdateListener implements FigureListener {
		private final Figure f;
		public UpdateListener(Figure f) {
			this.f = f;
		}
		@Override
		public void figureChanged(FigureEvent e) {
			Point p1 = e.getFigure().getBounds().getLocation();
			Point p2 = f.getBounds().getLocation();
			f.move(p1.x - p2.x, p1.y - p2.y);
		}
	}

}
