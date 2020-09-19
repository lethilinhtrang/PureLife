package com.haqyna.purelife;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
        progressDialog.setMessage(context.getString(R.string.du_lieu));
        progressDialog.show();
    }

//    @Override
//    protected String doInBackground(String... strings) {
//        try {
//            StringBuilder sb;
//            // Website này sẽ cho biết địa chỉ IP của bạn.
//            URL url = new URL(strings[0]);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                sb = new StringBuilder();
//                String line;
//                while ((line = br.readLine()) != null) {
//                    sb.append(line).append("\n");
//                }
//
////                Log.e("Dia chi IP ", sb.toString());
//                br.close();
//                return sb.toString();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return null;
//    }

    @Override
    protected String doInBackground(String... strings) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            URL url = new URL(strings[0]);

            URLConnection urlconnection = url.openConnection();

            InputStream inputStream = urlconnection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPostExecute(String aString) {
        super.onPostExecute(aString);
        // Hủy dialog đi.
        progressDialog.dismiss();

        try {
            int l = aString.length();
            tempDuLieu = aString.substring(2, l - 3);
        } catch (Exception ex) {
            Log.v("daLuong", ex.toString());
            tempDuLieu = "90";
        }

        switch (loai) {
            case 0:
                textView.setText(context.getString(R.string.nhiet_do) + "\n" + tempDuLieu + " °C");
                break;
            case 1:
                textView.setText(context.getString(R.string.nhiet_do) + "\n" + tempDuLieu + " °F");
                break;
            case 2:
//                textView.setText("Độ ẩm: " + "\n" + tempDuLieu + " %");
                Float doAm = 0f;
                try {
                    doAm = Float.parseFloat(tempDuLieu);
                    textView.setText(context.getString(R.string.do_am) + "\n" + tempDuLieu + " %");
                } catch (Exception E) {
                    doAm = 90f;
                    textView.setText(context.getString(R.string.do_am) + "\n" + 90 + " %");
                }
                // Cực kì khô hạn
                if ((doAm >= 0f) && (doAm <= 9f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.cuc_ki_kho_han));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorCucKiKhoHan)));
                }
                //Khô hạn
                if ((doAm >= 10f) && (doAm <= 19f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.kho_han));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorKhoHan)));
                }
                // Cực kì thấp
                if ((doAm >= 20f) && (doAm <= 29f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.cuc_ki_thap));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorCucKiThap)));
                }
                //Rất thấp
                if ((doAm >= 30f) && (doAm <= 39f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.rat_thap));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorRatThap)));
                }
                //Thấp
                if ((doAm >= 40f) && (doAm <= 49f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.do_am_thap));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorThap)));
                }
                //trung bình
                if ((doAm >= 50f) && (doAm <= 59f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.vua_phai));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorTrungBinh)));
                }
                //Khá vừa
                if ((doAm >= 60f) && (doAm <= 69f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.kha_vua));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorKhaVua)));
                }
                //Vừa
                if ((doAm >= 70f) && (doAm <= 79f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.vua));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorVua)));
                }
                //Tương đối cao
                if ((doAm >= 80f) && (doAm <= 89f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.tuong_doi_cao));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorTuongDoiCao)));
                }
                //Cao
                if ((doAm >= 90f) && (doAm <= 99f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.cao));
//                    textCanhBao.setText("Độ ẩm: Tốt");
                    textCanhBao.setTextColor(Color.parseColor(context.getString(R.color.colorCao)));
                }
                //Bão hòa
                if (doAm == 100f) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_nhiet_do_do_am) + context.getText(R.string.bao_hoa));
//                    textCanhBao.setText("Độ ẩm: Thâp");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorBaoHoa)));
                }
                break;
            case 3:
                textView.setText(context.getString(R.string.co) + "\n" + tempDuLieu + " PPM");
                Float CO = Float.parseFloat(tempDuLieu);
                if ((CO >= 0f) && (CO <= 50f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_khi_co) + context.getText(R.string.tot));
//                    textCanhBao.setText("Khí CO: tốt");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorTot)));
                }
                if ((CO >= 51f) && (CO <= 100f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_khi_co) + context.getText(R.string.vua_phai));
//                    textCanhBao.setText("Khí CO: vừa phải");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorTrungBinh)));
                }
                if ((CO >= 101f) && (CO <= 150f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_khi_co) + context.getText(R.string.khong_lanh_manh));
//                    textCanhBao.setText("Khí CO: không lành mạnh");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorKhongLanhManh)));
                }
                if ((CO >= 151f) && (CO <= 200f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_khi_co) + context.getText(R.string.khong_khoe));
//                    textCanhBao.setText("Khí CO: không khỏe");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorKhongKhoe)));
                }
                if ((CO >= 201f) && (CO <= 300f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_khi_co) + context.getText(R.string.rat_khong_khoe));
//                    textCanhBao.setText("Khí CO: rất không khỏe");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorRatKhongKhoe)));
                }
                if ((CO >= 301f) && (CO <= 500f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_khi_co) + context.getText(R.string.nguy_hiem));
//                    textCanhBao.setText("Khí CO: nguy hiểm");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorNguyHiem)));
                }
                break;
            case 4:
                textView.setText(context.getString(R.string.bui_pm_25) + "\n" + tempDuLieu + " PPM");
                Float buiPM = Float.parseFloat(tempDuLieu);
                if ((buiPM >= 0f) && (buiPM <= 15.4f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_bui_PM2_5) + context.getText(R.string.tot));
//                    textCanhBao.setText("Bụi PM 2.5: tốt");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorTot)));
                }
                if ((buiPM >= 15.5f) && (buiPM <= 40.4f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_bui_PM2_5) + context.getText(R.string.vua_phai));
//                    textCanhBao.setText("Bụi PM 2.5: vừa phải");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorTrungBinh)));
                }
                if ((buiPM >= 40.5f) && (buiPM <= 65.4f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_bui_PM2_5) + context.getText(R.string.khong_lanh_manh));
//                    textCanhBao.setText("Bụi PM 2.5: không lành mạnh");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorKhongLanhManh)));
                }
                if ((buiPM >= 65.5f) && (buiPM <= 150.4f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_bui_PM2_5) + context.getText(R.string.khong_khoe));
//                    textCanhBao.setText("Bụi PM 2.5: không khỏe");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorKhongKhoe)));
                }
                if ((buiPM >= 150.5f) && (buiPM <= 250.4f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_bui_PM2_5) + context.getText(R.string.rat_khong_khoe));
//                    textCanhBao.setText("Bụi PM 2.5: rất không khỏe");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorRatKhongKhoe)));
                }
                if ((buiPM >= 250.5f) && (buiPM <= 350.4f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_bui_PM2_5) + context.getText(R.string.nguy_hiem));
//                    textCanhBao.setText("Bụi PM 2.5: nguy hiểm");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorNguyHiem)));
                }
                if ((buiPM >= 350.5f) && (buiPM <= 500.4f)) {
                    textCanhBao.setText(context.getString(R.string.canh_bao_bui_PM2_5) + context.getText(R.string.rat_nguy_hiem));
//                    textCanhBao.setText("Bụi PM 2.5: rất nguy hiểm");
                    textCanhBao.setTextColor(Color.parseColor((String) context.getText(R.color.colorRatNguyHiem)));
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
