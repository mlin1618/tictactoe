/**
 * Created by ml996 on 12/21/16.
 */
import java.util.*;
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

        /*int t = checkTwo();
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
                //finding forks
                for(int i = 0; i < 2; i++){
                    for(int j = 0; j < 2; j++){
                        if(board[2*i][2*j] == 0 && board[2*i][(2*(j+1))]==1 && board[2*i][1] == 0){
                            move(2*j, 2*i, c);
                            f=!f;
                            break;
                        }
                        else if(board[2*i][2*j] == 0 && board[2*(i+1)][2*j] == 1 && board[1][2*j] == 0){
                            move(2*j, 2*i, c);
                            f=!f;
                            break;
                        }
                    }
                }
                if(!f){
                    boolean f2=false;
                    for(int i = 0; i < 2; i++){
                        for(int j = 0; j < 2; j++){
                            if(board[2*i][2*j] ==0){
                                move(2*j, 2*i,c);
                                f2 = !f2;
                                break;
                            }
                        }
                    }
                    if(!f2) {
                        if (board[1][0] == 0) {
                            move(0, 1, c);
                        } else if (board[0][1] == 0) {
                            move(1, 0, c);
                        } else if (board[2][1] == 0) {
                            move(1, 2, c);
                        } else {
                            move(2, 1, c);
                        }
                    }
                }
            }
        }
*/
    }
    public int[] findWin(boolean c){
        for(int i = 0; i < 8; i++){
            if((meta[i][0] == 2 && meta[i][1] == 0 && c) ||(meta[i][1] == 2 && meta[i][0] == 0 && !c)){
                if(i<=2){
                    return new int[]{i, Arrays.binarySearch(board[i], 0)};
                }
                else if(i<=5){
                    for(int j = 0; j < 3; j++){
                        if(board[i][j] == 0) return new int[]{j, i};

                    }
                }
                else if(i==6){
                    for(int j = 0; j < 3; j++){
                        if(board[j][j] ==0) return new int[]{j,j};
                    }
                }
                else{
                    for(int j = 0; j < 3; j++){
                        if(board[j][2-j] == 0) return new int[]{2-j,j};
                    }
                }
            }
        }
        return new int[]{-1};
    }
    public int[] blockWin(boolean c){
        int[] a = findWin(!c);
        if(a[0] != -1){
            return a;
        }
    }
    public int[] findFork(boolean c){
        int[][] corners = {{0,0}, {0,2}, {2,2}, {2,0}};
        for(int i = 0; i < 4; i++){
            if(board[corners[i][0]][corners[i][1]] == 0 && board[corners[(i+1)%4][0]][corners[(i+1)%4][1]] == 1 && board[(corners[i][0]+corners[(i+1)%4][0])/2][(corners[i][1]+corners[(i+1)%4][1])/2]==0){
               return corners[i];
            }
        }
        for(int i = 0; i < 4; i++){

        }
    }
    public int[] blockFork(boolean c){

    }
    public int[] findCorner(boolean c){

    }
    public int[] findSide(boolean c){

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
