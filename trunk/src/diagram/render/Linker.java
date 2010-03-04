
package diagram.render;

import diagram.geom.GeomObject;
import diagram.geom.Link;

/**
 * Description
 *
 */
public class Linker {
  
  private final GeomObject startObject;
  private final GeomObject endObject;
  private final Link link;
  
  /**
   * @param startObject
   * @param endObject
   * @param link
   */
  public Linker(GeomObject startObject, GeomObject endObject, Link link) {
    super();
    this.startObject = startObject;
    this.endObject = endObject;
    this.link = link;
  }

  
  public void connect(Integer startBorder, Integer endBorder){
      
  }
  
}
