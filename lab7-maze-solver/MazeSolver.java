public class MazeSolver {

    private char[][] maze;

    public MazeSolver(char[][] maze) {
        this.maze = maze;
    }

    /**
     * Prints the current state of the maze.
     */
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    /**
     * Public wrapper method to start the maze-solving process.
     * It should find the starting 'S' position and initiate the recursive search.
     * @return true if a path is found, false otherwise.
     */
    public boolean solve() {
        // TODO: Find the starting row and column of 'S'
        int startRow = -1;
        int startCol = -1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }
        //if starting point was found then start recursive search
        if (startRow != -1) {
            return solve(startRow, startCol);
        }
        return false;
    }

    /**
     * The core recursive method to solve the maze.
     * @param row The current row position.
     * @param col The current column position.
     * @return true if this position leads to a solution, false otherwise.
     */
    private boolean solve(int row, int col) {
        // TODO: Implement the recursive logic with backtracking here.
        // 1. Base Case (Stopping Conditions)
        // Check if out of bounds, a wall, or already visited.
        // Check if the current cell is the finish ('F').
        if(row >= maze.length || col >= maze[0].length || row<0 || col<0){
            return false;
        }
        if(maze[row][col] == '.' || maze[row][col] == '#'){
            return false;
        }
        if(maze[row][col] == 'F'){
            return true;
        }

        // 2. Recursive Step
        // Mark the current cell as part of the path.
        // Try moving North, East, South, West.
        // If any direction returns true, then you've found a path, return true.

        maze[row][col] = '.';

        if(solve(row+1,col) || solve(row,col+1) || solve(row-1,col) || solve(row,col-1)){
            return true;
        }

        // 3. Backtracking
        // If no direction works, un-mark the cell and return false.
        maze[row][col] = ' ';
        return false;
    }

    public static void main(String[] args) {
        //maze with solution
        char[][] mazeToSolve = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', ' ', '#', ' ', ' ', '#'},
                {'#', ' ', ' ', '#', ' ', '#', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', ' ', ' ', '#', 'F', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };
        //maze without solution
        char[][] mazeToSolve2 = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'S', '#', '#', ' ', ' ', '#'},
                {'#', ' ', ' ', '#', ' ', '#', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'},
                {'#', ' ', '#', ' ', '#', 'F', '#'},
                {'#', '#', '#', '#', '#', '#', '#'}
        };

        MazeSolver solver = new MazeSolver(mazeToSolve);
        MazeSolver solver2 = new MazeSolver(mazeToSolve2);

        System.out.println("Original Maze:");
        solver.printMaze();

        // should print solution found
        if (solver.solve()) {
            System.out.println("Solution Found:");
        } else {
            System.out.println("No Solution Found:");
        }
        solver.printMaze();

        System.out.println("Original Maze:");
        solver2.printMaze();

        //should print no solution found
        if (solver2.solve()) {
            System.out.println("Solution Found:");
        } else {
            System.out.println("No Solution Found:");
        }
        solver2.printMaze();
    }
}