package com.ymrabti.listview.laptop;

import android.content.Context;
import android.widget.ArrayAdapter;


import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ymrabti.listview.R;
import com.ymrabti.listview.laptop.Laptop;

public class LaptopListAdapter extends ArrayAdapter<Laptop> {

    private ArrayList<Laptop> listApps;
    private Context mContext;

    LaptopListAdapter(Context context, ArrayList<Laptop> listClo)
    {
        super(context ,0,listClo);this.listApps= listClo;this.mContext = context;
    }
    static class ViewHolder{
        TextView title;
    }
    @Override
    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.title =  convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Laptop Clocourant = listApps.get(position);
        holder.title.setText(Clocourant.getBrand());
        return convertView;
    }

    @Override
    public void add(Laptop app) {
        listApps.add(app);
        notifyDataSetChanged();
        Toast.makeText(mContext, app.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void remove(Laptop object) {
         super.remove(object);
        listApps.remove(object);
        notifyDataSetChanged();
    }
/*
 public List<Laptop> getLaptops() {
        return listApps;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }*/

}
