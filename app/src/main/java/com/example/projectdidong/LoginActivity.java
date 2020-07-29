package com.example.projectdidong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnCancel, btnLogin;
    CheckBox cbSave;
    RadioButton radGiangVien, radPhuHuynh, radSinhVien;
    RadioGroup radGroup;
    EditText edtTK, edtPass;
    TextView tvError;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.layout_login );
        setControl ( );
        setEvent ( );

    }

    private void setEvent ( ) {
        btnLogin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if (radGiangVien.isChecked ( )) {
                    Intent intent = new Intent ( LoginActivity.this , GiangVienActivity.class );
                    startActivity ( intent );

//                    if(!edtTK.getText ().toString ().equals ( "gv1" ) || (!edtPass.getText ().toString ().equals ( "123" ))){
//                        tvError.setText ( "Tài khoản hoặc mật khẩu sai !!! " );
//
//                    }else {
//
//                    }
                } else if (radSinhVien.isChecked ( )) {
                    Intent intent = new Intent ( LoginActivity.this , SinhVienActivity.class );
                    startActivity ( intent );
                } else if (radPhuHuynh.isChecked ( )) {
                    Intent intent = new Intent ( LoginActivity.this , PhuHuynhActivity.class );
                    startActivity ( intent );
                }
            }
        } );
        btnCancel.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                AlertDialog.Builder builder = new AlertDialog.Builder ( LoginActivity.this , android.R.style.Theme_DeviceDefault_Light_Dialog );
                builder.setTitle ( "Bạn có thật sự muốn thoát khỏi ứng dụng?" );
                builder.setPositiveButton ( "Yes" , new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick ( DialogInterface dialog , int which ) {
                        onBackPressed ( );
                    }
                } );
                builder.setNegativeButton ( "No" , new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick ( DialogInterface dialog , int which ) {
                    }
                } );
                builder.show ( );
            }
        } );
    }

    private void setControl ( ) {
        btnLogin = findViewById ( R.id.btnLogin );
        btnCancel = findViewById ( R.id.btnCancel );
        radGiangVien = (RadioButton) findViewById ( R.id.cbGiangVien );
        radPhuHuynh = (RadioButton) findViewById ( R.id.cbPhuHuynh );
        radSinhVien = (RadioButton) findViewById ( R.id.cbSinhVien );
        radGroup = (RadioGroup) findViewById ( R.id.radGroup );
        cbSave = (CheckBox) findViewById ( R.id.cbSave );
        edtTK = (EditText) findViewById ( R.id.edtUser );
        edtPass = (EditText) findViewById ( R.id.edtPass );
        tvError = findViewById ( R.id.tvError );
    }

}