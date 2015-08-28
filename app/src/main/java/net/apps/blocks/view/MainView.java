package net.apps.blocks.view;

import android.view.View;
import android.widget.GridView;

public interface MainView {

    void showMessage(String message);

    GridView getGvMatrix();

    void updateGvMatrix();

    View getGvElement(int position);

    boolean updateTimer();

    void resetTimer();

}
