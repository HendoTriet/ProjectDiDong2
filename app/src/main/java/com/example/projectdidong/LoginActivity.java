package com.example.projectdidong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button btnCancel, btnLogin;
    CheckBox cbSave;
    EditText edtTK, edtPass;
    TextView tvError;
    FirebaseAuth fAuth;


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

                final String sTK = edtTK.getText ( ).toString ( ).trim ( );
                final String sMK = edtPass.getText ( ).toString ( ).trim ( );
                fAuth.signInWithEmailAndPassword ( sTK , sMK ).addOnCompleteListener ( new OnCompleteListener<AuthResult> ( ) {
                    @Override
                    public void onComplete ( @NonNull Task<AuthResult> task ) {
                        if (!task.isSuccessful ( )) {
                            tvError.setText ( "Tài khoản hoặc mật khẩu sai !!! " );
                        }else {
                            startActivity ( new Intent ( getApplication ( ) , LopActivity.class ) );
                        }
                    }
                } );
            }
        } );

        btnCancel.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                AlertDialog.Builder builder = new AlertDialog.Builder ( LoginActivity.this , android.R.style.Theme_DeviceDefault_Light_Dialog );
                builder.setTitle ( "Bạn có thật sự muốn thoát khỏi ứng dụng?" );
                builder.setPositiveButton ( "Có" , new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick ( DialogInterface dialog , int which ) {
                        onBackPressed ( );
                    }
                } );
                builder.setNegativeButton ( "Không" , new DialogInterface.OnClickListener ( ) {
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
        cbSave = (CheckBox) findViewById ( R.id.cbSave );
        edtTK = (EditText) findViewById ( R.id.edtUser );
        edtPass = (EditText) findViewById ( R.id.edtPass );
        tvError = findViewById ( R.id.tvError );
        fAuth = FirebaseAuth.getInstance ( );
    }

}