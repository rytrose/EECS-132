/**
 * Filter that determines if an integer is not divisible by another integer.
 * @author Ryan Rose
 */
public class NotMultipleFilter implements IntegerFilter {
  
  /**
   * Holds the base value of the filter.
   */
  private int base;
  
  /**
   * Holds the upstream filter.
   */  
  private IntegerFilter upstreamFilter;
  
  /**
   * Constructor that takes an integer base value, and an upstream filter.
   * @param base base integer value of the filter
   * @param filter the upstream filter
   */
  public NotMultipleFilter(int base, IntegerFilter filter){
    this.base = base;
    this.upstreamFilter = filter;
  }
  
  /**
   * Gets the upstream filter.
   * @return returns the upstream filter
   */
  public IntegerFilter getUpstreamFilter(){
    return this.upstreamFilter;
  }
  
  /**
   * Sets the upstream filter.
   * @param filter the new upstream filter
   */
  public void setUpstreamFilter(IntegerFilter filter){
    this.upstreamFilter = filter;
  }
  
  /**
   * Gets the base value of the filter.
   * @return the base value of the filter
   */
  public int getBase(){
    return this.base;
  }
  
  /**
   * Passes this filter.
   * @param element the input to be filtered
   * @return boolean whether or not the input passes the filter
   */
  @Override
  public boolean passFilter(int element){
    if(this.getUpstreamFilter() != null){
      // passes upstream filter
      if(this.getUpstreamFilter().passFilter(element)){
        // passes this filter
        if(element % this.getBase() != 0)
          return true;
        else
          return false;
      }
      else
        return false;
    }
    else{ 
      if(element % this.getBase() != 0)
        return true;
      else
        return false;
    }
  }
}
