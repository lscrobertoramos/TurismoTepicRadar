package com.ut3.ehg.turismotepic;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HomeActivity extends Fragment implements View.OnClickListener {


    ViewGroup root;
    Context cntx;
    ImageButton bmenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final AppBarLayout appBarLayout;
        root = (ViewGroup) inflater.inflate(R.layout.activity_home, null);
        cntx = container.getContext();
        bmenu = (ImageButton)root.findViewById(R.id.bmenu);
        bmenu.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        if(!drawer.isDrawerOpen(GravityCompat.START))
            drawer.openDrawer(GravityCompat.START);
        else
            drawer.closeDrawer(GravityCompat.START);

    }
}
