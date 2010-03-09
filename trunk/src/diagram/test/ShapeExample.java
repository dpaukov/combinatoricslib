package diagram.test;

import javax.swing.*; // For JPanel, etc.

import diagram.geom.GeomObject;
import diagram.geom.Link;
import diagram.geom.Port;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class ShapeExample extends JPanel {

    private GeomObject object1 = new GeomObject(50, 50, 100, 80);
    private GeomObject object2 = new GeomObject(150, 150, 200, 200);
    private Link link = new Link(object1, object2);

    public void paintComponent(Graphics g) {
	clear(g);
	Graphics2D g2d = (Graphics2D) g;

	g2d.draw(object1.getShape());
	g2d.draw(object2.getShape());

	List<Port> ports = object1.getPorts(null);
	for (Port port : ports) {
	    g2d.draw(port.getNormalLine(10));
	}

	ports = object2.getPorts(null);
	for (Port port : ports) {
	    g2d.draw(port.getNormalLine(10));
	}

	link.connect(3, 1);
	g2d.draw(link.getPath());

    }

    // super.paintComponent clears offscreen pixmap,
    // since we're using double buffering by default.
    protected void clear(Graphics g) {
	super.paintComponent(g);
    }

    public static void main(String[] args) {
	WindowUtilities.openInJFrame(new ShapeExample(), 380, 400);
    }
}