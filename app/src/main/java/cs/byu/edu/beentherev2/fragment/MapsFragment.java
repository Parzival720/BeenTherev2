package cs.byu.edu.beentherev2.fragment;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cs.byu.edu.beentherev2.MainActivity;
import cs.byu.edu.beentherev2.R;
import cs.byu.edu.beentherev2.model.Event;
import cs.byu.edu.beentherev2.model.Journal;

public class MapsFragment extends Fragment {

    GoogleMap mMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            MainActivity mainActivity = (MainActivity) getActivity();

            //grab all events and put their markers onto the map!
            for (Journal journal : mainActivity.getJournals()) {
                for (Event event : journal.getEvents()) {
                    googleMap.addMarker(new MarkerOptions().position(event.getLocation()).title(event.getTitle()));
                }
            }

            addCustomMarker();


        }

        private void addCustomMarker() {
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mMap == null) {
                return;
            }

            // adding a marker on map with image from  drawable
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(40.24688, -111.64920))
                    .icon(BitmapDescriptorFactory.fromBitmap(mainActivity.getMarkerBitmapFromView())));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        //make location button take to current location (hardcoded to Provo right now)
        ImageButton addButton = (ImageButton) view.findViewById(R.id.location_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraUpdate center =
                        CameraUpdateFactory.newLatLng(new LatLng(40.24688, -111.64920));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                mMap.moveCamera(center);
                mMap.animateCamera(zoom);
            }
        });

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


}