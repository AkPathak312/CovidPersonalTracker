package com.example.covidpersonaltracker.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.covidpersonaltracker.BottomSheet;
import com.example.covidpersonaltracker.CovidDataModel;
import com.example.covidpersonaltracker.CustomListViewAdapter;
import com.example.covidpersonaltracker.DBHelper;
import com.example.covidpersonaltracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment {
    DBHelper db;
    TextView currentLocation;
    ListView listView;
    CustomListViewAdapter customListViewAdapter;
    FloatingActionButton add;
    LocationManager locationManager;
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
        currentLocation=fragmentView.findViewById(R.id.txtCurrentLocation);
        try {
            getLocation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        add.setOnClickListener(v->{
            BottomSheet bottomSheet=new BottomSheet();
            bottomSheet.show(getParentFragmentManager(),"BottomSheet");
        });
        listView.setAdapter(customListViewAdapter);
        return fragmentView;
    }

    public  void getLocation() throws IOException {
        if(ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
        &&ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            //Ask for Permission
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},101);
        }else{
            //get the location
            Log.d("LOCATION","inside loc");
            locationManager= (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location!=null){
                double latt=location.getLatitude();
                double longit=location.getLongitude();
                Geocoder geocoder=new Geocoder(getActivity(), Locale.getDefault());
                List<Address> addresses =geocoder.getFromLocation(latt,longit,1);
                String city=addresses.get(0).getAddressLine(0);
                String state=addresses.get(0).getAddressLine(1);
                String country=addresses.get(0).getAddressLine(2);
                currentLocation.setText(city+" "+state+" "+country);
            }
        }

    }

}