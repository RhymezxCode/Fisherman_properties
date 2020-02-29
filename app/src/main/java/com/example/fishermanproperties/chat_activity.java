package com.example.fishermanproperties;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


import static com.google.firebase.database.FirebaseDatabase.getInstance;


public class chat_activity extends AppCompatActivity {
    Bundle bundle;
    String property;
    TextView message_admin_message;
    TextView message_admin_user;
    TextView messageText;
    TextView messageUser;
    TextView messageTime;
    private static final int SIGN_IN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast


            // Load chat room contents
            bundle = getIntent().getExtras();
            if (bundle != null) {
                property = bundle.getString("property");
            }


            if (messageUser != null || messageText != null) {
                message_admin_user.setText("");
                message_admin_message.setText(property);
            }


            displayChatMessages();

        }


        FloatingActionButton fab =
                findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editInput = findViewById(R.id.input);
                String input = editInput.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(chat_activity.this, "All fields are required", Toast.LENGTH_LONG).show();

                } else {
                    // Read the input field and push a new instance
                    // of ChatMessage to the Firebase database
                    getInstance().getReference().push()
                            .setValue(new Chat(editInput.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName()));
                    // Clear the input
                    editInput.setText("");
                }

            }
        });
    }



    @SuppressLint("ClickableViewAccessibility")
    private void displayChatMessages() {
        ListView listOfMessages = findViewById(R.id.list_of_messages);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        assert firebaseUser != null;


        FirebaseListAdapter<Chat> adapter = new FirebaseListAdapter<Chat>(this, Chat.class, R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Chat model, int position) {
                // Get references to the views of message.xml
                messageText = v.findViewById(R.id.message_text);
                messageUser = v.findViewById(R.id.message_user);
                messageTime = v.findViewById(R.id.message_time);
                message_admin_message = v.findViewById(R.id.message_admin_message);
                message_admin_user = v.findViewById(R.id.message_admin_user);
                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(R.string.user);

                message_admin_user.setText(R.string.admin);
                message_admin_message.setText(property);


                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        listOfMessages.setAdapter(adapter);
    }

}
