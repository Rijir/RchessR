import java.awt.Point;
import java.util.HashSet;

/* This class describes the Pawn piece, including valid movement, point value, name, and string
 * representation.
 */
public class Pawn extends Piece{
  public Pawn(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  //Returns true if the space is one straight ahead and empty, one diagonal square ahead and an
  //enemy in the space, or two straight ahead and this is the pawn's first move. Eventually it will
  //also handle En Pasant, but that's a little bit complicated.
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

  //returns all spaces into which the pawn can move.
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

  //returns true if the pawn is currently in the promotion row.
  public boolean canPromote(){
    return (this.getTeam() == Team.WHITE && this.y() == 0) ||
           (this.getTeam() == Team.BLACK && this.y() == 7);
  }
}
