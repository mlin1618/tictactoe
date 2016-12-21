/**
 * Created by ml996 on 12/21/16.
 */
public class Game {
    public int[][] board = new int[3][3];
    public int[][] meta = new int[8][];
    public int moves = 0;
    public int type;
    public Game(int n){
        type = n;
    }
    public boolean move(int x, int y, boolean c){
        if (board[y][x] != 0) {
            return false;
        }
        board[y][x] = c?1:-1;
        meta[y][c?0:1]++;
        meta[x+3][c?0:1]++;
        if((x==y)){
            meta[6][c?0:1]++;
        }
        else if(x+y ==3){
            meta[7][c?0:1]++;
        }
        return true;
    }
    //checkMeta: {a,b} - a is 0 or 1 (win or not), b is which player
    public int[] checkMeta(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 2; j++){
                if(meta[i][j] == 3){
                    // return {0, j};
                }
            }
        }
    }
    public int[][] AI(){

    }
    public void printBoard(){
        String[][] x = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == -1){
                    x[i][j] = "O";
                }
                else if(board[i][j] == 1){
                    x[i][j] = "X";
                }
                else{
                    x[i][j] = " ";
                }
            }
        }
        System.out.println("    0   1   2");
        System.out.println("  *************");
        for(int i = 0 ; i < 3; i++) {
            System.out.println(i + " * " + x[i][0] + " * " + x[i][1] + " * " + x[i][2] + " *");
            if(i != 3){
                System.out.println("  *************");
            }
        }
    }
}
