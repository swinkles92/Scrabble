import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordSolverMain {
    /*
7
3. .. .. 2. .. .. 3.
.. .3 .. .. .. .3 ..
.. ..  a  d .2 .. ..
2. ..  u  h .. .. 2.
.. ..  l  o .2 .. ..
..  m  a  t .. .3 ..
 r  e  s  i  d .. 3.
toloeri
     */
    public static void main(String[] args) throws IOException {
        // This block validates dictionary file specification,
        // sorts the dictionary, and adds all words to trie
        String dictionaryFile = "";
        if(args.length > 0) {
            dictionaryFile = args[0];
        }
        dictionaryFile = "./resources/" + dictionaryFile;
        // Validating user input
        File wordFile = new File(dictionaryFile);
        List<String> wordsList = ParseFile.readFileToList(wordFile.toString());
        WordTrie wordTrie = new WordTrie();
        for(Object word : wordsList) {
            wordTrie.add((String) word);
        }
        System.out.println("Please enter board size, gameboard, and tray now.");
        System.out.println("Entering a board size of 1 will exit program.");
        Scanner sc = new Scanner(System.in);
        int boardSize = 0;
        int counter = 0;
        String gameBoard = "";
        String trayString;
        ArrayList<Character> tray = new ArrayList<>();
        while(boardSize != 1) {
            boardSize = Integer.parseInt(sc.nextLine());
            ArrayList<String[]> playInfo = new ArrayList<>();
            for(int i = 0; i < boardSize; i++) {
                gameBoard = gameBoard.concat(sc.nextLine());
                gameBoard = gameBoard.concat("\n");
            }
            trayString = sc.nextLine();
            for(int i = 0; i < trayString.length(); i ++) {
                tray.add(trayString.charAt(i));
            }
            String[][] boardSpaces = new String[boardSize][boardSize];
            boardSpaces = GameLoop.parseBoardString(boardSize, gameBoard, boardSpaces);
            for(int i = 0; i < boardSize; i++) {
                for(int j = 0; j < boardSize; j++) {
                    System.out.print(boardSpaces[i][j] + " ");
                }
                System.out.print("\n");
            }
            GameLoop.cpuTurn(boardSpaces, wordTrie, tray, playInfo);
        }
    }
    public class ParseFile {
        public static List<String> readFileToList(String fileName) throws IOException {
            List<String> textLines;
            try {
                textLines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
                textLines = ParseDictionary.parseDictionary(textLines);
            }
            catch (IOException e) {
                System.out.println("Invalid dictionary file specified.");
                System.out.println("Defaulting to sowpods.txt");
                textLines = Files.readAllLines(Paths.get("./resources/sowpods.txt"), StandardCharsets.UTF_8);
            }
            return textLines;
        }
    }
}
