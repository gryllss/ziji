package com.example.s.x5x5x5x55x;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private ImageButton mBack;
    private EditText mLoginPhoneNumber,mLoginPassword,mReInputLoginPassword;
    private Button mSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mBack = (ImageButton)findViewById(R.id.btnBack1);
        mLoginPhoneNumber = (EditText) findViewById(R.id.et_signupphonenumber);
        mLoginPassword = (EditText) findViewById(R.id.et_signuppassword);
        mReInputLoginPassword = (EditText) findViewById(R.id.et_reinputsignuppassword);
        mSignUp = (Button)findViewById(R.id.signup);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
