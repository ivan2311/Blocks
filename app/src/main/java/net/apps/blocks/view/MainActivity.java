package net.apps.blocks.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.apps.blocks.model.matrix.Matrix;
import net.apps.blocks.R;
import net.apps.blocks.presenter.MainPresenter;
import net.apps.blocks.presenter.MainPresenterImpl;

public class MainActivity extends Activity implements MainView {

    private GridView gvMatrix;
    private ProgressBar timer;
    private MainPresenter presenter;

    private Button btnHint;
    private Button btnRemove;
    private Button btnConnect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);



        gvMatrix = (GridView) findViewById(R.id.gvMatrix);

        gvMatrix.setAdapter(new ElementAdapter(this, presenter.getMatrix()));
        gvMatrix.setNumColumns(presenter.getMatrix().getNumCols());

        gvMatrix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(parent, view, position, id);
            }
        });

        timer = (ProgressBar) findViewById(R.id.timer);


        btnHint = (Button) findViewById(R.id.btn_hint);
        btnRemove = (Button) findViewById(R.id.btn_remove);
        btnConnect = (Button) findViewById(R.id.btn_connect);

        btnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onHintClick();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRemoveClick();
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onConnectClick();
            }
        });

        presenter.onCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public GridView getGvMatrix() {
        return gvMatrix;
    }

    @Override
    public void updateGvMatrix() {
        Matrix matrix = presenter.getMatrix();
        ElementAdapter elementAdapter = (ElementAdapter)gvMatrix.getAdapter();
        elementAdapter.setMatrix(matrix);
        gvMatrix.invalidateViews();
        gvMatrix.setAdapter(elementAdapter);
    }

    @Override
    public View getGvElement(int position) {
        return gvMatrix.getChildAt(position);
    }

    @Override
    public boolean updateTimer() {
        int progress = timer.getProgress() + 1;
        timer.setProgress(progress);
        return timer.getMax() == progress;
    }

    @Override
    public void resetTimer() {
        timer.setProgress(0);
    }
}
