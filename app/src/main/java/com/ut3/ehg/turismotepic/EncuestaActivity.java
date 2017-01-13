package com.ut3.ehg.turismotepic;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.ut3.ehg.turismotepic.rc.rc_encuesta;

import static android.content.Context.MODE_PRIVATE;

public class EncuestaActivity extends Fragment {

    RatingBar rb1,rb2,rb3,rb4,rb5;
    ImageButton btnEnviar;
    rc_encuesta rcEncuesta;
    SharedPreferences user,loginPreferences;
    float r1,r2,r3,r4,r5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root;
        Context cntx;
        FloatingActionButton fab;
        final AppBarLayout appBarLayout;

        root = (ViewGroup) inflater.inflate(R.layout.activity_encuesta, null);
        cntx = container.getContext();


        rb1 = (RatingBar) root.findViewById(R.id.ratingb1);
        rb2 = (RatingBar) root.findViewById(R.id.ratingb2);
        rb3 = (RatingBar) root.findViewById(R.id.ratingb3);
        rb4 = (RatingBar) root.findViewById(R.id.ratingb4);
        rb5 = (RatingBar) root.findViewById(R.id.ratingb5);
        btnEnviar = (ImageButton) root.findViewById(R.id.btnEnviar);
        user=this.getActivity().getSharedPreferences("user", MODE_PRIVATE);
        loginPreferences = this.getActivity().getSharedPreferences("loginPrefs",MODE_PRIVATE);
        btnEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int idUser=user.getInt("idUser",0);
                r1=rb1.getRating();
                r2=rb2.getRating();
                r3=rb3.getRating();
                r4=rb4.getRating();
                r5=rb5.getRating();
                rcEncuesta= new rc_encuesta(getActivity().getApplicationContext());
                rcEncuesta.open();
                rcEncuesta.enviarEncuesta(idUser,r1,r2,r3,r4,r5);
                Toast.makeText(getActivity(), "Encuesta enviada", Toast.LENGTH_SHORT).show();
                String fragmentTemp="com.ut3.ehg.turismotepic.HomeActivity";
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.lframe, Fragment.instantiate(getContext(), fragmentTemp));
                tx.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        return root;
    }

}