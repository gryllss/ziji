package com.example.s.x5x5x5x55x;

import android.content.Intent;
import android.media.tv.TvContentRating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.nio.file.FileVisitOption;

public class LoginActivity extends AppCompatActivity {

    private ImageButton mBack;
    private EditText mLoginPhoneNumber,mLoginPassword;
    private Button mLogin;
    private TextView mLoginToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBack = (ImageButton)findViewById(R.id.btnBack1);
        mLoginPhoneNumber = (EditText) findViewById(R.id.et_loginphonenumber);
        mLoginPassword = (EditText) findViewById(R.id.et_loginpassword);
        mLogin = (Button) findViewById(R.id.login);
        mLoginToSignUp = (TextView)findViewById(R.id.logintosignup);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLoginToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
