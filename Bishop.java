import java.awt.Point;
import java.util.HashSet;

public class Bishop extends Piece{
  public Bishop(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  public boolean validMove(int x, int y){
    if(!super.validMove(x, y)){
      return false;
    }else if(Math.abs(x - this.x()) == Math.abs(y - this.y())){
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
