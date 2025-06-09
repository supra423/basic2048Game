import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class GameEngine extends Board implements ActionListener {
    private final JFrame frame;
    private final Board board;
    private final GameControls keyH = new GameControls();
    public static int[][] matrix = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};

    public GameEngine(JFrame frame) {
        this.frame = frame;
        this.board = new Board();

        gameEngineUpdate(frame, board);


        Timer timer = new Timer(10, this);
        timer.start();
    }

    private void gameEngineUpdate(JFrame frame, Board board) {

        frame.addKeyListener(keyH);
        frame.add(board);
        board.gameUpdate(matrix);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (keyH.upPressed) {
            upMove(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.upPressed = false;
        } else if (keyH.downPressed) {
            downMove(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.downPressed = false;
        } else if (keyH.leftPressed) {
            leftMove(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.leftPressed = false;
        } else if (keyH.rightPressed) {
            rightMove(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.rightPressed = false;
        }
    }


    private void leftMove(int[][] matrix) {
        for (int[] arr : matrix) {
            int insertTile = 0;

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

            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] != 0 && arr[j] == arr[j + 1]) {
                    arr[j] += arr[j + 1];
                    arr[j + 1] = 0;
                }
            }
        }
    }

    private void reverseMatrixHorizontally(int[][] matrix) {
        int cols = matrix[0].length;

        for (int[] row : matrix) {
            for (int i = 0; i < cols / 2; i++) {
                int temp = row[i];
                row[i] = row[cols - 1 - i];
                row[cols - 1 - i] = temp;
            }
        }
    }

    private void rightMove(int[][] matrix) {
        reverseMatrixHorizontally(matrix);
        leftMove(matrix);
        reverseMatrixHorizontally(matrix);
    }

    private void tiltMatrixToLeft(int[][] matrix) {
        int[][] matrixCopy = createMatrixCopy(matrix);
        reverseMatrixHorizontally(matrixCopy);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int[] arr = matrixCopy[j];
                matrix[i][j] = arr[i];
            }
        }
    }

    private void tiltMatrixToRight(int[][] matrix) {
        reverseMatrixHorizontally(matrix);
        tiltMatrixToLeft(matrix);
        reverseMatrixHorizontally(matrix);
    }

    private int[][] createMatrixCopy(int[][] matrix) {
        int[][] matrixCopy = new int[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrixCopy[i][j] = matrix[i][j];
            }
        }
        return matrixCopy;
    }

    private void upMove(int[][] matrix) {
        tiltMatrixToLeft(matrix);
        leftMove(matrix);
        tiltMatrixToRight(matrix);
    }

    private void downMove(int[][] matrix) {
        tiltMatrixToRight(matrix);
        leftMove(matrix);
        tiltMatrixToLeft(matrix);
    }

    private void insertRandomTile(int[][] matrix) {

        List<int[]> emptyTiles = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    emptyTiles.add(new int[]{i, j});
                }
            }
        }

        if (!emptyTiles.isEmpty()) {
            Random tilePicker = new Random();
            int[] pos = emptyTiles.get(tilePicker.nextInt(emptyTiles.size()));
            int row = pos[0];
            int col = pos[1];

            matrix[row][col] = tilePicker.nextDouble() < 0.9 ? 2 : 4;
        }
    }
}