package jdraw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import jdraw.std.StdDrawModel;

@SuppressWarnings("serial")
public class DrawModelTest {

	static class TestFigure implements Figure {
		@Override public void setBounds(Point origin, Point corner) {}
		@Override public void draw(Graphics g) {}
		@Override public void move(int dx, int dy) {}
		@Override public boolean contains(int x, int y) { return false;	}
		@Override public java.awt.Rectangle getBounds() {return new java.awt.Rectangle();}
		@Override public List<FigureHandle> getHandles() { return null; }
		@Override public void addFigureListener(FigureListener listener) { }
		@Override public void removeFigureListener(FigureListener listener) { }
		@Override public Figure clone() { return null;}
	}

	private DrawModel m;
	private Figure f;
	private int cnt;
	private DrawModelEvent.Type type;
	
	protected DrawModel createDrawModel() {
		return new StdDrawModel();
	}

	@BeforeEach
	public void setUp() {
		m = createDrawModel();
		f = new TestFigure();
		cnt = 0;
		type = null;
	}

	@Test
	public void testAddFigure1() {
		m.addFigure(f);
		assertTrue((m.getFigures().iterator().next()).equals(f), "get back the Figure from the model");
	}

	@Test
	public void testAddFigure2() {
		Figure f1 = new TestFigure();
		Figure f2 = new TestFigure();
		Figure f3 = new TestFigure();
		m.addFigure(f1);
		m.addFigure(f2);
		m.addFigure(f3);
		String msg = "order of adding figures is not preserved";
		Iterator<Figure> it = m.getFigures().iterator();
		assertEquals(f1, it.next(), msg);
		assertEquals(f2, it.next(), msg);
		assertEquals(f3, it.next(), msg);
	}

	@Test
	public void testAddFigure3() {
		m.addModelChangeListener(e -> type = e.getType());
		m.addFigure(f);
		assertEquals(DrawModelEvent.Type.FIGURE_ADDED, type, "addFigure should notify a FIGURE_ADDED event");
	}

	@Test
	public void testAddFigure4() {
		m.addFigure(f);
		m.addFigure(f);
		Iterator<Figure> it = m.getFigures().iterator();
		it.next();
		assertFalse(it.hasNext(), "figures in the model should be unique");
	}

	@Test
	public void testAddFigure5() {
		m.addFigure(f);
		m.addModelChangeListener(e -> cnt++);
		m.addFigure(f);
		assertTrue(cnt == 0, "no notification if figure is already contained in model");
	}

	@Test
	public void testAddFigure6() {
		Figure f = new TestFigure() {
			@Override
			public void addFigureListener(FigureListener listener) {
				cnt++;
			}
		};
		m.addFigure(f);
		assertTrue(cnt > 0, "model has to register a listener in the figure");
	}

	@Test
	public void testRemoveFigure1() {
		m.addModelChangeListener(e -> cnt++);
		m.removeFigure(f);
		assertTrue(cnt == 0, "no notificatoin expected, figure was not contained in model");
	}

	@Test
	public void testRemoveFigure2() {
		Figure f = new TestFigure() {
			@Override
			public void addFigureListener(FigureListener l) {
				cnt++;
			}

			@Override
			public void removeFigureListener(FigureListener l) {
				cnt--;
			}
		};
		m.addFigure(f);
		m.removeFigure(f);
		assertTrue(cnt == 0, "listeners registered by the model must be removed");
	}

	@Test
	public void testRemoveFigure3() {
		List<FigureListener> listeners = new LinkedList<>();
		Figure f = new TestFigure() {
			@Override
			public void addFigureListener(FigureListener l) {
				listeners.add(l);
			}

			@Override
			public void removeFigureListener(FigureListener l) {
				listeners.add(l);
			}
		};
		m.addFigure(f);
		m.removeFigure(f);
		assertTrue(listeners.size() == 2, "listeners registered by the model must be removed");
		assertTrue(listeners.get(0) == listeners.get(1), "the listener which has been registered must also be removed");
	}

