/**
 * Class that performs actions on streams.
 * @author Ryan Rose
 */
public class StreamUtilities<T>{
  /**
   * Prints out the first n values of the stream, where n is an input integer.
   */
  public static <T> void print(Stream<T> stream, int x){
    for(int i = 0; i < x; i++){
      System.out.println(stream.next());
    }
  } 
}