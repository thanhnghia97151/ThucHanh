package com.example.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainUpdateProduct extends AppCompatActivity {
    Button btnback;
    Button btncreate;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        //
        btnback = findViewById(R.id.btnupdatetomanager);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainUpdateProduct.this, MainManager.class));
            }
        });
        //
        btncreate = findViewById(R.id.btncreate);
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostApi("https://60ad917480a61f00173312ce.mockapi.io/user");
            }
        });
    }

    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(MainUpdateProduct.this, "Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainUpdateProduct.this,MainShowProduct.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainUpdateProduct.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                EditText type,price,country;
                type = findViewById(R.id.editcreatetype);
                price = findViewById(R.id.editcreateprice);
                country =findViewById(R.id.editcreatecountry);
                HashMap<String, String>
                        params = new HashMap<>();
                params.put("type", type.getText().toString());
                params.put("price", price.getText().toString());
                params.put("country",country.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
