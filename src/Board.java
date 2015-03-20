package rchessr;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;
import java.util.HashSet;
import rchessr.pieces.*;

/* This class describes the chess board. It stores the positions of all of the pieces and provides
 * methods for viewing the board state and performing valid moves.
 */

 public class Board{
  //array containing the pieces. first dimention corresponds to row and second corresponds to 
  //column.
  private Piece[][] spaces;
  //A list of algebraic notation entries describing the moves up to this point in the game.
  private ArrayList<String> history;
  
  //Constructs a board with predefined piece positions and the given move history.
  //Prameters:
  //  placements: the two dimentional Piece array descriping the positions of all the pieces on the
  //  board
  //  history: the array list containing the algebraic notation representations of past moves in
  //  the game.
  public Board(Piece[][] placements, ArrayList<String> history){
    //THING I REALLY NEED TO DO!!!!: make sure to set all board fields of the pieces to point to
    //this, since there is no way that has already been set
    spaces = placements;
    this.history = history;
  }

  //Constructs a board with all the pieces in their starting position and no move history.
  public Board(){
    spaces = new Piece[][]{
          {new Rook(Team.BLACK, 0, 0, this), new Knight(Team.BLACK, 1, 0, this),
           new Bishop(Team.BLACK, 2, 0, this), new Queen(Team.BLACK, 3, 0, this),
           new King(Team.BLACK, 4, 0, this), new Bishop(Team.BLACK, 5 , 0, this),
           new Knight(Team.BLACK, 6, 0, this), new Rook(Team.BLACK, 7, 0, this)},
          {new Pawn(Team.BLACK, 0, 1, this), new Pawn(Team.BLACK, 1, 1, this),
           new Pawn(Team.BLACK, 2, 1, this), new Pawn(Team.BLACK, 3, 1, this),
           new Pawn(Team.BLACK, 4, 1, this), new Pawn(Team.BLACK, 5, 1, this),
           new Pawn(Team.BLACK, 6, 1, this), new Pawn(Team.BLACK, 7, 1, this)},
          {null,   null,   null,   null,   null,   null,   null,   null},
          {null,   null,   null,   null,   null,   null,   null,   null},
          {null,   null,   null,   null,   null,   null,   null,   null},
          {null,   null,   null,   null,   null,   null,   null,   null,},
          {new Pawn(Team.WHITE, 0, 6, this), new Pawn(Team.WHITE, 1, 6, this),
           new Pawn(Team.WHITE, 2, 6, this), new Pawn(Team.WHITE, 3, 6, this), 
           new Pawn(Team.WHITE, 4, 6, this), new Pawn(Team.WHITE, 5, 6, this),
           new Pawn(Team.WHITE, 6, 6, this), new Pawn(Team.WHITE, 7, 6, this)},
          {new Rook(Team.WHITE, 0, 7, this), new Knight(Team.WHITE, 1, 7, this),
           new Bishop(Team.WHITE, 2, 7, this), new Queen(Team.WHITE, 3, 7, this),
           new King(Team.WHITE, 4, 7, this), new Bishop(Team.WHITE, 5, 7, this),
           new Knight(Team.WHITE, 6, 7, this), new Rook(Team.WHITE, 7, 7, this)}
         };
    history = new ArrayList<String>();
  }

  //Constructs a board with predefined piece positions and no move history.
  //Parameters:
  //  placements: the two dimentional Piece array descriping the positions of all the pieces on the
  //  board
  public Board(Piece[][] placements){
    this(placements, new ArrayList<String>());
  }

  //Returns a string representation of the current board state.
  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("---------------------------------\n");
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        builder.append("| ");
        if(spaces[i][j] == null){
          builder.append(" ");
        }else{
          builder.append(spaces[i][j].toString());
        }
        builder.append(" ");
      }
      builder.append("|\n");
      builder.append("---------------------------------\n");
    }
    return builder.toString();
  }
  
  //returns the piece in the given position.
  //Parameters:
  //  x: the column of the piece
  //  y: the row of the piece
  public Piece getPiece(int x, int y){
    return spaces[y][x];
  }

  //places the given piece on the board at the indicated position.
  //parameter:
  //  p: the piece to place
  //  x: the column in which to place the piece
  //  y: the row in which to place the piece
  public void putPiece(Piece p, int x, int y){
    spaces[y][x] = p;
  }
  
  //Moves the piece at x1, y1 to x2, y2
  public boolean move(int x1, int y1, int x2, int y2){
    Piece p = this.getPiece(x1, y1);
    if(p != null && p.move(x2, y2)){
      this.putPiece(p, x2, y2);
      this.putPiece(null, x1, y1);
      return true;
    }else{
      return false;
    }
  }

  //performs the move given by an algebraic chess notation string
  //Parameters:
  //  s: the string containing the move
  //  t: the team which is currently moving
  public boolean move(String s, Team t) throws AmbiguousMoveException{
    String pieceName;
    String coords;
    if(s.length() == 2){
      pieceName = "p";
      coords = s;
    }else{
      pieceName = s.substring(0, 1);
      coords = s.substring(1);
    }
    Point p = translateMove(coords);
    HashSet<Piece> movers = getMovers(p.x, p.y, t);
    Piece moving = null;
    for(Piece foo: movers){
      if(foo.toString() == pieceName){
        if(moving == null){
          moving = foo;
        }else{
          throw new AmbiguousMoveException();
        }
      }
    }
    if(moving == null){
      return false;
    }else{
      return this.move(moving.x(), moving.y(), p.x, p.y);
    }
  }

  //translates the string coordinates into integers x and y. The string should be in of the form
  //[a-h][1-8]. Returns a point for now, might change that to a custom BoardPosition class or
  //something.
  private static Point translateMove(String coords){
    if((coords.length() != 2) || (!Character.isLetter(coords.charAt(0))) ||
        (!Character.isDigit(coords.charAt(1)))){
      throw new IllegalArgumentException();
    }
    Point p = new Point();
    char c = coords.charAt(0);
    if(Character.isUpperCase(c)){
      p.x = (int)c - 65;
    }else{
      p.x = (int)c - 97;
    }
    p.y = Integer.parseInt(coords.substring(1))-1;
    return p;
  }

  //Returns a set containing all pieces on the given team which can move to a given square.
  //Parameters:
  //  x: the col of the position to check
  //  y: the row ""
  //  team: get black or white pieces
  public HashSet<Piece> getMovers(int x, int y, Team team){
    HashSet<Piece> set = new HashSet<Piece>();
    for(Piece[] row : spaces){
      for(Piece p : row){
        if(p != null && p.getTeam() == team && p.validMove(x, y)){
          set.add(p);
        }
      }
    }
    return set;
  }

  //returns the integer value of all the pieces still on the board for a given team. King counts
  //for 40 so that he outweighs all other pieces. If the point value whithout the king is needed,
  //just subtract 40 from the score, since the king is guarenteed to be on the board (at least in
  //standard chess).
  public int getScore(Team team){
    int score = 0;
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(spaces[i][j] != null && spaces[i][j].getTeam() == team){
          score += spaces[i][j].getValue();
        }
      }
    }
    return score;
  }
}
