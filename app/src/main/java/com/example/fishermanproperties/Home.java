package com.example.fishermanproperties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Home extends AppCompatActivity implements View.OnClickListener {

    EditText Myemail, Mypassword;
    FirebaseAuth auth;
//    ProgressDialog progressDialog;
//    RequestQueue requestQueue;
//    String login = "http://fisher.fedoldagegovtcare.com/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Myemail = findViewById(R.id.Myemail);
        Mypassword = findViewById(R.id.Mypassword);
        Button loginbtn = findViewById(R.id.Loginbtn);
        TextView register = findViewById(R.id.registerhere);
        register.setOnClickListener(this);
        loginbtn.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please wait..");

//        requestQueue = Volley.newRequestQueue(Home.this);
    }




//    private void userLoginWithMySql() {

//        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject jObj = new JSONObject(response);
//                            String username = recurseKeys(jObj, "username");
//                            String email = recurseKeys(jObj, "email");
//                            String phone = recurseKeys(jObj, "phone");
//                            String token = recurseKeys(jObj, "token");
//                            String status = recurseKeys(jObj, "status");
//
//
//
//
//
//
//                            SharedPrefManager.getInstance(getApplicationContext()).getAllDetails(username, email, phone, token);
//
//                            startActivity(new Intent(Home.this, NavigationTest.class));
//                                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_LONG).show();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.hide();
//                        Toast.makeText(getApplicationContext(), error.toString(),
//                                Toast.LENGTH_LONG
//                        ).show();
//
//                    }
//                }
//
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", email);
//                params.put("password", password);
//                return params;
//            }
//        };
//
//        requestQueue = Volley.newRequestQueue(Home.this);
//        requestQueue.add(stringRequest);
//
//
//
//
//    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.registerhere:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.Loginbtn:
                final String email = Myemail.getText().toString().trim();
                final String password = Mypassword.getText().toString().trim();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(Home.this, "All fields are required", Toast.LENGTH_LONG).show();
                }else{
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Home.this, NavigationTest.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(Home.this, "Authentication failed!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
//            userLoginWithMySql();
                break;
        }
    }

//    public static String recurseKeys(JSONObject jObj, String findKey) throws JSONException {
//        String finalValue = "";
//        if (jObj == null) {
//            return "";
//        }
//
//        Iterator<String> keyItr = jObj.keys();
//        Map<String, String> map = new HashMap<>();
//
//        while(keyItr.hasNext()) {
//            String key = keyItr.next();
//            map.put(key, jObj.getString(key));
//        }
//
//        for (Map.Entry<String, String> e : (map).entrySet()) {
//            String key = e.getKey();
//            if (key.equalsIgnoreCase(findKey)) {
//                return jObj.getString(key);
//            }
//
//            // read value
//            Object value = jObj.get(key);
//
//            if (value instanceof JSONObject) {
//                finalValue = recurseKeys((JSONObject)value, findKey);
//            }
//        }
//
//        // key is not found
//        return finalValue;
//    }
}
