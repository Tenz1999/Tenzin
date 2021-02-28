/* A class to represent the walls inside a maze. */
public class Wall {

    /*
     * The row preceding the wall.
     */
    private int row;

    /*
     * The column preceding the wall.
     */
    private int col;

    /*
     * A char representing the orientation of the wall.
     */
    private char orientation;

    /*
     * A constructor for a Wall object.
     *
     * @param r      the row preceding the wall
     * @param c      the column preceding the wall
     * @param orient a char representing the orientation of the wall
     */
    public Wall(int r, int c, char orient) {
        this.row = r;
        this.col = c;
        this.orientation = orient;
    }

    /*
     * Returns the row of the wall.
     *
     * @return the row of the wall
     */
    public int getRow() {
        return this.row;
    }

    /*
     * Returns the column of the wall.
     *
     * @return the column of the wall
     */
    public int getCol() {
        return this.col;
    }

    /*
     * Returns the orientation of the wall.
     *
     * @return the orientation of the wall
     */
    public char getOrientation() {
        return this.orientation;
    }

}
