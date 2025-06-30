import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class GameEngine extends JPanel implements ActionListener {
    private final JFrame frame;
    public static Board board = new Board();
    private final GameControls keyH = new GameControls();

//    if you want to preload squares for testing/debugging, do these:
//    uncomment lines 17 - 25
//    set both the values of rows and cols to 4
//    and comment out line 30

//    public static int[][] matrix = {
//            // this right here is literally the board, kinda amazes me that the game is basically
//            // just a data structure, specifically, a 2-Dimensional Array or Matrix
//            // this is why you learn your Data Structures ;)
//            // you can change the values inside the matrix to preload squares for testing/debugging
//            {0, 0, 0, 0},
//            {0, 0, 0, 0},
//            {0, 0, 0, 0},
//            {0, 0, 0, 0} };

    final public static int rows = 6;
    final public static int cols = 6;

    public static int[][] matrix = new int[rows][cols];

    private final Deque<int[][]> matrixHistoryStack = new ArrayDeque<>();
    private static boolean canUndo = false;
    private int[][] copyOfMatrix; // this will be used to check if there were
                          // any movements made. If no moves were made, then
                          // this will prevent a tile from being randomly generated

    public static int score = 0;
//    private static int scoreEarned = 0;
    private final Deque<Integer> scoreHistoryStack = new ArrayDeque<>();

    public GameEngine(JFrame frame) {
        this.frame = frame;

        insertStartingTiles(matrix); // if you are changing the values inside the matrix,
        insertStartingTiles(matrix); // might as well comment out both these lines.
        gameUpdate(this.frame, board);
        matrixHistoryStack.push(matrix);

        Timer timer = new Timer(17, this);
        timer.start();
    }

    private void gameUpdate(JFrame frame, Board board) {

        frame.addKeyListener(keyH);
//        frame.add(board);
        board.boardUpdate(matrix);
    }

    @Override
    final public void actionPerformed(ActionEvent e) {
        if (keyH.upPressed) {
            canUndo = true;
            if (didBoardChanged()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }
            upMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameUpdate(this.frame, board);
            keyH.upPressed = false;
        } else if (keyH.downPressed) {
            canUndo = true;
            if (didBoardChanged()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }
            downMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameUpdate(this.frame, board);
            keyH.downPressed = false;
        } else if (keyH.leftPressed) {
            canUndo = true;
            if (didBoardChanged()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }
            leftMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameUpdate(this.frame, board);
            keyH.leftPressed = false;
        } else if (keyH.rightPressed) {
            canUndo = true;
            if (didBoardChanged()) {
                matrixHistoryStack.push(createMatrixCopy(matrix));
            }
            rightMove(matrix);
            copyOfMatrix = createMatrixCopy(matrix);
            insertRandomTile(matrix);
            gameUpdate(this.frame, board);
            keyH.rightPressed = false;

        } else if (keyH.undoPressed) {

            if (matrixHistoryStack.isEmpty()) {
                matrix = copyOfMatrix;
                board.boardUpdate(matrix);
            } else if (!didBoardChanged() && !matrixHistoryStack.isEmpty()) {
                // if player made a move but the board did not change
                matrixHistoryStack.pop();
                if (matrixHistoryStack.peek() != null) {
                    matrix = createMatrixCopy(matrixHistoryStack.peek());
                    board.boardUpdate(matrix);
                }
            } else if (canUndo && !matrixHistoryStack.isEmpty()) {
                matrix = createMatrixCopy(matrixHistoryStack.peek());
                board.boardUpdate(matrix);

                if (scoreHistoryStack.size() > 1) {
                    scoreHistoryStack.pop();
                    score = scoreHistoryStack.peek();
                } else {
                    score -= scoreHistoryStack.pop();
                }
                Window.updateScoreLabel(Window.scoreLabel);
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
                        if (didBoardChanged()) {
                            score += arr[j];
                            Window.updateScoreLabel(Window.scoreLabel);
                    }
                }
            }
            moveTilesAndReplaceWithZero(arr);
        }
        scoreHistoryStack.push(score);
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
        int[][] matrixCopy = new int[rows][cols];
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
        if (didBoardChanged()) {
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
    private boolean didBoardChanged() {
        return !Arrays.deepEquals(copyOfMatrix, matrixHistoryStack.peek());
    }
}