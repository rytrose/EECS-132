/**
 * Creates a stream of integers, with each consecutive term increasing by 1.
 * @author Ryan Rose
 */ 
public class IntegerStream implements Stream<Integer>{
  
  /**
   * Holds the current value of the stream.
   */
  private int value;
  
  /**
   * Holds where the stream started.
   */ 
  private int start;
  
  /**
   * Constructor that takes a starting integer.
   * @param start starting integer value
   */
  public IntegerStream(int start){
    this.value = start;
    this.start = start;
  }
  
  /**
   * Gets the value where the stream started.
   * @return returns where the stream started
   */
  public int getStart(){
    return this.start;
  }
  
  /**
   * Sets the value of the stream.
   * @param start new starting point for the stream
   */
  public void setValue(int start){
    this.value = start;
  }
  
  /**
   * Returns the current value the stream is at.
   */
  public int getValue(){
    return this.value;
  }
  
  
  /**
   * Returns the next value in the stream.
   */
  public Integer next(){
    if(this.getValue() == this.getStart()){
      this.setValue(this.getValue() + 1);
      return this.getStart();
    }
    else{
      int save = this.getValue();
      this.setValue(this.getValue() + 1);
      return save;
    }
  }
}