package diagram.geom;

import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class GeomObject {
  
  public enum Border {
    Top,
    Bottom,
    Left,
    Right
  }
  
  private final Rectangle2D.Double shape;
  private int countOfPorts = 5;

  /**
   * @param shape
   */
  public GeomObject(double x1, double y1, double x2, double y2) {
    super();
    this.shape = new Rectangle2D.Double(x1, y1, x2-x1, y2-y1);
  }

  /**
   * @return Returns the shape.
   */
  public Rectangle2D.Double getShape() {
    return shape;
  }
  
  
  public List<Port> getPorts(Border border) {
    // Iterate through the specified shape, perturb its coordinates, and
    // use them to build up the new shape.
    double[] coords = new double[6];
    Point2D.Double point1 = null, point2 = null;
    for (PathIterator i = shape.getPathIterator(null); !i.isDone(); i
        .next()) {
      int type = i.currentSegment(coords);
      switch (type) {
      case PathIterator.SEG_MOVETO:
        point1 = new Point2D.Double(coords[0], coords[1]);
        break;
      case PathIterator.SEG_LINETO:
        point2 = new Point2D.Double(coords[0], coords[1]);
        createBorderPorts();
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

  private List<Port> createBorderPorts() {
    return null;
  }
}
