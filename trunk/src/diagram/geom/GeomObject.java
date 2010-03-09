package diagram.geom;

import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import combinatorics.util.Util;
import diagram.geom.Port.State;

public class GeomObject {

    public enum Border {
	Top, Bottom, Left, Right
    }

    private final Rectangle2D.Double shape;
    private int countOfPorts = 8;
    private double marginOfPorts = 0.05; // in %
    private final HashMap<Integer, List<Port>> portsHash= new HashMap<Integer, List<Port>>();

    /**
     * @param shape
     */
    public GeomObject(double x1, double y1, double x2, double y2) {
	super();
	this.shape = new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1);
	Integer edges = getCountOfEdges();
	for (Integer i = 1; i <= edges; i++)
	    portsHash.put(i, createPorts(i));
    }

    /**
     * @return Returns the shape.
     */
    public Rectangle2D.Double getShape() {
	return shape;
    }

    public Integer getCountOfEdges() {
	Integer count = 0;
	// Iterate through the specified shape, perturb its coordinates, and
	// use them to build up the new shape.
	double[] coords = new double[6];
	for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i
		.next()) {
	    int type = i.currentSegment(coords);
	    switch (type) {
	    case PathIterator.SEG_MOVETO:
		break;
	    case PathIterator.SEG_LINETO:
		count++;
		break;
	    case PathIterator.SEG_QUADTO:
	    case PathIterator.SEG_CUBICTO:
		// not supported
	    case PathIterator.SEG_CLOSE:
		break;
	    }
	}
	return count;
    }

    public Line2D getEdge(Integer edgeNo) {

	Integer count = 0;
	Point2D point1 = null, point2 = null;
	double[] coords = new double[6];

	// Iterate through the specified shape, perturb its coordinates, and
	// use them to build up the new shape.
	for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i
		.next()) {
	    int type = i.currentSegment(coords);
	    switch (type) {
	    case PathIterator.SEG_MOVETO:
		point1 = new Point2D.Double(coords[0], coords[1]);
		break;
	    case PathIterator.SEG_LINETO:
		point2 = new Point2D.Double(coords[0], coords[1]);
		count++;
		if (count == edgeNo) {
		    return new Line2D.Double(point1, point2);
		}
		point1 = point2;
		break;
	    case PathIterator.SEG_QUADTO:
	    case PathIterator.SEG_CUBICTO:
		// not supported
	    case PathIterator.SEG_CLOSE:
		// newshape.closePath();
		break;
	    }
	}
	return null;
    }

    public List<Port> getPorts(List<Integer> edges) {

	// if edges is null, return ports for all borders
	if (edges == null) {
	    edges = new ArrayList<Integer>();
	    for (int i = 1; i <= getCountOfEdges(); i++)
		edges.add(i);
	}

	List<Port> ports = new ArrayList<Port>();
	for (Integer borderNumber : edges) {
	    ports.addAll(portsHash.get(borderNumber));
	}
	return ports;
    }

    public List<Port> getFreePorts(List<Integer> edges) {

	// if edges is null, return ports for all borders
	if (edges == null) {
	    edges = new ArrayList<Integer>();
	    for (int i = 0; i < getCountOfEdges(); i++)
		edges.add(i + 1);
	}

	List<Port> freePorts = new ArrayList<Port>();
	for (Integer borderNumber : edges) {
	    List<Port> ports = portsHash.get(borderNumber);
	    Iterator<Port> itr = ports.iterator();
	    while (itr.hasNext()){
		Port port = itr.next();
		if (port.getState() == State.free)
		    freePorts.add(port);
	    }
	}
	return freePorts;
    }

    
    
    private List<Port> createPorts(Integer edgeNo) {
	List<Port> ports = new ArrayList<Port>();
	Line2D line = getEdge(edgeNo);
	if (line != null) {
	    ports.addAll(createBorderPorts(line));
	}
	return ports;
    }


    protected List<Port> createBorderPorts(Line2D inLine) {

	List<Port> ports = new ArrayList<Port>();
	final Line2D line;
	if (inLine.getX2() < inLine.getX1())
	    line = new Line2D.Double(inLine.getP2(), inLine.getP1());
	else
	    line = inLine;

	// Linear interpolation
	double x0 = line.getX1();
	double x1 = line.getX2();
	double y0 = line.getY1();
	double y1 = line.getY2();

	// if line is not vertical
	if (x0 != x1) {

	    double marginX = (x1 - x0) * marginOfPorts;
	    double deltaX = (x1 - x0 - 2 * marginOfPorts) / countOfPorts;
	    double x = x0 + marginX;
	    while (x < x1) {
		double y = Util.linearInterpolation(x0, y0, x1, y1, x);
		ports.add(new Port(x, y, this, inLine));
		x += deltaX;
	    }
	} else {
	    // line is vertical
	    if (y1 < y0) {
		double temp = y1;
		y1 = y0;
		y0 = temp;
	    }
	    double marginY = (y1 - y0) * marginOfPorts;
	    double deltaY = (y1 - y0 - 2 * marginOfPorts) / countOfPorts;
	    double y = y0 + marginY;
	    while (y < y1) {
		ports.add(new Port(x0, y, this, inLine));
		y += deltaY;
	    }
	}
	return ports;
    }
    
    public Port createPortForLine(Line2D line){
	if (getShape().intersectsLine(line)) {
	    //select the best edge
	    Double minDist = Double.MAX_VALUE;
	    int minIndex = -1;
	    Point2D bestPoint = null;
	    Integer edges = getCountOfEdges();
	    // calculate intersect point on each edges
	    for (Integer i = 1; i <= edges; i++) {
		Line2D edge = getEdge(i);
		//calculate intersection point
		Point2D point = Util.calculateIntersection(edge, line);
		if (point != null){
		    Double dist = point.distance(line.getP1());
		    if (dist < minDist){
			bestPoint = point;
			minDist = dist;
			minIndex = i;
		    }
		}
	    }
	    
	    if (minIndex != -1){
		Port port = new Port(bestPoint, this, getEdge(minIndex));
		portsHash.get(minIndex).add(port);
		return port;
	    }
	}
	return null;
    }

}
