package sg.edu.rp.c347.p08locatingaplace;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    Spinner spn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn = findViewById(R.id.spinnerArea);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_CausewayPoint = new LatLng(1.290270, 103.851959);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint,
                        10));

                LatLng poi_north = new LatLng(1.4650972, 103.802653);
                final Marker cp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ North")
                        .snippet("Block 333, Admiralty Ave 3,765544," +
                                "\nOperating hours: 10am-5pm"+"\nTel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.staricon)));


                LatLng poi_central = new LatLng(1.3073877, 103.7956812);
                final Marker cpcentral = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_east = new LatLng(1.3533249, 103.9514914);
                final Marker cpeast = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
                ui.setMapToolbarEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            0);
                }


                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.equals(cp)) {
                            Toast.makeText(MainActivity.this,cp.getTitle(),Toast.LENGTH_LONG).show();
                        } if (marker.equals(cpcentral)) {
                            Toast.makeText(MainActivity.this,cpcentral.getTitle(),Toast.LENGTH_LONG).show();
                        }
                        if (marker.equals(cpeast)) {
                            Toast.makeText(MainActivity.this,cpeast.getTitle(),Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                });

                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (map != null) {
                            if (position == 0) {


                                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                LatLng poi_north = new LatLng(1.4650972, 103.802653);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                                        18));


                            } else if (position == 1) {

                                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                LatLng poi_central = new LatLng(1.3073877, 103.7956812);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 18));


                            } else if (position == 2) {

                                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                LatLng poi_east = new LatLng(1.3533249, 103.9514914);
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                                        18));


                            }

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
        });










        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng poi_north = new LatLng(1.4650972, 103.802653);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                            18));

                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng poi_central = new LatLng(1.3073877, 103.7956812);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                            18));

                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng poi_east = new LatLng(1.3533249, 103.9514914);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                            18));
                }
            }
        });
    }
}
