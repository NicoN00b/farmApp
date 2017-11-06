package com.epicodus.pdxfarmshare.ui;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.Location;

import com.epicodus.pdxfarmshare.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import static com.epicodus.pdxfarmshare.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    private CameraPosition mCameraPosition;

//    GeoJsonLayer layer = new GeoJsonLayer();

//    protected GeoDataClient mGeoDataClient;
//    private PlaceDetectionClient mPlaceDetectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        mGeoDataClient = Places.getGeoDataClient(this, null);
//
//        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
//
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng oregon = new LatLng(45.3, -122);
        mMap.addMarker(new MarkerOptions().position(oregon).title("Marker in Oregon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oregon));
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(map)).getMapAsync(this);
    }

    protected GoogleMap getMap() {
        return mMap;
    }
}
