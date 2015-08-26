package net.apps.blocks;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {

    private static final float VISIBILITY_COMPLETE = 1f;
    private static final float VISIBILITY_HALF = 0.5f;
    private static final float VISIBILITY_NONE = 0f;

    private MainView mainView;

    private Matrix matrix;

    private int pos1;
    private int pos2;

    private boolean secondClick;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (matrix.isEmptyElementAtPosition(position)) {
            return;
        }

        view.setAlpha(VISIBILITY_HALF);

        if (secondClick) {
            pos2 = position;
            checkConnection();
            resetState();
        } else {
            pos1 = position;
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
        if (directions.isEmpty()) {
            sb.append("ILLEGAL!!!");
        } else {
            for (String dir : directions) {
                sb.append(dir);
                sb.append(" ");
            }
            removeElements();
        }
        mainView.showMessage(sb.toString());
    }

    private void removeElements() {
        removeElement(pos1);
        removeElement(pos2);
    }

    private void removeElement(int pos) {
        GridView gvMatrix = mainView.getGvMatrix();

        View gvElement = gvMatrix.getChildAt(pos);

        matrix.setEmptyElementAtPosition(pos, true);

        ObjectAnimator anim = ObjectAnimator.ofFloat(gvElement, "alpha", VISIBILITY_HALF, VISIBILITY_NONE);
        anim.setDuration(500);
        anim.start();
    }

    private void resetState() {
        GridView gvMatrix = mainView.getGvMatrix();
        if (pos1>=0) {
            View gvElement = gvMatrix.getChildAt(pos1);
            gvElement.setAlpha(VISIBILITY_COMPLETE);
        }
        if (pos2>=0) {
            View gvElement = gvMatrix.getChildAt(pos2);
            gvElement.setAlpha(VISIBILITY_COMPLETE);
        }
        secondClick = false;
        pos1 = -1;
        pos2 = -1;
    }

    private void init() {
        secondClick = false;
        pos1 = -1;
        pos2 = -1;
        matrix = MatrixGenerator.randomMatrix(4, 4);
    }
}
