package com.ut3.ehg.turismotepic;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.ut3.ehg.turismotepic.rc.rc_pois;

import static android.content.Context.MODE_PRIVATE;


public class DetalladoActivity extends Fragment implements View.OnClickListener {
    private rc_pois poisdb;
    Cursor datos;
    ViewGroup root;
    Context cntx;
    SharedPreferences poi,ubic;
    SharedPreferences.Editor editarUbic;
    int cat;
    String destino;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final AppBarLayout appBarLayout;
        root = (ViewGroup) inflater.inflate(R.layout.activity_detallado, null);
        cntx = container.getContext();
        poi=this.getActivity().getSharedPreferences("poi",MODE_PRIVATE);


        ImageView iv1 = (ImageView)root.findViewById(R.id.iv1);
        String idPlace=poi.getString("idPoi","");
        TextView nombre = (TextView)root.findViewById(R.id.lugar);
        TextView posicion= (TextView)root.findViewById(R.id.posicion);
        TextView horario =(TextView)root.findViewById(R.id.horario);
        TextView costo=(TextView)root.findViewById(R.id.costo);
        //TextView descripcion=(TextView)root.findViewById(R.id.descripcion);
        JustifiedTextView J = (JustifiedTextView)root.findViewById(R.id.descripcion);
        ImageButton bruta = (ImageButton)root.findViewById(R.id.bruta);
        //Obtención de los datos
        poisdb= new rc_pois(root.getContext());
        poisdb.open();
        datos=poisdb.getDatos(idPlace);
        datos.moveToFirst();
        cat=datos.getInt(1);
        destino=datos.getString(2);
        nombre.setText(datos.getString(2));
        posicion.setText("("+datos.getString(5)+" "+datos.getString(6)+")");
        horario.setText("Horario: "+datos.getString(7));
        costo.setText("Costo: "+datos.getString(9));
        J.setText(datos.getString(4));
        J.setTextColor(Color.BLACK);
        J.setTextSize(13);
        //descripcion.setText(datos.getString(4));
        bruta.setOnClickListener(this);


        //Cosas de la imagen
        String image=datos.getString(11);
        if(!image.equals("")) {
            int id = getResources().getIdentifier(image, "drawable", getActivity().getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            iv1.setImageDrawable(drawable);
        }

        return root;
    }

    @Override
    public void onClick(View v) {
        String loc = datos.getString(5)+","+datos.getString(6);
        String fragmentTemp="com.ut3.ehg.turismotepic.MapsActivity";
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.lframe, Fragment.instantiate(getContext(), fragmentTemp)).addToBackStack("tag");
        tx.commit();
        drawer.closeDrawer(GravityCompat.START);
        ubic = getContext().getSharedPreferences("ubic",MODE_PRIVATE);
        editarUbic=ubic.edit();
        editarUbic.putString("loc",loc);
        editarUbic.putInt("cat",cat);
        editarUbic.putString("destino",destino);
        editarUbic.commit();
    }
}
