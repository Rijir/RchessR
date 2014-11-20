import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;
import java.util.HashSet;

/* This class describes the chess board. It stores the positions of all of the pieces and provides
 * methods 
 * 
 */

 public class Board{
  private Piece[][] spaces;
  private ArrayList<String> history;
  
  public Board(Piece[][] placements, ArrayList<String> history){
    spaces = placements;
    this.history = history;
  }

  public Board(){
    private spaces = new Piece[][]{
          {new Rook(Team.BLACK, 0, 0, this), new Knight(Team.BLACK, 1, 0, this),
           new Bishop(Team.BLACK, 2, 0, this), new Queen(Team.BLACK, 3, 0, this),
           new King(Team.BLACK, 4, 0, this), new Bishop(Team.BLACK, 5 , 0, this),
           new Knight(Team.BLACK, 6, 0, this), new Rook(Team.BLACK, 7, 0, this)},
          {new Pawn(Team.BLACK, 0, 1, this), new Pawn(Team.BLACK, 1, 1, this),
           new Pawn(Team.BLACK, 2, 1, this), new Pawn(Team.BLACK, 3, 1, this),
           new Pawn(Team.BLACK, 4, 1, this), new Pawn(Team.BLACK, 5, 1, this),
           new Pawn(Team.BLACK, 6, 1, this), new Pawn(Team.BLACK, 7, 1, this)},
          {null,   null,   null,   null,   null,   null,   null,   null},
          {null,   null,   null,   null,   null,   null,   null,   null},
          {null,   null,   null,   null,   null,   null,   null,   null},
          {null,   null,   null,   null,   null,   null,   null,   null,},
          {new Pawn(Team.WHITE, 0, 6, this), new Pawn(Team.WHITE, 1, 6, this),
           new Pawn(Team.WHITE, 2, 6, this), new Pawn(Team.WHITE, 3, 6, this), 
           new Pawn(Team.WHITE, 4, 6, this), new Pawn(Team.WHITE, 5, 6, this),
           new Pawn(Team.WHITE, 6, 6, this), new Pawn(Team.WHITE, 7, 6, this)},
          {new Rook(Team.WHITE, 0, 7, this), new Knight(Team.WHITE, 1, 7, this),
           new Bishop(Team.WHITE, 2, 7, this), new Queen(Team.WHITE, 3, 7, this),
           new King(Team.WHITE, 4, 7, this), new Bishop(Team.WHITE, 5, 7, this),
           new Knight(Team.WHITE, 6, 7, this), new Rook(Team.WHITE, 7, 7, this)}
         };
    history = new ArrayList<String>();
  }

  public Board(Piece[][] placements){
    this(placements, new ArrayList<String>());
  }

  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("---------------------------------\n");
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        builder.append("| ");
        if(spaces[i][j] == null){
          builder.append(" ");
        }else{
          builder.append(spaces[i][j].toString());
        }
        builder.append(" ");
      }
      builder.append("|\n");
      builder.append("---------------------------------\n");
    }
    return builder.toString();
  }
  
  public Piece getPiece(int x, int y){
    return spaces[y][x];
  }

  public void putPiece(Piece p, int x, int y){
    spaces[y][x] = p;
  }
  
  public boolean move(int x1, int y1, int x2, int y2){
    Piece p = this.getPiece(x1, y1);
    if(p.move(x2, y2)){
      this.putPiece(p, x2, y2);
      this.putPiece(null, x1, y1);
      return true;
    }else{
      return false;
    }
  }

  public boolean move(String s, Team t) throws AmbiguousMoveException{
    String pieceName;
    String coords;
    if(s.length() == 2){
      pieceName = "p";
      coords = s;
    }else{
      pieceName = s.substring(0, 1);
      coords = s.substring(1);
    }
    Point p = translateMove(coords);
    HashSet<Piece> movers = getMovers(p.x, p.y, t);
    Piece moving = null;
    for(Piece foo: movers){
      if(foo.toString() == pieceName){
        if(moving == null){
          moving = foo;
        }else{
          throw new AmbiguousMoveException();
        }
      }
    }
    if(moving == null){
      return false;
    }else{
      return this.move(moving.x(), moving.y(), p.x, p.y);
    }
  }

  private static Point translateMove(String coords){
    if((coords.length() != 2) || (!Character.isLetter(coords.charAt(0))) ||
        (!Character.isDigit(coords.charAt(1)))){
      throw new IllegalArgumentException();
    }
    Point p = new Point();
    char c = coords.charAt(0);
    if(Character.isUpperCase(c)){
      p.x = (int)c - 65;
    }else{
      p.x = (int)c - 97;
    }
    p.y = Integer.parseInt(coords.substring(1))-1;
    return p;
  }

  public HashSet<Piece> getMovers(int x, int y, Team team){
    HashSet<Piece> set = new HashSet<Piece>();
    for(Piece[] row : spaces){
      for(Piece p : row){
        if(p != null && p.getTeam() == team && p.validMove(x, y)){
          set.add(p);
        }
      }
    }
    return set;
  }

  public int getScore(Team team){
    int score = 0;
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(spaces[i][j] != null && spaces[i][j].getTeam() == team){
          score += spaces[i][j].getValue();
        }
      }
    }
    return score;
  }
}
