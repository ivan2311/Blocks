package net.apps.blocks;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public interface MainPresenter {

    void onCreate(Bundle savedInstanceState);

    void onItemClick(AdapterView<?> parent, View view, int position, long id);

    Matrix getMatrix();

}
