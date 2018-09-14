package com.example.s.x5x5x5x55x;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TimeExchangeActivity extends AppCompatActivity {

    private TextView mDuihuanPhone;
    private EditText mDuihuanma;
    private Button mDuihuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_exchange);
        mDuihuanPhone = (TextView) findViewById(R.id.tv_duihuan_phonenumber);
        mDuihuanma = (EditText)findViewById(R.id.et_duihuanma);
        mDuihuan = (Button)findViewById(R.id.duihuan);
    }
}
