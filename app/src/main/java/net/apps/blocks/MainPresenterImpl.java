package net.apps.blocks;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Korisnik on 8/25/2015.
 */
public class MainPresenterImpl implements MainPresenter {

    private int[] drawables = {
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

    private MainView mainView;

    private Matrix matrix;

    private int pos1;
    private int pos2;
    private ImageView ivElement1;
    private ImageView ivElement2;

    private boolean secondClick;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        matrix = MatrixGenerator.randomMatrix(4, 4);
        resetState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ImageView ivElement = (ImageView)view.findViewById(R.id.ivElement);

        //ivElement.setAlpha(0.4f);

        if (secondClick) {
            pos2 = position;
            checkConnection();
            resetState();
        } else {
            pos1 = position;
            ivElement1 = ivElement;
            secondClick = true;
        }
    }

    @Override
    public Matrix getMatrix() {
        return matrix;
    }

    private void checkConnection() {

        List<String> directions = matrix.getPath(pos1, pos2);

        StringBuilder sb = new StringBuilder();
        for (String dir : directions) {
            sb.append(dir);
            sb.append(" ");
        }

        mainView.showMessage(sb.toString());
    }

    private void resetState() {
        secondClick = false;
        pos1 = -1;
        pos2 = -1;
    }
}
