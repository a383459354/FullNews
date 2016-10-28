package com.fullnews.frament;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zh.fullnews.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etPwd;
    private EditText etRePwd;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        etRePwd = (EditText) findViewById(R.id.et_re_pwd);
        btnRegister = (Button) findViewById(R.id.btn_register);
        tvLogin = (TextView) findViewById(R.id.tv_login);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        String rePwd = etRePwd.getText().toString().trim();

        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(rePwd)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if (!pwd.equals(rePwd)){
            Toast.makeText(this, "两次输入密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
        }else{

            finish();
        }

        // TODO validate success, do something


    }
}
