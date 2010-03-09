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

    private final ArrayList<Point2D> vertices = new ArrayList<Point2D>();
    private final GeomObject startObject;
    private final GeomObject endObject;

    public Link(GeomObject startObject, GeomObject endObject) {
	this.startObject = startObject;
	this.endObject = endObject;
	vertices.clear();
    }

    public void connect(Integer startBorder, Integer endBorder) {
	List<Integer> startBorders = new ArrayList<Integer>();
	startBorders.add(startBorder);

	List<Integer> endBorders = new ArrayList<Integer>();
	endBorders.add(endBorder);

	List<Port> startPorts = startObject.getFreePorts(startBorders);
	List<Port> endPorts = endObject.getFreePorts(endBorders);
	
	vertices.clear();

	for (Port startPort : startPorts) {
	    for (Port endPort : endPorts) {
		Vector2D vectorNormalStart = startPort.getNormalVector();
		Vector2D vectorNormalEnd = endPort.getNormalVector();

		// calculate bounding box
		Rectangle2D boundRect = getBoundRect();
		// calculate angle between normal vectors
		Double ang = vectorNormalStart.ang(vectorNormalEnd);
		// calculate length of a step
		Double step = (1.05 + 0.1*Math.random())* Math.max(boundRect.getWidth(), boundRect
			.getHeight());
		// calculate distance
		Double distance = startPort.getPoint().distance(
			endPort.getPoint());

		// Calculate intersection point of two lines
		Line2D startLine = startPort.getNormalLine(step);
		Line2D endLine = endPort.getNormalLine(2.5*step);
		Point2D intersectPoint = Util.calculateIntersection(startLine,
			endLine);

		startPort.setState(Port.State.allocated);
		vertices.add(startPort.getPoint());
		
		while ((ang != Math.PI / 2 || ang != -Math.PI / 2)
			&& intersectPoint == null) {
		    
		    // check the intersection with the end object
		    boolean endObjectIntersect = endObject.getShape().intersectsLine(startLine);
		    if (endObjectIntersect){
			// create the specific port for this intersection
			Port newPort = endObject.createPortForLine(startLine);
			if (newPort != null) {
			    newPort.setState(State.allocated);
			    vertices.add(newPort.getPoint());
			    return;
			}
		    }

		    
		    // new start point is a simple old end point of start line
		    Port newStartPort = new Port(startLine.getP2(), startPort
			    .getObject(), startLine);
		    // to calculate new end port
		    Vector2D newNormalVector1 = newStartPort.getNormalVector();
		    Vector2D newNormalVector2 = newNormalVector1.mult(-1.0);
		    Vector2D newVector3 = vectorNormalStart;
		    
		    Line2D newStartLine1 = newNormalVector1.getLine(
			    newStartPort.getPoint(), step);
		    Line2D newStartLine2 = newNormalVector2.getLine(
			    newStartPort.getPoint(), step);
		    Line2D newStartLine3 = newVector3.getLine(
			    newStartPort.getPoint(), step);
		    
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
		    intersectPoint = Util.calculateIntersection(startLine,
			    endLine);
		}
		vertices.add(intersectPoint);
		vertices.add(endPort.getPoint());
		endPort.setState(Port.State.allocated);
		return;
	    }
	}
    }

    private Rectangle2D getBoundRect() {
	Rectangle2D start = startObject.getShape();
	Rectangle2D end = endObject.getShape();
	double minX = Math.min(start.getMinX(), end.getMinX());
	double maxX = Math.max(start.getMaxX(), end.getMaxX());
	double minY = Math.min(start.getMinY(), end.getMinY());
	double maxY = Math.max(start.getMaxY(), end.getMaxY());
	return new Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY);
    }

    public GeneralPath getPath() {
	GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
		vertices.size());

	if (vertices.size() > 1) {
	    polyline.moveTo(vertices.get(0).getX(), vertices.get(0).getY());
	    for (int index = 1; index < vertices.size(); index++) {
		polyline.lineTo(vertices.get(index).getX(), vertices.get(index)
			.getY());
	    }
	}
	return polyline;
    }
}
