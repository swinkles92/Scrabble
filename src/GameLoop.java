import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
                    /*else if((i + 1) < boardSpaces.length &&
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
                    }*/
                }
            }
        }
        return anchorsList;
    }
    public static ArrayList<Character> crossCheck(String [][] boardSpaces,
                                               int row,
                                               int col,
                                               WordTrie wordList) {
        ArrayList<Character> validLetters = new ArrayList<>();
        String downWord = findDownWord(boardSpaces, row, col).toLowerCase();
        if(wordList.contains("a" + downWord)) {
            validLetters.add('a');
        }
        else if(wordList.contains("b" + downWord)) {
            validLetters.add('b');
        }
        else if(wordList.contains("c" + downWord)) {
            validLetters.add('c');
        }
        else if(wordList.contains("d" + downWord)) {
            validLetters.add('d');
        }
        else if(wordList.contains("e" + downWord)) {
            validLetters.add('e');
        }
        else if(wordList.contains("f" + downWord)) {
            validLetters.add('f');
        }
        else if(wordList.contains("g" + downWord)) {
            validLetters.add('g');
        }
        else if(wordList.contains("h" + downWord)) {
            validLetters.add('h');
        }
        else if(wordList.contains("i" + downWord)) {
            validLetters.add('i');
        }
        else if(wordList.contains("j" + downWord)) {
            validLetters.add('j');
        }
        else if(wordList.contains("k" + downWord)) {
            validLetters.add('k');
        }
        else if(wordList.contains("l" + downWord)) {
            validLetters.add('l');
        }
        else if(wordList.contains("m" + downWord)) {
            validLetters.add('m');
        }
        else if(wordList.contains("n" + downWord)) {
            validLetters.add('n');
        }
        else if(wordList.contains("o" + downWord)) {
            validLetters.add('o');
        }
        else if(wordList.contains("p" + downWord)) {
            validLetters.add('p');
        }
        else if(wordList.contains("q" + downWord)) {
            validLetters.add('q');
        }
        else if(wordList.contains("r" + downWord)) {
            validLetters.add('r');
        }
        else if(wordList.contains("s" + downWord)) {
            validLetters.add('s');
        }
        else if(wordList.contains("t" + downWord)) {
            validLetters.add('t');
        }
        else if(wordList.contains("u" + downWord)) {
            validLetters.add('u');
        }
        else if(wordList.contains("v" + downWord)) {
            validLetters.add('v');
        }
        else if(wordList.contains("w" + downWord)) {
            validLetters.add('w');
        }
        else if(wordList.contains("x" + downWord)) {
            validLetters.add('x');
        }
        else if(wordList.contains("y" + downWord)) {
            validLetters.add('y');
        }
        else if(wordList.contains("z" + downWord)) {
            validLetters.add('z');
        }
        else if(downWord.equals("")) {
            for(char c = 'a'; c <= 'z'; c++) {
                validLetters.add(c);
            }
        }
        return validLetters;
    }
    public static String findDownWord(String[][] boardSpaces, int row, int col) {
        String word = "";
        for(int i = row; i < boardSpaces.length; i++) {
            if(boardSpaces[i][col].contains(" ")) {
                String tmp = boardSpaces[i][col];
                word = word + tmp.trim();
            }
            else break;
        }
        return word;
    }
    public static void leftPart(String[][] boardSpaces,
                                WordTrie wordTrie,
                                ArrayList<Character> tray,
                                int row,
                                int col,
                                String partialWord,
                                WordTNode node,
                                ArrayList<String> legalWords,
                                int limit) {
        HashMap<Character, WordTNode> children = node.getChildren();
        WordTNode letterNode;
        extendRight(boardSpaces, wordTrie, tray, row, col, partialWord, node, legalWords);
        if(limit > 0) {
            for(var child : children.keySet()) {
                if (tray.contains(child.charValue())) {
                    char c = child.charValue();
                    tray.remove(Character.valueOf(c));
                    if(children.get(c) != null) {
                        letterNode = children.get(c);
                        children = letterNode.getChildren();
                        partialWord = c + partialWord;
                        leftPart(boardSpaces, wordTrie, tray,
                                row, col, partialWord, letterNode, legalWords, limit - 1);
                    }
                    tray.add(c);
                }
            }
        }
    }
    public static void extendRight(String[][] boardSpaces,
                                     WordTrie wordTrie,
                                     ArrayList<Character> tray,
                                     int row,
                                     int col,
                                     String partialWord,
                                     WordTNode node,
                                     ArrayList<String> legalWords) {
        HashMap<Character, WordTNode> children = node.getChildren();
        WordTNode letterNode;
        if(col < boardSpaces.length) {
            if(!boardSpaces[row][col].contains(" ")) {
                if(node.isCompleteWord()) {
                    if(!legalWords.contains(partialWord)) {
                        legalWords.add(partialWord);
                    }
                }
                for(var child : children.keySet()) {
                    int counter = col;
                    if (tray.contains(child.charValue())) {
                        ArrayList<Character> crossCheckedLetters = crossCheck(boardSpaces, row, col, wordTrie);
                        if (crossCheckedLetters.contains(child.charValue())) {
                            char c = child.charValue();
                            tray.remove(Character.valueOf(c));
                            if(children.get(c) != null) {
                                letterNode = children.get(c);
                                children = letterNode.getChildren();
                                if (col++ < boardSpaces.length) {
                                    partialWord = partialWord + c;
                                    extendRight(boardSpaces, wordTrie, tray,
                                            row, col, partialWord, letterNode, legalWords);
                                }
                            }
                            tray.add(c);
                        }
                    }
                    col = counter;
                }
            }
            else {
                String squareString = boardSpaces[row][col].trim();
                char c = squareString.charAt(0);
                letterNode = children.get(c);
                if(children.containsKey(c)) {
                    col++;
                    partialWord = partialWord + c;
                    extendRight(boardSpaces, wordTrie, tray,
                            row, col, partialWord, letterNode, legalWords);
                }
            }
        }
    }
}
