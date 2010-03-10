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
    private int countOfPorts = 20;
    private double marginOfPorts = 0.05; // in %
    private final HashMap<Integer, List<Port>> portsHash= new HashMap<Integer, List<Port>>();

    /**
     * @param shape
     */
    public GeomObject(double x1, double y1, double width, double height) {
	super();
	this.shape = new Rectangle2D.Double(x1, y1, width, height);
	Integer edges = getCountOfEdges();
	for (Integer i = 1; i <= edges; i++)
	    portsHash.put(i, createPorts(i));
    }
    
    private GeomObject(GeomObject obj) {
	super();
	this.shape = new Rectangle2D.Double(obj.getShape().getX(), obj.getShape().getY(),
		obj.getShape().getWidth(), obj.getShape().getHeight());
	for (Integer i : obj.portsHash.keySet()) {
	    List<Port> list = obj.portsHash.get(i);
	    List<Port> newList = new ArrayList<Port>();
	    for (Port port : list)
		newList.add(new Port(port.getPoint(), this, port.getEdge()));
	    portsHash.put(i, newList);
	}
    }
    
    public GeomObject clone(){
	return new GeomObject(this);
    }
    
    /**
     * @return Returns the shape.
     */
    public Rectangle2D.Double getShape() {
	return shape;
    }
    
    public Point2D getCenterPoint() {
	return new Point2D.Double(getShape().getCenterX(), getShape().getCenterY());
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
    
    
    public List<Line2D> getEdges() {

	Point2D point1 = null, point2 = null;
	double[] coords = new double[6];
	List<Line2D> edgesArray = new ArrayList<Line2D>();

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
		edgesArray.add(new Line2D.Double(point1, point2));
		point1 = point2;
		break;
	    case PathIterator.SEG_QUADTO:
	    case PathIterator.SEG_CUBICTO:
	    case PathIterator.SEG_CLOSE:
		// not supported
		break;
	    }
	}
	return edgesArray;
    }
    
    /**
     * Returns list of edge numbers which are visible from the specified point
     * @param point
     * @return
     */
    public List<Integer> getVisibleEdgeNumbers(Point2D point){
	
	List<Integer> visibleEdges = new ArrayList<Integer>();

	// if point is outside of shape
	if (!getShape().contains(point)) {
	    // for each edge
	    Integer edges = getCountOfEdges();
	    for (Integer i = 1; i <= edges; i++) {
		// get edge
		Line2D line = getEdge(i);
		if (!line.getP1().equals(line.getP2())) {
		    // calculate center point on the line
		    Vector2D vectorLine = Vector2D.createVector(line);
		    vectorLine.mult(0.5 * Math.sqrt(Math.pow(line.getX2()
			    - line.getX1(), 2)
			    + Math.pow(line.getY2() - line.getY1(), 2)));
		    Point2D centerPoint = new Point2D.Double(line.getX1()
			    + vectorLine.a, line.getY1() + vectorLine.b);
		    // line from center point to the specified point
		    Vector2D vectorRadius = Vector2D
			    .createVector(new Line2D.Double(centerPoint, point));
		    Vector2D vectorNormal = Vector2D.createNormalVector(line);
		    Double angle = vectorNormal.ang(vectorRadius);
		    if (-Math.PI / 2 <= angle && angle <= Math.PI / 2)
			visibleEdges.add(i);
		}

	    }
	}
	return visibleEdges;
    }
    
    public List<Point2D> getPoints(){
	List<Point2D> points = new ArrayList<Point2D>();
	double[] coords = new double[6];
	// Iterate through the specified shape, perturb its coordinates, and
	// use them to build up the new shape.
	for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i
		.next()) {
	    int type = i.currentSegment(coords);
	    switch (type) {
	    case PathIterator.SEG_MOVETO:
		//points.add(new Point2D.Double(coords[0], coords[1]));
		break;
	    case PathIterator.SEG_LINETO:
		points.add(new Point2D.Double(coords[0], coords[1]));
		break;
	    case PathIterator.SEG_QUADTO:
	    case PathIterator.SEG_CUBICTO:
	    case PathIterator.SEG_CLOSE:
		// not supported
		break;
	    }
	}
	return points;
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


    /**
     * Returns list of free ports for specified edges
     * @param edges Edge's numbers
     */
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
	    if (ports != null) {
	    Iterator<Port> itr = ports.iterator();
        	    while (itr.hasNext()){
        		Port port = itr.next();
        		if (port.getState() == State.free)
        		    freePorts.add(port);
        	    }
	    }
	}
	
	
	// if there is no free port, we create new
	if (freePorts.isEmpty()){
	    // TODO: create new port on specified edges
	}
	return freePorts;
    }

    
    /**
     * Creates list of new ports for specified edge number. All created ports are free
     * @param edgeNo Edge number
     */
    private List<Port> createPorts(Integer edgeNo) {
	List<Port> ports = new ArrayList<Port>();
	Line2D line = getEdge(edgeNo);
	if (line != null) {
	    ports.addAll(createBorderPorts(line));
	}
	return ports;
    }


    /**
     * Creates list of ports on specified line
     */
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
    
    /**
     * Creates new free port for specified attached line. Port is located 
     * on the edge which is the best for the line
     */
    public Port createNewPortForAttachedLine(Line2D line){
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
