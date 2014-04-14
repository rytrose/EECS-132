/**
 * Creates a stream of Mersenne prime numbers.
 * @author Ryan Rose
 */
public class MersennePrimeStream extends PrimeStream{
  /**
   * Constructor that runs the super constructor.
   */
  public MersennePrimeStream(){
    super();
  }
  
  /**
   * Helper method that determines if an integer is a power of two.
   * @returns boolean whether or not a number is a power of two
   */
  private boolean isPowerOfTwo(int i){
    return (i & (i - 1)) == 0;
  }
  
  /**
   * Returns the next smallest Mersenne prime.
   * @return returns the next smallest Mersenne prime
   */
  @Override
  public Integer next(){
    while(!isPowerOfTwo(this.getValue() + 1)){
      super.next();        
    }
    if(this.getValue() == 3)
      super.next();
    Integer save = this.getValue();
    super.next();
    return save;
  }
}