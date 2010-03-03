
package diagram.render;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import diagram.geom.Link;

/**
 * Description
 *
 */
public class Linker {
  
  private final Rectangle2D.Double startObject;
  private final Rectangle2D.Double endObject;
  private final Link link;
  /**
   * @param startObject
   * @param endObject
   * @param link
   */
  public Linker(Double startObject, Double endObject, Link link) {
    super();
    this.startObject = startObject;
    this.endObject = endObject;
    this.link = link;
  }

  
}
