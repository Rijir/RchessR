package rchessr.pieces;

import java.awt.Point;
import java.util.HashSet;
import rchessr.Team;
import rchessr.Board;

/* This class specifies shared behavior and fields for chess pieces.
 */
public abstract class Piece{
  private int x, y;//the current position on the board. x is col, y is row
  private Team team;//black or white
  private Board board;//the board object, used for checking if a square is empty and such.
  private boolean hasMoved;//a boolean which is turned on after the first call of the move() which
                           //results in the piece actually moving.

  //Constructs a piece with the given color at the given position on the given board
  //Parameters:
  //  team: white or black
  //  x: col
  //  y: row
  //  board: the board which the piece is on
  public Piece(Team team, int x, int y, Board board){
    this.x = x;
    this.y = y;
    this.team = team;
    this.board = board;
    hasMoved = false;
  }

  //This method should be overwriten by subclasses to contain specific checks for the pieces own
  //movement. Subclasses should call this at the begining of their own method, since this contains
  //generic checks such as making sure that the space is actually on the board or if there is a
  //piece on the same team in the target square (there isn't friendly fire in chess).
  public boolean validMove(int x, int y){
    if(x < 0 || x > 7 || y < 0 || y > 7){
      return false;
    }
    if(this.getBoard().getPiece(x, y) != null &&
       this.getBoard().getPiece(x, y).getTeam() == this.getTeam()){
      return false;
    }
    return true;
  }
  
  //This method changes the pieces position, marks that the piece has moved, and returns true if
  //the given move is valid. It just returns false otherwise. NOTE: the board still has to change
  //where the piece actually is, this just changes where the piece thinks it is.
  public boolean move(int x, int y){
    if(validMove(x, y)){
      this.x = x;
      this.y = y;
      hasMoved = true;
      return true;
    }else{
      return false;
    }
  }

  //returns if the piece is white or black
  public Team getTeam(){
    return team;
  }

  //returns the board object. Used by subclasses.
  public Board getBoard(){
    return board;
  }

  //returns the current column
  public int x(){
    return x;
  }

  //returns the current row
  public int y(){
    return y;
  }

  //returns whether the piece has moved yet
  public boolean hasMoved(){
    return hasMoved;
  }

  //returns the string representation of the piece to be used in text-based board output. Should be
  //overwritten by subclasses to show the correct character.
  public String toString(){
    return "X";
  }

  //returns the full name of the piece. Should be overwritten by subclasses
  public String getName(){
    return "Abstract Piece";
  }

  //returns the point value of the piece. Should be overwritten by subclasses
  public int getValue(){
    return 0;
  }

  //returns the spaces into which the piece can currently move. Should be overwritten by
  //subclasses. Alternatively I may rewrite this to just check every space on the board, since
  //there aren't that many, and just check validMove() for each one. That would cut down on
  //subclass length.
  public HashSet<Point> getValidMoves(){
    return new HashSet<Point>();
  }
}
