package net.apps.blocks.model.matrix.generator;

import net.apps.blocks.R;
import net.apps.blocks.model.matrix.generator.MatrixGenerator;

public class SquaresInRectangleMatrixGenerator extends MatrixGenerator {

    private static final int NUM_OF_ROWS = 5;
    private static final int NUM_OF_COLS = 4;
    private static final int NUM_OF_PAIRS = 2;
    private static final int[] DRAWABLES = {
            R.drawable.square_blue,
            R.drawable.square_green,
            R.drawable.square_yellow,
            R.drawable.square_red,
            R.drawable.square_grey
    };

    @Override
    public int[] getDrawables() {
        return DRAWABLES;
    }

    @Override
    public int getNumOfPairs() {
        return NUM_OF_PAIRS;
    }

    @Override
    public int getNumOfRows() {
        return NUM_OF_ROWS;
    }

    @Override
    public int getNumOfCols() {
        return NUM_OF_COLS;
    }

    @Override
    public int[] getTypes() {
        return DRAWABLES;
    }
}
