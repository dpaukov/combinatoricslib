
package diagram.geom;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * Description
 *
 */
public class Port {
  
  public enum State {
    free,
    allocated
  }
  
  private final Point2D.Double point;
  private State state;
  
  /**
   * @param point
   * @param state
   */
  public Port(Double point, State state) {
    super();
    this.point = point;
    this.state = state;
  }
  
  /**
   * @param point
   * @param state
   */
  public Port(Double point) {
    super();
    this.point = point;
    this.state = State.free;
  }

  /**
   * @param point
   * @param state
   */
  public Port(double x, double y) {
    super();
    this.point = new Point2D.Double(x, y);
    this.state = State.free;
  }
  
  /**
   * @return Returns the state.
   */
  public State getState() {
    return state;
  }

  /**
   * @param state The state to set.
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * @return Returns the point.
   */
  public Point2D.Double getPoint() {
    return point;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Port [point=" + point + ", state=" + state + "]";
  }

  
  
}
