/**
 * Filter that determines if a string is a palindrome.
 * @author Ryan Rose
 */
public class PalindromeFilter implements StringFilter{
 
  /**
   * Holds the upstream filter.
   */
  private StringFilter upstreamFilter;
  
  /**
   * Constructor that takes an upstream filter.
   * @param filter the new upstream filter
   */
  public PalindromeFilter(StringFilter filter){
    this.upstreamFilter = filter;
  }
  
  /**
   * Returns the upstream filter.
   * @return returns the upstream filter
   */
  public StringFilter getUpstreamFilter(){
    return this.upstreamFilter;
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
   * Returns a boolean whether or not the string passes the filter.
   * @param s the string to be passed through the filter
   * @return returns whether or not the string passes the filter
   */
  @Override
  public boolean passFilter(String s){
    if(this.getUpstreamFilter() != null){
      if(this.getUpstreamFilter().passFilter(s)){
        if(this.isPalindrome(s))
          return true;
        else
          return false;
      }
      else
        return false;
    }
    else{
        if(this.isPalindrome(s))
          return true;
        else
          return false;
    }
  }
}