/* import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/** 
 * The game Gomoku, which is a two player game played on a grid,
 * with the winner being the first player to get five of their pieces in a row either
 * horizontally, vertically, or diagonally.
 * @author Ryan Rose
 */
public class Gomoku extends JFrame implements ActionListener{
  // Board for the game.
  public JButton[][] board;
  // Number of rows of the game.
  private int rows = 0;
  // Number of columns of the game.
  private int columns = 0;
  // Default height.
  private int height = 800;
  // Default width.
  private int width = 800;
  // Turn number.
  private int turn = 0;
  // Boolean value if the game has been won.
  private boolean isWon = false;
  
  public static void main(String[] args){}
  
  /** 
   * Creates a default 19 x 19 board.
   */
  public Gomoku(){
    this.rows = 19;
    this.columns = 19;
    JPanel panel = new JPanel(new GridLayout(rows, columns));
    this.board = new JButton[rows][columns];
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        board[i][j] = new JButton();
        board[i][j].setBackground(Color.green);
        panel.add(board[i][j]);
        board[i][j].addActionListener(this);
      }
    }
    this.getContentPane().add(panel, "Center");
    this.setSize(width, height);
  }
  
  /**
   * Creates a board with a given number of rows and columns.
   */ 
  public Gomoku(int rows, int columns){
    this.rows = rows;
    this.columns = columns;
    JPanel panel = new JPanel(new GridLayout(rows, columns));
    this.board = new JButton[rows][columns];
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        board[i][j] = new JButton();
        board[i][j].setBackground(Color.green);
        panel.add(board[i][j]);
        board[i][j].addActionListener(this);
      }
    }
    this.getContentPane().add(panel, "Center");
    this.setSize(width, height);
  }
  
  public boolean isWon(){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j].getBackground() != Color.green){
          if(this.numberInLine(board, i + 1, j + 1, "N") == 5 ||
             this.numberInLine(board, i + 1, j + 1, "NE") == 5 ||
             this.numberInLine(board, i + 1, j + 1, "E") == 5 ||
             this.numberInLine(board, i + 1, j + 1, "SE") == 5 ||
             this.numberInLine(board, i + 1, j + 1, "S") == 5 ||
             this.numberInLine(board, i + 1, j + 1, "SW") == 5 ||
             this.numberInLine(board, i + 1, j + 1, "W") == 5 ||
             this.numberInLine(board, i + 1, j + 1, "NW") == 5)
            return true;
          else
            return false;
        }
      }
    }
    return false;
  }
  
  public void actionPerformed(ActionEvent e){
    JButton b = (JButton)e.getSource();
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(b == board[i][j]){
          if(turn % 2 == 0){
            board[i][j].setBackground(Color.black);
            turn += 1;                
          }
          else{
            board[i][j].setBackground(Color.white);
            turn += 1;
          }
        }
      }
    }
  }
  
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
    int inLine = 0;
    if(board[row - 1][column - 1].getBackground() != Color.green){
    // Handles north.
    if(direction.equals("N")){
      // int inLine = 0;
      for(int i = 0; (row - i > 0) && 
          (board[(row - 1) - i][column - 1].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles northeast.
    if(direction.equals("NE")){
      // int inLine = 0;
      for(int i = 0; (row - i > 0) && (column + i < board[0].length) && 
          (board[(row - 1) - i][(column - 1)+ i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles east.
    if(direction.equals("E")){
      // int inLine = 0;
      for(int i = 0; (column + i < board[0].length) &&
          (board[row - 1][(column - 1) + i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles southeast.
    if(direction.equals("SE")){
      // int inLine = 0;
      for(int i = 0; (row + i < board.length) && (column + i < board[0].length) &&
          (board[(row - 1) + i][(column - 1) + i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles south.
    if(direction.equals("S")){
      // int inLine = 0;
      for(int i = 0; row + i < board.length &&
          (board[(row - 1) + i][column - 1].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles southwest.
    if(direction.equals("SW")){
      // int inLine = 0;
      for(int i = 0; (row + i < board.length) && (column - i > 0) &&
          (board[(row - 1) + i][(column - 1) - i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles west.
    if(direction.equals("W")){
      // int inLine = 0;
      for(int i = 0; column - i > 0 &&
          (board[row - 1][(column - 1) - i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles northwest.
    if(direction.equals("NW")){
      // int inLine = 0;
      for(int i = 0; (row - i > 0) && (column - i > 0) &&
          (board[(row - 1) - i][(column - 1) - i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    else
      return inLine;
    }
    else
      return inLine;
  }
  
  /** 
   * Returns true if the space in an input direction starting from the piece being played is open
   * at the end of all the played pieces of the color that's being played.
   * @param board  two dimensional array of JButtons used as the board
   * @param row  row of currently played piece
   * @param column  column of currently played piece
   * @param direction  specified direction from currently played piece of either 
   *        "N", "NE", "E", "SE", "S", "SW", "W", or "NW"
   * @return returns true if the space at the end of the currently played pieces of the color being
   *         played in the specified direction is open
   */
  public boolean isOpen(JButton[][] board, int row, int column, String direction){
    Color green = Color.green;
    // Handles north.
    if(direction == "N"){
      int i = 0;
      while(row - i >= 0 &&
            (board[row - i][column].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row - i][column].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles northeast.
    if(direction == "NE"){
      int i = 0;
    //  Color green = new Color
      while((row - i >= 0) && (column + i < board[0].length) && 
            (board[row - i][column + i].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row - i][column + i].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles east.
    if(direction == "E"){
      int i = 0;
      while((column + i < board[0].length) &&
            (board[row][column + i].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row][column + i].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles southeast.
    if(direction == "SE"){
      int i = 0;
      while((row + i < board.length) && (column + i < board[0].length) &&
            (board[row + 1][column + 1].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row + 1][column + 1].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles south.
    if(direction == "S"){
      int i = 0;
      while(row + i < board.length &&
            (board[row + i][column].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row + i][column].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles southwest.
    if(direction == "SW"){
      int i = 0;
      while((row + i < board.length) && (column - i >= 0) &&
            (board[row + i][column].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row + i][column].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles west.
    if(direction == "W"){
      int i = 0;
      while(column - i >= 0 &&
            (board[row][column - i].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row][column - i].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles northwest.
    if(direction == "NW"){
      int i = 0;
      while((row - i >= 0) && (column - i >= 0) &&
            (board[row - i][column - i].getBackground() == board[row][column].getBackground()))
        i++;
      if(board[row - i][column - i].getBackground() == green)
        return true;
      else
        return false;
    }
    else 
      return false;
  }
}

  
