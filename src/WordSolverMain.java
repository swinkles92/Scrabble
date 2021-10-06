import java.util.Scanner;

public class WordSolverMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int boardSize;
        int counter = 0;
        String gameBoard = "";
        String trayString;
        char[] tray = new char[7];

        boardSize = Integer.parseInt(sc.nextLine());
        while(counter < boardSize) {
            gameBoard = gameBoard.concat(sc.nextLine());
            if(counter + 1 != boardSize) {
                gameBoard = gameBoard.concat("\n");
            }
            counter++;
        }
        trayString = sc.nextLine();
        for(int i = 0; i < trayString.length(); i ++) {
            tray[i] = trayString.charAt(i);
        }
    }
}
