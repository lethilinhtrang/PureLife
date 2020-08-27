package com.haqyna.purelife;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class GetData extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    Context context;
    TextView textView, textCanhBao;
    String tempDuLieu = "";
    int loai = 0;

    public GetData(Context context, TextView textView, TextView textCanhBao, int loai) {
        this.context = context;
        this.textView = textView;
        this.textCanhBao = textCanhBao;
        this.loai = loai;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Hiển thị Dialog khi bắt đầu xử lý.
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Pure Life");
        progressDialog.setMessage("Đang lấy dữ liệu...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            StringBuilder sb;
            // Website này sẽ cho biết địa chỉ IP của bạn.
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }

//                Log.e("Dia chi IP ", sb.toString());
                br.close();
                return sb.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    @Override
    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);
        // Hủy dialog đi.
        progressDialog.dismiss();

        try {
            int l = aString.length();
            tempDuLieu = aString.substring(2, l - 3);
        } catch (Exception ex) {
            tempDuLieu = "30";
        }

        switch (loai) {
            case 0:
                textView.setText("Nhiệt độ: " + "\n" + tempDuLieu + " °C");
                break;
            case 1:
                textView.setText("Nhiệt độ: " + "\n" + tempDuLieu + " °F");
                break;
            case 2:
//                textView.setText("Độ ẩm: " + "\n" + tempDuLieu + " %");
                Float doAm = 0f;
                try {
                    doAm = Float.parseFloat(tempDuLieu);
                    textView.setText("Độ ẩm: " + "\n" + tempDuLieu + " %");
                } catch (Exception E) {
                    doAm = 70f;
                    textView.setText("Độ ẩm: " + "\n" + 70 + " %");
                }

                if ((doAm >= 60f) && (doAm < 75f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_nhiet_do_do_am) + getText(R.string.tot));
                    textCanhBao.setText("Độ ẩm: Tốt");
//                    text1.setTextColor(Color.parseColor((String) getText(R.color.colorTrungBinh)));
                }
                if (doAm < 60f) {
//                    textCanhBao.setText(getString(R.string.canh_bao_nhiet_do_do_am) + getText(R.string.do_am_thap));
                    textCanhBao.setText("Độ ẩm: Thâp");
//                    text1.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
                }
                if (doAm >= 75f) {
//                    textCanhBao.setText(getString(R.string.canh_bao_nhiet_do_do_am) + getText(R.string.do_am_cao));
                    textCanhBao.setText("Độ ẩm: Cao");
//                    text1.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
                }
                break;
            case 3:
                textView.setText("Khí CO: " + "\n" + tempDuLieu + " (µg/m3)");
                Float CO = Float.parseFloat(tempDuLieu);
                if ((CO >= 0f) && (CO <= 50f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.tot));
                    textCanhBao.setText("Khí CO: tốt");
//                    textCanhBao.setTextColor(Color.parseColor((String) getText(R.color.colorTot)));
                }
                if ((CO >= 51f) && (CO <= 100f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.vua_phai));
                    textCanhBao.setText("Khí CO: vừa phải");
//                    textCanhBao.setTextColor(Color.parseColor((String) getText(R.color.colorTrungBinh)));
                }
                if ((CO >= 101f) && (CO <= 150f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.khong_lanh_manh));
                    textCanhBao.setText("Khí CO: không lành mạnh");
//                    textCanhBao.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
                }
                if ((CO >= 151f) && (CO <= 200f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.khong_khoe));
                    textCanhBao.setText("Khí CO: không khỏe");
//                    textCanhBao.setTextColor(Color.parseColor((String) getText(R.color.colorKhongKhoe)));
                }
                if ((CO >= 201f) && (CO <= 300f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.rat_khong_khoe));
                    textCanhBao.setText("Khí CO: rất không khỏe");
//                    textCanhBao.setTextColor(Color.parseColor((String) getText(R.color.colorRatKhongKhoe)));
                }
                if ((CO >= 301f) && (CO <= 500f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_khi_co) + getText(R.string.nguy_hiem));
                    textCanhBao.setText("Khí CO: nguy hiểm");
//                    textCanhBao.setTextColor(Color.parseColor((String) getText(R.color.colorNguyHiem)));
                }
                break;
            case 4:
                textView.setText("Bụi PM 2.5 " + "\n" + tempDuLieu + " PPM");
                Float buiPM = Float.parseFloat(tempDuLieu);
                if ((buiPM >= 0f) && (buiPM <= 15.4f)) {
//                    textCanhBao.setText("Bụi PM 2.5: " + getText(R.string.tot));
                    textCanhBao.setText("Bụi PM 2.5: tốt");
//                    text3.setTextColor(Color.parseColor((String) getText(R.color.colorTot)));
                }
                if ((buiPM >= 15.5f) && (buiPM <= 40.4f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.vua_phai));
                    textCanhBao.setText("Bụi PM 2.5: vừa phải");
//                    text3.setTextColor(Color.parseColor((String) getText(R.color.colorTrungBinh)));
                }
                if ((buiPM >= 40.5f) && (buiPM <= 65.4f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.khong_lanh_manh));
                    textCanhBao.setText("Bụi PM 2.5: không lành mạnh");
//                    text3.setTextColor(Color.parseColor((String) getText(R.color.colorKhongLanhManh)));
                }
                if ((buiPM >= 65.5f) && (buiPM <= 150.4f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.khong_khoe));
                    textCanhBao.setText("Bụi PM 2.5: không khỏe");
//                    text3.setTextColor(Color.parseColor((String) getText(R.color.colorKhongKhoe)));
                }
                if ((buiPM >= 150.5f) && (buiPM <= 250.4f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.rat_khong_khoe));
                    textCanhBao.setText("Bụi PM 2.5: rất không khỏe");
//                    text3.setTextColor(Color.parseColor((String) getText(R.color.colorRatKhongKhoe)));
                }
                if ((buiPM >= 250.5f) && (buiPM <= 350.4f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.nguy_hiem));
                    textCanhBao.setText("Bụi PM 2.5: nguy hiểm");
//                    text3.setTextColor(Color.parseColor((String) getText(R.color.colorNguyHiem)));
                }
                if ((buiPM >= 350.5f) && (buiPM <= 500.4f)) {
//                    textCanhBao.setText(getString(R.string.canh_bao_bui_PM2_5) + getText(R.string.rat_nguy_hiem));
                    textCanhBao.setText("Bụi PM 2.5: rất nguy hiểm");
//                    text3.setTextColor(Color.parseColor((String) getText(R.color.colorRatNguyHiem)));
                }
                break;
            default:
        }
    }

    private float getRandomValue() {
        float value = new Random().nextFloat() * 7 + 35;
//        Log.i("MainActivity", "current value: " + value);
        return value;
    }
}