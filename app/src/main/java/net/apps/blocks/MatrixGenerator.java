package net.apps.blocks;

/**
 * Created by Korisnik on 8/24/2015.
 */
public class MatrixGenerator {

    private static final int[] drawables = {
            R.drawable.square_blue,
            R.drawable.square_green,
            R.drawable.square_blue,
            R.drawable.square_yellow,
            R.drawable.square_red,
            R.drawable.square_red,
            R.drawable.square_yellow,
            R.drawable.square_green,
            R.drawable.square_red,
            R.drawable.square_blue,
            R.drawable.square_red,
            R.drawable.square_yellow,
            R.drawable.square_green,
            R.drawable.square_yellow,
            R.drawable.square_green,
            R.drawable.square_blue
    };

    public static Matrix randomMatrix(int rows, int cols) {

        Matrix matrix = new Matrix(rows, cols);
        int i=0;
        for (int row=0; row<rows; row++) {
            for (int col=0; col<cols; col++) {
                Element element = new Element(drawables[i++]);
                matrix.setElement(row, col, element);
            }
        }
        return matrix;
    }
}
