package net.apps.blocks.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import net.apps.blocks.R;
import net.apps.blocks.model.matrix.Matrix;

public class ElementAdapter extends BaseAdapter {

    private Context context;
    private Matrix matrix;

    public ElementAdapter(Context context, Matrix matrix) {
        this.context = context;
        this.matrix = matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public int getCount() {
        return matrix.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vElement;

        if (convertView == null) {
            vElement = inflater.inflate(R.layout.element, null);
            ImageView ivElement = (ImageView) vElement.findViewById(R.id.ivElement);
            ivElement.setImageResource(matrix.getElementAtPosition(position).getResource());
        } else {
            vElement = convertView;
        }

        return vElement;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return matrix.getElementAtPosition(position);
    }
}
