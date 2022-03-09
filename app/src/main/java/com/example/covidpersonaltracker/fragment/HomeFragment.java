package com.example.covidpersonaltracker.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidpersonaltracker.DBHelper;
import com.example.covidpersonaltracker.R;


public class HomeFragment extends Fragment {
    DBHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new DBHelper(getContext());
//        boolean flag=db.insertData("101 F","2022/01/02","NA");
//        Log.d("FLAG : ",flag+"");
//        flag=db.insertData("99 F","2022/11/02","NA");
//        Log.d("FLAG : ",flag+"");
//        flag=db.insertData("102 F","2022/01/22","NA");
//        Log.d("FLAG : ",flag+"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}