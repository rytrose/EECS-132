/**
 * Class that creates a stream of twin primes.
 * @author Ryan Rose
 */
public class TwinPrimeStream extends IntegerPair{
  /**
   * Stores a prime stream.
   */
  private PrimeStream stream = null;
  
  /**
   * Constructor that starts a twin prime stream.
   */
  public TwinPrimeStream(){
    super(3, 5);
    this.stream = new PrimeStream();
    stream.next();
    stream.next();
  }
  
  /**
   * Helper method that returns the stream.
   * @return returns the prime stream
   */
  private PrimeStream getStream(){
    return this.stream;
  }
  
  /**
   * Returns the next twin prime pair.
   * @return returns the next twin prime pair
   */
  public String next(){
    if(this.getValue1() == 3){
      String save = this.toString();
      this.setValue1(this.getStream().next());
      this.setValue2(this.getStream().next());
      return save;
    }
    if(this.getValue2() - this.getValue1() == 2){
      String save = this.toString();
      this.setValue1(this.getStream().getValue());
      this.setValue2(this.getStream().next());
      return save;
    }
    if(this.getValue2() - this.getValue1() != 2){
      while(this.getValue2() - this.getValue1() != 2){
        this.setValue1(this.getStream().getValue());
        this.setValue2(this.getStream().next());
      }
      String save = this.toString();
      this.setValue1(this.getStream().getValue());
      this.setValue2(this.getStream().next());
      return save;
    }
    return null;
  }
}
