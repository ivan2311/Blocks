package net.apps.blocks.view;

import android.widget.GridView;

public interface MainView {

    void showMessage(String message);

    GridView getGvMatrix();

    void updateGvMatrix();
}
