package com.example.projectdidong;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ListViewAdapter.SinhVien;
import ListViewAdapter.SinhVienAdapter;

public class GiangVienActivity extends AppCompatActivity {
    private ListView lvSinhVien;
    private ArrayList<SinhVien> sinhVienArrayList;
    private SinhVienAdapter adapter;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_giang_vien );
        getData();
        lvSinhVien = findViewById ( R.id.lvSinhVien );
        sinhVienArrayList = new ArrayList<> ( );
        adapter = new SinhVienAdapter ( this , R.layout.listview_item , sinhVienArrayList );
        lvSinhVien.setAdapter ( adapter );
    }

    //lay du lieu tu  firebase
    private void getData ( ) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance ( );
        DatabaseReference myRef = firebaseDatabase.getReference ( "FbSinhVien" );
        myRef.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                //xoa du lieu tren listview
                adapter.clear ( );
                for (DataSnapshot data : dataSnapshot.getChildren ( )) {
                    SinhVien sinhVien = data.getValue (SinhVien.class);
                        //add Sinh Vien
                        sinhVien.setsStt ( data.getKey () );
                        adapter.add ( sinhVien );
                    Log.d ( "MYTAG","onDataChange" + sinhVien.getsHoTen () );

                }
                Toast.makeText ( getApplicationContext ( ) , "Load data succcess" , Toast.LENGTH_SHORT ).show ( );
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Toast.makeText ( getApplicationContext ( ) , "Load data fail" + databaseError.toString ( ) , Toast.LENGTH_SHORT ).show ( );
                Log.d ( "MYTAG","onCanlled" + databaseError.toString () );
            }
        } );
    }
}