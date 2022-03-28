package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public EditText username,password;
    public ImageButton start, register;
    String usernames,pases;
    String url = "https://demagogical-neutron.000webhostapp.com/inicio.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.usuario_i);
        password=findViewById(R.id.contra_i);
        start=findViewById(R.id.buton_i_i);
        register=findViewById(R.id.buton_r_i);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,registro.class);
                startActivity(intent);
            }

        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciar();

            }
        });

    }
    public void iniciar(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere,validando");

        progressDialog.show();

        usernames = username.getText().toString().trim();
        pases = password.getText().toString().trim();


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                if(response.equalsIgnoreCase("Ingreso correctamente")){

                    username.setText("");
                    password.setText("");
                    startActivity(new Intent(getApplicationContext(),welcome.class));
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("usuario",usernames);
                params.put("contra",pases);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

}