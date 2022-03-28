package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {
    public EditText usuario,contraseña;
    public ImageButton logeate;
    RequestQueue requestQueue;
    String username;
    String pase;
    ProgressDialog progressDialog;
    String HttpUrl = "https://demagogical-neutron.000webhostapp.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        usuario=findViewById(R.id.usuario_r);
        contraseña=findViewById(R.id.pasword_r);
        logeate=findViewById(R.id.buton_login);
        requestQueue = Volley.newRequestQueue(registro.this);
        progressDialog = new ProgressDialog(registro.this);

        logeate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("se esta actualizando la base de datos con tus datos");
                progressDialog.show();
                losvalores();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                progressDialog.dismiss();
                                Toast.makeText(registro.this, "Se ha registrado correctamente", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        },
                        new Response.ErrorListener() {
                            private VolleyError error;
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                progressDialog.dismiss();
                                Toast.makeText(registro.this, "Ha fallado el registro", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("usuario",username);
                        params.put("contra",pase);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(registro.this);
                requestQueue.add(stringRequest);
                usuario.setText("");
                contraseña.setText("");
            }
        });



    }
    public void losvalores(){
        username = usuario.getText().toString().trim();
        pase = contraseña.getText().toString().trim();
    }
}