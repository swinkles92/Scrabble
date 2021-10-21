import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class GameLoop {
    public static void cpuTurn(String[][] boardSpaces, WordTrie wordTrie, ArrayList<Character> tray,
                               ArrayList<String[]> playInfo) {
        ArrayList<String> possWords = new ArrayList<>();
        int row, col, k, wordScore;
        // bestWordHoriz = {row, col, word, wordScore};
        String[] bestWordHoriz = {"0", "0", "0", "0"};
        // bestWordHoriz = {row, col, word, wordScore};
        String[] bestWordVert = {"0", "0", "0", "0"};
        String[][] transMat = transposeBoard(boardSpaces);
        // Horizontal Search
        ArrayList<String[]> possAnchors = GameLoop.findPossAnchors(boardSpaces);
        for(String[] anchor : possAnchors) {
            possWords.clear();
            row = Integer.parseInt(anchor[0]);
            col = Integer.parseInt(anchor[1]);
            k = col;
            if(col - 1 > 0 && boardSpaces[row][col - 1].contains(" ")) {
                String tmp = findPrefix(boardSpaces, row, col);
                GameLoop.extendRight(boardSpaces, wordTrie, tray, row, col, tmp,
                        wordTrie.root, possWords, playInfo);
            }
            else {
                GameLoop.leftPart(boardSpaces, wordTrie, tray, row, col,
                        "", wordTrie.root, possWords, k, playInfo);
            }
            for(String word : possWords) {
                wordScore = scoreWord(word);
                if(wordScore > Integer.parseInt(bestWordHoriz[3])) {
                    bestWordHoriz[0] = String.valueOf(row);
                    bestWordHoriz[1] = String.valueOf(col);
                    bestWordHoriz[2] = word;
                    bestWordHoriz[3] = String.valueOf(wordScore);
                }
            }
        }
        // Vertical Search
        possAnchors.clear();
        possAnchors = GameLoop.findPossAnchors(transMat);
        for(String[] anchor : possAnchors) {
            possWords.clear();
            row = Integer.parseInt(anchor[0]);
            col = Integer.parseInt(anchor[1]);
            k = col;
            if(col - 1 > 0 && transMat[row][col - 1].contains(" ")) {
                String tmp = findPrefix(transMat, row, col);
                GameLoop.extendRight(transMat, wordTrie, tray, row, col, tmp,
                        wordTrie.root, possWords, playInfo);
            }
            else {
                GameLoop.leftPart(transMat, wordTrie, tray, row, col,
                        "", wordTrie.root, possWords, k, playInfo);
            }
            for(String word : possWords) {
                wordScore = scoreWord(word);
                if(wordScore > Integer.parseInt(bestWordVert[3])) {
                    bestWordVert[0] = String.valueOf(row);
                    bestWordVert[1] = String.valueOf(col);
                    bestWordVert[2] = word;
                    bestWordVert[3] = String.valueOf(wordScore);
                }
            }
        }
        int bestWordHorizScore = Integer.parseInt(bestWordHoriz[3]);
        int bestWordVertScore = Integer.parseInt(bestWordVert[3]);
        String bestWord;
        if(bestWordHorizScore > bestWordVertScore) {
            bestWord = bestWordHoriz[2];
            int bestWordRow = Integer.parseInt(bestWordHoriz[0]);
            int bestWordCol = Integer.parseInt(bestWordHoriz[1]);
            boolean isHorizWord = true;
            mutateBoard(boardSpaces, bestWord, bestWordRow, bestWordCol, isHorizWord);
        }
        else {
            bestWord = bestWordVert[2];
            int bestWordRow = Integer.parseInt(bestWordVert[0]);
            int bestWordCol = Integer.parseInt(bestWordVert[1]);
            boolean isHorizWord = false;
            mutateBoard(boardSpaces, bestWord, bestWordRow, bestWordCol, isHorizWord);
        }
        int bestWordScore = scoreWord(bestWord);
        /*System.out.println("Solution " + bestWord + " has " + bestWordScore + " points");
        System.out.println("Solution Board:");
        for(int i = 0; i < boardSpaces.length; i++) {
            for(int j = 0; j < boardSpaces.length; j++) {
                System.out.print(boardSpaces[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println();*/
    }
    public static String[][] parseBoardString(int boardSize, String gameBoard, String[][] boardSpaces) {
        LinkedList<String> temp = new LinkedList<>();
        for(int i = 0; i < ((int)Math.pow(boardSize, 2) * 3); i++) {
            if(gameBoard.charAt(i) == '\n') {
            }
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
                if(boardSpaces[i][j].charAt(0) == ' ') {
                    if((j - 1) >= 0 && boardSpaces[i][j-1].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i),
                                String.valueOf(j - 1),
                                String.valueOf(boardSpaces[i][j - 1])};
                        anchorsList.add(tmp);
                    }
                    if((j + 1) < boardSpaces.length &&
                            boardSpaces[i][j + 1].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i),
                                String.valueOf(j + 1),
                                String.valueOf(boardSpaces[i][j + 1])};
                        anchorsList.add(tmp);
                    }
                    if((i + 1) < boardSpaces.length &&
                            boardSpaces[i + 1][j].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i + 1),
                                String.valueOf(j),
                                String.valueOf(boardSpaces[i][j].charAt(1))};
                        anchorsList.add(tmp);
                    }
                    if((i - 1) >= 0 &&
                            boardSpaces[i - 1][j].contains(".")) {
                        String[] tmp = {
                                String.valueOf(i - 1),
                                String.valueOf(j),
                                String.valueOf(boardSpaces[i][j].charAt(1))};
                        anchorsList.add(tmp);
                    }
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
        for(int i = row; i >= 0; i--) {
            if(boardSpaces[i][col].contains(" ")) {
                String tmp = boardSpaces[i][col];
                word = tmp.trim() + word;
            }
            else break;
        }
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
                                int limit,
                                ArrayList<String[]> playInfo) {
        HashMap<Character, WordTNode> children = node.getChildren();
        WordTNode letterNode;
        if(col >= 0) {
            extendRight(boardSpaces, wordTrie, tray, row, col, "", node, legalWords, playInfo);
            if(limit > 0) {
                for(var child : children.keySet()) {
                    char c = child.charValue();
                    if (tray.contains(child.charValue())) {
                        tray.remove(Character.valueOf(c));
                        if(children.containsKey(c)) {
                            letterNode = children.get(c);
                            children = letterNode.getChildren();
                            partialWord = c + partialWord;
                            col--;
                            if(col >= 0) {
                                leftPart(boardSpaces, wordTrie, tray,
                                        row, col, partialWord, letterNode, legalWords,
                                        limit - 1, playInfo);
                            }
                        }
                        tray.add(c);
                    }
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
                                     ArrayList<String> legalWords,
                                     ArrayList<String[]> playInfo) {
        HashMap<Character, WordTNode> children = node.getChildren();
        WordTNode letterNode;
        if(col < boardSpaces.length) {
            if(!boardSpaces[row][col].contains(" ")) {
                if(node.isCompleteWord()) {
                    if(!legalWords.contains(partialWord)) {
                        legalWords.add(partialWord);
                        String[] tmp = {partialWord, String.valueOf(row), String.valueOf(col)};
                        playInfo.add(tmp);
                    }
                }
                for(var child : children.keySet()) {
                    int counter = col;
                    if (tray.contains(child.charValue())) {
                        ArrayList<Character> crossCheckedLetters = crossCheck(boardSpaces, row, col, wordTrie);
                        if (crossCheckedLetters.contains(child.charValue())) {
                            char c = child.charValue();
                            tray.remove(Character.valueOf(c));
                            if(children.containsKey(c)) {
                                letterNode = children.get(c);
                                children = letterNode.getChildren();
                                if (col++ < boardSpaces.length) {
                                    partialWord = partialWord + c;
                                    extendRight(boardSpaces, wordTrie, tray,
                                            row, col, partialWord, letterNode, legalWords, playInfo);
                                }
                                tray.add(c);
                            }
                        }
                    }
                    col = counter;
                }
            }
            else {
                String squareString = boardSpaces[row][col].trim();
                squareString = squareString.toLowerCase();
                char c = squareString.charAt(0);
                letterNode = children.get(c);
                if(children.containsKey(c)) {
                    col++;
                    partialWord = partialWord + c;
                    extendRight(boardSpaces, wordTrie, tray,
                            row, col, partialWord, letterNode, legalWords, playInfo);
                }
            }
        }
    }
    public static int findK(String[][] boardSpaces, int row, int col) {
        int k = 0;
        for(int i = col; i > 0; i--) {
            k++;
        }
        return k;
    }
    public static int scoreWord(String word) {
        int score = 0;
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == 'a' ||
                    word.charAt(i) == 'e' ||
                    word.charAt(i) == 'i' ||
                    word.charAt(i) == 'o' ||
                    word.charAt(i) == 'u' ||
                    word.charAt(i) == 'l' ||
                    word.charAt(i) == 'n' ||
                    word.charAt(i) == 's' ||
                    word.charAt(i) == 't' ||
                    word.charAt(i) == 'r') {
                score += 1;
            }
            else if(word.charAt(i) == 'd' || word.charAt(i) == 'g') {
                score += 2;
            }
            else if(word.charAt(i) == 'b' ||
                    word.charAt(i) == 'c' ||
                    word.charAt(i) == 'm' ||
                    word.charAt(i) == 'p') {
                score += 3;
            }
            else if(word.charAt(i) == 'f' ||
                    word.charAt(i) == 'h' ||
                    word.charAt(i) == 'v' ||
                    word.charAt(i) == 'w' ||
                    word.charAt(i) == 'y') {
                score += 4;
            }
            else if(word.charAt(i) == 'k') {
                score += 5;
            }
            else if(word.charAt(i) == 'j' || word.charAt(i) == 'x') {
                score += 8;
            }
            // Q or Z
            else score += 10;
        }
        return score;
    }
    public static String[][] transposeBoard(String[][] boardSpaces) {
        int size = boardSpaces.length;
        String[][] transposedMatrix = new String[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                transposedMatrix[i][j] = boardSpaces[j][i];
            }
        }
        return transposedMatrix;
    }
    public static String findPrefix(String[][] boardSpaces, int row, int col) {
        String word = "";
        for(int i = col; i > 0; i--) {
            if(boardSpaces[i][col].contains(" ")) {
                String tmp = boardSpaces[row][i];
                word = tmp.trim() + word;
            }
            else break;
        }
        return word;
    }
    public static String[][] mutateBoard(String[][] boardSpaces,
                                         String word,
                                         int row,
                                         int col,
                                         boolean isHorizWord) {
        int len = word.length();
        if(isHorizWord) {
            for(int i = len; i > 0; i--) {
                boardSpaces[row][i] = " " + word.charAt(i - 1);
            }
        }
        else {
            for(int i = len; i > 0; i--) {
                boardSpaces[i][col] = " " + word.charAt(i - 1);
            }
        }
        return boardSpaces;
    }
}
