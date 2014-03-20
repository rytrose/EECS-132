/** 
 * The game Gomoku, which is a two player game played on a grid,
 * with the winner being the first player to get five of their pieces in a row either
 * horizontally, vertically, or diagonally.
 * @author Ryan Rose
 */
import javax.swing.JFrame;
import javax.swing.JButton;
public class Gomoku extends JFrame{
  // Number of rows of the game.
  private int rows = 0;
  // Number of columns of the game.
  private int columns = 0;
  
  
  /** 
   * Returns the number of consecutive pieces of the played color in a specified direction. 
   * @param board  two dimensional array of JButtons used as the board
   * @param row  row of currently played piece
   * @param column  column of currently played piece
   * @param direction  specified direction from currently played piece of either 
   *        "N", "NE", "E", "SE", "S", "SW", "W", or "NW"
   * @return the number of pieces in line
   */
  public int numberInLine(JButton[][] board, int row, int column, String direction){
    // Handles north.
    if(direction == "N"){
      int inLine = 0;
      for(int i = 0; row - i >= 0; i++){
        if(board[row - i][column].getBackground() == board[row][column].getBackground())
          inLine += 1;
      }
      return inLine;
    }
    // Handles northeast.
    if(direction == "NE"){
      int inLine = 0;
      for(int i = 0; (row - i >= 0) && (column + i < board[0].length); i++){
        if(board[row - i][column + i].getBackground() == board[row][column].getBackground())
          inLine += 1;
      }
      return inLine;
    }
    // Handles east.
    if(direction == "E"){
      int inLine = 0;
      for(int i = 0; column + i < board[0].length; i++){
        if(board[row][column + 1].getBackground() == board[row][column].getBackground())
          inLine += 1;
      }
      return inLine;
    }
    // Handles southeast.
    if(direction == "SE"){
      int inLine = 0;
      for(int i = 0; (row + i < board.length) && (column + i < board[0].length); i++){
        if(board[row + i][column + i].getBackground() == board[row][column].getBackground())
          inLine += 1;
      }
      return inLine;
    }
    // Handles south.
    if(direction == "S"){
      int inLine = 0;
      for(int i = 0; row + i < board.length; i++){
        if(board[row + i][column].getBackground() == board[row][column].getBackground())
          inLine += 1;
      }
      return inLine;
    }
    // Handles southwest.
    if(direction == "SW"){
      int inLine = 0;
      for(int i = 0; (row + i < board.length) && (column - i >= 0); i++){
        if(board[row + i][column + i].getBackground() == board[row][column].getBackground())
          inLine += 1;
      }
      return inLine;
    }    
//  return 0;
  }
}