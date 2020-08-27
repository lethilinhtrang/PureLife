package com.haqyna.purelife.ui.notifications;

        import android.annotation.SuppressLint;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;
        import com.biansemao.widget.ThermometerView;
        import com.haqyna.purelife.GetData;
        import com.haqyna.purelife.R;

        import org.json.JSONArray;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
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

    String urlDoC = "http://188.166.206.43/oIXHj_zEa_p1GFQcQ12r34VRYRaORok7/get/V5";
    String urlDoAm = "http://188.166.206.43/oIXHj_zEa_p1GFQcQ12r34VRYRaORok7/get/V6";
    String urlPM = "http://188.166.206.43/oIXHj_zEa_p1GFQcQ12r34VRYRaORok7/get/V7";
    String urlCO = "http://188.166.206.43/oIXHj_zEa_p1GFQcQ12r34VRYRaORok7/get/V8";
    String urlDoF = "http://188.166.206.43/oIXHj_zEa_p1GFQcQ12r34VRYRaORok7/get/V9";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);


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

//        requestQueue = Volley.newRequestQueue(getContext());

        new GetData(getContext(), text_nhietdo, null,0).execute(urlDoC);
        new GetData(getContext(), text_nhietdo_f, null, 1).execute(urlDoF);
        new GetData(getContext(), text_humidity, text1, 2).execute(urlDoAm);
        new GetData(getContext(), text_CO, text2, 3).execute(urlCO);
        new GetData(getContext(), text_PM, text3, 4).execute(urlPM);

//        hTkA4mK2WW1LVUggYesKk8T0JdwIgwPq
//        11:52
//        oIXHj_zEa_p1GFQcQ12r34VRYRaORok7

//        layNhietDo("C");

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlDoC, null,
//                new Response.Listener<JSONArray>() {
//                    @SuppressLint("ResourceType")
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
//                        int l = response.toString().length();
//                        tempNhietDo = response.toString().substring(2, l - 2);
//                        text_nhietdo.setText(getString(R.string.nhiet_do) + "\n" + tempNhietDo + " °C");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        requestQueue.add(jsonArrayRequest);
//
//        JsonArrayRequest jsonArrayRequestf = new JsonArrayRequest(Request.Method.GET, urlDoF, null,
//                new Response.Listener<JSONArray>() {
//                    @SuppressLint("ResourceType")
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
//                        int l = response.toString().length();
//
//                        tempNhietDo = response.toString().substring(2, l - 2);
//                        text_nhietdo_f.setText(getString(R.string.nhiet_do) + "\n" + tempNhietDo + " °F");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        requestQueue.add(jsonArrayRequestf);
//
//        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, urlDoAm, null,
//                new Response.Listener<JSONArray>() {
//                    @SuppressLint("ResourceType")
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
//
//                        int l = response.toString().length();
//
//                        tempDoAm = response.toString().substring(2, l - 2);
//                        Float doAm=0f;
//                        try{
//                            doAm = Float.parseFloat(tempDoAm);
//                        }
//                        catch (Exception E){
//                            doAm=70f;
//                        }
//
//
//                        if ((doAm >= 60f) && (doAm < 75f)) {
//                            text1.setText(getString(R.string.canh_bao_nhiet_do_do_am) + getText(R.string.tot));
//                            text1.setTextColor(Color.parseColor((String) getText(R.color.colorTrungBinh)));
//                        }
//                        if (doAm < 60f) {
//                            text1.setText(getString(R.string.canh_bao_nhiet_do_do_am) + getText(R.string.do_am_thap));
//                            text1.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
//                        }
//                        if (doAm >= 75f) {
//                            text1.setText(getString(R.string.canh_bao_nhiet_do_do_am) + getText(R.string.do_am_cao));
//                            text1.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
//                        }
//                        text_humidity.setText(getString(R.string.do_am) + "\n" + tempDoAm + " %");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        requestQueue.add(jsonArrayRequest2);
//
//        JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(Request.Method.GET, urlPM, null,
//                new Response.Listener<JSONArray>() {
//                    @SuppressLint("ResourceType")
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
//
//                        int l = response.toString().length();
//
//                        tempDoBuiPM25 = response.toString().substring(2, l - 2);
//                        Float buiPM = Float.parseFloat(tempDoBuiPM25);
//                        if ((buiPM >= 0f) && (buiPM <= 15.4f)) {
//                            text3.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.tot));
//                            text3.setTextColor(Color.parseColor((String) getText(R.color.colorTot)));
//                        }
//                        if ((buiPM >= 15.5f) && (buiPM <= 40.4f)) {
//                            text3.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.vua_phai));
//                            text3.setTextColor(Color.parseColor((String) getText(R.color.colorTrungBinh)));
//                        }
//                        if ((buiPM >= 40.5f) && (buiPM <= 65.4f)) {
//                            text3.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.khong_lanh_manh));
//                            text3.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
//                        }
//                        if ((buiPM >= 65.5f) && (buiPM <= 150.4f)) {
//                            text3.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.khong_khoe));
//                            text3.setTextColor(Color.parseColor((String) getText(R.color.colorKhongKhoe)));
//                        }
//                        if ((buiPM >= 150.5f) && (buiPM <= 250.4f)) {
//                            text3.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.rat_khong_khoe));
//                            text3.setTextColor(Color.parseColor((String) getText(R.color.colorRatKhongKhoe)));
//                        }
//                        if ((buiPM >= 250.5f) && (buiPM <= 350.4f)) {
//                            text3.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.nguy_hiem));
//                            text3.setTextColor(Color.parseColor((String) getText(R.color.colorNguyHiem)));
//                        }
//                        if ((buiPM >= 350.5f) && (buiPM <= 500.4f)) {
//                            text3.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.rat_nguy_hiem));
//                            text3.setTextColor(Color.parseColor((String) getText(R.color.colorRatNguyHiem)));
//                        }
//                        text_PM.setText(getString(R.string.bui_pm_25) + "\n" + tempDoBuiPM25 + "\n (µg/m3)");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        requestQueue.add(jsonArrayRequest3);
//
//        JsonArrayRequest jsonArrayRequest4 = new JsonArrayRequest(Request.Method.GET, urlCO, null,
//                new Response.Listener<JSONArray>() {
//                    @SuppressLint("ResourceType")
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
//
//                        int l = response.toString().length();
//
//                        tempCO = response.toString().substring(2, l - 2);
//                        Float CO = Float.parseFloat(tempCO);
//                        if ((CO >= 0f) && (CO <= 50f)) {
//                            text2.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.tot));
//                            text2.setTextColor(Color.parseColor((String) getText(R.color.colorTot)));
//                        }
//                        if ((CO >= 51f) && (CO <= 100f)) {
//                            text2.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.vua_phai));
//                            text2.setTextColor(Color.parseColor((String) getText(R.color.colorTrungBinh)));
//                        }
//                        if ((CO >= 101f) && (CO <= 150f)) {
//                            text2.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.khong_lanh_manh));
//                            text2.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
//                        }
//                        if ((CO >= 151f) && (CO <= 200f)) {
//                            text2.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.khong_khoe));
//                            text2.setTextColor(Color.parseColor((String) getText(R.color.colorKhongKhoe)));
//                        }
//                        if ((CO >= 201f) && (CO <= 300f)) {
//                            text2.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.rat_khong_khoe));
//                            text2.setTextColor(Color.parseColor((String) getText(R.color.colorRatKhongKhoe)));
//                        }
//                        if ((CO >= 301f) && (CO <= 500f)) {
//                            text2.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.nguy_hiem));
//                            text2.setTextColor(Color.parseColor((String) getText(R.color.colorNguyHiem)));
//                        }
//                        text_CO.setText(getString(R.string.co) + "\n" + tempCO + " PPM");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        requestQueue.add(jsonArrayRequest4);

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

        button1.setText(getText(R.string.on) + "\n" + getText(R.string.dieu_hoa));
        button2.setText(getText(R.string.on) + "\n" + getText(R.string.phun_suong));
        button3.setText(getText(R.string.on) + "\n" + getText(R.string.loc_khi));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button1) {
                    button1.setText(getText(R.string.off) + "\n" + getText(R.string.dieu_hoa));
                } else {
                    button1.setText(getText(R.string.on) + "\n" + getText(R.string.dieu_hoa));
                }
                state_button1 = !state_button1;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button2) {
                    button2.setText(getText(R.string.off) + "\n" + getText(R.string.phun_suong));
                } else {
                    button2.setText(getText(R.string.on) + "\n" + getText(R.string.phun_suong));
                }
                state_button2 = !state_button2;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state_button3) {
                    button3.setText(getText(R.string.off) + "\n" + getText(R.string.loc_khi));
                } else {
                    button3.setText(getText(R.string.on) + "\n" + getText(R.string.loc_khi));
                }
                state_button3 = !state_button3;
            }
        });

        return root;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.item1:
