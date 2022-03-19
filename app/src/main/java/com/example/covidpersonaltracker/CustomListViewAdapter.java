package com.example.covidpersonaltracker;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomListViewAdapter extends BaseAdapter {

    Activity activity;
    List<CovidDataModel> covidDataModel;
    public CustomListViewAdapter(){

    }

    public CustomListViewAdapter(Activity activity, List<CovidDataModel> covidDataModel) {
        this.activity=activity;
        this.covidDataModel=covidDataModel;
    }

    @Override
    public int getCount() {
        return covidDataModel.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= activity.getLayoutInflater().inflate(R.layout.listview_row,null,false);
        TextView txtTemp=view.findViewById(R.id.txtTemp);
        txtTemp.setText(covidDataModel.get(position).getTemp());
        Button btnDelete=view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidDataModel.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    public  void addDataNode(CovidDataModel data){
        this.covidDataModel.add(data);
        notifyDataSetChanged();
    }
}
