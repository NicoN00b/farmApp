//package com.epicodus.pdxfarmshare.ui;
//
//import android.nfc.Tag;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.Toast;
//import com.google.maps.android.data.Feature;
//import com.google.maps.android.data.geojson.GeoJsonFeature;
//import com.google.maps.android.data.geojson.GeoJsonLayer;
//import com.google.maps.android.data.geojson.GeoJsonPointStyle;
//import com.epicodus.pdxfarmshare.R;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.maps.android.*;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//
//
//public class GeoJsonActivity extends MapsActivity {
//
//
//    protected int getLayoutId() {
//        return R.layout.activity_geo_json;
//    }
//
//    private class DownloadGeoJsonFile extends AsyncTask<String, Void, GeoJsonActivity> {
//        @Override
//        protected GeoJsonActivity doInBackground(String... params) {
//            try {
//                // Open a stream from the URL
//                InputStream stream = new URL(params[0]).openStream();
//
//                String line;
//                StringBuilder result = new StringBuilder();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
//                while ((line = reader.readLine()) != null) {
//                    // Read and save each line of the stream
//                    result.append(line);
//                }
//
//                // Close the stream
//                reader.close();
//                stream.close();
//
//                return new GeoJsonActivity(getMap(), new JSONObject(result.toString()));
//            } catch (IOException e) {
//                Log.e(Tag,"GeoJSON file could not be read");
//            } catch (JSONException e) {
//                Log.e(Tag, "GeoJSON file could not be converted to a JSONObject");
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(GeoJsonActivity layer) {
//            if (layer != null) {
//                addGeoJsonLayerToMap(layer);
//            }
//        }
//    }
//
//
//    private void addGeoJsonLayerToMap(GeoJsonActivity layer) {
//
//        layer.addLayerToMap();
//        getMap().moveCamera(CameraUpdateFactory.newLatLng(new LatLng(31.4118,-103.5355)));
//        // Demonstrate receiving features via GeoJsonActivity clicks.
//        layer.setOnFeatureClickListener(new GeoJsonActivity.GeoJsonOnFeatureClickListener() {
//            @Override
//            public void onFeatureClick(Feature feature) {
//                Toast.makeText(GeoJsonActivity.this,
//                        "Feature clicked: " + feature.getProperty("title"),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//    }
//}
