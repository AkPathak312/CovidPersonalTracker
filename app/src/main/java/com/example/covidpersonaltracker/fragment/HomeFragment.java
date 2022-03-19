package com.example.covidpersonaltracker.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.covidpersonaltracker.BottomSheet;
import com.example.covidpersonaltracker.CovidDataModel;
import com.example.covidpersonaltracker.CustomListViewAdapter;
import com.example.covidpersonaltracker.DBHelper;
import com.example.covidpersonaltracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class HomeFragment extends Fragment {
    DBHelper db;
    ListView listView;
    CustomListViewAdapter customListViewAdapter;
    FloatingActionButton add;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DBHelper(getContext());
        customListViewAdapter=new CustomListViewAdapter(getActivity(),db.fetchData());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView=inflater.inflate(R.layout.fragment_home, container, false);
        listView=fragmentView.findViewById(R.id.list_view);
        add=fragmentView.findViewById(R.id.addTemp);
        add.setOnClickListener(v->{
            BottomSheet bottomSheet=new BottomSheet();
            bottomSheet.show(getParentFragmentManager(),"BottomSheet");
        });
        listView.setAdapter(customListViewAdapter);
        return fragmentView;
    }
}