/**
 * Class that produces a stream of integers that are not multiples of a given base number.
 * @author Ryan Rose
 */
public class NotMultipleStream extends IntegerStream {
  
  /**
   * Stores the base value of the stream.
   */
  private int base;
  
  /**
   * Stores the filter for the stream.
   */
  private NotMultipleFilter filter;
  
  /**
   * Gets the filter for this stream.
   * @return returns the filter in this stream
   */
  public NotMultipleFilter getFilter(){
    return this.filter;
  }
  
  /**
   * Constructor that takes a base value for the filter, and an initial value for the stream.
   */
  public NotMultipleStream(int base, int initial){
    super(initial);
    this.filter = new NotMultipleFilter(base, null);
    this.base = base;
  }
  
  /**
   * Returns the next lowest integer that is not a multiple of the base number.
   */
  @Override
  public Integer next(){
    if(filter.passFilter(this.getValue() + 1)){
      this.setValue(this.getValue() + 1);
    }
    else{
      this.setValue(this.getValue() + 1);
      this.next();
    }
    return this.getValue();
  } 
}