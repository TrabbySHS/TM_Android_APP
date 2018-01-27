package com.example.kamil.fragmentstest;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CArrayListAdapter extends ArrayAdapter{
    private ArrayList<TaskModel> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView txtTitle;
        TextView txtPriority;
        TextView txtDate;
    }

     public CArrayListAdapter(ArrayList<TaskModel> list, Context context) {
        super(context, R.layout.array_list_adapter, list);
        this.dataSet = list;
        this.mContext=context;

    }

    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        TaskModel taskModel=(TaskModel) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TaskModel taskModel = dataSet.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.array_list_adapter, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.taskName);
            viewHolder.txtPriority = (TextView) convertView.findViewById(R.id.priority);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.date);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtTitle.setText(taskModel.getTitle());
        viewHolder.txtPriority.setText(""+taskModel.getPriority());
        viewHolder.txtDate.setText(""+taskModel.data);
        // Return the completed view to render on screen
        return convertView;
    }

}
