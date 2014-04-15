/**
 * Creates a stream of strings, which increase in lexicographical order.
 * @author Ryan Rose
 */
public class StringStream implements Stream<String>{
  
  /**
   * Holds the current value of the stream.
   */
  private String value;
  
  /**
   * Marks if the stream is at the start.
   */ 
  private boolean isStart = true;
  
  /**
   * Constructor that takes a starting string.
   * @param start starting string value
   * @throws Exception throws this exception if input does not only consist of lower case letters
   */
  public StringStream(String start) throws Exception {
    for(int i = 0; i < start.length(); i++){
      if(start.charAt(i) < 'a' || start.charAt(i) > 'z')
        throw new Exception("String must consist of only lower case letters.");
    }
    this.value = start;
  }
  
  /**
   * Returns the current value the stream is at.
   * @return returns the current value of the stream
   */
  public String getValue(){
    return this.value;
  }
  
  /**
   * Returns the start status of the stream. 
   * @return returns the start status of the stream
   */
  public boolean getStart(){
    return this.isStart;
  }
  
  /**
   * Sets the start status of the stream.
   */
  public void setStart(boolean b){
    this.isStart = b;
  }
  
  /**
   * Sets the value of the stream.
   * @param start new starting point for the stream
   */
  public void setValue(String s){
    this.value = s;
  }
  
  /**
   * Returns the next value in the stream.
   * @return returns the next value in the stream
   */
  public String next(){
    StringBuilder s = new StringBuilder();
    if(this.getStart()){
      this.setStart(false);
      return this.getValue();
    }
    s.append(this.getValue());
    // If empty string, return "a".
    if(this.getValue().equals("")){
      this.setValue("a");
      return "a";
    }
    // If the last char is not a 'z', increment it.
    if(s.charAt(s.length() - 1) != 'z'){
      s.setCharAt(s.length() - 1, (char)(s.charAt(s.length() - 1) + 1));
      this.setValue(s.toString());
      return s.toString();
    }
    // If the last char is 'z', check back.
    if(s.charAt(s.length() - 1) == 'z'){
      String save = this.checkBack(s.toString());
      this.setValue(save);
      return save;
    }
    return s.toString();
  }
  
  /**
   * Helper method that checks the previous letter when the next() function reaches a 'z'.
   * @return returns the properly incremented string
   */
  private String checkBack(String string){
    StringBuilder s = new StringBuilder();
    s.append(string);
    for(int i = s.length() - 1; i >= 0; i--){
      if(i == 0){
        for(int i2 = 0; i2 < s.length(); i2++){
          s.setCharAt(i2, 'a');
        }
        s.append('a');
        return s.toString();
      }
      if(s.charAt(i - 1) == 'z'){
        this.checkBack(s.deleteCharAt(s.length() - 1).toString());
        s.append('a');
      }
      if(s.charAt(i - 1) != 'z'){
        s.setCharAt(i - 1, (char)(s.charAt(i - 1) + 1));
        s.setCharAt(i, 'a');
        return s.toString();
      }
    }
    return s.toString();
  }
}
