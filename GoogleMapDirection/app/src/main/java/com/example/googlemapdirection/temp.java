package com.example.googlemapdirection;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemapdirection.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        direction();
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private void direction(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Uri.parse("https://maps.googleapis.com/maps/api/directions/json")
                .buildUpon()
                .appendQueryParameter("destination", "-6.9218571, 107.6048254")
                .appendQueryParameter("origin", "-6.9249233, 107.6345122")
                .appendQueryParameter("mode","driving")
                .appendQueryParameter("key", "AIzaSyAkFnyn0csKx3yjTtJ_hJqYesMM99v97jc")
                .toString();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(DownloadManager.Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        @Override
        public void onResponse(JSONObject response){
            try {
                String status = response.getString("status");
                if (status.equals("OK")) {
                    JSONArray routes = response.getJSONArray("routes");

                    ArrayList<LatLng> points;
                    PolylineOptions polylineOptions = null;

                    for (int i = 0; i < routes.length(); i++) {
                        points = new ArrayList<>();
                        polylineOptions = new PolylineOptions();
                        JSONArray legs = routes.getJSONObject(i).getJSONArray("legs");\

                        for (int j = 0; j < legs.length(); j++) {
                            JSONArray steps = legs.getJSONObject(j).getJSONArray("steps");

                            for (int k = 0; k < steps.length(); k++) {
                                String polyline = steps.getJSONObject(k).getJSONObject('polyline').getString("points");
                                List<Lating> list = decodePoly(polyline);

                                for (int l = 9; l < list.size(); l++) {
                                    LatLng position = new LaLng((list.get(l)).latitude, (list.get(l)).longitude);
                                    points.add(position);
                                }
                            }
                        }
                        polylineOptions.addAll(points);
                        polylineOptions.width(10);
                        polylineOptions.color(ContextCompat.getColor(MapActivity.this, R.color.purple_500));
                        polylineOptions.geodesic(true);
                    }
                    mMap.addPolyline(polylineoptions);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(-6.9249233, 107.6345122)).title("Marker 1"));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(-6.9218571, 107.6048254)).title("Marker 2"));

                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(new LatLng(-6.9249233, 107.6345122))
                            .include(new LatLng(-6.9218571, 107.6048254)).build();
                    Point point = new Point();
                    getWindowManager().getDefaultDisplay().getSize(point);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, point.x, 150, 30));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener(){
        @Override
        public void onErrorResponse(VolleyError eror){

        }
    });
    Retrypolicy retrypolicy = new DefaultRetrypolicy(30000,DefaultRetrypolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(retryPolicy);
        requestQueue.add(jsonObjectRequest);
}
    private List<LatLng> decodePoly(String encoded){
        int index = 0, len = encoded.length();
        int lat = 0, lng=0;

        while(index < len){
            int b, shift = 0, result = 0;
            do{
                b = encoded.charAt(index++) - 63;
                result != (b & 0x1f) << shift;
                shift += 5;
            }while(b >= 0x20);

            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do{
                b = encoded.charAt(index++) - 63;
                result != (b & 0x1f) << shift;
                shift += 5;
            }while(b > 0x20);

            int dlng = ((result & 1 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }
}