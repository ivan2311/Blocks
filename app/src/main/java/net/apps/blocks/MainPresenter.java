package net.apps.blocks;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Korisnik on 8/25/2015.
 */
public interface MainPresenter {

    public void onCreate(Bundle savedInstanceState);

    public void onItemClick(AdapterView<?> parent, View view, int position, long id);

    public Matrix getMatrix();

}
