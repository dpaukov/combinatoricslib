package diagram.geom;

import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import combinatorics.util.Util;
import diagram.geom.Port.State;

/**
 * Description
 * 
 */
public class Link {

    private final List<Point2D> m_vertices = new ArrayList<Point2D>();
    private final GeomObject m_startObject;
    private final GeomObject m_endObject;

    public Link(GeomObject startObject, GeomObject endObject) {
	this.m_startObject = startObject;
	this.m_endObject = endObject;
	m_vertices.clear();
    }

    public void connect() {

	final class Result {
	    Integer startEdge;
	    Integer endEdge;
	    List<Point2D> vertices;
	}

	List<Result> results = new ArrayList<Result>();
	GeomObject objStart = m_startObject.clone();
	GeomObject objEnd = m_endObject.clone();

	// get visible edges
	List<Integer> startEdges = objStart.getVisibleEdgeNumbers(objEnd
		.getCenterPoint());
	List<Integer> endEdges = objEnd.getVisibleEdgeNumbers(objStart
		.getCenterPoint());

	// create results
	for (Integer startEdge : startEdges) {
	    for (Integer endEdge : endEdges) {
		Result result = new Result();
		result.vertices = connect(objStart, startEdge, objEnd, endEdge);
		result.startEdge = startEdge;
		result.endEdge = endEdge;
		results.add(result);
	    }
	}

	// select the best result which contains minimum vertices
	int size = Integer.MAX_VALUE;
	int bestIndex = -1;
	for (int index = 0; index < results.size(); index++) {
	    Result result = results.get(index);
	    if (result.vertices.size() < size) {
		size = result.vertices.size();
		bestIndex = index;
	    }
	}

	if (bestIndex != -1) {
	    m_vertices.clear();
	    m_vertices.addAll(connect(m_startObject,
		    results.get(bestIndex).startEdge, m_endObject, results
			    .get(bestIndex).endEdge));
	}

    }

    protected List<Point2D> connect(GeomObject startObject,
	    Integer startBorder, GeomObject endObject, Integer endBorder) {

	List<Point2D> vertices = new ArrayList<Point2D>();

	List<Integer> startBorders = new ArrayList<Integer>();
	startBorders.add(startBorder);

	List<Integer> endBorders = new ArrayList<Integer>();
	endBorders.add(endBorder);

	List<Port> startPorts = startObject.getFreePorts(startBorders);
	List<Port> endPorts = endObject.getFreePorts(endBorders);

	if (startPorts.isEmpty() || endPorts.isEmpty())
	    return vertices; // object can't be connected due to absent the free
			     // ports

	Port startPort = startPorts.get(startPorts.size()/2);
	Port endPort = endPorts.get(endPorts.size()/2);

	Vector2D vectorNormalStart = startPort.getNormalVector();
	Vector2D vectorNormalEnd = endPort.getNormalVector();

	// calculate bounding box
	Rectangle2D boundRect = getBoundRect(startObject, endObject);
	// calculate angle between normal vectors
	Double ang = vectorNormalStart.ang(vectorNormalEnd);
	// calculate length of a step
	Double step = (1.1 + 0.3 * Math.random())
		* Math.max(boundRect.getWidth(), boundRect.getHeight());
	// calculate distance
	Double distance = startPort.getPoint().distance(endPort.getPoint());

	// Calculate intersection point of two lines
	Line2D endLine = endPort.getNormalLine(2.5 * step);
	step /= 3;
	//step = 0.5 * calculateStep(startPort.getObject(),
	// endPort.getObject());
	Line2D startLine = startPort.getNormalLine(step);
	Point2D intersectPoint = Util.calculateIntersection(startLine, endLine);

	startPort.setState(Port.State.allocated);
	vertices.add(startPort.getPoint());

	while ((ang != Math.PI / 2 || ang != -Math.PI / 2)
		&& intersectPoint == null) {

	    // check the intersection with the end object
	    boolean endObjectIntersect = endObject.getShape().intersectsLine(
		    startLine);
	    if (endObjectIntersect) {
		// create the specific port for this intersection
		Port newPort = endObject
			.createNewPortForAttachedLine(startLine);
		if (newPort != null) {
		    newPort.setState(State.allocated);
		    vertices.add(newPort.getPoint());
		    return vertices;
		}
	    }

	    // new start point is a simple old end point of start line
	    Port newStartPort = new Port(startLine.getP2(), startPort
		    .getObject(), startLine);

	    // to calculate new end port
	    Vector2D newNormalVector1 = newStartPort.getNormalVector();
	    Vector2D newNormalVector2 = newNormalVector1.mult(-1.0);
	    Vector2D newVector3 = vectorNormalStart;

	    Line2D newStartLine1 = newNormalVector1.getLine(newStartPort
		    .getPoint(), step);
	    Line2D newStartLine2 = newNormalVector2.getLine(newStartPort
		    .getPoint(), step);
	    Line2D newStartLine3 = newVector3.getLine(newStartPort.getPoint(),
		    step);

	    // Choose the direction
	    // 1) calculate distance to the end port
	    Double distance1 = newStartLine1.getP2().distance(
		    endPort.getPoint());
	    Double distance2 = newStartLine2.getP2().distance(
		    endPort.getPoint());
	    Double distance3 = newStartLine3.getP2().distance(
		    endPort.getPoint());
	    // 2) select the smallest distance which is the correct direction
	    if (distance1 < distance2) {
		if (distance1 < distance3) {
		    vectorNormalStart = newNormalVector1;
		    startLine = newStartLine1;
		} else {
		    vectorNormalStart = newVector3;
		    startLine = newStartLine3;
		}
	    } else {
		if (distance2 < distance3) {
		    vectorNormalStart = newNormalVector2;
		    startLine = newStartLine2;
		} else {
		    vectorNormalStart = newVector3;
		    startLine = newStartLine3;
		}
	    }
	    vertices.add(newStartPort.getPoint());
	    ang = vectorNormalStart.ang(vectorNormalEnd);
	    intersectPoint = Util.calculateIntersection(startLine, endLine);
	}
	vertices.add(intersectPoint);
	vertices.add(endPort.getPoint());
	endPort.setState(Port.State.allocated);

	return vertices;
    }

