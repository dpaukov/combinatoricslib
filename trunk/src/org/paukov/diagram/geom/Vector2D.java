package org.paukov.diagram.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


/**
 * 
 * @author dpaukov
 *
 *                                          
 *              (a,b) = (x2-x1,y2-y1)         \
 *    *----------------------------------------*
 *   (x1,y1)                                  / (x2,y2)
 *    
 *
 */
public class Vector2D {

    protected final Double a;
    protected final Double b;

    private Vector2D(Point2D begin, Point2D end) {
	this(end.getX() - begin.getX(), end.getY() - begin.getY());
    }

    private Vector2D(Line2D line) {
	this(line.getP2().getX() - line.getP1().getX(), line.getP2().getY()
		- line.getP1().getY());
    }

    private Vector2D(double x, double y) {
	super();
	double norma = Math.sqrt(x * x + y * y);
	if (norma != 0) {
	    a = x / norma;
	    b = y / norma;
	} else {
	    a = x;
	    b = y;
	}
    }

    public static Vector2D createNormalVector(Line2D line) {
	Point2D pointBegin = new Point2D.Double(line.getP1().getY(), line
		.getP2().getX());
	Point2D pointEnd = new Point2D.Double(line.getP2().getY(), line.getP1()
		.getX());
	return new Vector2D(pointBegin, pointEnd);
    }

    public static Vector2D createVector(Line2D line) {
	return new Vector2D(line);
    }

    public double scalarMult(Vector2D vector) {
	return a * vector.a + b * vector.b;
    }

    public Vector2D mult(double multValue) {
	return new Vector2D(a * multValue, b * multValue);
    }

    public double length() {
	return Math.sqrt(a * a + b * b);
    }

    public Double cosAngle(Vector2D vector) {
	if (length() != 0 && vector.length() != 0)
	    return scalarMult(vector) / (length() * vector.length());
	return null;
    }

    public double ang(Vector2D vector) {
	Double d = a * vector.b - b * vector.a;
	Double arccos = Math.acos(cosAngle(vector));
	if (d >= 0)
	    return arccos;
	else
	    return -arccos;
    }

    public Line2D getLine(Point2D point, Double length) {
	return new Line2D.Double(point, new Point2D.Double(point.getX() + a
		* length, point.getY() + b * length));
    }

    @Override
    public String toString() {
	return "Vector2D [a=" + a + ", b=" + b + "]";
    }

}
