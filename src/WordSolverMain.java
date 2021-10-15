import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
        ArrayList<Character> tray = new ArrayList<>();
        tray.add('t');
        tray.add('o');
        tray.add('l');
        tray.add('o');
        tray.add('e');
        tray.add('r');
        tray.add('d');
        //['t', 'o', 'l', 'o', 'e', 'r', 'i';];
        String[][] boardSpaces = new String[7][7];
        boardSpaces = GameLoop.parseBoardString(boardSize, gameBoard, boardSpaces);
        /*for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                System.out.print(boardSpaces[i][j] + " ");
            }
            System.out.println('\n');
        }*/
        /*ArrayList<String[]> possAnchors = GameLoop.findPossAnchors(boardSpaces);
        for(int i = 0; i < possAnchors.size(); i ++) {
            System.out.println(Arrays.toString(possAnchors.get(i)));
        }*/

        File wordFile = new File("./resources/dictionary.txt");
        List wordsList = ParseFile.readFileToList(wordFile.toString());
        wordsList = ParseFile.readFileToList(wordFile.toString());
        WordTrie wordTrie = new WordTrie();
        for(Object word : wordsList) {
            wordTrie.add((String) word);
        }
        wordTrie.contains("xylophone");
        String test = GameLoop.extendRight(boardSpaces, wordTrie, tray, 2, 2,
                "", wordTrie.root);
        System.out.println(test);
        /*ArrayList<String[]> possAnchors = GameLoop.crossCheck(boardSpaces, wordTrie);
        for(int i = 0; i < possAnchors.size(); i ++) {
            System.out.println(Arrays.toString(possAnchors.get(i)));
        }*/

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
    public class ParseFile {
        public static List<String> readFileToList(String fileName) {
            List<String> textLines = Collections.emptyList();
            try {
                textLines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return textLines;
        }
    }
}
