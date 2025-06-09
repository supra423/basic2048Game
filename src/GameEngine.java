import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameEngine extends Board implements ActionListener {
//    Board newBoard = new Board();
//    private static Board board = new Board();
    private final GameControls keyH = new GameControls();
    static int rows = 4;
    static int cols = 4;
//    public static int[][] matrix = new int[rows][cols];
    public static int[][] matrix = {
        {0, 0, 0, 0},
        {0, 0, 4, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 2} };

    public GameEngine(JFrame frame) {
        frame.addKeyListener(keyH);
        Timer timer = new Timer(10, this);
        timer.start();
//        this.boardUpdate(matrix);
//        displayCells(matrix);
        this.boardUpdate(matrix);
    }

//    public static Board getBoard() {
//        return board;
//    }

//    public static void setBoard(Board board) {
//        GameEngine.board = board;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (keyH.upPressed) {
            keyH.upPressed = false;
        } else if (keyH.downPressed) {
            keyH.downPressed = false;
        } else if (keyH.leftPressed) {
            System.out.println("left");
            leftMove(matrix);
            keyH.leftPressed = false;
        } else if (keyH.rightPressed) {
            keyH.rightPressed = false;
        }
    }

    private void leftMove(int[][] matrix) {
        for (int[] arr : matrix) {
            int insertTile = 0;
            int mergeTile = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    arr[insertTile] = arr[i];
                    insertTile++;
                }
            }
            while (insertTile < arr.length) {
                arr[insertTile] = 0;
                insertTile++;
            }

            for (int j = 0; j < arr.length - 1; j++)  {
                if (arr[j] != 0 && arr[j] == arr[j + 1]) {
                    arr[j] += arr[j + 1];
                    arr[j + 1] = 0;
                }
            }
        }
    }

    static void displayRows(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(arr[j] + ", ");
            }
            System.out.println();
        }
    }
//    public void displayCells(int[][] matrix) {
//        Board newBoard = new Board();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                newBoard.add(new Cell(2));
//            }
//        }
//    }
//
//    public void boardUpdate(int[][] matrix) {
//        Board newBoard = new Board();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if (matrix[i][j] != 0) {
//                    newBoard.add(new Cell(matrix[i][j]));
//                } else {
//                    newBoard.add(new Cell());
//                }
//            }
//        }
//    }
}
