import java.awt.Point;
import java.util.HashSet;

/* This class describes the Knight piece, including valid movement, point value, name, and string
 * representation. 
 */
public class Knight extends Piece{
  public Knight(Team team, int x, int y, Board board){
    super(team, x, y, board);
  }

  //returns true if the space is 1 away in one dimention and 2 away in the other.
  public boolean validMove(int x, int y){
    if(!super.validMove(x, y)){
      return false;
    }
    if(((Math.abs(x - this.x()) == 2) && (Math.abs(y - this.y()) == 1))
        || ((Math.abs(x - this.x()) == 1) && (Math.abs(y - this.y()) == 2))){
      return true;
    }else{
      return false;
    }
  }

  public String toString(){
    return "N";//N is used because King already took K. this is standard across most chess notation
  }

  public String getName(){
    return "Knight";
  }
  
  public int getValue(){
    return 3;
  }

  //returns all spaces 1 away in one dimention and 2 away ing the other.
  public HashSet<Point> getValidMoves(){
    HashSet<Point> set = new HashSet<Point>();
    if(this.validMove(this.x() + 2, this.y() + 1)){
      set.add(new Point(this.x() + 2, this.y() + 1));
    }
    if(this.validMove(this.x() + 2, this.y() - 1)){
      set.add(new Point(this.x() + 2, this.y() - 1));
    }
    if(this.validMove(this.x() - 2, this.y() + 1)){
      set.add(new Point(this.x() - 2, this.y() + 1));
    }
    if(this.validMove(this.x() - 2, this.y() - 1)){
      set.add(new Point(this.x() - 2, this.y() - 1));
    }
    if(this.validMove(this.x() + 1, this.y() + 2)){
      set.add(new Point(this.x() + 1, this.y() + 2));
    }
    if(this.validMove(this.x() + 1, this.y() - 2)){
      set.add(new Point(this.x() + 1, this.y() - 2));
    }
    if(this.validMove(this.x() - 1, this.y() + 2)){
      set.add(new Point(this.x() - 1, this.y() + 2));
    }
    if(this.validMove(this.x() - 1, this.y() - 2)){
      set.add(new Point(this.x() - 1, this.y() - 2));
    }
    return set;
  }
}
