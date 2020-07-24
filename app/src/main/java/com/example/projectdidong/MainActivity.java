package com.example.projectdidong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnCancel, btnLogin;
    CheckBox cbSave;
    RadioButton radGiangVien, radPhuHuynh, radSinhVien;
    RadioGroup radGroup;
    EditText edtTK, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    private void setEvent() {
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edtTK.getText().toString();
//                edtPass.getText().toString();
//                if (edtTK.equals("admin") || edtPass.equals("123456")) {
//                    finish();
//                }
//            }
//        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có thật sự muốn thoát khỏi app");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
//        cbSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(buttonView.isChecked()){
//                    SharedPreferences sharedPreferences = getSharedPreferences("OK",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("cbSave","true");
//                    editor.apply();
//                }else{
//                    SharedPreferences sharedPreferences = getSharedPreferences("OK",MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("cbSave","false");
//                    editor.apply();
//                }
//            }
//        });


        //
    }

    private void setControl() {
        btnCancel = findViewById(R.id.btnCancel);
        radGiangVien = (RadioButton) findViewById(R.id.cbGiangVien);
        radPhuHuynh = (RadioButton) findViewById(R.id.cbPhuHuynh);
        radSinhVien = (RadioButton) findViewById(R.id.cbSinhVien);
        radGroup = (RadioGroup) findViewById(R.id.radGroup);
        cbSave = (CheckBox) findViewById(R.id.cbSave);
        edtTK = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
    }

}