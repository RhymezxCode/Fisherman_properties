package com.example.fishermanproperties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Register extends AppCompatActivity implements View.OnClickListener{
    EditText editTextUsername, editTextEmail, editTextPassword, editTextPhone, editTextSex;
    private Button buttonSignup;
    ProgressDialog progressDialog;
    private TextView textViewLogin;
    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextSex = findViewById(R.id.editTextSex);
        editTextUsername = findViewById(R.id.editTextUsername);
        buttonSignup = findViewById(R.id.buttonSignup);
        textViewLogin = findViewById(R.id.already);

        progressDialog = new ProgressDialog(this);
        buttonSignup.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();


    }


    private void register(final String username, final String email, final String password, final String sex, final String phone){
     auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
         @Override
         public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){
                 FirebaseUser firebaseUser = auth.getCurrentUser();
                 assert firebaseUser != null;
                 String userid = firebaseUser.getUid();

                 reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                 HashMap<String, String> hashMap = new HashMap<>();
                 hashMap.put("id", userid);
                 hashMap.put("username", username);
                 hashMap.put("email", email);
                 hashMap.put("password", password);
                 hashMap.put("sex", sex);
                 hashMap.put("phone", phone);


                 reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if(task.isSuccessful()){
                             progressDialog.dismiss();
                             FirebaseAuthException e = (FirebaseAuthException )task.getException();

                             assert e != null;
                             Toast.makeText(Register.this, "Registration successful: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(Register.this, Home.class));
                         }
                     }
                 });
             }else{
                 progressDialog.hide();
                 FirebaseAuthException e = (FirebaseAuthException )task.getException();
                 assert e != null;
                 Toast.makeText(Register.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
             }
         }
     });
    }

//    public void Navbtn(View view) {
//
//        Intent intent = new Intent(this, NavigationTest.class);
//        startActivity(intent);
//    }

//    private void registerUserMySql(){

//
//

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            access_token = jsonObject.getString("token");
//                            startActivity(new Intent(Register.this, Home.class));
//                            Toast.makeText(getApplicationContext(), jsonObject.getString("data"), Toast.LENGTH_LONG).show();
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
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("username",  username);
//                params.put("email" , email);
//                params.put("password",  password);
//                params.put("sex" , sex);
//                params.put("phone", phone);
//                return params;
//            }
//        };
//
//        requestQueue = Volley.newRequestQueue(Register.this);
//        requestQueue.add(stringRequest);
//
//
//    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignup) {
            String email = editTextEmail.getText().toString().trim();
            final String username = editTextUsername.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();
            final String sex = editTextSex.getText().toString().trim();
            final String phone = editTextPhone.getText().toString().trim();

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(phone)){
                Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_LONG).show();
            } else if(password.length() < 6){
                Toast.makeText(Register.this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
            }else{

                register(username, email, password, sex, phone);
            }


        }
//            registerUserMySql();
        if(view == textViewLogin)
            startActivity(new Intent(this, Home.class));
    }
}
