import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ScrabbleGameMain extends Application{
    private final int SIZE = 800;
    private int score = 0;
    private char[] trayArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
    private int selectedTrayEle = 0;
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Scrabble");
        BorderPane root = new BorderPane();

        TilePane gameBoard = generateBoard();
        root.setCenter(gameBoard);

        VBox topVBox = new VBox();
        Label scoreLabel = new Label("Score: " + score);
        topVBox.getChildren().add(scoreLabel);
        topVBox.setAlignment(Pos.CENTER);
        root.setTop(topVBox);

        TilePane trayPane = generateTray(trayArray);
        root.setBottom(trayPane);

        Scene scene = new Scene(root, SIZE, SIZE);
        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public TilePane generateBoard() {
        TilePane gameBoard = new TilePane();
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setVgap(.25);
        gameBoard.setHgap(.25);
        gameBoard.setMaxWidth(SIZE / 1.60);
        String[][] boardConfig = getBoardConfig();
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 15; j++) {
                Rectangle rect = new Rectangle(SIZE / 25, SIZE / 25);
                if(boardConfig[i][j].contains("3WS")) {
                    rect.setFill(Color.RED);
                }
                else if(boardConfig[i][j].contains("2LS")) {
                    rect.setFill(Color.LIGHTBLUE);
                }
                else if(boardConfig[i][j].contains("2WS")) {
                    rect.setFill(Color.LIGHTPINK);
                }
                else if(boardConfig[i][j].contains("3LS")) {
                    rect.setFill(Color.BLUE);
                }
                else if(boardConfig[i][j].contains("S")) {
                    rect.setFill(Color.LAVENDER);
                }
                else rect.setFill(Color.GHOSTWHITE);
                gameBoard.getChildren().add(rect);
            }
        }
        /*for(int i = 0; i < 225; i++) {
            StackPane stackPane = new StackPane();
            int counter = i;
            Rectangle rect = new Rectangle(SIZE / 25, SIZE / 25);
            rect.setFill(Color.GHOSTWHITE);
            if(i =
            Label label = new Label();
            label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
            label.setTextFill(Color.CORNFLOWERBLUE);
            label.setMouseTransparent(true);
            stackPane.getChildren().addAll(rect);
            gameBoard.getChildren().add(stackPane);
        }*/
        return gameBoard;
    }
    public TilePane generateTray(char[] trayArray) {
        TilePane trayPane = new TilePane();
        trayPane.setAlignment(Pos.CENTER);
        trayPane.setVgap(.25);
        trayPane.setHgap(.25);
        for(int i = 0; i < 7; i++) {
            StackPane stackPane = new StackPane();
            Rectangle rect = new Rectangle(SIZE / 10, SIZE / 10);
            rect.setFill(Color.MISTYROSE);
            Label label = new Label(String.valueOf(trayArray[i]));
            label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
            label.setTextFill(Color.CORNFLOWERBLUE);
            label.setMouseTransparent(true);
            stackPane.getChildren().addAll(rect, label);
            trayPane.getChildren().add(stackPane);
        }
        return trayPane;
    }
    public String[][] getBoardConfig() {
        String[][] boardConfig = {
                // 1
                {"3WS", "0", "0", "2LS", "0", "0", "0", "3WS", "0", "0", "0", "2LS", "0", "0", "3WS"},
                // 2
                {"0", "2WS", "0", "0", "0", "3LS", "0", "0", "0", "3LS", "0", "0", "0", "2WS", "0"},
                // 3
                {"0", "0", "2WS", "0", "0", "0", "2LS", "0", "2LS", "0", "0", "0", "2WS", "0", "0"},
                // 4
                {"2LS", "0", "0", "2WS", "0", "0", "0", "2LS", "0", "0", "0", "2WS", "0", "0", "2LS"},
                // 5
                {"0", "0", "0", "0", "2WS", "0", "0", "0", "0", "0", "2WS", "0", "0", "0", "0"},
                // 6
                {"0", "3LS", "0", "0", "0", "3LS", "0", "0", "0", "3LS", "0", "0", "0", "3LS", "0"},
                // 7
                {"0", "0", "2LS", "0", "0", "0", "2LS", "0", "2LS", "0", "0", "0", "2LS", "0", "0"},
                // 8
                {"3WS", "0", "0", "2LS", "0", "0", "0", "S", "0", "0", "0", "2LS", "0", "0", "3WS"},
                // 9
                {"0", "0", "2LS", "0", "0", "0", "2LS", "0", "2LS", "0", "0", "0", "2LS", "0", "0"},
                // 10
                {"0", "3LS", "0", "0", "0", "3LS", "0", "0", "0", "3LS", "0", "0", "0", "3LS", "0"},
                // 11
                {"0", "0", "0", "0", "2WS", "0", "0", "0", "0", "0", "2WS", "0", "0", "0", "0"},
                // 12
                {"2LS", "0", "0", "2WS", "0", "0", "0", "2LS", "0", "0", "0", "2WS", "0", "0", "2LS"},
                // 13
                {"0", "0", "2WS", "0", "0", "0", "2LS", "0", "2LS", "0", "0", "0", "2WS", "0", "0"},
                // 14
                {"0", "2WS", "0", "0", "0", "3LS", "0", "0", "0", "3LS", "0", "0", "0", "2WS", "0"},
                // 15
                {"3WS", "0", "0", "2LS", "0", "0", "0", "3WS", "0", "0", "0", "2LS", "0", "0", "3WS"}
        };
        return boardConfig;
    }
}
