import java.util.Scanner;

// THIS IS ONLY A TEST FILE FOR MY 2048 PROJECT

public class MatrixTest {

    static void displayColumns(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int[] arr : matrix) {
                System.out.print(arr[i] + ", ");
            }
            System.out.println();
        }
    }

    static void displayColumnsReversed(int[][] matrix) {
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int[] arr : matrix) {
                System.out.print(arr[i] + ", ");
            }
            System.out.println();
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

    static void leftMove(int[][] matrix) {
        // what's cool is that I can use this single function
        // to shift do all movements in four different directions just by
        // flipping/inverting the matrix before calling this function
        // and then return the matrix back to its original position
        // for example:
        // if I want to do a right move, I need to reverse
        // the entire matrix before calling this function
        // and then reverse the matrix again, now the matrix
        // will look like it did a right move.

        // try this out, get a pen and paper,
        // flip the paper upside down
        // draw an arrow pointing to the LEFT
        // flip the paper upside down again
        // the arrow will point to the right side
        // crazy right?

        // same goes for an up move,
        // but this time, tilt the paper to the LEFT
        // draw an arrow pointing to the LEFT
        // tilt the paper again back to the RIGHT
        // the arrow will point upwards
        // yeah, this is how the movements work

        for (int[] arr : matrix) {
            // this will be used to determine where
            // the non-zero tiles are going to be
            // inserted
            int insertTile = 0;
            int mergeTile = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    // a non-zero tile will be placed where arr[insertTile] will be
                    arr[insertTile] = arr[i];
                    insertTile++;
                }
            }
            while (insertTile < arr.length) {
                // replaces the original position of the moved tile
                // with a "0"
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

    static void reverseMatrixHorizontally(int[][] matrix) {
        int cols = matrix[0].length;

        // the inner loop swaps two elements at the same time
        // say {1, 2, 3, 4}
        // 1st iteration = {4, 2, 3, 1} swapped 1 and 4
        // 2nd iteration = {4, 3, 2, 1} swapped 2 and 3
        for (int[] row : matrix) {
            for (int i = 0; i < cols / 2; i++) {
                int temp = row[i];
                row[i] = row[cols - 1 - i];
                row[cols - 1 - i] = temp;
            }
        }
    }

    static void rightMove(int[][] matrix) {
        // see how simple this looks?
        reverseMatrixHorizontally(matrix);
        leftMove(matrix);
        reverseMatrixHorizontally(matrix);
    }


    static void tiltMatrixToLeft(int [][] matrix) {
        int[][] matrixCopy = createMatrixCopy(matrix);
        reverseMatrixHorizontally(matrixCopy);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int[] arr = matrixCopy[j];
                matrix[i][j] = arr[i];
            }
        }
    }

    static void tiltMatrixToRight(int [][] matrix) {
        int[][] matrixCopy = createMatrixCopy(matrix);
        reverseMatrixHorizontally(matrix);
        tiltMatrixToLeft(matrix);
        reverseMatrixHorizontally(matrix);

//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix.length; j++) {
//                int[] arr = matrixCopy[j];
//                matrix[i][j] = arr[i];
//            }
//        }
    }

    static int[][] createMatrixCopy(int[][] matrix) {
        int[][] matrixCopy = new int[4][4];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrixCopy[i][j] = matrix[i][j];
            }
        }
        return matrixCopy;
    }

    static void upMove(int[][] matrix) {
        tiltMatrixToLeft(matrix);
        leftMove(matrix);
        tiltMatrixToRight(matrix);
    }
    static void downMove(int[][] matrix) {
        tiltMatrixToRight(matrix);
        leftMove(matrix);
        tiltMatrixToLeft(matrix);
    }

    public static void main(String[] args) {
        // create a 4x4 integer matrix
        int[][] matrix = {
                {4, 0, 0, 0,},
                {0, 0, 0, 4,},
                {4, 0, 0, 0,},
                {0, 2, 2, 0,} };

        label:
        while (true) {
            System.out.println("This is the current matrix: ");
//            reverseMatrixHorizontally(matrix);
//            displayRows(matrix);
//            displayColumns(matrix);
//            displayColumnsReversed(matrix);
//            tiltMatrixToLeft(matrix);
//            tiltMatrixToRight(matrix);
            displayRows(matrix);
            System.out.println("Type 'L' to move matrix to the left or 'R' to move matrix to the right");
            System.out.println("Type 'U' to move matrix to the left or 'D' to move matrix to the right");
            System.out.println("Type 'Q' to quit.");
            Scanner scanner = new Scanner(System.in);
            String whichSide = scanner.nextLine().toLowerCase();

            switch (whichSide) {
                case "l":
                    leftMove(matrix);
                    break;
                case "r":
                    rightMove(matrix);
                    break;
                case "u":
                    upMove(matrix);
                    break;
                case "d":
                    downMove(matrix);
                    break;
                case "q":
                    break label;
                default:
                    System.out.println("Invalid input!");
                    break;
            }

        }

    }
}