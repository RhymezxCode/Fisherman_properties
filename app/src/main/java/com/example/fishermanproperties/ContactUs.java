package com.example.fishermanproperties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ContactUs extends AppCompatActivity implements View.OnClickListener {
    EditText email_address;
    EditText phone_number;
    EditText properties_details;
    EditText full_name;
    EditText pass;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        full_name = findViewById(R.id.full_name);
        email_address = findViewById(R.id.email_address);
        phone_number = findViewById(R.id.phone_number);
        pass = findViewById(R.id.password);
        properties_details = findViewById(R.id.properties_details);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

//    private void InsertContact() {
//        final String name = full_name.getText().toString().trim();
//        final String email = email_address.getText().toString().trim();
//        final String phone = phone_number.getText().toString().trim();
//        final String properties = properties_details.getText().toString().trim();
//        progressDialog.setMessage("Request sent successfully!!!");
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(getApplicationContext(), jsonObject.getString("Message"), Toast.LENGTH_LONG).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.hide();
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//
//
//                }) {
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Auth-token", "xxxxxxxxxxxxxxx");
//                return headers;
//            }
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("fullname", name);
//                params.put("email", email);
//                params.put("properties", properties);
//                params.put("phone_number", phone);
//                return params;
//            }
//
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//
//    }

    private void contact_us(final String fullname,final String email, final String properties, final String phone) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        assert firebaseUser != null;
        String userid = firebaseUser.getUid();
        DatabaseReference ref = database.getReference("Contacts").child(userid);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("fullname", fullname);
        hashMap.put("email", email);
        hashMap.put("properties", properties);
        hashMap.put("phone number", phone);

        ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ContactUs.this, "All your details has been sent to the database, we will get back to you ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ContactUs.this, NavigationTest.class));
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == submit) {
            final String fullname = full_name.getText().toString().trim();
            final String email = email_address.getText().toString().trim();
            final String properties = properties_details.getText().toString().trim();
            final String phone = phone_number.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(fullname) || TextUtils.isEmpty(properties) || TextUtils.isEmpty(phone)) {
                Toast.makeText(ContactUs.this, "All fields are required", Toast.LENGTH_LONG).show();
            } else {
                contact_us(fullname,email, properties, phone);
            }


        }
    }
}
