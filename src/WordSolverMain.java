import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordSolverMain {
    public static void main(String[] args) {
        int boardSize = 7;
        String gameBoard =
                "3. .. .. 2. .. .. 3.\n" +
                ".. .3 .. .. .. .3 ..\n" +
                ".. ..  a  d .2 .. ..\n" +
                "2. ..  u  h .. .. 2.\n" +
                ".. ..  l  o .2 .. ..\n" +
                "..  m  a  t .. .3 ..\n" +
                " r  e  S  i  d .. 3.\n";
        char[] tray = {'t', 'o', 'l', 'o', 'e', 'r', 'i'};
        String[][] boardSpaces = new String[7][7];
        boardSpaces = GameLoop.parseBoardString(boardSize, gameBoard, boardSpaces);
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                System.out.print(boardSpaces[i][j] + " ");
            }
            System.out.println('\n');
        }
        ArrayList<String[]> possAnchors = GameLoop.findPossAnchors(boardSpaces);
        for(int i = 0; i < possAnchors.size(); i ++) {
            System.out.println(Arrays.toString(possAnchors.get(i)));
        }
        String[] words = {"car", "cars", "cat", "cats", "do", "dog",
                "dogs", "done", "ear", "ears", "eat", "eats"};
        WordTrie wordTrie = new WordTrie();
        for(String word : words) {
            wordTrie.add(word);
        }
        /*Scanner sc = new Scanner(System.in);
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
        }*/
    }
}
