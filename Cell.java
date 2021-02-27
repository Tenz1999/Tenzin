/* A cell that represents a single tile of the maze. */
public class Cell {

    /* whether or not wall on north side exists. */
    private boolean north;

    /* whether or not wall on south side exists. */
    private boolean south;

    /* whether or not wall on east side exists. */
    private boolean east;

    /* whether or not wall on west side exists. */
    private boolean west;

    /* Cell constructor. Creates four walls by default. */
    public Cell() {
        this.north = true;
        this.south = true;
        this.west = true;
        this.east = true;
    }

    /*
     * Cell constructor with defined wall parameters.
     * @param n true if north end of the tile has a wall
     * @param s true if south end of the tile has a wall
     * @param w true if west end of the tile has a wall
     * @param e true if east end of the tile has a wall
     */
    public Cell(boolean n, boolean s, boolean e, boolean w) {
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
    }

    /*
     * Return whether this cell's north wall exists.
     * @return true if and only if the north wall exists
     */
    public boolean hasNorth() {
        return this.north;
    }

    /*
     * Return whether this cell's south wall exists.
     * @return true if and only if the south wall exists
     */
    public boolean hasSouth() {
        return this.south;
    }

    /*
     * Return whether this cell's west wall exists.
     * @return true if and only if the west wall exists
     */
    public boolean hasWest() {
        return this.west;
    }

    /*
     * Return whether this cell's east wall exists.
     * @return true if and only if the east wall exists
     */
    public boolean hasEast() {
        return this.east;
    }

    /*
     * Indicate whether this cell's north wall should exist.
     * @param northVal  true if wall exists; false otherwise
     */
    public void setNorth(boolean northVal) {
        this.north = northVal;
    }

    /*
     * Indicate whether this cell's south wall should exist.
     * @param southVal  true if wall exists; false otherwise
     */
    public void setSouth(boolean southVal) {
        this.south = southVal;
    }

    /*
     * Indicate whether this cell's west wall should exist.
     * @param westVal true if wall exists; false otherwise
     */
    public void setWest(boolean westVal) {
        this.west = westVal;
    }

    /*
     * Indicate whether this cell's east wall should exist.
     * @param eastVal  true if wall exists; false otherwise
     */
    public void setEast(boolean eastVal) {
        this.east = eastVal;
    }



}
