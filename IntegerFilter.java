/**
 * Allows for the implementation of integer filters.
 * @author Ryan Rose
 */
public interface IntegerFilter{
  /**
   * Returns a boolean value whether the input passes the filter.
   */
  public boolean passFilter(int element);
  
}