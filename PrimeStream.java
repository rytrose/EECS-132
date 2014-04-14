/**
 * Creates a stream of prime numbers, starting from 2, using the Sieve of Eratosthenes.
 * @author Ryan Rose
 */
public class PrimeStream extends IntegerStream{
  /**
   * Holds the filter used in the Sieve of Eratosthenes.
   */
  private NotMultipleFilter filter = null;
  
  /**
   * Constructor that creates a new prime stream, starting with 2.
   */
  public PrimeStream(){
    super(2);
  }
  
  /**
   * Sets the filter for this stream.
   * @param filter the current filter of the stream
   */
  private void setFilter(NotMultipleFilter filter){
    this.filter = filter;
  }
  
  /**
   * Gets the filter for this stream.
   */
  private NotMultipleFilter getFilter(){
    return this.filter;
  }
  
  /**
   * Returns the next sequential prime number, using the Sieve of Eratosthenes.
   */
  @Override
  public Integer next(){
    if(this.getValue() == 2){
      this.setFilter(new NotMultipleFilter(this.getValue(), this.getFilter()));
      this.setValue(super.next());
      return 2;
    }
    if(this.getValue() != 2){
      while(this.getFilter().passFilter(this.getValue()) == false){
        this.setFilter(new NotMultipleFilter(this.getValue(), this.getFilter()));
        this.setValue(super.next());
      }
      this.setFilter(new NotMultipleFilter(this.getValue(), this.getFilter()));
    }
    return this.getValue();
  }
}