package edu.ufam.icomp.sd.maprest;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.ufam.icomp.sd.maprest.model.Country;

public class MapsCountryActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Country country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_country);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        country = new Country();
        country.setName(getIntent().getStringExtra("name"));
        country.setLatlong(getIntent().getStringExtra("latlong"));
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

        if (country == null){
            Log.d("MAIN", "SCR");
        }
        // Add a marker in Sydney and move the camera
        String [] pos = country.getLatlong().split(":");
        LatLng country_pos = new LatLng(Double.valueOf(pos[0]),Double.valueOf(pos[1]));
        mMap.addMarker(new MarkerOptions().position(country_pos).title("Marker in "+country.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(country_pos));
    }
}
