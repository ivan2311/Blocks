package net.apps.blocks;

import android.widget.GridView;

public interface MainView {

    void showMessage(String message);

    GridView getGvMatrix();

    void reset();
}
