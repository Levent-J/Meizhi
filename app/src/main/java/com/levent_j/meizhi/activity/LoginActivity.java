package com.levent_j.meizhi.activity;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.TextView;

import com.levent_j.meizhi.R;
import com.levent_j.meizhi.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by levent_j on 16-4-24.
 */
public class LoginActivity extends BaseActivity{
    @Bind(R.id.et_username)
    EditText mUsername;
    @Bind(R.id.ti_usernameWrapper)
    TextInputLayout mUsernameWrapper;
    @Bind(R.id.et_password)
    EditText mPassword;
    @Bind(R.id.ti_passwordWrapper)
    TextInputLayout mPasswordWrapper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        mUsernameWrapper.setHint("输入账号");
        mPasswordWrapper.setHint("输入密码");
    }

    @Override
    protected void setListener() {

    }
}
