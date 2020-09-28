package com.haqyna.purelife.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.biansemao.widget.ThermometerView;
import com.haqyna.purelife.GetData;
import com.haqyna.purelife.MainActivity;
import com.haqyna.purelife.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ThermometerView thermometerTv;
    Button button1, button2, button3;
    static TextView text_nhietdo;
    static TextView text_nhietdo_f;
    TextView text_humidity;
    TextView text_CO;
    TextView text_PM;
    TextView text1;
    TextView text2;
    TextView text3;

    String tempNhietDo = "";
    String tempDoAm = "";
    String tempCO = "";
    String tempDoBuiPM25 = "";

    boolean state_button1 = false, state_button2 = false, state_button3 = false;

    RequestQueue requestQueue;

    String urlDoC = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V5";
    String urlDoAm = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V6";
    String urlPM = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V7";
    String urlCO = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V8";
    String urlDoF = "http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/get/V9";
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        button1 = root.findViewById(R.id.button1);
        button2 = root.findViewById(R.id.button2);
        button3 = root.findViewById(R.id.button3);

        text_nhietdo = root.findViewById(R.id.text_nhietdo);
        text_nhietdo_f = root.findViewById(R.id.text_nhietdo_f);
        text_humidity = root.findViewById(R.id.text_humidity);
        text_CO = root.findViewById(R.id.text_CO);
        text_PM = root.findViewById(R.id.text_PM);
        text1 = root.findViewById(R.id.text1);
        text2 = root.findViewById(R.id.text2);
        text3 = root.findViewById(R.id.text3);



        new GetData(getContext(), text_nhietdo, null, 0).execute(urlDoC);
        new GetData(getContext(), text_nhietdo_f, null, 1).execute(urlDoF);
        new GetData(getContext(), text_humidity, text1, 2).execute(urlDoAm);
        new GetData(getContext(), text_CO, text2, 3).execute(urlCO);
        new GetData(getContext(), text_PM, text3, 4).execute(urlPM);



        button1.setText(getText(R.string.on) + "\n" + getText(R.string.dieu_hoa));
        button2.setText(getText(R.string.on) + "\n" + getText(R.string.phun_suong));
        button3.setText(getText(R.string.on) + "\n" + getText(R.string.loc_khi));


        button1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (state_button1) {
                    button1.setText(getText(R.string.off) + "\n" + getText(R.string.dieu_hoa));
                    //xu li bat dieu hoa
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                URL url = null;
                                HttpURLConnection urlConnection = null;
                                try {
                                    url = new URL("http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/update/V1?value=1&fbclid=IwAR0gHd_YWaUc0JmYv05nJowb5niLXjLC1wIMQDrXapb6wyTtC6F1mfP4FIw");
                                    urlConnection = (HttpURLConnection) url.openConnection();
                                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                    readStream(in);
                                } catch (Exception e){
                                    e.printStackTrace();

                                    // to do
                                } finally {
                                    if (urlConnection != null) {
                                        urlConnection.disconnect();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();


                } else {
                    button1.setText(getText(R.string.on) + "\n" + getText(R.string.dieu_hoa));

                    //xu li tắt dieu hoa
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                URL url = null;
                                HttpURLConnection urlConnection = null;
                                try {
                                    url = new URL("http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/update/V1?value=0&fbclid=IwAR2ZcUUnUiOi0p_2mCKBbtTdklAn9XFl1MW-eWEyhVQl3bPWzXaQxncvMTI");
                                    urlConnection = (HttpURLConnection) url.openConnection();
                                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                    readStream(in);
                                } catch (Exception e){
                                    e.printStackTrace();

                                    // to do
                                } finally {
                                    if (urlConnection != null) {
                                        urlConnection.disconnect();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();
                }
                state_button1 = !state_button1;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button2) {
                    button2.setText(getText(R.string.off) + "\n" + getText(R.string.phun_suong));
                    //xu li bat phun sương
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                URL url = null;
                                HttpURLConnection urlConnection = null;
                                try {
                                    url = new URL("http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/update/V2?value=1&fbclid=IwAR3TfA9DUXJRXwkX9Q8owOvwkxp75Z0-fYnzQHKr7cI_dBAIdmfC62dK0yQ");
                                    urlConnection = (HttpURLConnection) url.openConnection();
                                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                    readStream(in);
                                } catch (Exception e){
                                    e.printStackTrace();

                                    // to do
                                } finally {
                                    if (urlConnection != null) {
                                        urlConnection.disconnect();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();

                } else {
                    button2.setText(getText(R.string.on) + "\n" + getText(R.string.phun_suong));
                    //xu li tắt phun sương
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                URL url = null;
                                HttpURLConnection urlConnection = null;
                                try {
                                    url = new URL("http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/update/V2?value=0&fbclid=IwAR1WHxtOJ994JphmlRnS3YYJ6CLRC4zMyp8zJn_1-soFKumnKlg0EBPTCjU");
                                    urlConnection = (HttpURLConnection) url.openConnection();
                                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                    readStream(in);
                                } catch (Exception e){
                                    e.printStackTrace();

                                    // to do
                                } finally {
                                    if (urlConnection != null) {
                                        urlConnection.disconnect();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();

                }
                state_button2 = !state_button2;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button3) {
                    button3.setText(getText(R.string.off) + "\n" + getText(R.string.loc_khi));
                    //xu li bật hút bụi
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                URL url = null;
                                HttpURLConnection urlConnection = null;
                                try {
                                    url = new URL("http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/update/V3?value=1&fbclid=IwAR237Z_eFywwxjf67cQDDuqTV7b47c4lt4qFEUxHjHyBdymlw5rrL7eDVoM");
                                    urlConnection = (HttpURLConnection) url.openConnection();
                                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                    readStream(in);
                                } catch (Exception e){
                                    e.printStackTrace();

                                    // to do
                                } finally {
                                    if (urlConnection != null) {
                                        urlConnection.disconnect();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();

                } else {
                    button3.setText(getText(R.string.on) + "\n" + getText(R.string.loc_khi));
                                           //xu li tắt hút bụi
                                           Thread thread = new Thread(new Runnable() {

                                               @Override
                                               public void run() {
                                                   try  {
                                                       URL url = null;
                                                       HttpURLConnection urlConnection = null;
                                                       try {
                                                           url = new URL("http://188.166.206.43/CAPTHutAcs8rLDgwQ0RU3KjYmnvMo1EM/update/V3?value=0&fbclid=IwAR0iAASR22JipEXgoiblxJvXHugwQ_w1WnATfZ7Xyzti8G9lSn2rdsESD0U");
                                                           urlConnection = (HttpURLConnection) url.openConnection();
                                                           InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                                           readStream(in);
                                                       } catch (Exception e){
                                                           e.printStackTrace();

                                                           // to do
                                                       } finally {
                                                           if (urlConnection != null) {
                                                               urlConnection.disconnect();
                                                           }
                                                       }
                                                   } catch (Exception e) {
                                                       e.printStackTrace();
                                                   }
                                               }
                                           });

                                           thread.start();


                }
                state_button3 = !state_button3;
            }
        });

        return root;
    }

    public static void chuyenNhietDo(String kieuDo) {
        if ("C".equals(kieuDo)) {
            text_nhietdo_f.setVisibility(View.GONE);
            text_nhietdo.setVisibility(View.VISIBLE);
        } else {
            text_nhietdo_f.setVisibility(View.VISIBLE);
            text_nhietdo.setVisibility(View.GONE);
        }
    }
}