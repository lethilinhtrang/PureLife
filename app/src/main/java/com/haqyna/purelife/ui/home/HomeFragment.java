package com.haqyna.purelife.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.biansemao.widget.ThermometerView;
import com.haqyna.purelife.MainActivity;
import com.haqyna.purelife.R;

import org.json.JSONArray;

import java.util.Random;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ThermometerView thermometerTv;
    private Button button1, button2, button3;

    boolean state_button1 = false, state_button2 = false, state_button3 = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        button1 = root.findViewById(R.id.button1);
        button2 = root.findViewById(R.id.button2);
        button3 = root.findViewById(R.id.button3);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String url = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V5";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                        int l = response.toString().length();

                        String temp = response.toString().substring(2, l-2);

                        textView.setText(temp);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

//        thermometerTv = root.findViewById(R.id.tv_thermometer);
//
//        thermometerTv.setMinimumHeight(200);

//        root.findViewById(R.id.btn_anim).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                thermometerTv.setValueAndStartAnim(getRandomValue());
//            }
//        });
//        root.findViewById(R.id.btn_operate).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                thermometerTv.setCurValue(getRandomValue());
//            }
//        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button1) {
                    button1.setText(getText(R.string.dieu_hoa) + "\n"+ getText(R.string.off));
                }
                else {
                    button1.setText(getText(R.string.dieu_hoa) + "\n"+ getText(R.string.on));
                }
                state_button1=!state_button1;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button2) {
                    button2.setText(getText(R.string.phun_suong) + "\n"+ getText(R.string.off));
                }
                else {
                    button2.setText(getText(R.string.phun_suong) + "\n"+ getText(R.string.on));
                }
                state_button2=!state_button2;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button3) {
                    button3.setText(getText(R.string.loc_khi) + "\n"+ getText(R.string.off));
                }
                else {
                    button3.setText(getText(R.string.loc_khi) + "\n"+ getText(R.string.on));
                }
                state_button3=!state_button3;
            }
        });

        return root;
    }

//    private float getRandomValue(){
//        float value = new Random().nextFloat() * 7 + 35;
//        Log.i("MainActivity", "current value: " + value);
//        return value;
//    }
}