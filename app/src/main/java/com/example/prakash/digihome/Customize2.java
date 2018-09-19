package com.example.prakash.digihome;

import android.content.Intent;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by PRAKASH on 06-Aug-17.
 */

public class Customize2 extends Fragment {


    Button addlight = null;
    Button addfan = null;
    Button addswitch = null;
    EditText lightname = null ;
    EditText lightpin = null ;
    EditText fanname = null;
    EditText fanpin = null;
    EditText switchname = null;
    EditText switchpin = null;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customize2, container, false);



        addlight = rootView.findViewById(R.id.addlight);
        addfan = rootView.findViewById(R.id.addfan);
        addswitch = rootView.findViewById(R.id.addswitch);
        lightname = rootView.findViewById(R.id.lightname);
        lightpin = rootView.findViewById(R.id.lightpin);
        fanname = rootView.findViewById(R.id.fanname);
        fanpin = rootView.findViewById(R.id.fanpin);
        switchname = rootView.findViewById(R.id.switchname);
        switchpin = rootView.findViewById(R.id.switchpin);

        addlight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String lpin = lightpin.getText().toString();
                final String lname = lightname.getText().toString();

                        final Bundle bundle1 = new Bundle();
                        bundle1.putString("LName",lname);
                        bundle1.putString("LPin",lpin);
                        CustomMessageEvent event = new CustomMessageEvent();
                        event.setBundle(bundle1);
                        EventBus.getDefault().post(event);

                    }
                });


        addfan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String fpin = fanpin.getText().toString();
                final String fname = fanname.getText().toString();

                final Bundle bundle2 = new Bundle();
                bundle2.putString("FName",fname);
                bundle2.putString("FPin",fpin);
                FanEvent event = new FanEvent();
                event.setBundle(bundle2);
                EventBus.getDefault().post(event);



            }
        });


        addswitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String spin = switchpin.getText().toString();
                final String sname = switchname.getText().toString();

                final Bundle bundle3 = new Bundle();
                bundle3.putString("SName",sname);
                bundle3.putString("SPin",spin);
                SwitchEvent event = new SwitchEvent();
                event.setBundle(bundle3);
                EventBus.getDefault().post(event);

            }
        });

        return rootView;
    }






}
