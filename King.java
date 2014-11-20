import java.awt.Point;
import java.util.HashSet;

public class King extends Piece{
  public King(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  public boolean validMove(int x, int y){//TODO: Add castling support
    if(!super.validMove(x, y)){
      return false;
    }else if((Math.abs(x - this.x()) == 1) && (Math.abs(y - this.y()) == 1)){
      return true;
    }else{
      return false;
    }
  }

  public String toString(){
    return "K";
  }

  public String getName(){
    return "King";
  }
  
  public int getValue(){
    return 40;
  }

  public HashSet<Point> getValidMoves(){
    HashSet<Point> set = new HashSet<Point>();
    for(int i = -1; i <= 1; i++){
      for(int j = -1; j <= 1; j++){
        if(this.validMove(this.x() + i, this.y() + j)){
          set.add(new Point(this.x() + i, this.y() + j));
        }
      }
    }
    return set;
  }
}