////                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
////                startActivityForResult(new Intent(getContext(), SettingActivity.class, REQUEST_CODE));
//                startActivityForResult(new Intent(getContext(), SettingActivity.class), REQUEST_CODE);
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
////        super.onCreateOptionsMenu(menu, inflater);
//        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.item1:
////                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
////                startActivityForResult(new Intent(getContext(), SettingActivity.class, REQUEST_CODE));
//                startActivityForResult(new Intent(getContext() , SettingActivity.class), REQUEST_CODE);
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    public static void chuyenNhietDo(String kieuDo) {
        if ("C".equals(kieuDo)) {
            text_nhietdo_f.setVisibility(View.GONE);
            text_nhietdo.setVisibility(View.VISIBLE);
        } else {
            text_nhietdo_f.setVisibility(View.VISIBLE);
            text_nhietdo.setVisibility(View.GONE);
        }

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @SuppressLint("ResourceType")
//                    @Override
//                    public void onResponse(JSONArray response) {
////                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
//                        int l = response.toString().length();
//
//                        tempNhietDo = response.toString().substring(2, l - 2);
//
//                        Float nhietdo = Float.parseFloat(tempNhietDo);
//
//                        if ((nhietdo >= 15f) && (nhietdo < 35f)) {
//                            text1.setText(getString(R.string.canh_bao_nhiet_do_do_am) + getText(R.string.tot));
//                            text1.setTextColor(Color.parseColor((String) getText(R.color.colorOrange)));
//                        }
//                        text_nhietdo.setText(getString(R.string.nhiet_do) + "\n" + tempNhietDo + " °C");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        requestQueue.add(jsonArrayRequest);
    }

    //    private float getRandomValue(){
//        float value = new Random().nextFloat() * 7 + 35;
//        Log.i("MainActivity", "current value: " + value);
//        return value;
//    }
}