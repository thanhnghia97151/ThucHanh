package com.example.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainShowProduct extends AppCompatActivity {
    Button btnback;
    RecyclerView recyclerView;
    ArrayList arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_product);
        //
        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainShowProduct.this,MainManager.class));
            }
        });
        //
        recyclerView = findViewById(R.id.rcvshow);
        arrayList = new ArrayList();
        GetArrayJson("https://60ad917480a61f00173312ce.mockapi.io/user",arrayList,recyclerView);

    }

    private void GetArrayJson(String url, ArrayList arr, RecyclerView rcv){
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for(int i=0; i<response.length(); i++){
                                    try {
                                        JSONObject object = (JSONObject) response.get(i);
                                        arr.add(new Product(""+i+1,
                                                            "Type"+object.getString("type").toString(),
                                                            "Price: "+object.getString("price").toString(),
                                                                    "Country: "+object.getString("country").toString()
                                        ));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                rcv.clearFocus();
                                CustomAdapter adt = new CustomAdapter(MainShowProduct.this,arr);
                                rcv.setLayoutManager(new GridLayoutManager(MainShowProduct.this,1));
                                rcv.setAdapter(adt);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainShowProduct.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
