package com.example.prakash.digihome;

import android.app.Activity;
import android.widget.Toast;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.IOException;
import java.util.UUID;

import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by PRAKASH on 06-Aug-17.
 */

public class Dashboard1 extends Fragment {

    SendMessage SM;

    TextView txt1;
    TextView txt2;
    Button btn;
    ImageButton imgb;
    String txt3,txt4,txt5,txt6,txt7,txt8;
    LinearLayout mylayout;
    public String data[]={txt3,txt4};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard1, container, false);


        txt1=(TextView)rootView.findViewById(R.id.txt1);
        txt2=(TextView)rootView.findViewById(R.id.txt2);
        btn=(Button)rootView.findViewById(R.id.button);
        btn.setVisibility(View.INVISIBLE);
        mylayout = (LinearLayout)rootView.findViewById(R.id.mylayout);

        return rootView;
    }



    @Subscribe
    public void onEvent(CustomMessageEvent event){
        Log.d("Live:", "Event fired " + event.getBundle());

        Bundle bundle = event.getBundle();
        txt3=bundle.getString("LName");
        txt4=bundle.getString("LPin");
        lightadd(txt3,txt4);



    }

    private void lightadd(String txt3,final String txt4) {
        final Button btn = new Button(getContext());
        final TextView txt1 = new TextView(getContext());
        LinearLayout.LayoutParams myparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        btn.setText(txt4);
        txt1.setText(txt3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Live:", "Selectd PinNo " + txt4);
                SM.turnOn(txt4);

            }
        });

        mylayout.addView(btn,myparams);
        mylayout.addView(txt1,myparams);


    }

    @Subscribe
    public void onEvent(FanEvent event){
        Log.d("Live:", "Event fired " + event.getBundle());

        Bundle bundle = event.getBundle();
        txt5=bundle.getString("FName");
        txt6=bundle.getString("FPin");
        fanadd(txt5);
    }

    private void fanadd(String txt5) {

        final ImageButton imgb = new ImageButton(getContext());
        LinearLayout.LayoutParams myparams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    @Subscribe
    public void onEvent(SwitchEvent event){
        Log.d("Live:", "Event fired " + event.getBundle());

        Bundle bundle = event.getBundle();
        txt3=bundle.getString("SName");
        txt4=bundle.getString("SPin");
        txt1.setText(txt3);
        txt2.setText(txt4);
    }


    interface SendMessage {
        void turnOff(String message);
        void turnOn(String message);
    }




    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }


    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


}
