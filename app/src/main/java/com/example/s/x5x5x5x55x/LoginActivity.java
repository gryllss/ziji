package com.example.s.x5x5x5x55x;

import android.content.Intent;
import android.media.tv.TvContentRating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s.x5x5x5x55x.Bmob.MyUser;
import com.example.s.x5x5x5x55x.utils.DateAndString;

import java.nio.file.FileVisitOption;
import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class LoginActivity extends AppCompatActivity {

    private ImageButton mBack;
    private EditText mLoginPhoneNumber, mLoginPassword;
    private Button mLogin;
    private TextView mLoginToSignUp;
    private Boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "2a654d2984b42dffb0a329dcc7189b4d");
        mBack = (ImageButton) findViewById(R.id.btnBack1);
        mLoginPhoneNumber = (EditText) findViewById(R.id.et_loginphonenumber);
        mLoginPassword = (EditText) findViewById(R.id.et_loginpassword);
        mLogin = (Button) findViewById(R.id.login);
        mLoginToSignUp = (TextView) findViewById(R.id.logintosignup);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mLoginToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final MyUser myUser = new MyUser();
                    String userPhone = mLoginPhoneNumber.getText().toString();
                    String userPassword = mLoginPassword.getText().toString();
                    myUser.setUsername(userPhone);
                    myUser.setPassword(userPassword);
                    myUser.login(LoginActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast toast = Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            isLogin =true;
                            finish();

                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast toast = Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }


                    });


            }
        });





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isLogin){

            MyUser myUser = BmobUser.getCurrentUser(LoginActivity.this,MyUser.class);
            myUser.setCurrentTimeMillisVer(String.valueOf(System.currentTimeMillis()));
            myUser.update(LoginActivity.this, myUser.getObjectId(), new UpdateListener() {
                @Override
                public void onSuccess() {


                }

                @Override
                public void onFailure(int i, String s) {


                }
            });

//            BmobUser newUser = new BmobUser();
//            newUser = BmobUser.getCurrentUser(LoginActivity.this);
//            newUser.setEmail("12344565@163.com");
////                BmobUser bmobUser = BmobUser.getCurrentUser(getActivity());
//            newUser.update(LoginActivity.this, new UpdateListener() {
//                @Override
//                public void onSuccess() {
//                    Toast.makeText(LoginActivity.this, "yes", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(int i, String s) {
//                    Toast.makeText(LoginActivity.this, "no", Toast.LENGTH_SHORT).show();
//                }
//            });
            isLogin = false;
        }

    }
}
