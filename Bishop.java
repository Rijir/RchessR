import java.awt.Point;
import java.util.HashSet;

/* This class describes the Bishop piece including its valid moves, point value, name, and 
 * character representation.
 */
public class Bishop extends Piece{
  public Bishop(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  public boolean validMove(int x, int y){
    if(!super.validMove(x, y)){//generic checks
      return false;
    //the change in x = the change in y, which means that the move is on a diagonal line from the
    //current position
    }else if(Math.abs(x - this.x()) == Math.abs(y - this.y())){
      //makes sure there is nothing in the way
      for(int i = 1; i < Math.abs(x - this.x()); i++){
        if(this.getBoard().getPiece(this.x() + i * Integer.signum(x - this.x()),
              this.y() + i * Integer.signum(y - this.y())) != null){
          return false;
        }
      }
      return true;
    }else{
      return false;
    }
  }

  public String toString(){
    return "B";
  }

  public String getName(){
    return "Bishop";
  }
  
  public int getValue(){
    return 3;
  }
  
  //goes through all positions on the four diagonals from the current position and checks if the
  //piece can move there
  public HashSet<Point> getValidMove(){
    HashSet<Point> set = new HashSet<Point>();
    for(int i = 1; i < Math.max(Math.max(this.x(), 7 - this.x()), Math.max(this.y(), 7 - this.y())); i++){
      if(this.validMove(this.x() + i, this.y() + i)){
        set.add(new Point(this.x() + i, this.y() + i));
      }
      if(this.validMove(this.x() + i, this.y() - i)){
        set.add(new Point(this.x() + i, this.y() - i));
      }
      if(this.validMove(this.x() - i, this.y() + i)){
        set.add(new Point(this.x() - i, this.y() + i));
      }
      if(this.validMove(this.x() - i, this.y() - i)){
        set.add(new Point(this.x() - i, this.y() - i));
      }
    }
    return set;
  }
}
