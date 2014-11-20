import java.awt.Point;
import java.util.HashSet;

public class Queen extends Piece{
  public Queen(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  public boolean validMove(int x, int y){
    if(!super.validMove(x, y)){
      return false;
    }else if(x == this.x()){
      for(int i = Math.min(y, this.y())+1; i < Math.max(y, this.y()); i++){
        if(this.getBoard().getPiece(x, i) != null){
          return false;
        }
      }
    }else if(y == this.y()){
      for(int i = Math.min(x, this.x())+1; i < Math.max(x, this.x()); i++){
        if(this.getBoard().getPiece(i, y) != null){
          return false;
        }
      }
    }else if(Math.abs(x - this.x()) == Math.abs(y - this.y())){
      for(int i = 1; i < Math.abs(x - this.x()); i++){
        if(this.getBoard().getPiece(this.x() + i * Integer.signum(x - this.x()),
              this.y() + i * Integer.signum(y - this.y())) != null){
          return false;
        }
      }
    }else{
      return false;
    }
    return true;
  }

  public String toString(){
    return "Q";
  }
  
  public String getName(){
    return "Queen";
  }
  
  public int getValue(){
    return 9;
  }
  
  public HashSet<Point> getValidMoves(){
    HashSet<Point> set = new HashSet<Point>();
    for(int i = 1; i < Math.max(Math.max(this.x(), 7 - this.x()), Math.max(this.y(), 7 - this.y())); i++){
      if(this.validMove(this.x() + i, this.y())){
        set.add(new Point(this.x() + i, this.y()));
      }
      if(this.validMove(this.x() - i, this.y())){
        set.add(new Point(this.x() + i, this.y()));
      }
      if(this.validMove(this.x(), this.y() + i)){
        set.add(new Point(this.x(), this.y() + i));
      }
      if(this.validMove(this.x(), this.y() - i)){
        set.add(new Point(this.x(), this.y() - i));
      }
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
