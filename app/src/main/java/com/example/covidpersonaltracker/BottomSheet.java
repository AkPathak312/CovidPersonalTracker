package com.example.covidpersonaltracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BottomSheet  extends BottomSheetDialogFragment {
    TextView datetime;
    Button btnSave;
    EditText edtTemp;
    DBHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db=new DBHelper(getActivity());
        View view=inflater.inflate(R.layout.bottom_sheet,container,false);
        datetime=view.findViewById(R.id.txtDateTime);
        datetime.setText(LocalDateTime.now().toString());
        btnSave=view.findViewById(R.id.btnSave);
        edtTemp=view.findViewById(R.id.edtTemp);
        btnSave.setOnClickListener(v->{
            CovidDataModel covidDataModel=new CovidDataModel(0,edtTemp.getText().toString(),LocalDateTime.now().toString(),"NA");
            db.insertData(edtTemp.getText().toString(),LocalDateTime.now().toString(),"NA");
            CustomListViewAdapter customListViewAdapter=new CustomListViewAdapter();
            customListViewAdapter.addDataNode(covidDataModel);
            dismiss();
        });
        return view;
    }
}
