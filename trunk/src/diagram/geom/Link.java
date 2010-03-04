package diagram.geom;

import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import combinatorics.util.Util;

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

	List<Port> startPorts = startObject.getPorts(startBorders);
	List<Port> endPorts = endObject.getPorts(endBorders);

	for (Port startPort : startPorts) {
	    for (Port endPort : endPorts) {
		Vector2D vectorNormalStart = startPort.getNormalVector();
		Vector2D vectorNormalEnd = endPort.getNormalVector();

		Double cosAngle = vectorNormalStart.cosAngle(vectorNormalEnd);
		Double distance = startPort.getPoint().distance(
			endPort.getPoint());

		if (cosAngle >= 0 && cosAngle < 1) {
		    Line2D startLine = startPort.getNormal(200);
		    Line2D endLine = endPort.getNormal(200);
		    Point2D intersectPoint = Util.calculateIntersection(
			    startLine, endLine);

		    vertices.add(startPort.getPoint());
		    if (intersectPoint != null) {
			vertices.add(intersectPoint);
		    }
		    vertices.add(endPort.getPoint());
		    return;
		}
	    }
	}
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
