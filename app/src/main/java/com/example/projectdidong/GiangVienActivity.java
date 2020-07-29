package com.example.projectdidong;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ListViewAdapter.SinhVien;
import ListViewAdapter.SinhVienAdapter;

public class GiangVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_giang_vien );
        ListView lvSinhVien = findViewById ( R.id.lvSinhVien );
        ArrayList<SinhVien> sinhVienArrayList = new ArrayList<> ( );
        SinhVienAdapter adapter = new SinhVienAdapter ( this , R.layout.listview_item , sinhVienArrayList );
        sinhVienArrayList.add ( new SinhVien ( "ádf" , "ádf" , "3423" , "toán" , "5" ) );
        sinhVienArrayList.add ( new SinhVien ( "ádf" , "ádf" , "3423" , "toán" , "5" ) );
        sinhVienArrayList.add ( new SinhVien ( "ádf" , "ádf" , "3423" , "toán" , "5" ) );
        sinhVienArrayList.add ( new SinhVien ( "ádf" , "ádf" , "3423" , "toán" , "5" ) );
        sinhVienArrayList.add ( new SinhVien ( "ádf" , "ádf" , "3423" , "toán" , "5" ) );


        lvSinhVien.setAdapter ( adapter );
    }

}