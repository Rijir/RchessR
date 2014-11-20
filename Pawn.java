import java.awt.Point;
import java.util.HashSet;

public class Pawn extends Piece{
  public Pawn(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  public boolean validMove(int x, int y){
    if(!super.validMove(x, y)){
      return false;
    }
    int direction;
    if(this.getTeam() == Team.WHITE){
      direction = -1;
    }else{
      direction = 1;
    }
    if((this.getBoard().getPiece(x, y) == null && x == this.x() && y == this.y() + direction)
        || ((this.getBoard().getPiece(x, y) != null)
            && (Math.abs(x - this.x()) == 1) && (y == this.y() + direction))
        || (!this.hasMoved() && this.getBoard().getPiece(x, y) == null
            && this.getBoard().getPiece(x, y - direction) == null 
            && x == this.x() && y == this.y() + 2*direction)
        /*TODO: add en passant check*/){
      return true;
    }else{
      return false;
    }
  }

  public String toString(){
    return "p";
  }

  public String getName(){
    return "Pawn";
  }
  
  public int getValue(){
    return 1;
  }

  public HashSet<Point> getValidMoves(){
    HashSet<Point> set = new HashSet<Point>();
    if(this.validMove(this.x(), this.y() + 1)){
      set.add(new Point(this.x(), this.y() + 1));
    }
    if(this.validMove(this.x(), this.y() + 2)){
      set.add(new Point(this.x(), this.y() + 2));
    }
    if(this.validMove(this.x(), this.y() - 1)){
      set.add(new Point(this.x(), this.y() - 1));
    }
    if(this.validMove(this.x(), this.y() - 2)){
      set.add(new Point(this.x(), this.y() - 2));
    }
    if(this.validMove(this.x() + 1, this.y() + 1)){
      set.add(new Point(this.x() + 1, this.y() + 1));
    }
    if(this.validMove(this.x() + 1, this.y() - 1)){
      set.add(new Point(this.x() + 1, this.y() - 1));
    }
    if(this.validMove(this.x() - 1, this.y() + 1)){
      set.add(new Point(this.x() + 1, this.y() + 1));
    }
    if(this.validMove(this.x() - 1, this.y() - 1)){
      set.add(new Point(this.x() + 1, this.y() - 1));
    }
    return set;
  }

  public boolean canPromote(){
    return (this.getTeam() == Team.WHITE && this.y() == 0) ||
           (this.getTeam() == Team.BLACK && this.y() == 7);
  }
}
