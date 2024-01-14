package com.jasononeal.roc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterActivity extends BaseActivity  {
    Button registerButton, cancelButton;
    EditText emailField, passwordField;
    TextView goLogin, errors;
    @Override
    protected int getContentView() {
        return R.layout.register;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        registerButton = (Button)findViewById(R.id.registerBtn);
        cancelButton = (Button)findViewById(R.id.registerCancel);
        emailField = (EditText)findViewById(R.id.registerEmail);
        passwordField = (EditText)findViewById(R.id.registerPassword);
        goLogin = (TextView)findViewById(R.id.go_login);
        errors = (TextView)findViewById(R.id.registerErrors);

        registerButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            if(email.equals("") || password.equals("")){
                errors.setText(getText(R.string.missing_fields));
            } else {
                register(email, password);
            }
        });

        cancelButton.setOnClickListener(v -> {
            emailField.setText("");
            passwordField.setText("");
        });

        goLogin.setOnClickListener(view -> {
            Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(registerIntent);
        });
    }

    public void register(String email, String password){
        /*
        API api = new API();
        api.addParams("email", email);
        api.addParams("password", password);
        api.post("/user/register", response -> Log.i("register response", response.toString()));

         */

    }
}