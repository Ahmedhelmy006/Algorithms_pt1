package FourthWeek;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final int size;
    private final StringBuilder string_tiles = new StringBuilder();
    private final int[][] tiles;
    int[][] goal;


    public Board(int[][] tiles){
        size = tiles.length;
        this.tiles = tiles.clone();
        goal = generateGoal(size);
    }

    public static int[][] generateGoal(int size){
        int [][] goal = new int[size][size];
        int start = 1;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                goal[i][j] = start;
                start++;
            }
        }
        goal[size-1][size-1] = 0;
        return goal;
    }

    // string representation of this board
    public String toString(){
        string_tiles.append(size).append("\n");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j <= size; j++){
                if(j == size){
                    string_tiles.append("\n");
                }
                else {
                    string_tiles.append(" ").append(tiles[i][j]);
                }
            }
        }
        return string_tiles.toString();
    }



    // board dimension n
    public int dimension(){
        return size;
    }


    // number of tiles out of place
    public int hamming(){
        int errors_count = 0;
        for(int i = 0; i < dimension(); i++){
            for(int j = 0; j < dimension(); j++){
                if(tiles[i][j] != goal[i][j]){
                    errors_count++;
                }
            }
        }
        return errors_count;
    }


    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int manhattan = 0;
        for(int i = 0; i < size; i++){
            for(int j =0; j < size; j++){
                if(tiles[i][j] != goal[i][j]){
                    manhattan = manhattan +((Math.abs(i-((tiles[i][j]) - 1 ) / dimension())) + Math.abs(j - (tiles[i][j]) % dimension()));
                }
            }
        }
        return manhattan;
    }


    // is this board the goal board?
    public boolean isGoal(){
        for(int i = 0; i < dimension(); i++){
            for(int j = 0; j < dimension(); j++){
                if(tiles[i][j] != goal[i][j]){
                    return false;
                }
            }
        }
        return true;
    }



    // does this board equal y?
    public boolean equals(Board y){
        if(y.dimension() != dimension()){
            return false;
        }
        else{
            for(int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (y.tiles[i][j] != tiles[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // All neighboring boards
    public Iterable<Board> neighbors() {
        return generateNeighbours();
    }

    // Generate all possible moves
    public List<Board> generateNeighbours() {
        List<Board> neighbours = new ArrayList<>();
        int[] pos = locateZero();
        int row = pos[0];
        int col = pos[1];

        // Element is not located in either of the corners or not located on either sides of the matrix.
        if (col != 0 && col != dimension() - 1 && row != 0 && row != dimension() - 1) {
            // Move Zero to the Right
            int[][] moveRight = deepClone(tiles);
            moveRight = swap(moveRight, pos, row, col + 1);
            int[][] moveLeft = deepClone(tiles);
            moveLeft = swap(moveLeft, pos, row, col - 1);
            int[][] moveUp = deepClone(tiles);
            moveUp = swap(moveUp, pos, row - 1, col);
            int[][] moveDown = deepClone(tiles);
            moveDown = swap(moveDown, pos, row + 1, col);
            Board rightTiles = new Board(moveRight);
            Board leftTiles = new Board(moveLeft);
            Board upTiles = new Board(moveUp);
            Board downTiles = new Board(moveDown);
            neighbours.add(rightTiles);
            neighbours.add(leftTiles);
            neighbours.add(upTiles);
            neighbours.add(downTiles);
        }
        // Left Side
        else if (col == 0 && row != dimension() - 1 && row != 0) {
            int[][] moveRight = deepClone(tiles);
            moveRight = swap(moveRight, pos, row, col + 1);
            int[][] moveUp = deepClone(tiles);
            moveUp = swap(moveUp, pos, row - 1, col);
            int[][] moveDown = deepClone(tiles);
            moveDown = swap(moveDown, pos, row + 1, col);
            Board rightTiles = new Board(moveRight);
            Board upTiles = new Board(moveUp);
            Board downTiles = new Board(moveDown);
            neighbours.add(rightTiles);
            neighbours.add(upTiles);
            neighbours.add(downTiles);
        }
        // Right Side
        else if (col == dimension() - 1 && row != dimension() - 1 && row != 0) {
            int[][] moveLeft = deepClone(tiles);
            moveLeft = swap(moveLeft, pos, row, col - 1);
            int[][] moveUp = deepClone(tiles);
            moveUp = swap(moveUp, pos, row - 1, col);
            int[][] moveDown = deepClone(tiles);
            moveDown = swap(moveDown, pos, row + 1, col);
            Board leftTiles = new Board(moveLeft);
            Board upTiles = new Board(moveUp);
            Board downTiles = new Board(moveDown);
            neighbours.add(leftTiles);
            neighbours.add(upTiles);
            neighbours.add(downTiles);
        }
        // First row
        else if (col != 0 && col != dimension() - 1 && row == 0) {
            int[][] moveRight = deepClone(tiles);
            moveRight = swap(moveRight, pos, row, col + 1);
            int[][] moveLeft = deepClone(tiles);
            moveLeft = swap(moveLeft, pos, row, col - 1);
            int[][] moveDown = deepClone(tiles);
            moveDown = swap(moveDown, pos, row + 1, col);
            Board rightTiles = new Board(moveRight);
            Board leftTiles = new Board(moveLeft);
            Board downTiles = new Board(moveDown);
            neighbours.add(rightTiles);
            neighbours.add(leftTiles);
            neighbours.add(downTiles);
        }
        // Second Row
        else if (col != 0 && col != dimension() - 1 && row == dimension() - 1) {
            int[][] moveRight = deepClone(tiles);
            moveRight = swap(moveRight, pos, row, col + 1);
            int[][] moveLeft = deepClone(tiles);
            moveLeft = swap(moveLeft, pos, row, col - 1);
            int[][] moveUp = deepClone(tiles);
            moveUp = swap(moveUp, pos, row - 1, col);
            Board rightTiles = new Board(moveRight);
            Board leftTiles = new Board(moveLeft);
            Board upTiles = new Board(moveUp);
            neighbours.add(rightTiles);
            neighbours.add(leftTiles);
            neighbours.add(upTiles);
        }
        // Top Left
        else if (col == 0 && row == 0) {
            int[][] moveRight = deepClone(tiles);
            moveRight = swap(moveRight, pos, row, col + 1);
            int[][] moveDown = deepClone(tiles);
            moveDown = swap(moveDown, pos, row + 1, col);
            Board rightTiles = new Board(moveRight);
            Board downTiles = new Board(moveDown);
            neighbours.add(rightTiles);
            neighbours.add(downTiles);
        }
        // Top Right
        else if (col == dimension() - 1 && row == 0) {
            int[][] moveLeft = deepClone(tiles);
            moveLeft = swap(moveLeft, pos, row, col - 1);
            int[][] moveDown = deepClone(tiles);
            moveDown = swap(moveDown, pos, row + 1, col);
            Board leftTiles = new Board(moveLeft);
            Board downTiles = new Board(moveDown);
            neighbours.add(leftTiles);
            neighbours.add(downTiles);
        }
        // Down Right
        else if (col == dimension() - 1 && row == dimension() - 1) {
            int[][] moveLeft = deepClone(tiles);
            moveLeft = swap(moveLeft, pos, row, col - 1);
            int[][] moveUp = deepClone(tiles);
            moveUp = swap(moveUp, pos, row - 1, col);
            Board leftTiles = new Board(moveLeft);
            Board upTiles = new Board(moveUp);
            neighbours.add(leftTiles);
            neighbours.add(upTiles);
        }
        // Down Left
        else if (col == 0 && row == dimension() - 1) {
            int[][] moveRight = deepClone(tiles);
            moveRight = swap(moveRight, pos, row, col + 1);
            int[][] moveUp = deepClone(tiles);
            moveUp = swap(moveUp, pos, row - 1, col);
            Board rightTiles = new Board(moveRight);
            Board upTiles = new Board(moveUp);
            neighbours.add(rightTiles);
            neighbours.add(upTiles);
        }

        return neighbours;
    }

    // Locate the position of the zero element
    public int[] locateZero() {
        int[] pos = new int[2];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] == 0) {
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }
            }
        }
        return pos;
    }

    // Swap zero with new position
    public int[][] swap(int[][] neighbour, int[] zeroPos, int newRow, int newCol) {
        int zeroRow = zeroPos[0];
        int zeroCol = zeroPos[1];
        int temp = neighbour[newRow][newCol];
        neighbour[newRow][newCol] = 0;
        neighbour[zeroRow][zeroCol] = temp;
        return neighbour;
    }

    // Deep clone a 2D array
    private int[][] deepClone(int[][] array) {
        int[][] clone = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            clone[i] = array[i].clone();
        }
        return clone;
    }



    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        int[][] twin = tiles.clone();
        int n = twin.length;
        int temp;
        // Find two non-blank tiles to swap
        int row1 = 0, col1 = 0, row2 = 0, col2 = 1;
        boolean found = false;

        // Ensure that the tiles are not blank (0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (twin[i][j] != 0 && twin[i][j + 1] != 0) {
                    row1 = i;
                    col1 = j;
                    row2 = i;
                    col2 = j + 1;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        temp = twin[row1][col1];
        twin[row1][col1] = twin[row2][col2];
        twin[row2][col2] = temp;
        return new Board(twin);
    }

    // unit testing (not graded)
    public static void main(String[] args){
        int[][] my_board = new int[3][3];
        my_board[0][0] = 8;
        my_board[0][1] = 1;
        my_board[0][2] = 3;
        my_board[1][0] = 4;
        my_board[1][1] = 0;
        my_board[1][2] = 2;
        my_board[2][0] = 7;
        my_board[2][1] = 6;
        my_board[2][2] = 5;

        int[][] goalBoard = new int[3][3];
        goalBoard[0][0] = 1;
        goalBoard[0][1] = 2;
        goalBoard[0][2] = 3;
        goalBoard[1][0] = 4;
        goalBoard[1][1] = 5;
        goalBoard[1][2] = 6;
        goalBoard[2][0] = 7;
        goalBoard[2][1] = 8;
        goalBoard[2][2] = 0;

        Board board = new Board(my_board);
        StdOut.println(board.toString());
        Board goalboard = new Board(goalBoard);
        Board another_board = new Board(my_board);
        boolean isEqual = board.equals(another_board);
        StdOut.println(isEqual);
        StdOut.println(board.manhattan());
        StdOut.println(goalboard.isGoal());
        Board twinTest = goalboard.twin();
        StdOut.println(twinTest.toString());
        StdOut.println(goalboard.toString());
        StdOut.println(goalboard.manhattan());
        StdOut.println(goalboard.hamming());

        Iterable<Board> neighbours = board.neighbors();

        List<Board> neighboursList = new ArrayList<>();
        for (Board neighbour : neighbours) {
            neighboursList.add(neighbour);
        }

        // Print out the neighbours
        System.out.println("Neighbours of the board:");
        for (Board neighbour : neighboursList) {
            System.out.println(neighbour);
        }

    }

}