	@Test
	public void testRemoveFigure4() {
		List<FigureListener> listeners = new LinkedList<>();
		Figure f1 = new TestFigure() {
			@Override
			public void addFigureListener(FigureListener l) {
				listeners.add(l);
			}

			@Override
			public void removeFigureListener(FigureListener l) {
				listeners.add(l);
			}
		};
		Figure f2 = new TestFigure() {
			@Override
			public void addFigureListener(FigureListener l) {
				listeners.add(l);
			}

			@Override
			public void removeFigureListener(FigureListener l) {
				listeners.add(l);
			}
		};
		m.addFigure(f1);
		m.addFigure(f2);
		m.removeFigure(f1);
		m.removeFigure(f2);
		assertTrue(listeners.size() == 4, "listeners registered by the model must be removed");
		assertTrue(listeners.get(0) == listeners.get(2), "the listener which has been registered must also be removed");
		assertTrue(listeners.get(1) == listeners.get(3), "the listener which has been registered must also be removed");
	}

	@Test
	public void testRemoveAllFigures1() {
		m.addFigure(f);
		m.addModelChangeListener(e -> type = e.getType());
		m.removeAllFigures();
		assertEquals(DrawModelEvent.Type.DRAWING_CLEARED, type, "removeAllFigures should notify a DRAWING_CLEARED event");
	}

	@Test
	public void testRemoveAllFigures2() {
		class Fig extends TestFigure {
			@Override
			public void addFigureListener(FigureListener l) {
				cnt++;
			}

			@Override
			public void removeFigureListener(FigureListener l) {
				cnt--;
			}
		}
		
		Figure f1 = new Fig();
		Figure f2 = new Fig();
		m.addFigure(f1);
		m.addFigure(f2);
		m.removeAllFigures();
		assertTrue(cnt == 0, "listeners registered by the model must be removed");
	}

	@Test
	public void testSetFigureIndex1() {
		Figure f1 = new TestFigure();
		Figure f2 = new TestFigure();
		Figure f3 = new TestFigure();
		m.addFigure(f1);
		m.addFigure(f2);
		m.addFigure(f3);
		m.addModelChangeListener(e -> type = e.getType());
		m.setFigureIndex(f3, 0);
		Iterator<Figure> it = m.getFigures().iterator();
		assertEquals(f3, it.next(), "f3 should be at position 0");
		assertEquals(f1, it.next(), "f1 should be at position 1");
		assertEquals(f2, it.next(), "f2 should be at position 2");
		assertEquals(DrawModelEvent.Type.DRAWING_CHANGED, type, "setFigureIndex should notify a DRAWING_CHANGED event");
	}

	@Test
	public void testSetFigureIndex2() {
		Figure f1 = new TestFigure();
		Figure f2 = new TestFigure();
		Figure f3 = new TestFigure();
		m.addFigure(f1);
		m.addFigure(f2);
		m.addFigure(f3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			m.setFigureIndex(f, 1);
		});
	}

	@Test
	public void testSetFigureIndex3() {
		Figure f1 = new TestFigure();
		Figure f2 = new TestFigure();
		m.addFigure(f1);
		m.addFigure(f2);
		m.setFigureIndex(f2, 0);
		m.setFigureIndex(f2, 1);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			m.setFigureIndex(f2, 2);
		});

		assertTrue(
			StreamSupport.stream(m.getFigures().spliterator(), false).anyMatch(f -> f == f2),
			"in case that an IndexOutOfBoundsException occurs, the figure must not be removed from the model"
		);
	}

	@Test
	public void testSetFigureIndex4() {
		Figure f1 = new TestFigure();
		Figure f2 = new TestFigure();
		m.addFigure(f1);
		m.addFigure(f2);
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			m.setFigureIndex(f2, -1);
		});

		assertTrue(
			StreamSupport.stream(m.getFigures().spliterator(), false).anyMatch(f -> f == f2),
			"in case that an IndexOutOfBoundsException occurs, the figure must not be removed from the model"
		);
	}

}
