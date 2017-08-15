package com.example.administrator.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.application.activity.HomeActivity;
import com.example.administrator.application.commons.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.et_Username)
    EditText etUsername;
    @BindView(R.id.et_Password)
    EditText etPassword;
    @BindView(R.id.btn_Register)
    Button btnRegister;
    @BindView(R.id.btn_Login)
    Button btnLogin;


    ActivityUtils mActivityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
         mActivityUtils=new ActivityUtils(MainActivity.this);
    }

    @OnClick({R.id.btn_Register,R.id.btn_Login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Register:
                mActivityUtils.showToast("注册成功");
                break;
            case R.id.btn_Login:
                mActivityUtils.startActivity(HomeActivity.class);
                break;
        }
    }
}
