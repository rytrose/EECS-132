import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * Test class Gomoku
 */
public class GomokuTester{
  
  /**
   * Tests the numberInLine method.
   */
  @Test
  public void testNumberInLine(){
    Gomoku game = new Gomoku();
    game.setVisible(false);
    JButton[][] test = new JButton[19][19];
    for(int i = 0; i < test.length; i++){
      for(int j = 0; j < test[i].length; j++){
        test[i][j] = new JButton();
        test[i][j].setBackground(Color.green);
      }
    }
    // horizontal row of three
    test[1][1].setBackground(Color.black);
    test[1][2].setBackground(Color.black);
    test[1][3].setBackground(Color.black);
    // vertical column, with opposing color directly below 
    test[2][0].setBackground(Color.black);
    test[3][0].setBackground(Color.black);
    // diagonal in the middle of the board
    test[4][0].setBackground(Color.white);
    test[5][1].setBackground(Color.white);
    test[6][2].setBackground(Color.white);
    test[7][3].setBackground(Color.white);
    // diagonal to upper right corner
    test[0][18].setBackground(Color.white);
    test[1][17].setBackground(Color.white);
    test[2][16].setBackground(Color.white);
    test[3][15].setBackground(Color.white);
    // bottom left corner
    test[18][0].setBackground(Color.black);
    // bottom right corner
    test[18][18].setBackground(Color.white);
    
    // test 1
    assertEquals(1, game.numberInLine(test, 19, 1, "N"));  // test first
    assertEquals(1, game.numberInLine(test, 19, 1, "S"));
    assertEquals(1, game.numberInLine(test, 19, 1, "E"));
    assertEquals(1, game.numberInLine(test, 19, 1, "W"));
    assertEquals(1, game.numberInLine(test, 19, 19, "NW"));  // test last  
    assertEquals(1, game.numberInLine(test, 19, 19, "NE"));
    assertEquals(1, game.numberInLine(test, 19, 19, "SE"));
    assertEquals(1, game.numberInLine(test, 19, 19, "SW"));
    
    // test 0
    assertEquals(0, game.numberInLine(test, 1, 1, "N"));  // test first
    assertEquals(0, game.numberInLine(test, 1, 1, "NE"));
    assertEquals(0, game.numberInLine(test, 1, 1, "E"));
    assertEquals(0, game.numberInLine(test, 1, 1, "SE"));
    assertEquals(0, game.numberInLine(test, 1, 1, "S"));
    assertEquals(0, game.numberInLine(test, 1, 1, "SW"));
    assertEquals(0, game.numberInLine(test, 1, 1, "W"));
    assertEquals(0, game.numberInLine(test, 1, 1, "NW"));
    
    // test many
    assertEquals(3, game.numberInLine(test, 2, 2, "E"));
    assertEquals(3, game.numberInLine(test, 2, 4, "W"));
    assertEquals(2, game.numberInLine(test, 3, 1, "S"));
    assertEquals(2, game.numberInLine(test, 4, 1, "N"));
    assertEquals(3, game.numberInLine(test, 6, 2, "SE"));  //  test middle
    assertEquals(2, game.numberInLine(test, 6, 2, "NW"));
    assertEquals(4, game.numberInLine(test, 1, 19, "SW"));  // test last
    assertEquals(3, game.numberInLine(test, 3, 17, "NE"));
  }
  
  /**
   * Tests the isOpen method.
   */
  @Test
  public void testIsOpen(){
    Gomoku game = new Gomoku();
    game.setVisible(false);
    JButton[][] test = new JButton[19][19];
    for(int i = 0; i < test.length; i++){
      for(int j = 0; j < test[i].length; j++){
        test[i][j] = new JButton();
        test[i][j].setBackground(Color.green);
      }
    }
    // horizontal row of three
    test[1][1].setBackground(Color.black);
    test[1][2].setBackground(Color.black);
    test[1][3].setBackground(Color.black);
    // vertical column, with opposing color directly below 
    test[2][0].setBackground(Color.black);
    test[3][0].setBackground(Color.black);
    // diagonal in the middle of the board
    test[4][0].setBackground(Color.white);
    test[5][1].setBackground(Color.white);
    test[6][2].setBackground(Color.white);
    test[7][3].setBackground(Color.white);
    // diagonal to upper right corner
    test[0][18].setBackground(Color.white);
    test[1][17].setBackground(Color.white);
    test[2][16].setBackground(Color.white);
    test[3][15].setBackground(Color.white);
    // bottom left corner
    test[18][0].setBackground(Color.black);
    // diagonal to bottom right corner
    test[16][16].setBackground(Color.white);
    test[17][17].setBackground(Color.white);
    test[18][18].setBackground(Color.white);
    // floating single piece
    test[11][11].setBackground(Color.black);
    // piece completely surrounded
    test[14][6].setBackground(Color.white);
    test[13][5].setBackground(Color.black);
    test[13][6].setBackground(Color.black);
    test[13][7].setBackground(Color.black);
    test[14][5].setBackground(Color.black);
    test[14][7].setBackground(Color.black);
    test[15][5].setBackground(Color.black);
    test[15][6].setBackground(Color.black);
    test[15][7].setBackground(Color.black);
    
    // test 0
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "N"));  // test first
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "S"));                                                                      
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "E"));
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "W"));
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "NE"));
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "NW"));
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "SW"));
    assertTrue("Incorrectly states a blank space is closed.", game.isOpen(test, 1, 1, "SE"));
    
    // test 1
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "N"));  // test middle
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "S"));
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "E"));
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "W"));
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "NE"));
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "NW"));
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "SE"));
    assertTrue("Incorrectly states the piece is not open in all directions.", game.isOpen(test, 12, 12, "SW"));
    
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "N"));
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "S"));
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "E"));
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "W"));
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "NW"));
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "NE"));
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "SE"));
    assertFalse("Incorrectly states the pice is open in all directions.", game.isOpen(test, 15, 7, "SW"));
    
    // test many
    assertFalse("Incorrectly states that off of the board is open.", game.isOpen(test, 4, 16, "NE"));  // test last
    assertTrue("Incorectly states the space is not open.", game.isOpen(test, 1, 19, "SW"));
    assertFalse("Incorrectly states the space is not occupied by the other color.", game.isOpen(test, 3, 1, "S"));
    assertTrue("Incorrectly states the space above is occupied.", game.isOpen(test, 4, 1, "N"));
    assertTrue("Incorrectly states the space is occupied.", game.isOpen(test, 2, 4, "W"));
    assertTrue("Incorrectly states the space is occupied.", game.isOpen(test, 2, 2, "E"));
    assertFalse("Incorrectly states that off of the board is open.", game.isOpen(test, 17, 17, "SE"));
    assertTrue("Incorrectly states the space is occupied.", game.isOpen(test, 19, 19, "NW"));  // test last
    
  }
  
  
}
