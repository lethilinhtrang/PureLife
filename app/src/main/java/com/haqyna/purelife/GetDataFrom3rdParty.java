package com.haqyna.purelife;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.provider.Settings.System.getString;

public class GetDataFrom3rdParty extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    Context context;
    TextView text_nhietdo;
    String tempNhietDo = "";

    RequestQueue requestQueue;

    //    private ItemsAdapter mAdapter;
//
//    private List<Item> itemsList;
//
    public GetDataFrom3rdParty(Context context, TextView text_nhietdo) {
        this.context = context;
        this.text_nhietdo = text_nhietdo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Hiển thị Dialog khi bắt đầu xử lý.
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("EaSu");
        progressDialog.setMessage("Đang tìm giá...");
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

//        try {
//            JSONArray jArray = new JSONArray(aString);
//            for (int i = 0; i < jArray.length(); i++) {
//
//                JSONObject jObject = jArray.getJSONObject(i);
//
//                String name = jObject.getString("name");
//                String tab1_text = jObject.getString("tab1_text");
//                int active = jObject.getInt("active");
//
//            } // End Loop
//            this.progressDialog.dismiss();
//        } catch (JSONException e) {
//            Log.e("JSONException", "Error: " + e.toString());
//        }

//        Document document = Jsoup.parse(aString);
//
//        Elements divItems = document.getElementsByAttribute("data-product-id");
//        for (Element divItem : divItems) {
//
//            String name = divItem.attr("data-product-name");
//            String price = divItem.attr("data-price");
//
//            Element img = divItem.getElementsByTag("img").first();
//            String imgSrc = img.attr("src");
//
//            Item item = new Item();
//            item.setItem(name);
//            item.setPrice(Integer.valueOf(price));
//            item.setImage(imgSrc);
//            itemsList.add(item);
//        }
//
//        mAdapter = new ItemsAdapter(context, itemsList);
//        recyclerView.setAdapter(mAdapter);

        requestQueue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, aString, null,
                new Response.Listener<JSONArray>() {
                    @SuppressLint({"ResourceType", "SetTextI18n"})
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                        int l = response.toString().length();
                        tempNhietDo = response.toString().substring(2, l - 2);
                        text_nhietdo.setText("Nhiệt độ" + "\n" + tempNhietDo + " °C");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
