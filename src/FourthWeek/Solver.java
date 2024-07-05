package  FourthWeek;

import edu.princeton.cs.algs4.MinPQ;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final int priority;
        private final SearchNode previous;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.priority = board.manhattan() + moves;
            this.previous = previous;
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrevious() {
            return previous;
        }

        @Override
        public int compareTo(SearchNode other) {
            return Integer.compare(this.priority, other.priority);
        }
    }


    private SearchNode solutionNode;
    private boolean isSolvable;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Initial board cannot be null");
        }

        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));
        Set<Board> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            SearchNode current = pq.delMin();
            Board currentBoard = current.getBoard();

            if (currentBoard.isGoal()) {
                solutionNode = current;
                isSolvable = true;
                break;
            }

            if (!visited.contains(currentBoard)) {
                visited.add(currentBoard);

                for (Board neighbor : currentBoard.neighbors()) {
                    if (current.getPrevious() == null || !neighbor.equals(current.getPrevious().getBoard())) {
                        pq.insert(new SearchNode(neighbor, current.getMoves() + 1, current));
                    }
                }
            }
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (!isSolvable) {
            return -1;
        }
        return solutionNode.getMoves();
    }

    public Iterable<Board> solution() {
        if (!isSolvable) {
            return null;
        }
        Stack<Board> solution = new Stack<>();
        for (SearchNode node = solutionNode; node != null; node = node.getPrevious()) {
            solution.push(node.getBoard());
        }
        return solution;
    }
}
