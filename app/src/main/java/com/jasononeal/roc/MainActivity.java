package com.jasononeal.roc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class MainActivity extends BaseActivity  {
    Button loginButton, cancelButton;
    EditText emailField, passwordField;
    TextView goRegister, errors;
    @Override
    protected int getContentView() {
        return R.layout.main_activity;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        loginButton = (Button)findViewById(R.id.loginBtn);
        cancelButton = (Button)findViewById(R.id.loginCancel);
        emailField = (EditText)findViewById(R.id.loginEmail);
        passwordField = (EditText)findViewById(R.id.loginPassword);
        goRegister = (TextView)findViewById(R.id.no_account);
        errors = (TextView)findViewById(R.id.loginErrors);

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            if(email.equals("") || password.equals("")){
                errors.setText(getText(R.string.missing_fields));
            } else {
                login(email, password);
            }
        });

        cancelButton.setOnClickListener(v -> {
            emailField.setText("");
            passwordField.setText("");
        });

        goRegister.setOnClickListener(view -> {
            Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    public void login(String email, String password){
        /*
        API api = new API();
        api.addParams("email", email);
        api.addParams("password", password);
        api.post("/user/login", response -> Log.i("login response", response.toString()));

        */
        boolean valid = email.equals("jason.allen.oneal@gmail.com") && password.equals("P455w3rd");

        String jsonResult;
        Gson gson = new Gson();
        TypeToken<Map<String, Object>> mapType = new TypeToken<Map<String, Object>>(){};
        JSONObject result;
        if(valid){
            jsonResult = "{\"status\": \"ok\", \"data\": {\"message\": \"Successfully logged in.\", \"result\": {\"user\": {\"id\": 1, \"email\": \"jason.allen.oneal@gmail.com\", \"admin\": true, \"lastRealm\": 1, \"joined\": \"2024-01-06 07:46:12\", \"firstRun\": true}}}";
        } else {
            jsonResult = "{\"status\": \"error\", \"data\": {\"message\": \"Incorrect credentials\"}}";
        }
        result = gson.fromJson(jsonResult, (Type) mapType);

        try {
            String dataJsonStr = gson.toJson(result.get("data").toString());
            Map<String, Object> data = gson.fromJson(dataJsonStr, (Type) mapType);
            if (result.getString("status").equals("ok")) {
                String resultJsonStr = gson.toJson(data.get("result"));
                Map<String, Object> resultJson = gson.fromJson(resultJsonStr, (Type) mapType);
                String userJsonStr = gson.toJson(resultJson.get("user"));
                Map<String, Object> user = gson.fromJson(userJsonStr, (Type) mapType);
                this.jwt.createToken("user", user);
                this.showToast((String)data.get("message"));
                Intent registerIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(registerIntent);
            }else{
                this.showToast((String)data.get("message"));
            }
        } catch(JSONException e){
            Log.e("user status check", Objects.requireNonNull(e.getMessage()));
        }
    }
}