import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/* Class to create a window representing a maze. */
public class MazeRenderer extends JFrame {

    /* serial versioning number to make checkstyle happy. */
    private static final long serialVersionUID = 1L;

    /* the initial length and width for this JFrame. */
    private static final int INIT_SIZE = 500;

    /* the maze this window will represent. */
    private Maze maze;

    /* the frame into which we will place tiles to represent cells. */
    private GridLayout grid;

    /*
     * Constructor which makes the basic window to hold a maze.
     * @param theMaze the maze this window will hold
     */
    public MazeRenderer(Maze theMaze) {
        super("Maze Renderer");
        this.maze = theMaze;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(INIT_SIZE, INIT_SIZE);
        this.grid = new GridLayout(this.maze.getNumRows(),
                this.maze.getNumCols());
        this.setLayout(this.grid);
    }

    /* Place correct tiles into window and make this item visible. */
    public void createAndShowGUI() {
        for (int row = 0; row < this.maze.getNumRows(); row++) {
            for (int col = 0; col < this.maze.getNumCols(); col++) {
                this.add(new MyJLabel(this.findCorrectTilePath(
                        this.maze.getCellAt(row, col))));
            }
        }
        this.setVisible(true);
    }

    /*
     * Determine path for correct tile to represent a particular cell.
     * Image path names include letters for those directions in which
     * a wall has been removed.
     * @param cell the cell for whose tile the path is needed
     * @return the String representing the appropriate file path for
     * this particular cell
     */
    private String findCorrectTilePath(Cell cell) {

        String code = "";
        if (!cell.hasNorth())
            code += "n";

        if (!cell.hasSouth())
            code += "s";

        if (!cell.hasEast())
            code += "e";

        if (!cell.hasWest())
            code += "w";

        if (code.equals(""))
            code = "0";

        return "tiles/maze_" + code + ".png";
    }

    /* Class defining the label objects that are placed into grid. */
    private class MyJLabel extends JLabel {

        /* serial versioning number to make checkstyle happy. */
        private static final long serialVersionUID = 1L;

        /* the image that this JLabel will display. */
        ImageIcon imageIcon;

        /*
         * Construct a new JLabel that will hold a single cell.
         * @param path where to find the image to display
         */
        public MyJLabel(String path) {
            super();
            this.imageIcon = new ImageIcon(path);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.imageIcon.getImage(), 0, 0,
                    getWidth(), getHeight(), this);
        }
    }

}
