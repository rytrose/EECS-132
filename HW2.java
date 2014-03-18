/* Ryan Rose EECS 132
 * Demonstates how loops can be used to manipulate strings in various ways.
 * */

public class HW2 {
  
  /** 
   * Replaces the Kth occurance of the first char input with the second char input.
   * @param toBeReplaced  char being replaced 
   * @param replaceWith  char being substituted in for the char being replaced
   * @param k  char will be replaced at every kth occurrence
   * @param s  string of letters in which the char is being replaced
   */ 
  public static String replaceKth(char toBeReplaced, char replaceWith, int k, String s){
    StringBuilder builder = new StringBuilder();
    int occurrence = 0;
    /* 
     * Prereq: k is not negative.
     * Runs through the string to find where the kth occurence of the first input character 
     * and, when found, replaces it with the second input character.
     */
    for(int i = 0; i < s.length(); i++){
      // If k = 0, return the original String.
      if(k == 0)
        return s;
      // If char at i is the char to be replaced, but not yet the Kth occurence, append the
      // char at i and add one to occurrence.
      if(s.charAt(i) == toBeReplaced && occurrence < k)
        occurrence++;
      /* If char at i is the char to be replaced, and is the Kth occurrence, replace the char 
       * at i with the replacement char and reset occurrence.
       */
      if(s.charAt(i) == toBeReplaced && occurrence == k){
        builder.append(replaceWith);
        occurrence++;
      }
      // If char at i is not the char to be replaced, append the char at i to the builder.
      else
        builder.append(s.charAt(i));
    }
    return builder.toString();
  }
  
  /**
   * Combines two strings into one string, alternating characters from each string, with any extra
   * characters of a string added consecutively to the end.
   * @param s1  first string being combined
   * @param s2  second string being combined
   */
  public static String interleave(String s1, String s2){
    // Two indexes for the while loop.
    int i1 = 0, i2 = 0;
    StringBuilder builder = new StringBuilder();
    /* 
     * Scans both strings and builds a string alternating characters from each, then scans
     * if either string has more characters than the other and adds those characters consecutively
     * to the end.
     */
    while(i1 < s1.length() || i2 < s2.length()){
      // Both strings have characters.
      if(i1 < s1.length() && i2 < s2.length()){
        builder.append(s1.charAt(i1));
        builder.append(s2.charAt(i2));
      }
      // s1 still has characters, and adds them to the end of the builder.
      if(i1 < s1.length() && i2 >= s2.length())
        builder.append(s1.charAt(i1));
      // s2 still has characters, and adds them to the end of the builder.
      if(i1 >= s1.length() && i2 < s2.length())
        builder.append(s2.charAt(i2));        
      i1++;
      i2++;
    }
    return builder.toString();     
  }
  
  /**
   * Takes a word, i.e. consecutive letters, and replaces the all but the first and last letter 
   * of the word with underscores, thus "blanking" the word.
   * @param s  string in which the words will be blanked
   */
  public static String blankWords(String s){
    // Index for the while loop.
    int i = 0;
    // Remembers at what index the word started at.
    int wordBreak = -1;
    StringBuilder builder = new StringBuilder();
    
    /* 
     * Scans the string a builds a blanked string by adding letters, marking where non-letters are,
     * and the blanking the letters of the previous word once it reaches a new non-letter.
     */
    while(i < s.length()){
      // If i is a letter, append it to builder.
      if((i < s.length()) && ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || 
                              (s.charAt(i) >= 'a' && s.charAt(i) <= 'z'))){
        builder.append(s.charAt(i));
        i++;
      }
      // If it's not a letter, blank out the previous word.
      if((i < s.length()) && (s.charAt(i) > 'Z' || s.charAt(i) < 'A') &&
         (s.charAt(i) > 'z' || s.charAt(i) < 'a')){
        builder.append(s.charAt(i));
        // Blanks out the word, from the second to last character to the second.
        for(int i2 = (i - 2); i2 > wordBreak + 1; i2--)
          builder.setCharAt(i2, '_');
        wordBreak = i;
        i++;
      }
      // Blanks the word if the last character in the string is not punctuation.
      if((i < s.length()) && (i == s.length() - 1) && ((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || 
                                                       (s.charAt(i) >= 'a' && s.charAt(i) <= 'z'))){
        // Blanks out the word, from the second to last character to the second.
        for(int i2 = (i - 1); i2 > wordBreak + 1; i2--)
          builder.setCharAt(i2, '_');
        wordBreak = i;
      }
    }
    return builder.toString();
  }
  
  /**
   * Returns every nth word, defined here as a sequence of non-space characters, of a string of words,
   * and separates each word with a space.
   * @param n  the n value given to return every nth word
   * @param s  the string of words from which the method will return every nth word
   */
  public static String nthWord(int n, String s){
    StringBuilder builder = new StringBuilder();
    // If n is 1, return all the words of the string, i.e. the whole string.
    if(n == 1)
      return s;
    if(n > 1){
      int wordCount = 0;
      int i = 0;
      /*
       * Prereq: n is a positive integer.
       * Scans the string, remembering the word count of each word as it comes to it, and prints
       * the words whose remainder when divided by n is zero.
       */
      while(i < s.length()){
        // Appends every nth word. 
        if((s.charAt(i) != ' ') && ((wordCount % n) == 0))
          builder.append(s.charAt(i));
        // Manages spaces.
        if((s.charAt(i) == ' '))
          wordCount++;
        if((s.charAt(i) == ' ') && ((wordCount % n) == 0))
          builder.append(s.charAt(i));
        i++;
      }
    }
    return builder.toString();
  }
  
  /**
   * Truncates the string at legal locations, defined by hyphens or spaces, to at least a 
   * desired input length.
   * @param length  desired minimum length after truncation
   * @param s  string to be truncated
   */
  public static String truncateAfter(int length, String s){
    // Keeps track of the length the output string will return.
    int lengthCount = 0;
    StringBuilder builder = new StringBuilder();
     
    /* 
     * Prereq: the string is labeled with hyphens where truncation is legal.
     * Scans the string, checking whether the character is a letter, space, or hyphen, and either
     * appends a letter to the string, truncating or appending a space when appropriate, or
     * skipping or appending and truncating a hyphen.
     */
    for(int i = 0; i < s.length(); i++){
      // Appends non-truncating characters.
      if((s.charAt(i) != ' ') && (s.charAt(i) != '-')){
        builder.append(s.charAt(i));
        lengthCount++;
      }
      // Appends spaces before the minimum is reached.
      else if((s.charAt(i) == ' ') && lengthCount < length){
        builder.append(s.charAt(i));
        lengthCount++;
      }
      /* 
       * If the minimum length is achieved, and the loop reaches returns the truncated
       * string without the space.  
       */ 
      else if((s.charAt(i) == ' ') && lengthCount >= length)
        return builder.toString();
      // Truncates the string at a hyphen if appending the hyphen would achieve the minimum length.  
      else if((s.charAt(i) == '-') && (lengthCount + 1) == length){
        builder.append(s.charAt(i));
        return builder.toString();
      }
      // Truncates the string at a hyphen past the length minimum.    
      else if((s.charAt(i) == '-') && lengthCount >= length){
        builder.append(s.charAt(i));
        return builder.toString();
      }
    }
    return builder.toString();
  }
}