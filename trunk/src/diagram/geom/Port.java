package diagram.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Description
 * 
 */
public class Port {

    public enum State {
	free, allocated
    }

    private final Point2D point;
    private State state;
    private final GeomObject object;
    private final Line2D line;

    /**
     * @param point
     * @param state
     */
    public Port(Point2D point, State state, GeomObject object, Line2D line) {
	super();
	this.point = point;
	this.state = state;
	this.object = object;
	this.line = line;
    }

    /**
     * @param point
     * @param state
     */
    public Port(Point2D point, GeomObject object, Line2D line) {
	super();
	this.point = point;
	this.state = State.free;
	this.object = object;
	this.line = line;
    }

    /**
     * @param point
     * @param state
     */
    public Port(double x, double y, GeomObject object, Line2D line) {
	super();
	this.point = new Point2D.Double(x, y);
	this.state = State.free;
	this.object = object;
	this.line = line;
    }

    /**
     * @return Returns the state.
     */
    public State getState() {
	return state;
    }

    /**
     * @param state
     *            The state to set.
     */
    public void setState(State state) {
	this.state = state;
    }

    /**
     * @return Returns the point.
     */
    public Point2D getPoint() {
	return point;
    }

    public GeomObject getObject() {
	return object;
    }

    public Line2D getLine() {
	return line;
    }

    public Vector2D getNormalVector() {
	return Vector2D.createNormalVector(line);
    }

    public Line2D getNormal(double length) {
	return getNormalVector().getLine(point, length);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Port [point=" + point + ", state=" + state + "]";
    }

}
