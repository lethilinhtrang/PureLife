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
    Button button1, button2, button3;
    TextView text_humidity, text_CO, text_PM;

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

        text_humidity = root.findViewById(R.id.text_humidity);
        text_CO = root.findViewById(R.id.text_CO);
        text_PM = root.findViewById(R.id.text_PM);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

//        hTkA4mK2WW1LVUggYesKk8T0JdwIgwPq
//        11:52
//        oIXHj_zEa_p1GFQcQ12r34VRYRaORok7

        String url = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V5";
        String urlDoAm = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V6";
        String urlPM = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V7";
        String urlCO = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V8";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                        int l = response.toString().length();

                        String temp = response.toString().substring(2, l-2);

                        textView.setText(temp + " °C");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, urlDoAm, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                        int l = response.toString().length();

                        String temp = response.toString().substring(2, l-2);

                        text_humidity.setText(temp + " %");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest2);

        JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(Request.Method.GET, urlPM, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                        int l = response.toString().length();

                        String temp = response.toString().substring(2, l-2);

                        text_CO.setText(temp+ " PPM");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest3);

        JsonArrayRequest jsonArrayRequest4 = new JsonArrayRequest(Request.Method.GET, urlCO, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                        int l = response.toString().length();

                        String temp = response.toString().substring(2, l-2);

                        text_PM.setText(temp+ " µg/m3");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest4);

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

        button1.setText(getText(R.string.on) + "\n"+ getText(R.string.dieu_hoa));
        button2.setText(getText(R.string.on) + "\n"+ getText(R.string.phun_suong));
        button3.setText(getText(R.string.on)+ "\n"+ getText(R.string.loc_khi));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button1) {
                    button1.setText(getText(R.string.off)+ "\n"+ getText(R.string.dieu_hoa));
                }
                else {
                    button1.setText(getText(R.string.on) + "\n"+ getText(R.string.dieu_hoa));
                }
                state_button1=!state_button1;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button2) {
                    button2.setText(getText(R.string.off) + "\n"+ getText(R.string.phun_suong));
                }
                else {
                    button2.setText(getText(R.string.on) + "\n"+ getText(R.string.phun_suong));
                }
                state_button2=!state_button2;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button3) {
                    button3.setText(getText(R.string.off)+ "\n"+ getText(R.string.loc_khi));
                }
                else {
                    button3.setText(getText(R.string.on)+ "\n"+ getText(R.string.loc_khi));
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