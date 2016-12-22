/**
 * Created by ml996 on 12/21/16.
 */
public class Game {
    public int[][] board = new int[3][3];
    public int[][] meta = new int[8][];//meta: 0,1,2 3 rows, 3,4,5, 3 columns, 6, 7 2 diagonals
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
    //checkMeta: if N=0, check for win, if N=1 check for 2 consecutive 
    //returns array {a,b,c,d} a is win or not, b is which player winning or almost win, c is column/diagonal/row, d is which one
    public int checkWin(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 2; j++){
                if(meta[i][j] == 3){
                    return (j==0)?1:-1;
                }
            }
        }
        return 0;
    }
    public int checkTwo(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 2; j++){
                if(meta[i][j] == 2 && meta[i][(j+1)%2] == 0){
                    return i;
                }
            }
        }
        return -1;
    }
    public void AI(boolean c){
        int t = checkTwo();
        if(t!=-1){
            if(t <= 2){
                for(int i = 0; i < 3; i++){
                    if(board[t][i] ==0){
                        move(i,t,c);
                    }
                }
            }
            else if(t <= 5){
                for(int i = 0; i < 3; i++){
                    if(board[i][t] ==0){
                        move(t,i,c);
                    }
                }
            }
            else if(t==6){
                for(int i = 0; i < 3; i++){
                    if(board[i][i] ==0){
                        move(i,i,c);
                    }
                }
            }
            else{
                for(int i = 0; i < 3; i++){
                    if(board[i][2-i] ==0){
                        move(2-i,i,c);
                    }
                }
            }
        }
        else{
            if(board[1][1] == 0){
                move(1,1,c);
            }
            else{
                boolean f = false;
                for(int i = 0; i < 2; i++){
                    for(int j = 0; j < 2; j++){
                        if(board[2*i][2*j] ==0 ){//add something to check for forks
                            move(2*j, 2*i, c);
                            f = !f;
                            break;
                        }
                    }
                }
                if(!f){
                    if(board[1][0] == 0){
                        move(0,1,c);
                    }
                    else if(board[0][1] ==0){
                        move(1,0,c);
                    }
                    else if(board[2][1] ==0){
                        move(1,2,c);
                    }
                    else{
                        move(2,1,c);
                    }
                }
            }
        }

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
