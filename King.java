import java.awt.Point;
import java.util.HashSet;

/*This class describes the king piece, including valid movement, point value, name, and string
 *representation.
 */
public class King extends Piece{
  public King(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  //Checks that the space is one step away in any direction.
  public boolean validMove(int x, int y){//TODO: Add castling support
    if(!super.validMove(x, y)){//generic checks
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
    return 40;//This is usually not defined in points value systems. 40 was chosen in order to
              //outweigh all other pieces.
  }

  //adds all 8 spaces around the king (unless he is on the edge of the board, it only adds existing
  //spaces).
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
