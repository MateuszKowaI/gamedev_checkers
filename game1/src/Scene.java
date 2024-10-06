import java.util.Scanner;

public class Scene {
    private static String[][] spots = new String[8][8];

    public Scene() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i + j) %2 == 1){
                    if(i == 5 || i == 6 || i == 7){
                        spots[i][j] = "●";
                    }else if(i == 0 || i == 1 || i == 2){
                        spots[i][j] = "○";
                    }else{
                        spots[i][j] = "□";
                    }
                }else{
                        spots[i][j] = "■";
                }
            }
        }
    }
    public static void printScene(){
        System.out.println("  0 1 2 3 4 5 6 7");
        for(int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(spots[i][j] + " ");
            }
            System.out.println(i + " ");
        }
        System.out.println("  0 1 2 3 4 5 6 7");
    }
    public static void startGame(){
        Scanner scan = new Scanner(System.in);
        int whitepawns = 12, blackpawns = 12;
        while(whitepawns != 0 || blackpawns != 0){
            printScene();
            System.out.println("Pawns left:\nwhite:" + whitepawns + " black:" + blackpawns);

            blackpawns = whiteMove(scan, blackpawns);

            printScene();
            System.out.println("Pawns left:\nwhite:" + whitepawns + " black:" + blackpawns);

            blackMove(scan, whitepawns);
        }
        if(blackpawns == 0){
            System.out.printf("Player 1 wins!");
        }else{
            System.out.printf("Player 2 wins!");
        }
        scan.close();
    }
    public static int whiteMove(Scanner scan, int blackpawns){
        System.out.println("Player 1:");
        boolean correct;
        int col, row, move_col, move_row;
        do {
            System.out.println("Select column and row number of your pawn.");
            col = scan.nextInt();
            row = scan.nextInt();
            if(withinBounds(row, col) && spots[row][col].equals("●")){
                correct = true;
            }
            else{
                correct = false;
                System.out.println("Invalid coordinates.");
            }
        }while(!correct);

        do{
            System.out.println("Where would you like to move?");
            move_col = scan.nextInt();
            move_row = scan.nextInt();
            if(withinBounds(move_row, move_col) && move_row == row-1 && (move_col == col+1 || move_col == col-1)){
                correct = true;
            }
            else{
                correct = false;
                System.out.println("Invalid coordinates.");
            }
        }while(!correct);
        //coords are correct, change spots
        if((row + col) %2 == 1) {
            spots[row][col] = "□";
        }else{
            spots[row][col] = "■";
        }
        //kill
        if(spots[move_row][move_col] == "○"){
            spots[move_row][move_col] = "●";
            return blackpawns-1;
        }
        spots[move_row][move_col] = "●";
        return blackpawns;
    }

    public static int blackMove(Scanner scan, int whitepawns){
        System.out.println("Player 2:");
        boolean correct;
        int col, row, move_col, move_row;
        do {
            System.out.println("Select column and row number of your pawn.");
            col = scan.nextInt();
            row = scan.nextInt();
            if(withinBounds(row, col) && spots[row][col].equals("○")){
                correct = true;
            }
            else{
                correct = false;
                System.out.println("Invalid coordinates.");
            }
        }while(!correct);

        do{
            System.out.println("Where would you like to move?");
            move_col = scan.nextInt();
            move_row = scan.nextInt();
            if(withinBounds(move_row, move_col) && move_row == row+1 && (move_col == col+1 || move_col == col-1)){
                correct = true;
            }
            else{
                correct = false;
                System.out.println("Invalid coordinates.");
            }
        }while(!correct);
        //coords are correct, change spots
        if((row + col) %2 == 1) {
            spots[row][col] = "□";
        }else{
            spots[row][col] = "■";
        }
        //kill
        if(spots[move_row][move_col] == "●"){
            spots[move_row][move_col] = "○";
            return whitepawns-1;
        }
        spots[move_row][move_col] = "○";
        return whitepawns;
    }
    public static boolean withinBounds(int i, int j){
        if(i >= 0 && i < 8 && j >= 0 && j < 8){
            return true;
        }
        return false;
    }
}
