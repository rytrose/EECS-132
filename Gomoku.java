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
  /** 
   * Board for the game.
   */
  private JButton[][] board;
  /**
   * Number of rows of the game.
   */
  private int rows = 0;
  /**
   * Number of columns of the game.
   */
  private int columns = 0;
  /**
   * Default height.
   */
  private int height = 800;
  /**
   * Default width.
   */
  private int width = 800;
  /**
   * Turn number.
   */
  private int turn = 0;
  /**
   * Restart button.
   */
  private JButton restart;
  
  /**
   * Main method which creates a 19 x 19 board if there are no arguments, or a board with the given
   * arguments.
   * @exception Gomoku catches inputs that are not ints
   */
  public static void main(String[] args){
    if(args.length != 0){
      if(args.length == 2){
        try{
          new Gomoku(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        catch(NumberFormatException e){
          System.out.println("Two positive integer inputs only.");
        }
      }
      if(args.length != 2)
        System.out.println("Two positive integer inputs only.");
    }
    else
      new Gomoku();
  }
  
  /** 
   * Creates a default 19 x 19 board.
   */
  public Gomoku(){
    super("Gomoku");
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
    this.setVisible(true);
  }
  
  /**
   * Creates a board with a given number of rows and columns.
   */ 
  public Gomoku(int rows, int columns){
    super("Gomoku");
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
    this.setVisible(true);
  }
  
  /** 
   * Determines if the game has been won.
   * @return returns true if one player plays exactly five consecutive pieces in any direction 
   */
  public boolean isWon(){
    boolean isWon = false;
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j].getBackground() != Color.green){
          if((this.numberInLine(board, i + 1, j + 1, "N") + 
              this.numberInLine(board, i + 1, j + 1, "S")) == 6 ||
             (this.numberInLine(board, i + 1, j + 1, "NE") +
              this.numberInLine(board, i + 1, j + 1, "SW")) == 6||
             (this.numberInLine(board, i + 1, j + 1, "E") +
             this.numberInLine(board, i + 1, j + 1, "W")) == 6 ||
             (this.numberInLine(board, i + 1, j + 1, "SE") +
             this.numberInLine(board, i + 1, j + 1, "NW")) == 6)
            isWon = true;
        }
      }
    }
    return isWon;
  }
  
  /** 
   * Identifies a three-three.
   * @param board  two dimensional array of JButtons used as the board
   * @param row  row of currently played piece
   * @param column  column of currently played piece
   * @return returns true if the spot is a three-three
   */
  public boolean isThreeThree(JButton[][] board, int row, int column){
    boolean isThreeThree = false;
    boolean isCross = false;
    if((row > 2) && (row < board.length - 1) && (column > 2) && (column < board[0].length - 1)){
      if(numberInLine(board, row - 1, column, "N") == 1 &&
         numberInLine(board, row, column + 1, "E") == 1 &&
         numberInLine(board, row + 1, column, "S") == 1 &&
         numberInLine(board, row, column - 1, "W") == 1)
        isCross = true;
       else if (numberInLine(board, row - 1, column + 1, "NE") == 1 &&
         numberInLine(board, row + 1, column - 1, "SE") == 1 && 
         numberInLine(board, row + 1, column - 1, "SW") == 1 &&
         numberInLine(board, row - 1, column + 1, "NW") == 1)
        isCross = true;
      if(isCross == true){
        if(isOpen(board, row - 1, column, "N") == true &&
           isOpen(board, row, column + 1, "E") == true &&
           isOpen(board, row + 1, column, "S") == true &&
           isOpen(board, row, column - 1, "W") == true  &&
           (((board[row - 1][column].getBackground() == Color.black) && turn % 2 == 0) ||
            (board[row - 1][column].getBackground() == Color.white) && turn % 2 == 1))
          isThreeThree = true;
        else if(isOpen(board, row - 1, column + 1, "NE") == true &&
                isOpen(board, row + 1, column + 1, "SE") == true && 
                isOpen(board, row + 1, column - 1, "SW") == true &&
                isOpen(board, row - 1, column - 1, "NW") == true &&
                (((board[row - 2][column - 2].getBackground() == Color.black) && turn % 2 == 0) ||
                 (board[row - 2][column - 2].getBackground() == Color.white) && turn % 2 == 1))
          isThreeThree = true;
      }
    }
    return isThreeThree;
  }
  
  /**
   * Identifies a four-four.
   * @param board  two dimensional array of JButtons used as the board
   * @param row  row of currently played piece
   * @param column  column of currently played piece
   * @return returns true if a spot is a four-four.
   */  
  public boolean isFourFour(JButton[][] board, int row, int column){
    boolean isFourFour = false;
    boolean isCross = false;
    if((row > 3) && (row < board.length - 2) && (column > 3) && (column < board[0].length - 2)){
      if(numberInLine(board, row - 1, column, "N") == 2 &&
         numberInLine(board, row, column + 1, "E") == 2 &&
         numberInLine(board, row + 1, column, "S") == 2 &&
         numberInLine(board, row, column - 1, "W") == 2)
        isCross = true;
       else if (numberInLine(board, row - 1, column + 1, "NE") == 2 &&
         numberInLine(board, row + 1, column + 1, "SE") == 2 && 
         numberInLine(board, row + 1, column - 1, "SW") == 2 &&
         numberInLine(board, row - 1, column - 1, "NW") == 2)
        isCross = true;
      if(isCross == true){
        if(isOpen(board, row - 1, column, "N") == true &&
           isOpen(board, row, column + 1, "E") == true &&
           isOpen(board, row + 1, column, "S") == true &&
           isOpen(board, row, column - 1, "W") == true  &&
           (((board[row - 1][column].getBackground() == Color.black) && turn % 2 == 0) ||
            (board[row - 1][column].getBackground() == Color.white) && turn % 2 == 1))
          isFourFour = true;
        else if(isOpen(board, row - 1, column + 1, "NE") == true &&
                isOpen(board, row + 1, column + 1, "SE") == true && 
                isOpen(board, row + 1, column - 1, "SW") == true &&
                isOpen(board, row - 1, column - 1, "NW") == true &&
                (((board[row - 2][column - 2].getBackground() == Color.black) && turn % 2 == 0) ||
                 (board[row - 2][column - 2].getBackground() == Color.white) && turn % 2 == 1))
          isFourFour = true;
      }
    }
    return isFourFour;
  }
  
  
  
  
  
  /** 
   * Manages what occurs when a button, or space on the board, is clicked.
   */
  public void actionPerformed(ActionEvent e){
    JButton b = (JButton)e.getSource();
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if((b == board[i][j]) && this.isThreeThree(board, i + 1, j + 1) == true)
          System.out.println("Three-three, cannot play there!");
        if((b == board[i][j]) && this.isFourFour(board, i + 1, j + 1) == true)
          System.out.println("Four-four, cannot play there!");
        if((b == board[i][j]) && (this.isWon() == false) && (board[i][j].getBackground() == Color.green)
             && this.isThreeThree(board, i + 1, j + 1) == false && this.isFourFour(board, i + 1, j + 1) == false){
          if(turn % 2 == 0){
            board[i][j].setBackground(Color.black);
            turn += 1;                
          }
          else{
            board[i][j].setBackground(Color.white);
            turn += 1;
          }
          if(this.isWon() == true)
            System.out.println("The game is won!");
            this.winGame();
        } 
      }
    }
    if(b == restart){
     new Gomoku(rows, columns);
     this.setVisible(false);
     System.out.println("New game:");
    }
  }
  
  /** 
   * Creates a restart button when the game is won. 
   */
  public void winGame(){
    if(this.isWon() == true){
      restart = new JButton("Restart?");
      restart.setSize(width, height/rows);
      this.getContentPane().add(restart, "North");
      restart.addActionListener(this);
      this.setSize(width, height + restart.getHeight());
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
      for(int i = 0; (row - i > 0) && 
          (board[(row - 1) - i][column - 1].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles northeast.
    if(direction.equals("NE")){
      for(int i = 0; (row - i > 0) && ((column - 1) + i < board[0].length) && 
          (board[(row - 1) - i][(column - 1)+ i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles east.
    if(direction.equals("E")){
      for(int i = 0; ((column - 1) + i < board[0].length) &&
          (board[row - 1][(column - 1) + i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles southeast.
    if(direction.equals("SE")){
      for(int i = 0; ((row - 1) + i < board.length) && ((column - 1) + i < board[0].length) &&
          (board[(row - 1) + i][(column - 1) + i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles south.
    if(direction.equals("S")){
      for(int i = 0; (row - 1) + i < board.length &&
          (board[(row - 1) + i][column - 1].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles southwest.
    if(direction.equals("SW")){
      for(int i = 0; ((row - 1) + i < board.length) && ((column - i > 0) &&
          (board[(row - 1) + i][(column - 1) - i].getBackground() == board[row - 1][column - 1].getBackground())); i++)
        inLine += 1;
      return inLine;
    }
    // Handles west.
    if(direction.equals("W")){
      for(int i = 0; column - i > 0 &&
          (board[row - 1][(column - 1) - i].getBackground() == board[row - 1][column - 1].getBackground()); i++)
        inLine += 1;
      return inLine;
    }
    // Handles northwest.
    if(direction.equals("NW")){
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
    if(board[row - 1][column - 1].getBackground() != Color.green){
    // Handles north.
    if(direction.equals("N")){
      int i = 0;
      while(row - i > 0 &&
            (board[(row - 1) - i][column - 1].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[(row - 1) - i][column - 1].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles northeast.
    if(direction.equals("NE")){
      int i = 0;
      while((row - i > 0) && (column + i < board[0].length) && 
            (board[(row - 1) - i][(column - 1) + i].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[(row - 1) - i][(column - 1)+ i].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles east.
    if(direction.equals("E")){
      int i = 0;
      while((column + i < board[0].length) &&
            (board[(row - 1)][(column - 1) + i].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[row - 1][(column - 1) + i].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles southeast.
    if(direction.equals("SE")){
      int i = 0;
      while((row + i < board.length) && (column + i < board[0].length) &&
            (board[(row - 1) + i][(column - 1) + i].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[(row - 1) + i][(column - 1) + i].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles south.
    if(direction == "S"){
      int i = 0;
      while(row + i < board.length &&
            (board[(row - 1) + i][column - 1].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[(row - 1) + i][column - 1].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles southwest.
    if(direction.equals("SW")){
      int i = 0;
      while((row + i < board.length) && (column - i > 0) &&
            (board[(row - 1) + i][column - 1].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[(row - 1) + i][column - 1].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles west.
    if(direction.equals("W")){
      int i = 0;
      while(column - i > 0 &&
            (board[row - 1][(column - 1) - i].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[row - 1][(column - 1) - i].getBackground() == green)
        return true;
      else
        return false;
    }
    // Handles northwest.
    if(direction.equals("NW")){
      int i = 0;
      while((row - i > 0) && (column - i > 0) &&
            (board[(row - 1) - i][(column - 1) - i].getBackground() == board[row - 1][column - 1].getBackground()))
        i++;
      if(board[(row - 1) - i][(column - 1) - i].getBackground() == green)
        return true;
      else
        return false;
    }
    else 
      return false;
    }
    else 
      return true;
  }
}
  
