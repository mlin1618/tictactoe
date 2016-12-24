/**
 * Created by ml996 on 12/21/16.
 */
import java.util.*;
public class Game {
    public int[][] board = new int[3][3];
    public int[][] meta = new int[8][2];//meta: 0,1,2 3 rows, 3,4,5, 3 columns, 6, 7 2 diagonals
    public int moves = 0;
    public int type;
    public Game(int n){
        type = n;
    }
    public void humanVsHuman(){
        Scanner sc = new Scanner(System.in);
        boolean X = true;
        boolean win = false;
        for(int i = 0; i < 9; i++){
            printBoard();
            System.out.println("Enter the coordinates of your move (i.e. top right is \"2, 0\"), Player " + (X?"X":"O"));
            int x1 = sc.nextInt(); int y1 = sc.nextInt();
            while(!available(x1,y1)){
                System.out.println("Invalid move. Try again.");
                x1 = sc.nextInt(); y1 = sc.nextInt();
            }
            move(x1,y1,X,this.board,this.meta);
            if (this.checkWin()==(X?1:-1)) {
                printBoard();
                System.out.println("Player " + (X?"X":"O") + " wins!");
                win = true;
                break;
            }
            X = !X;
        }
        if(!win){
            System.out.println("It's a draw!");
        }
    }
    public void humanVsComputer(boolean c){
        Scanner sc = new Scanner(System.in);
        boolean X = c;
        boolean win = false;
        for(int i = 0; i < 9; i++){
            printBoard();
            if(X){
                System.out.println("Enter the coordinates of your move (i.e. top right is \"2, 0\"), Player " + (X?"X":"O"));
                int x1 = sc.nextInt(); int y1 = sc.nextInt();
                while(!available(x1,y1)){
                    System.out.println("Invalid move. Try again.");
                    x1 = sc.nextInt(); y1 = sc.nextInt();
                }
                move(x1,y1,X,this.board,this.meta);
            }
            else{
                AI(X);
            }
            if (this.checkWin()==(X?1:-1)) {
                printBoard();
                System.out.println("Player " + (X?"X":"O") + " wins!");
                win = true;
                break;
            }
            X = !X;
        }
        if(!win){
            printBoard();
            System.out.println("It's a draw!");
        }
    }
    public void computerVsComputer(){
        boolean X = true;
        boolean win = false;
        for(int i = 0; i < 9; i++){
            printBoard();
            AI(X);
            if (this.checkWin()==(X?1:-1)) {
                printBoard();
                System.out.println("Player " + (X?"X":"O") + " wins!");
                win = true;
                break;
            }
            X = !X;
        }
        if(!win){
            printBoard();
            System.out.println("It's a draw!");
        }
    }
    public boolean move(int x, int y, boolean c, int[][] board1, int[][] meta1){
        if (board1[y][x] != 0) {
            return false;
        }
        board1[y][x] = c?1:-1;
        meta1[y][c?0:1]++;
        meta1[x+3][c?0:1]++;
        if((x==y)){
            meta1[6][c?0:1]++;
        }
        if(x+y ==2){
            meta1[7][c?0:1]++;
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
    public boolean available(int x, int y){
        if(!(String.valueOf(x).equals("0") || String.valueOf(x).equals("1") || String.valueOf(x).equals("2"))|| !(String.valueOf(y).equals("0")|| String.valueOf(y).equals("1")|| String.valueOf(y).equals("2"))) {
            return false;
        }
        if(board[y][x] != 0) {
            return false;
        }
        else{
            return true;
        }
    }
    public boolean AI(boolean c){
        //System.out.println(Arrays.deepToString(meta));
        int[] w = wins(board,meta,c);
        if(w[0] != -1){
            if(available(w[0],w[1])) {
                //System.out.println("\t\t\t\t1");
                move(w[0], w[1], c, board, meta);
                return true;
            }
        }
        w = blockWin(c);
        if(w[0] != -1) {
            if (available(w[0], w[1])) {
                //System.out.println("\t\t\t\t2");
                move(w[0], w[1], c, board, meta);
                return true;
            }
        }
        w = findFork(c);
        if(w[0] != -1){
            if(available(w[0],w[1])) {
                //System.out.println("\t\t\t\t3");
                move(w[0], w[1], c, board, meta);
                return true;
            }
        }
        w = blockFork(c);
        if(w[0] != -1){
            if(available(w[0],w[1])) {
                //System.out.println("\t\t\t\t4");
                move(w[0], w[1], c, board, meta);
                return true;
            }
        }
        w= findCenter(c);
        if(w[0] != -1){
            if(available(w[0],w[1])) {
                //System.out.println("\t\t\t\t5");
                move(w[0], w[1], c, board, meta);
                return true;
            }
        }
        w=findCorner(c);
        if(w[0] != -1){
            if(available(w[0],w[1])) {
                //System.out.println("\t\t\t\t6");
                move(w[0], w[1], c, board, meta);
                return true;
            }
        }
        w=findSide(c);
        if(w[0] != -1){
            if(available(w[0],w[1])) {
                //System.out.println("\t\t\t\t7");
                move(w[0], w[1], c, board, meta);
                return true;
            }
        }
        //System.out.println("RIP");
        return false;
    }
    public int[] wins(int[][] board1, int[][] meta1, boolean c){
        int[] wins = new int[]{-1,-1,0};
        for(int i = 0; i < 8; i++){
            if((meta1[i][0] == 2 && meta1[i][1] == 0 && c) ||(meta1[i][1] == 2 && meta1[i][0] == 0 && !c)){
                if(i<=2){
                    for(int j = 0; j < 3; j++){
                        if(board1[i][j] == 0){wins[0] = j; wins[1] = i; wins[2]++;}
                    }
                    //wins[0] = Arrays.binarySearch(board1[i], 0); wins[1] = i; wins[2]++;
                }
                else if(i<=5){
                    for(int j = 0; j < 3; j++){
                        if(board1[j][i-3] == 0) {wins[0]=i-3; wins[1]=j; wins[2]++;}
                    }
                }
                else if(i==6){
                    for(int j = 0; j < 3; j++){
                        if(board1[j][j] ==0) {wins[0]=j;wins[1]=j;wins[2]++;}
                    }
                }
                else{
                    for(int j = 0; j < 3; j++){
                        if(board1[j][2-j] == 0) {wins[0] = 2-j; wins[1] = j; wins[2]++;}
                    }
                }
            }
        }
        return wins;
    }
    public int[] blockWin(boolean c){
        int[] a = wins(board,meta,!c);
        if(a[0] != -1){
            return a;
        }
        return new int[]{-1};
    }
    public boolean isFork(int i, int j, boolean c){
        int[][] tempBoard = new int[3][3];
        for(int k = 0; k < 3; k++){
            for(int l = 0; l < 3; l++){
                tempBoard[k][l] = board[k][l];
            }
        }
        int[][] tempMeta = new int[8][2];
        for(int k = 0; k < 8; k++){
            for(int l = 0; l < 2; l++){
                tempMeta[k][l] = meta[k][l];
            }
        }
        if(tempBoard[i][j] == 0){
            move(j,i,c,tempBoard,tempMeta);
            int count = wins(tempBoard, tempMeta,c)[2];
            if(count > 1){
                return true;
            }
        }
        return false;
    }
    public int[] findFork(boolean c){
        int x = c?1:-1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int[][] tempBoard = new int[3][3];
                for(int k = 0; k < 3; k++){
                    for(int l = 0; l < 3; l++){
                        tempBoard[k][l] = board[k][l];
                    }
                }
                int[][] tempMeta = new int[8][2];
                for(int k = 0; k < 8; k++){
                    for(int l = 0; l < 2; l++){
                        tempMeta[k][l] = meta[k][l];
                    }
                }
                if(tempBoard[i][j] == 0){
                    move(j,i,c,tempBoard,tempMeta);
                    int count = wins(tempBoard, tempMeta,c)[2];
                    if(count > 1){
                        return new int[]{j,i};
                    }
                }
                else{
                    continue;
                }
            }
        }
        /*int[][] corners = {{0,0}, {0,2}, {2,2}, {2,0}};
        for(int i = 0; i < 4; i++){
            if(board[corners[i][0]][corners[i][1]] == 0 && board[corners[(i+1)%4][0]][corners[(i+1)%4][1]] == 1 && board[(corners[i][0]+corners[(i+1)%4][0])/2][(corners[i][1]+corners[(i+1)%4][1])/2]==0){
               return corners[i];
            }
        }
        for(int i = 0; i < 4; i++){

        }*/
        return new int[]{-1};
    }
    public int[] blockFork(boolean c){
        for(int i = 0; i <3; i++){
            for(int j = 0; j < 3; j++){
                if (isFork(i, j, !c)) {
                    if(meta[i][c?0:1] == 1)
                        return new int[]{j, i};
                    else if(meta[j+3][c?0:1] == 1)
                        return new int[]{j, i};
                }
            }
        }
        /*int[] a = findFork(!c);
        if(a[0] != -1){
            return a;
        }*/
        return new int[]{-1};
    }
    public int[] findCenter(boolean c){
        if(board[1][1] == 0){
            return new int[]{1,1};
        }
        return new int[]{-1};
    }
    public int[] findCorner(boolean c){
        int x = c?1:-1;
        int[][] co = {{0,0}, {0,2}, {2,2}, {2,0}};
        for(int i = 0; i < 4; i++){
            if(board[co[i][0]][co[i][1]] == -x && board[co[(i+2)%4][0]][co[(i+2)%4][1]] == 0){
                return new int[]{co[(i+2)%4][1], co[(i+2)%4][0]};
            }
        }
        Random r = new Random();
        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
        while(a.size()>0){
            int j = r.nextInt(a.size());
            int i = a.get(j);
            if(board[co[i][0]][co[i][1]] == 0){
                return new int[]{co[i][1], co[i][0]};
            }
            a.remove(j);
        }
        return new int[]{-1};
    }
    public int[] findSide(boolean c){
        int[][] co = {{0,1},{1,0},{1,2},{2,1}};
        Random r = new Random();
        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
        while(a.size()>0){
            int j = r.nextInt(a.size());
            int i = a.get(j);
            if(board[co[i][0]][co[i][1]] == 0){
                return new int[]{co[i][1], co[i][0]};
            }
            a.remove(j);
        }
        /*for(int i = 0; i < 4; i++){
            if(board[co[i][0]][co[i][1]] == 0){
                return new int[]{co[i][1], co[i][0]};
            }
        }*/
        return new int[]{-1};
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
