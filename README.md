import java.util.Random;


/* A class to implement a maze. */
public class Maze {

    /* The maze. */
    private Cell[][] maze;

    /* A random number generator. */
    private Random randNum;

    /* An array of all of the walls in the maze. */
    private Wall[] walls;

    /*
     * Constructor for the Maze.
     * @param generator a random number generator
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public Maze(Random generator, int rows, int cols) {

        /* This is equivalent to 4 * AREA - PERIMETER. */
        int numWalls = 2 * rows * cols - rows - cols;

        this.maze = new Cell[rows][cols];
        this.walls = new Wall[numWalls];
        this.randNum = generator;

        int num = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.maze[i][j] = new Cell();
                if (i < rows - 1) {
                    this.walls[num++] = new Wall(i, j, 'h');
                }
                if (j < cols - 1) {
                    this.walls[num++] = new Wall(i, j, 'v');
                }
            }
        }

    }

    /*
     * Generate the maze.  This takes a constructed Maze object with
     * all of its walls present, and randomly removes interior walls until all 
     * cells of the maze are reachable from all other cells of the maze. 
     * Also removes the west wall of the top left corner cell (for a maze start)
     * and the east wall of the bottom right corner cell for a maze finish line.
     */
    public void generateMaze() {

        int rows = this.getNumRows();
        int cols = this.getNumCols();

        /* Sets a starting point and ending point in the maze. */
        this.maze[0][0].setWest(false);
        this.maze[rows - 1][cols - 1].setEast(false);

        /* The number of cells in the maze. */
        int numCells = rows * cols;

        /* UnionFind object. */
        UnionFind unions = new UnionFind(numCells);

        /* While there are more than two subsets in the maze, remove walls. */
        while (unions.getNumSubsets() > 1) {

            /* Find a random wall in the array of walls. */
            Wall randWall = this.walls[this.randNum.nextInt(this.walls.length)];

            int row = randWall.getRow();
            int col = randWall.getCol();
            int aCell = row * cols + col;

            /* orientation is horizontal. */
            if (randWall.getOrientation() == 'h') {

                int bCell = (row + 1) * cols + col;

                /* If there is no path between the cells, knock down a wall. */
                if (unions.find(aCell) != unions.find(bCell)) {
                    this.maze[row][col].setSouth(false);
                    this.maze[row + 1][col].setNorth(false);
                    unions.union(aCell, bCell);
                }

            } else /* orientation is vertical */ {

                int bCell = row * cols + col + 1;

                /* If there is no path between the cells, knock down a wall. */
                if (unions.find(aCell) != unions.find(bCell)) {
                    this.maze[row][col].setEast(false);
                    this.maze[row][col + 1].setWest(false);
                    unions.union(aCell, bCell);
                }

            }
        }
    }

    /*
     * Return the Cell object stored at the given (row, column) position.
     * @param r the row position of the Cell in the Maze object
     * @param c the col position of the Cell in the Maze object
     * @return the Cell object that is at the specified position
     */
    public Cell getCellAt(int r, int c) {
        return this.maze[r][c];
    }

    /*
     * Set the cell at the given (row, column) position to the provided cell.
     * @param r the row position of the new Cell in the maze
     * @param c the column position of the new Cell in the maze
     * @param cell the new Cell object to be set in the specified position
     */
    public void setCellAt(int r, int c, Cell cell) {
        this.maze[r][c] = cell;
    }

    /*
     * Return the number of rows in the maze.
     * @return the number of rows in the maze
     */
    public int getNumRows() {
        return this.maze.length;
    }

    /*
     * Return the number of columns in the maze.
     * @return the number of columns in the maze
     */
    public int getNumCols() {
        return this.maze[0].length;
    }


}
