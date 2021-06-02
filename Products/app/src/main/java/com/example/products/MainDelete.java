package com.example.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainDelete extends AppCompatActivity {
    Button btnyes;
    Button btncancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_delete);
        //
        btncancel = findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainDelete.this,MainShowProduct.class));
            }
        });
        //
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id_delete");
        //
        btnyes = findViewById(R.id.btnyes);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainDelete.this,""+id,Toast.LENGTH_SHORT).show();
                DeleteApi("https://60ad917480a61f00173312ce.mockapi.io/user",id);
            }
        });
    }

    private void DeleteApi(String url, String id){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' +id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainDelete.this, "Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainDelete.this,MainShowProduct.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainDelete.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
