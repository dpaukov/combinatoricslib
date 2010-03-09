package diagram.test;

import javax.swing.*; // For JPanel, etc.

import diagram.geom.GeomObject;
import diagram.geom.Link;
import diagram.geom.Port;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class ShapeExample extends JPanel {

    private GeomObject object1 = new GeomObject(100, 100, 130, 120);
    private GeomObject object2 = new GeomObject(150, 130, 180, 150);

    private GeomObject object3 = new GeomObject(300, 130, 330, 150);
    private GeomObject object4 = new GeomObject(350, 100, 380, 120);
    

    private GeomObject object5 = new GeomObject(100, 300, 200, 350);
    private GeomObject object6 = new GeomObject(100, 400, 200, 450);

    
    public void paintComponent(Graphics g) {
	clear(g);
	Graphics2D g2d = (Graphics2D) g;

	test(g2d, object1, object2);
	test(g2d, object3, object4);
	test(g2d, object5, object6);
	

    }
    
    public void test (Graphics2D g2d, GeomObject obj1, GeomObject obj2){
	g2d.draw(obj1.getShape());
	g2d.draw(obj2.getShape());

	List<Port> ports = obj1.getPorts(null);
	for (Port port : ports) {
	    g2d.draw(port.getNormalLine(4));
	}

	ports = obj2.getPorts(null);
	for (Port port : ports) {
	    g2d.draw(port.getNormalLine(4));
	}

	Link link1 = new Link(obj1, obj2);
	Link link2 = new Link(obj2, obj1);
	
//	link1.connect(2, 2);
//	g2d.draw(link1.getPath());
//	link2.connect(3, 3);
//	g2d.draw(link2.getPath());
	
	for (int i = 1; i<=4; i++){
	    for (int j = 1; j<=4; j++){
		link1.connect(i, j);
		link2.connect(i, j);
		g2d.draw(link1.getPath());
		g2d.draw(link2.getPath());
	    }
	}
    }

    // super.paintComponent clears offscreen pixmap,
    // since we're using double buffering by default.
    protected void clear(Graphics g) {
	super.paintComponent(g);
    }

    public static void main(String[] args) {
	WindowUtilities.openInJFrame(new ShapeExample(), 680, 800);
    }
}