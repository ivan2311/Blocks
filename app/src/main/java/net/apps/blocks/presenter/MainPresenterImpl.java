package net.apps.blocks.presenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
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
import net.apps.blocks.view.UIUpdater;

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
    private boolean illegalConnect;

    private int numOfStage;


    private UIUpdater uiUpdater;

    public MainPresenterImpl(final MainView mainView) {
        this.mainView = mainView;
        numOfStage = 0;
        secondClick = false;
        illegalConnect = false;
        pos1 = -1;
        pos2 = -1;
        setMatrix();

        uiUpdater = new UIUpdater(new Runnable() {
            @Override
            public void run() {
                if (mainView.updateTimer()) {
                    mainView.showMessage("GAME OVER");
                    stopTimer();
                    restartStage();
                }
            }
        }, 50);
    }

    private void startTimer() {
        uiUpdater.startUpdates();
    }

    private void stopTimer() {
        uiUpdater.stopUpdates();
    }

    private void resetTimer() {
        mainView.resetTimer();
    }



    public int getNumOfStage() {
        return numOfStage;
    }

    public void setNumOfStage(int numOfStage) {
        if (this.numOfStage == numOfStage) return;
        this.numOfStage = numOfStage;
        setMatrix();
    }

    public void nextStage() {
        numOfStage++;
        if (!setMatrix()) {
            numOfStage = 0;
            setMatrix();
        }
        mainView.updateGvMatrix();
        resetTimer();
        startTimer();
    }

    private void restartStage() {
        setMatrix();
        mainView.updateGvMatrix();
        resetTimer();
        startTimer();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        startTimer();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (matrix.getStatusElementAtPosition(position) != Element.STATUS_FULL) {
            return;
        }

        view.setAlpha(VISIBILITY_HALF);

        if (secondClick) {
            pos2 = position;
            checkConnection(illegalConnect);
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

    private void checkConnection(boolean illegalConnect) {
        List<String> directions = matrix.getPath(pos1, pos2, illegalConnect);
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
                    nextStage();
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
        illegalConnect = false;
    }

    private boolean setMatrix() {
        Stage stage = StageProvider.getStage(numOfStage);
        if (stage != null) {
            matrix = stage.getMatrix();
            return true;
        }
        return false;
    }


    @Override
    public void onHintClick() {
        int count = matrix.getCount();
        for (int pos1 = 0; pos1 < count; pos1++) {
            int pos2 = matrix.getPair(pos1);
            if (pos2 >= 0) {
                animateHint(pos1);
                animateHint(pos2);
                return;
            }

        }
        mainView.showMessage("No legal connection!");

    }

    private void animateHint(int pos) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mainView.getGvElement(pos), "alpha", VISIBILITY_COMPLETE, VISIBILITY_HALF);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mainView.getGvElement(pos), "alpha", VISIBILITY_HALF, VISIBILITY_COMPLETE);
        set.play(fadeIn).after(1000).after(fadeOut);
        set.start();
    }

    @Override
    public void onRemoveClick() {
        List<Integer> positions = matrix.getElementPositionsByRandomType();
        for (int pos : positions) {
            removeElement(pos);
        }
    }

    @Override
    public void onConnectClick() {
        illegalConnect = true;
    }
}
