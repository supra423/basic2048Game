import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class GameEngine extends Board implements ActionListener {
    private final JFrame frame;
    private final Board board;
    private final GameControls keyH = new GameControls();
    private static int[][] matrix = {
            // you can change the values inside the matrix to preload squares for testing/debugging
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0} };

    Deque<int[][]> matrixHistoryStack = new ArrayDeque<>();
    private static boolean canUndo = false;
    int[][] copyOfMatrix; // this will be used to check if there were
                          // any movements made, if no moves were made, then
                          // this will prevent a tile from being randomly generated


    public GameEngine(JFrame frame) {
        this.frame = frame;
        this.board = new Board();

        insertStartingTiles(matrix); // if you are changing the values inside the matrix,
        insertStartingTiles(matrix); // might as well comment out both these lines.
        gameEngineUpdate(frame, board);
        matrixHistoryStack.push(matrix);

        Timer timer = new Timer(50, this);
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
            canUndo = true;

            if (didPlayerMakeMove()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }

            upMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.upPressed = false;
        } else if (keyH.downPressed) {
            canUndo = true;

            if (didPlayerMakeMove()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }

            downMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.downPressed = false;
        } else if (keyH.leftPressed) {
            canUndo = true;

            if (didPlayerMakeMove()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }

            leftMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.leftPressed = false;
        } else if (keyH.rightPressed) {
            canUndo = true;

            if (didPlayerMakeMove()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }

            rightMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameEngineUpdate(this.frame, this.board);
            keyH.rightPressed = false;

        } else if (keyH.undoPressed) {
            if (canUndo && !matrixHistoryStack.isEmpty()) {
                matrix = createMatrixCopy(matrixHistoryStack.peek());
                board.gameUpdate(matrix);
            } else if (!didPlayerMakeMove() && !matrixHistoryStack.isEmpty()) {
                matrixHistoryStack.pop();
                if (matrixHistoryStack.peek() != null) {
                    matrix = createMatrixCopy(matrixHistoryStack.peek());
                    board.gameUpdate(matrix);
                }
            } else if (matrixHistoryStack.isEmpty()) {
                matrix = copyOfMatrix;
                board.gameUpdate(matrix);
            }
                canUndo = false;
                keyH.undoPressed = false;
        }
    }


    private void leftMove(int[][] matrix) {
        for (int[] arr : matrix) {
            moveTilesAndReplaceWithZero(arr);

            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] != 0 && arr[j] == arr[j + 1]) {
                    arr[j] += arr[j + 1];
                    arr[j + 1] = 0;
                }
            }

            moveTilesAndReplaceWithZero(arr);
        }
    }

    private void moveTilesAndReplaceWithZero(int[] arr) {

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
            System.arraycopy(matrix[i], 0, matrixCopy[i], 0, matrix[i].length);
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
        if (didPlayerMakeMove()) {
            insertStartingTiles(matrix);
        }
    }
    private void insertStartingTiles(int[][] matrix) {
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
    private boolean didPlayerMakeMove() {
        return !Arrays.deepEquals(copyOfMatrix, matrixHistoryStack.peek());
    }
}