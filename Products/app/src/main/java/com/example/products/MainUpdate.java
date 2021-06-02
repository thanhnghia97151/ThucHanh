package com.example.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainUpdate extends AppCompatActivity {
    Button btnback;
    Button btnsave;
    EditText edittype,editprice,editcountry;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateproduct);
        //
        btnback = findViewById(R.id.btntomanager);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainUpdate.this,MainShowProduct.class));
            }
        });
        //
        Intent intent = getIntent();
        String id = intent.getExtras().getString("id_update");
        String type=intent.getExtras().getString("type");
        String price=intent.getExtras().getString("price");
        String country=intent.getExtras().getString("country");
        //
        edittype = findViewById(R.id.editupdatetype);
        editprice = findViewById(R.id.editupdateprice);
        editcountry = findViewById(R.id.editupdatecountry);
        //
        edittype.setText(type.replace("Type:",""));
        editprice.setText(price.replace("Price:",""));
        editcountry.setText(country.replace("Country:",""));
        //
        btnsave = findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutApi("https://60ad917480a61f00173312ce.mockapi.io/user",id,edittype.getText().toString(),editprice.getText().toString(),editcountry.getText().toString());
            }
        });
    }
    private void PutApi(String url,String id,String type, String price, String country){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' +id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainUpdate.this, "Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainUpdate.this, MainShowProduct.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainUpdate.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("type", type);
                params.put("price", price);
                params.put("country",country);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
