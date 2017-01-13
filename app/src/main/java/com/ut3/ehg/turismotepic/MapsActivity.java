package com.ut3.ehg.turismotepic;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.*;
import java.util.logging.Handler;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class MapsActivity extends Fragment implements OnMapReadyCallback, DirectionFinderListener {
    private GoogleMap mMap;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    // prueba
    Cursor datos;
    SharedPreferences.Editor editarUbic;

    SharedPreferences ubic;
    ViewGroup root;
    Context cntx;
    LocationProvider loc;
    String latitud, longitud,destino;
    int cat;
    Location location;
    LocationManager lm;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final AppBarLayout appBarLayout;

        root = (ViewGroup) inflater.inflate(R.layout.fragment_maps, null);
        cntx = container.getContext();
        lm = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            else
            {
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

        }else
        {
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

       // latitud=String.valueOf(location.getLatitude());
       // longitud=String.valueOf(location.getLongitude());
        //System.out.println("Ubicacion: "+latitud+","+longitud);

       // SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
       // mapFragment.getMapAsync(this);
       // sendRequest();

        latitud=String.valueOf(location.getLatitude());
        longitud=String.valueOf(location.getLongitude());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        sendRequest();

        //contar el tiempo
        /*
        new CountDownTimer(10000, 10000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

            }

        }.start();
        */



        return root;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera

    }

    private void sendRequest() {
        ubic=this.getActivity().getSharedPreferences("ubic",MODE_PRIVATE);
        String loc=ubic.getString("loc","");
        String origin = latitud+","+longitud;
        String destination = loc;
        cat=ubic.getInt("cat",0);
        destino=ubic.getString("destino","");

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getContext(), "Espera",
                "Buscando las indicaciones", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        System.out.println("Cat: "+cat);
        String image=getIcono(cat);
        int id = getResources().getIdentifier(image, "drawable", getActivity().getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        //iv1.setImageDrawable(drawable);
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 14));
            ((TextView) getActivity().findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) getActivity().findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.inicio))
                    .title("Tu ubicación")
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(id))
                    .title(destino)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.parseColor("#F29100")).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    public String getIcono(int cat) {
        String icono="";
        switch(cat){
            case 1:
                icono="hoteles_ico";
                break;
            case 2:
                icono="restaurantes_ico";
                break;
            case 3:
                icono="monumentos_ico";
                break;
            case 4:
                icono="museos_ico";
                break;
            case 5:
                icono="bancos_ico";
                break;
            case 6:
                icono="farmacias_ico";
                break;
            case 7:
                icono="tiendas_ico";
                break;
            case 8:
                icono="mall_ico";
                break;
            case 9:
                icono="parques_ico";
                break;
            case 10:
                icono="otros_ico";
                break;

        }
        return icono;
    }



}
