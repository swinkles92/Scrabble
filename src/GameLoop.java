import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameLoop {
    public void cpuTurn() {

    }
    public static String[][] parseBoardString(int boardSize, String gameBoard, String[][] boardSpaces) {
        LinkedList<String> temp = new LinkedList<>();
        for(int i = 0; i < ((int)Math.pow(boardSize, 2) * 3); i++) {
            if(gameBoard.charAt(i) == '\n') continue;
            else if(gameBoard.charAt(i) == ' ') {
                if(gameBoard.charAt(i + 1) >= 'a' && gameBoard.charAt(i) <= 'Z') {
                    String subString = gameBoard.substring(i, i + 2);
                    temp.add(subString);
                    i++;
                }
            }
            else {
                String subString = gameBoard.substring(i, i + 2);
                temp.add(subString);
                i++;
            }
        }
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                boardSpaces[i][j] = temp.removeFirst();
            }
        }
        return boardSpaces;
    }
    public static ArrayList<String[]> findPossAnchors(String[][] boardSpaces) {
        ArrayList<String[]> anchorsList = new ArrayList<>();
        for(int i = 0; i < boardSpaces.length; i++) {
            for(int j = 0; j < boardSpaces.length; j++) {
                if(boardSpaces[i][j].contains(" ")) {
                    if((j - 1) >= 0 && boardSpaces[i][j-1].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i),
                                String.valueOf(j),
                                String.valueOf(boardSpaces[i][j].charAt(1))};
                        anchorsList.add(tmp);
                    }
                    else if((j + 1) < boardSpaces.length &&
                            boardSpaces[i][j + 1].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i),
                                String.valueOf(j),
                                String.valueOf(boardSpaces[i][j].charAt(1))};
                        anchorsList.add(tmp);
                    }
                    else if((i + 1) < boardSpaces.length &&
                            boardSpaces[i + 1][j].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i),
                                String.valueOf(j),
                                String.valueOf(boardSpaces[i][j].charAt(1))};
                        anchorsList.add(tmp);
                    }
                    else if((i - 1) >= 0 &&
                            boardSpaces[i - 1][j].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i),
                                String.valueOf(j),
                                String.valueOf(boardSpaces[i][j].charAt(1))};
                        anchorsList.add(tmp);
                    }
                }
            }
        }
        return anchorsList;
    }
}
