import java.util.ArrayList;

public class GameLoop {
    public void cpuTurn() {

    }
    public static ArrayList parseBoardString(int boardSize, String gameBoard, ArrayList boardSpaces) {
        for(int i = 0; i < ((int)Math.pow(boardSize, 2) * 3); i++) {
            if(gameBoard.charAt(i) == '\n') continue;
            else if(gameBoard.charAt(i) == ' ') {
                if(gameBoard.charAt(i + 1) >= 'a' && gameBoard.charAt(i) <= 'Z') {
                    boardSpaces.add(gameBoard.charAt(i + 1));
                    i++;
                }
                else continue;
            }
            else {
                String subString = gameBoard.substring(i, i + 2);
                boardSpaces.add(subString);
                i++;
            }
        }
        return boardSpaces;
    }
    public static String board2String(int boardSize, ArrayList<String> boardSpaces) {
        String gameBoard = "";
        System.out.println(boardSpaces.get(0).getClass());
        System.out.println(boardSpaces.get(0).charAt(1));
        System.out.println(boardSpaces.get(0).charAt(1) >= 'a');
        for(int i = 0; i < boardSpaces.size(); i++) {
            if(boardSpaces.get(i).charAt(1) >= 'a' && boardSpaces.get(i).charAt(1) <= 'Z') {
                gameBoard = gameBoard + boardSpaces.get(i);
                //gameBoard = gameBoard.concat(" " + boardSpaces.get(i) + " ");
            }
            if(i % boardSize == 0) {
                //gameBoard = gameBoard.concat(boardSpaces.get(i) + "\n");
            }
            else {
                //gameBoard = gameBoard.concat(boardSpaces.get(i) + " ");
            }
        }
        return gameBoard;
    }
}
