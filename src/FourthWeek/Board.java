package FourthWeek;

import edu.princeton.cs.algs4.StdOut;

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

    /*

    // is this board the goal board?
    public boolean isGoal(){}

    // does this board equal y?
    public boolean equals(Object y){}

    // all neighboring boards
    public Iterable<Board> neighbors()

    // a board that is obtained by exchanging any pair of tiles
    public Board twin()

     */
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

        Board board = new Board(my_board);
        StdOut.print(board.manhattan());
    }

}