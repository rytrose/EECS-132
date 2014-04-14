/**
 * Creates a stream of the smallest palindromes in lexicographical order.
 * @author Ryan Rose
 */
public class PalindromeStream extends StringStream{  
  
  /**
   * Constructor that takes a starting string.
   * @param start starting string value
   * @throws Exception throws this exception if input does not only consist of lower case letters
   */
  public PalindromeStream(String start) throws Exception{
    super(start);
  }
  
  /**
   * Helper method that identifies palindromes.
   * @param s string input
   * @return boolean value of whether or not the input string is a palindrome
   */
  private boolean isPalindrome(String s){
    for(int i = 0; i < s.length() / 2; i++){
      if(s.charAt(i) != s.charAt(s.length() - i - 1))
        return false;
    }
    return true;
  }
  
  /**
   * Provides the next smallest palindrome in lexicographical order.
   * @return returns the next smallest palindrome
   */
  @Override
  public String next(){
    String next = super.next();
    if(isPalindrome(next)){
      this.setValue(next);
    }
    else{
      this.setValue(next);
      this.next();
    }
    return this.getValue();
  }     
}