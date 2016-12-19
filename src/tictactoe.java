/**
 * Created by ml996 on 12/19/16.
 */

import java.util.*;

public class tictactoe {
    public static void printBoard(int[][] y){
        String[][] x = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(y[i][j] == -1){
                    x[i][j] = "O";
                }
                else if(y[i][j] == 1){
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
    public static boolean checkWin(int[][] x){
        for(int i = 0; i < 3; i++){
            if(x[i][0] == x[i][1] && x[i][1] == x[i][0] && x[i][0] != 0){
                return true;
            }
            if(x[0][i] == x[1][i] && x[1][i] == x[2][i] && x[0][i] != 0){
                return true;
            }
        }
        if(((x[0][0] == x[1][1] && x[1][1] == x[2][2]) || (x[0][2] == x[1][1] && x[1][1] == x[2][0])) && x[1][1] != 0){
            return true;
        }
        return false;
    }
    public static int[] bestMove(int[][] x, boolean t){
        public static boolean checkRCD(int[][] x, )
    }
    public static void main(String[] args){
        int[][] board = new int[3][3];
        System.out.println("Select a game type: \n (1) Human vs. Human \n (2) Human vs. Computer \n (3) Computer vs. Computer \n (Enter 1, 2, or 3)");
        while(true){
            Scanner sc = new Scanner(System.in);
            String x = sc.nextLine();
            if(x.equals("1")){
                boolean win = false;
                boolean X = true;
                while(!win){
                    printBoard(board);
                    System.out.println("Enter the coordinates of your move (i.e. top right is \"2, 0\"), Player " + (X?"X":"O"));
                    int x1 = sc.nextInt(); int y1 = sc.nextInt();
                    board[y1][x1] = X?1:-1;
                    if (checkWin(board)) {
                        win = true;
                        break;
                    }
                    X = !X;
                }
                String winner = X?"X":"O";
                printBoard(board);
                System.out.println(winner + " wins!");
                break;
            }
            else if(x.equals("2")){
                System.out.println("Would you like to move first? (Y/N)");
                String yn = sc.nextLine();
                if(yn.substring(0,1).equalsIgnoreCase("Y")){
                    boolean X = true;
                    boolean win = false;
                    while(!win){
                        printBoard(board);
                        System.out.println("Enter the coordinates of your move (i.e. top right is \"2, 0\"), Player X");
                        int x1 = sc.nextInt(); int y1 = sc.nextInt();
                        board[y1][x1] = 1;
                        bestMove(board, !X);
                    }
                }
                else{
                    boolean X = false;
                    boolean win = false;
                    while(!win){
                        printBoard(board);
                        bestMove(board, !X);
                        printBoard(board);
                        System.out.println("Enter the coordinates of your move (i.e. top right is \"2, 0\"), Player O");
                        int x1 = sc.nextInt(); int y1 = sc.nextInt();
                        board[y1][x1] = 1;
                    }
                }

            }
            else if(x.equals("3")){


            }
            else{
                System.out.println("Invalid. Please enter 1, 2, or 3.");
            }
        }

    }
}
