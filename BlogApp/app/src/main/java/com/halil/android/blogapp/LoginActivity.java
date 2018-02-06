package com.halil.android.blogapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void doLogin(View view){
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordeditText = findViewById(R.id.passwordEditText);
        String username = usernameEditText.getText().toString();
        String password = passwordeditText.getText().toString();
        if(username.equals("admin")&& password.equals("admin")){
            Intent loginDoneIntent = new Intent(this,ArticlesListActivity.class);
            startActivity(loginDoneIntent);
        }
    }
}
