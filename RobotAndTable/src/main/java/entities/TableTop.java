package entities;


public class TableTop {

    private final static int X_AXIS_FOR_TABLE = 4;
    private final static int Y_AXIS_FOR_TABLE = 4;
    private final static int MIN_X_AXIS_FOR_TABLE = 0;
    private final static int MIN_Y_AXIS_FOR_TABLE = 0;

    private int height;
    private int width;
    private int minHeight;
    private int minWidth;

    public TableTop() {
        height = Y_AXIS_FOR_TABLE;
        width = X_AXIS_FOR_TABLE;
        minHeight = MIN_X_AXIS_FOR_TABLE;
        minWidth = MIN_Y_AXIS_FOR_TABLE;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMinWidth() {
        return minWidth;
    }

}