    private Rectangle2D getBoundRect(GeomObject startObject,
	    GeomObject endObject) {
	Rectangle2D start = startObject.getShape();
	Rectangle2D end = endObject.getShape();
	double minX = Math.min(start.getMinX(), end.getMinX());
	double maxX = Math.max(start.getMaxX(), end.getMaxX());
	double minY = Math.min(start.getMinY(), end.getMinY());
	double maxY = Math.max(start.getMaxY(), end.getMaxY());
	return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    }

    private Double calculateStep(GeomObject obj1, GeomObject obj2) {
	Double minDist = Double.MAX_VALUE;
	Line2D minLine = new Line2D.Double(0.0, 0.0, 0.0, 0.0);
	List<Line2D> edges = obj1.getEdges();
	List<Point2D> points = obj2.getPoints();
	for (Point2D point : points) {
	    for (Line2D edge : edges) {
		Double dist1 = point.distance(edge.getP1());
		if (dist1 < minDist) {
		    minDist = dist1;
		    minLine.setLine(point, edge.getP1());
		}
		dist1 = point.distance(edge.getP2());
		if (dist1 < minDist) {
		    minDist = dist1;
		    minLine.setLine(point, edge.getP2());
		}
	    }
	}
	double dx = Math.abs(minLine.getX2() - minLine.getX1());
	double dy = Math.abs(minLine.getY2() - minLine.getY1());
	if (dx == 0)
	    return dy;
	else if (dy == 0)
	    return dx;
	else
	    return Math.min(dx, dy);
    }

    public GeneralPath getPath() {
	GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
		m_vertices.size());

	if (m_vertices.size() > 1) {
	    polyline.moveTo(m_vertices.get(0).getX(), m_vertices.get(0).getY());
	    for (int index = 1; index < m_vertices.size(); index++) {
		polyline.lineTo(m_vertices.get(index).getX(), m_vertices.get(index)
			.getY());
	    }
	}
	return polyline;
    }
}
