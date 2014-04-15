import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class Array2Lab
 */
public class Array2LabTester {
  
  /**
   * Test the contains method.
   */
  @Test
  public void testContains() {
    String[][] array1 = {{"Hello"}};
    
    String s = new StringBuilder("Hello").toString();  // avoids compiler optimization of strings
    assertTrue("Incorrectly claims \"Hello\" is not in array1", Array2Lab.contains(array1, s));
    assertFalse("Incorrectly claims \"Hi\" is in array1", Array2Lab.contains(array1, "Hi"));
    
    String[][] array2 = 
    {{"apple", "pear", "banana", "orange"}, {"car", "bicycle", "train", "bus", "truck", "skateboard"},
      {"pizza"}, {"student", "visitor", "alumnus"}, {"red", "orange", "yellow", "green", "blue", "indigo", "violet"}};
    
    s = new StringBuilder("car").toString();
    assertTrue("Incorrectly claims \"car\" is not in array2", Array2Lab.contains(array2, s));
    s = new StringBuilder("skateboard").toString();
    assertTrue("Incorrectly claims \"skateboard\" is not in array2", Array2Lab.contains(array2, s));
    s = new StringBuilder("visitor").toString();
    assertTrue("Incorrectly claims \"visitor\" is not in array2", Array2Lab.contains(array2, s));
    s = new StringBuilder("violet").toString();
    assertTrue("Incorrectly claims \"violet\" is not in array2", Array2Lab.contains(array2, s));
    assertFalse("Incorrectly claims \"puce\" is in array2", Array2Lab.contains(array2, "puce"));
  }
  
  /**
   * Test the arrayToString method.
   */
  @Test
  public void testArrayToString() {
    assertEquals("{}", Array2Lab.arrayToString(new int[0][0]));
    assertEquals("{{1}}", Array2Lab.arrayToString(new int[][]{{1}}));
    assertEquals("{{1 2}\n {3 4}}", Array2Lab.arrayToString(new int[][]{{1, 2}, {3, 4}}));
    assertEquals("{{1 2}\n {}\n {3 4 5}\n {6}}", Array2Lab.arrayToString(new int[][]{{1, 2},{},{3, 4, 5},{6}}));
  }
  
  /**
   * Test the transpose method.
   */
  @Test
  public void testTranspose() {
    int[][] array = {{1, 2, 3, 4}, {11, 12, 13, 14, 15, 16}, {21, 22, 23, 24}, {31, 32, 33}};
    
    int[][] out1 = {{12}};
    int[][] out2 = {{13, 23}, {14, 24}};
    int[][] out3 = {{2, 12, 22, 32}, {3, 13, 23, 33}};
    int[][] out4 = {{12}, {13}, {14}, {15}};
    
    equals2Darrays(out1, Array2Lab.transpose(array, 1, 1, 1, 1));
    equals2Darrays(out2, Array2Lab.transpose(array, 1, 2, 2, 3));
    equals2Darrays(out3, Array2Lab.transpose(array, 0, 3, 1, 2));
    equals2Darrays(out4, Array2Lab.transpose(array, 1, 1, 1, 4));
  }
  
  /**
   * Helper method to compare two dimensional arrays.
   */
  private void equals2Darrays(int[][] expected, int[][] received) {
    assertEquals(expected.length, received.length);
    for (int i = 0; i < expected.length; i++) {
      assertArrayEquals(expected[i], received[i]);
    }
  }
  
  /**
   * Test the removeRowAndCol method
   */
  @Test
  public void testRemoveRowAndCol() {
    int[][] array = {{1, 2, 3, 4}, {11, 12, 13, 14, 15, 16}, {21, 22, 23, 24}, {31, 32, 33}};
    
    equals2Darrays(new int[][]{{1}}, Array2Lab.removeRowAndCol(new int[][]{{1, 2}, {3, 4}}, 1, 1));
    
    int[][] out2 = {{12, 13, 14, 15, 16}, {22, 23, 24}, {32, 33}};
    equals2Darrays(out2, Array2Lab.removeRowAndCol(array, 0, 0));
    
    int[][] out3 = {{1, 2, 3}, {11, 12, 13, 15, 16}, {31, 32, 33}};
    equals2Darrays(out3, Array2Lab.removeRowAndCol(array, 2, 3));
  }
    
}
