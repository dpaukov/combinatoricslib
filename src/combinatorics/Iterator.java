package combinatorics;

/**
 * Iterator for enumeration of elements of collections
 * 
 * @param <T> Type of elements in collections
 */
public interface Iterator<T> {

  /**
   * Initial action
   */
  public void first();

  /**
   * Move to next element in the collection
   */
  public void next();

  /**
   * Checks if collection is finished
   * 
   * @return true if collection is finished, otherwise false
   */
  public boolean isDone();

  /**
   * Returns current element in the collection
   * 
   * @return Current element in the collection
   */
  public T getCurrentItem();
}
