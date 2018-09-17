package com.example.s.x5x5x5x55x;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s.x5x5x5x55x.Bmob.ActivationCode;
import com.example.s.x5x5x5x55x.Bmob.MyUser;
import com.example.s.x5x5x5x55x.utils.DateAndString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class TimeExchangeActivity extends AppCompatActivity {

    private TextView mDuihuanPhone;
    private EditText mDuihuanma;
    private Button mDuihuan;
    private String userPhone;
    private String duiHuanMa;


    private Boolean isUsedCode = false;
    private String objectId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(TimeExchangeActivity.this, "2a654d2984b42dffb0a329dcc7189b4d");
        setContentView(R.layout.activity_time_exchange);
        mDuihuanPhone = (TextView) findViewById(R.id.tv_duihuan_phonenumber);
        mDuihuanma = (EditText) findViewById(R.id.et_duihuanma);
        mDuihuan = (Button) findViewById(R.id.duihuan);
        MyUser myUser = BmobUser.getCurrentUser(TimeExchangeActivity.this, MyUser.class);
        userPhone = myUser.getUsername();
        StringBuilder sb = new StringBuilder(userPhone);
        sb.replace(3, 7, "****");
        mDuihuanPhone.setText(sb.toString());


        mDuihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                duiHuanMa = mDuihuanma.getText().toString();
                BmobQuery<ActivationCode> query = new BmobQuery<ActivationCode>();
                query.addWhereEqualTo("activationCode", duiHuanMa);
                query.findObjects(TimeExchangeActivity.this, new FindListener<ActivationCode>() {
                    @Override
                    public void onSuccess(List<ActivationCode> list) {
                        String str = "";
                        for (ActivationCode activationCode1 : list) {
                            str = activationCode1.getObjectId();
                            isUsedCode = activationCode1.getUsed();


                        }
                        objectId = str;
//                        Toast.makeText(TimeExchangeActivity.this, objectId, Toast.LENGTH_SHORT).show();
                        if (!objectId.equals("") && !isUsedCode) {

                            ActivationCode activationCode = new ActivationCode();
                            MyUser newUser = new MyUser();
                            newUser = BmobUser.getCurrentUser(TimeExchangeActivity.this, MyUser.class);
                            activationCode.setUsed(true);
                            activationCode.setUsederPhone(newUser.getUsername());
                            activationCode.setJiHuoTime(DateAndString.date2Str(DateAndString.millis2Date(System.currentTimeMillis())));
                            activationCode.update(TimeExchangeActivity.this, objectId, new UpdateListener() {
                                @Override
                                public void onSuccess() {

                                    MyUser newUser = new MyUser();
                                    newUser = BmobUser.getCurrentUser(TimeExchangeActivity.this, MyUser.class);
                                    newUser.setOutTime(DateAndString.dateAddYear(newUser.getOutTime()));
                                    newUser.update(TimeExchangeActivity.this, newUser.getObjectId(), new UpdateListener() {
                                        @Override
                                        public void onSuccess() {
                                            Toast.makeText(TimeExchangeActivity.this, "激活成功", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }

                                        @Override
                                        public void onFailure(int i, String s) {
                                            Toast.makeText(TimeExchangeActivity.this, "激活码异常，请重新获取", Toast.LENGTH_SHORT).show();

                                        }
                                    });


                                }

                                @Override
                                public void onFailure(int i, String s) {

                                }
                            });


                        } else {
                            if (isUsedCode) {
                                Toast.makeText(TimeExchangeActivity.this, "激活码已被使用", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(TimeExchangeActivity.this, "激活码不存在，请输入正确的激活码", Toast.LENGTH_SHORT).show();
                            }

                        }


                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(TimeExchangeActivity.this, "no", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


    }


}
