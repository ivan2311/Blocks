package net.apps.blocks.presenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import net.apps.blocks.model.Element;
import net.apps.blocks.model.stage.Stage;
import net.apps.blocks.view.MainView;
import net.apps.blocks.model.matrix.Matrix;
import net.apps.blocks.model.stage.StageProvider;

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

    private int numOfStage;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        numOfStage = 0;
        secondClick = false;
        pos1 = -1;
        pos2 = -1;
        setMatrix();
    }

    public int getNumOfStage() {
        return numOfStage;
    }

    public void setNumOfStage(int numOfStage) {
        if (this.numOfStage == numOfStage) return;
        this.numOfStage = numOfStage;
        setMatrix();
    }

    public boolean nextStage() {
        numOfStage++;
        if (StageProvider.hasStage(numOfStage)) {
            setMatrix();
            mainView.updateGvMatrix();
            return true;
        }
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (matrix.getStatusElementAtPosition(position) != Element.STATUS_FULL) {
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
        if (directions.isEmpty()) {
            mainView.showMessage("ILLEGAL!!!");
        } else {
            removeElements();
        }
    }

    private void removeElements() {
        removeElement(pos1);
        removeElement(pos2);
    }

    private void removeElement(final int pos) {
        GridView gvMatrix = mainView.getGvMatrix();

        View gvElement = gvMatrix.getChildAt(pos);



        ObjectAnimator anim = ObjectAnimator.ofFloat(gvElement, "alpha", VISIBILITY_HALF, VISIBILITY_NONE);
        anim.setDuration(500);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                matrix.setStatusElementAtPosition(pos, Element.STATUS_EMPTY);
                if (matrix.isFinished()) {
                    if (!nextStage()) {
                        mainView.showMessage("FINISHED!!!");
                    }
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                matrix.setStatusElementAtPosition(pos, Element.STATUS_PENDING);
            }
        });

        anim.start();

    }

    private void resetState() {
        GridView gvMatrix = mainView.getGvMatrix();
        if (pos1 >= 0) {
            View gvElement = gvMatrix.getChildAt(pos1);
            gvElement.setAlpha(VISIBILITY_COMPLETE);
        }
        if (pos2 >= 0) {
            View gvElement = gvMatrix.getChildAt(pos2);
            gvElement.setAlpha(VISIBILITY_COMPLETE);
        }
        secondClick = false;
        pos1 = -1;
        pos2 = -1;
    }

    private void setMatrix() {
        Stage stage = StageProvider.getStage(numOfStage);
        if (stage != null) {
            matrix = stage.getMatrix();
        }
    }
}