/**
 * Class that performs actions on streams.
 * @author Ryan Rose
 */
public class StreamUtilities<T>{
  /**
   * Prints out the first n values of the stream, where n is an input integer.
   */
  public static <T> void print(Stream<T> stream, int n){
    for(int i = 0; i < n; i++){
      System.out.println(stream.next());
    }
  }
  
  /**
   * Sums the first n values of an integer stream.
   * @return returns the sum of the first n values
   */
  public static int sum(IntegerStream stream, int n){
    int sum = 0;
    for(int i = 0; i < n; i++)
      sum += stream.next();
    return sum;
  }
  
  /**
   * Multiplies the first n values of an integer stream together.
   * @return returns the product of the first n values
   */
  public static int product(IntegerStream stream, int n){
    int product = 1;
    for(int i = 0; i < n; i++)
      product = product * stream.next();
    return product;
  }
  
  /**
   * Concatenates the first n terms of a stream of strings.
   * @return returns the concatenated string
   */ 
  public static String concatenate(StringStream stream, int n){
    StringBuilder s = new StringBuilder();
    for(int i = 0; i < n; i++)
      s.append(stream.next());
    return s.toString();  
  } 
}