import java.awt.Point;
import java.util.HashSet;

public abstract class Piece{
  private int x, y;
  private Team team;
  private Board board;
  private boolean hasMoved;

  public Piece(Team team, int x, int y, Board board){
    this.x = x;
    this.y = y;
    this.team = team;
    this.board = board;
    hasMoved = false;
  }

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

  public Team getTeam(){
    return team;
  }

  public Board getBoard(){
    return board;
  }

  public int x(){
    return x;
  }

  public int y(){
    return y;
  }

  public boolean hasMoved(){
    return hasMoved;
  }

  public String toString(){
    return "X";
  }

  public String getName(){
    return "Abstract Piece";
  }

  public int getValue(){
    return 0;
  }

  public HashSet<Point> getValidMoves(){
    return new HashSet<Point>();
  }
}
