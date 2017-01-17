/**
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nortti.ru.gomoscow;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import nortti.ru.gomoscow.models.Place;

/**
 * Constants used in this sample.
 */
public final class Constants {

    DatabaseReference myRef;
    String name;
    double Lat;
    double Long;
    HashMap<String, LatLng> place;

    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 2000; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<String, LatLng>();
    static {
        // San Francisco International Airport.
        BAY_AREA_LANDMARKS.put("SFO", new LatLng(55.812099, 37.830623));

        // Googleplex.
        BAY_AREA_LANDMARKS.put("GOOGLE", new LatLng(55.821669,37.803758));
    }

    public static final HashMap<String, LatLng> loadGeofences(){
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Places");
        final HashMap<String, LatLng> place = new HashMap<String, LatLng>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("DEBUG",""+dataSnapshot.getChildrenCount());
                for (DataSnapshot placeSnapshot : dataSnapshot.getChildren()) {
                    final Place places = placeSnapshot.getValue(Place.class);
                    String name = places.getName();
                    double Lat = places.getLatitude();
                    double Long = places.getLongitude();

                    place.put(name,new LatLng(Lat,Long));
                    Log.d("ADDED", name);


                    /*LatLng latLng = new LatLng(Lat, Long);
                    MarkerOptions newMarker = new MarkerOptions();
                    newMarker.position(latLng);
                    newMarker.icon(BitmapDescriptorFactory.fromResource(places.getResId()));
                    newMarker.title(name);
                    mGoogleMap.addMarker(newMarker);*/
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR", "Failed to load Place");
            }
        });
        return place;
    }
}
