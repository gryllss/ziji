package com.example.s.x5x5x5x55x;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s.x5x5x5x55x.Bmob.MyUser;
import com.example.s.x5x5x5x55x.utils.DateAndString;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignUpActivity extends AppCompatActivity {

    private ImageButton mBack;
    private EditText mLoginPhoneNumber,mLoginPassword,mReInputLoginPassword;
    private Button mSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Bmob.initialize(this, "2a654d2984b42dffb0a329dcc7189b4d");
        mBack = (ImageButton)findViewById(R.id.btnBack1);
        mLoginPhoneNumber = (EditText) findViewById(R.id.et_signupphonenumber);
        mLoginPassword = (EditText) findViewById(R.id.et_signuppassword);
        mReInputLoginPassword = (EditText) findViewById(R.id.et_reinputsignuppassword);
        mSignUp = (Button)findViewById(R.id.signup);

       mSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String userPhone = mLoginPhoneNumber.getText().toString();
               String userPasswprd = mLoginPassword.getText().toString();
               String userReInputPasswprd = mReInputLoginPassword.getText().toString();
               if (userPhone.equals("") || userPasswprd.equals("")){
                   Toast toast = Toast.makeText(SignUpActivity.this, "手机号和密码不能为空", Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.CENTER,0,0);
                   toast.show();
               }else{
                   if ( !isChinaPhoneLegal(userPhone)){
                       Toast toast = Toast.makeText(SignUpActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT);
                       toast.setGravity(Gravity.CENTER,0,0);
                       toast.show();
                   }else {
                       if (!isPasswordLegal(userPasswprd)){
                           Toast toast = Toast.makeText(SignUpActivity.this, "请输入至少6位密码", Toast.LENGTH_SHORT);
                           toast.setGravity(Gravity.CENTER,0,0);
                           toast.show();
                       }else if (!userPasswprd.equals(userReInputPasswprd)){
                           Toast toast = Toast.makeText(SignUpActivity.this, "两次密码不一致", Toast.LENGTH_SHORT);
                           toast.setGravity(Gravity.CENTER,0,0);
                           toast.show();
                       }else{
                             MyUser myUser = new MyUser();
                           myUser.setUsername(userPhone);
                           myUser.setPassword(userPasswprd);
                           myUser.setOutTime(DateAndString.date2Str(new Date(new Date().getTime() + 2*24*60*60*1000)));
                           myUser.signUp(SignUpActivity.this, new SaveListener() {
                               @Override
                               public void onSuccess() {
                                   Toast toast = Toast.makeText(SignUpActivity.this, "注册成功", Toast.LENGTH_SHORT);
                                   toast.setGravity(Gravity.CENTER,0,0);
                                   toast.show();
                                   finish();

                               }

                               @Override
                               public void onFailure(int i, String s) {

                                   ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                                   NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                                   if(networkInfo ==null && !networkInfo.isAvailable()){
                                       Toast toast = Toast.makeText(SignUpActivity.this, "注册失败，请检查网络或稍后重试", Toast.LENGTH_SHORT);
                                       toast.setGravity(Gravity.CENTER,0,0);
                                       toast.show();

                                   }

                                   Toast toast = Toast.makeText(SignUpActivity.this, "手机号已被注册，请直接登陆", Toast.LENGTH_SHORT);
                                   toast.setGravity(Gravity.CENTER,0,0);
                                   toast.show();

                               }
                           });

                       }
                   }
               }







//注意：不能用save方法进行注册


           }
       });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^(13|14|15|16|17|18|19)\\d{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isPasswordLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^.{6,20}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


}
