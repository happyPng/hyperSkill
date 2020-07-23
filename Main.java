package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char turn='X';
    public static char player='X';
    public static char ai='O';
    public static int checkTable(char table[][]) {
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(j==0 && i==0) {
                    if((table[i][j]==table[i+1][j+1]&&table[i][j]==table[i+2][j+2])||(table[i][j]==table[i+1][j]&&table[i][j]==table[i+2][j])) {
                        if(table[i][j]=='X') {
                            return 10;
                        }else if(table[i][j]=='O') {
                            return -10;
                        }
                    }

                }
                if(j==0 &&i==2) {
                    if(table[i][j]==table[i-1][j+1]&&table[i][j]==table[i-2][j+2]) {
                        if(table[i][j]=='X') {
                            return 10;
                        }else if(table[i][j]=='O') {
                            return -10;
                        }
                    }

                }
                if(j==0) {
                    if(table[i][j]==table[i][j+1]&&table[i][j]==table[i][j+1]&&table[i][j]==table[i][j+2]) {
                        if(table[i][j]=='X') {
                            return 10;
                        }else if(table[i][j]=='O') {
                            return -10;
                        }
                    }

                }
                if(i==0&&j==1) {
                    if(table[i][j]==table[i+1][j]&&table[i][j]==table[i+2][j]) {
                        if(table[i][j]=='X') {
                            return 10;
                        }else if(table[i][j]=='O') {
                            return -10;
                        }
                    }

                }
                if(i==0&&j==2) {
                    if(table[i][j]==table[i+1][j]&&table[i][j]==table[i+2][j]) {
                        if(table[i][j]=='X') {
                            return 10;
                        }else if(table[i][j]=='O') {
                            return -10;
                        }
                    }

                }

            }

        }
        return 0;
    }
    public static void printTable(char table[][]) {
        System.out.println("---------");
        for(int i=0;i<3;i++) {
            System.out.print("|");
            for(int j=0;j<3;j++) {
                if(table[i][j]!='_') {
                    System.out.print(" "+table[i][j]);
                }else {
                    System.out.print(" "+" ");
                }
            }
            System.out.print(" |"+"\n");

        }
        System.out.println("---------");
        if(checkTable(table)==10) {
            System.out.println("X wins");
        }else if(checkTable(table)==-10) {
            System.out.println("O wins");
        }else if(checkTable(table)==0&&isOver(table)) {
            System.out.println("Draw");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner in=new Scanner(System.in);
        char[][] table = new char[3][3];
        resetTable(table);
        String input ;
        String cInput [];
        while(true){
            System.out.println("Input command: ");
            input=in.nextLine();
            cInput = input.split(" ");
            if(cInput[0].equals("exit"))break;
            if(cInput.length!=3||!cInput[0].equals("start")||!(cInput[1].equals("easy")||cInput[1].equals("user")||cInput[1].equals("medium")||cInput[1].equals("hard"))||!(cInput[2].equals("easy")||cInput[2].equals("user")||cInput[2].equals("medium")||cInput[2].equals("hard"))){
                System.out.println("Bad parameters!");
                continue;
            }
            printTable(table);
            //This is where the game starts and we check wich player it is thats playing.Its the heart of the code.
                while(!isOver(table)) {
                    if(turn=='X'){
                        if(cInput[1].equals("easy")){
                            pcMoveEasy(table);
                        }else if(cInput[1].equals("medium")){
                            pcMoveMedium(table);
                        }else if(cInput[1].equals("user")){
                            playerMove(table);
                        }else if(cInput[1].equals("hard")){
                            pcMoveHard(table);
                        }
                        if(checkTable(table)==10||checkTable(table)==-10){
                            printTable(table);
                            break;
                        }
                        printTable(table);
                    }else{
                        if(cInput[2].equals("easy")){
                            pcMoveEasy(table);
                        }else if(cInput[2].equals("medium")){
                            pcMoveMedium(table);
                        }else if(cInput[2].equals("user")){
                            playerMove(table);
                        }else if (cInput[2].equals("hard")) {
                            pcMoveHard(table);
                        }
                        if(checkTable(table)==10||checkTable(table)==-10){
                            printTable(table);
                            break;
                        }
                        printTable(table);
                    }
                }
                resetTable(table);
            }
        }
        public static boolean isOver(char table[][]) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (table[i][j] == '_') {
                        return false;
                    }
                }
            }
            return true;
        }
        public static void resetTable(char table[][]){
            turn='X';
            for(int i=0;i<3;i++) {
                for(int j=0;j<3;j++) {
                    table[i][j]='_';
                }
            }
    }
    public static void pcMoveEasy(char table[][]) throws InterruptedException {
        System.out.println("Making move level \"easy\"");
        Random lvlEasy = new Random();
        int eCordI,eCordJ;
        while(true){
            eCordI = lvlEasy.nextInt(3);
            eCordJ = lvlEasy.nextInt(3);
            if(table[eCordI][eCordJ]!='_'){
                continue;
            }
            table[eCordI][eCordJ]=turn;
            Thread.sleep(300);
            switchTurn();
            break;
        }
    }
    public static int checkTurn(char turn){
        if(turn=='X'){
            return 10;
        }else{
            return -10;
        }
    }
    public static boolean pcMoveMedium(char table[][]) throws InterruptedException {
        System.out.println("Making move level \"medium\"");
        Random lvlEasy = new Random();
        char tempTable[][] = table;
        int eCordI,eCordJ;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(tempTable[i][j]=='_'){
                    //Checking win
                    tempTable[i][j]=turn;
                    if(checkTable(tempTable)==checkTurn(turn)){
                        table[i][j]=turn;
                        switchTurn();

                        return true;
                    }
                    tempTable[i][j]='_';
                    //Checking win of opponent
                    switchTurn();
                    tempTable[i][j]=turn;
                    if(checkTable(tempTable)==checkTurn(turn)){
                        switchTurn();
                        table[i][j]=turn;
                        switchTurn();

                        return false;
                    }
                    switchTurn();
                    tempTable[i][j]='_';
                }
            }
        }
        while(true){
            eCordI= lvlEasy.nextInt(3);
            eCordJ= lvlEasy.nextInt(3);
            if(table[eCordI][eCordJ]!='_'){
                continue;
            }
            Thread.sleep(300);
            table[eCordI][eCordJ]=turn;
            switchTurn();
            break;
        }
        return false;
    }
    static class Move{
        int row,col;
    }
    static Move findBestMove(char board[][])
    {
        int bestVal = (turn=='X')?-1000:1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j] == '_')
                {
                    // Make the move
                    board[i][j] = (turn=='X')?player:ai;
                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, (turn=='X')?false:true);

                    // Undo the move
                    board[i][j] = '_';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if ((turn=='X')?moveVal > bestVal:moveVal<bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
    public static void pcMoveHard(char table[][]) throws InterruptedException {
        System.out.println("Making move level \"hard\"");
        Move bestMove = findBestMove(table);
        table[bestMove.row][bestMove.col] = turn;
        Thread.sleep(300);
        switchTurn();
    }
    public static void switchTurn(){
            switch(turn) {
                case 'X':turn = 'O'; break;
                case 'O':turn = 'X'; break;
            }
    }
    public static int score(String res){
        if(res.equals("X")){
            return 10;
        }else if(res.equals("O")){
            return -10;
        }else{
            return 0;
        }
    }
    public static int minimax(char board[][],boolean isMax) {
        int result = checkTable(board);
        if(result==10||result==-10){
            return result;
        }
        if(isOver(board)){
            return 0;
        }
        if (isMax) {
            int bestScore = -9999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == '_') {
                        board[i][j] = player;
                        bestScore = Math.max(minimax(board, !isMax), bestScore);
                        board[i][j] = '_';
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 9999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Is the spot available?
                    if (board[i][j] == '_') {
                        board[i][j] = ai;
                        bestScore = Math.min(minimax(board, !isMax), bestScore);
                        board[i][j] = '_';
                    }
                }
            }
            return bestScore;
        }
    }
    public static void playerMove(char table[][]){
        Scanner in = new Scanner(System.in);
        String input;
        String cordI,cordJ;
        while(true){
            try {
                System.out.println("Enter the cords: ");
                input = in.nextLine();
                cordJ=input.split(" ")[0];
                cordI=input.split(" ")[1];
                if(Character.isLetter(cordI.charAt(0))||Character.isLetter(cordJ.charAt(0))){
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if(Integer.parseInt(cordJ)>3||Integer.parseInt(cordI)>3){
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if(table[Math.abs(Integer.parseInt(cordI)-3)][Integer.parseInt(cordJ)-1]!='_'){
                    System.out.println("This cell is occupied! Choose another one!");
                }else {
                    table[Math.abs(Integer.parseInt(cordI)-3)][Integer.parseInt(cordJ)-1]=turn;
                    switchTurn();
                    break;
                }
            }catch(Exception e) {
                System.out.println("Wrong input!");
            }
            //in.nextLine();
        }
       }
}