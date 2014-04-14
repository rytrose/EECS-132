/**
 * Allows for the implementation of string filters.
 * @author Ryan Rose
 */
public interface StringFilter{
  /**
   * Returns a boolean value whether the input passes the filter.
   */
  public boolean passFilter(String element);

}
