package net.apps.blocks.presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import net.apps.blocks.model.matrix.Matrix;

public interface MainPresenter {

    void onCreate(Bundle savedInstanceState);

    void onItemClick(AdapterView<?> parent, View view, int position, long id);

    Matrix getMatrix();

    void onHintClick();
    void onRemoveClick();
    void onConnectClick();
}
