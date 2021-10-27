Scrabble
Author: Samuel Winkles (# 101479375)
swinkles92@unm.edu
Version: 1.0

Program Descriptions:

**Word Solver**:
    This program begins by taking an argument to specify
which word dictionary is desired. The user is then prompted
for 3 more pieces of information to be entered into the
command line: gameboard size, gameboard configuration, and
letter tray. This input is then parsed and a function call
to cpuTurn is made with the parsed information.
 
   The cpuTurn function first generates a transposed version
of the gameboard. Next, all possible anchors on the board are
found. An anchor is any space adjacent to a letter-filled board
space. With the newly found anchors cpuTurn will call leftPart or
extendRight.
 
   Both leftPart and extendRight are recursive functions that
will pull letters from the letter tray and attempt to form new
possible words. All legal words found are recorded and send back
to the cpuTurn function in a list.
 
   Now that a list of all potential, legal words has been found
it is necessary to assign a score to each word. Once completed,
the highest scoring word is labeled as the solution and added
to the gameboard.

 The main method in WordSolver will continue to accept
user input until the user exits the program, or enter in
a board size of 1.

**ScrabbleGameMain:**
    This program will display a standard scrabble board,
as well as a full tray of letters. Although a user is able
to select specific letters in the tray and add them to the board;
the GUI is not connected to any form of game logic.

Known Bugs/Issues:

**WordSolver:**
The WordSolver's ability to find words is inconsistent.
Included in a comment block at the top of the WordSolver
class is a sample input in which a word will be correctly
found and added to the board.

The cpuTurn is not able to handle blank letter tiles
in the tray.

Tiles with score modifiers will only be registered as
standard, blank tiles.

**ScrabbleGameMain:**
As previously mentioned, this class is merely a graphical
prototype with no actual gameplay logic.

Users are not limited to where they may place letters on the
board, and there is no way for a user to end their turn.

The program also will not generate a new tray of letters.
