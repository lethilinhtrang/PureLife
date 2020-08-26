package com.haqyna.purelife;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetDoC extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    Context context;
    TextView text_nhietdo;
    String tempNhietDo = "";

    public GetDoC(Context context, TextView text_nhietdo) {
        this.context = context;
        this.text_nhietdo = text_nhietdo;
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
            tempNhietDo = aString.substring(2, l - 3);
        } catch (Exception ex) {
            tempNhietDo = "30";
        }


        text_nhietdo.setText("Nhiệt độ" + "\n" + tempNhietDo + " °C");
    }
}
