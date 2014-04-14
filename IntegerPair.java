/**
 * Represents a pair of integers.
 * @author Ryan Rose
 */
public class IntegerPair{
  
  /**
   * Holds the first integer.
   */
  private int value1;
  
  /**
   * Holds the second integer.
   */
  private int value2;
  
  /**
   * Constructor that takes two integers to be stored in the integer pair.
   * @param firstInteger the first integer of the pair
   * @param secondInteger the second integer of the pair
   */
  public IntegerPair(int firstInteger, int secondInteger){
    this.value1 = firstInteger;
    this.value2 = secondInteger;
  }
  
  /**
   * Gets the first integer.
   * @return returns the first integer
   */
  public int getValue1(){
    return this.value1;
  }
  
  /**
   * Sets the first integer.
   */
  public void setValue1(int i){
    this.value1 = i;
  }
  
  /**
   * Gets the second integer.
   * @return returns the second integer
   */
  public int getValue2(){
    return this.value2;
  }

  /**
   * Sets the second integer.
   */
  public void setValue2(int i){
    this.value2 = i;
  }  
  
  /**
   * Returns a string representation of the integer pair.
   * @return returns a string representation of the pair
   */
  @Override
  public String toString(){
    StringBuilder s = new StringBuilder();
    s.append('(');
    s.append(this.getValue1());
    s.append(',');
    s.append(' ');
    s.append(this.getValue2());
    s.append(')');
    return s.toString();
  }
}