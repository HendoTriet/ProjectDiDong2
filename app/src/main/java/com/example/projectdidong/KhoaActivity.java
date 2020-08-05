package com.example.projectdidong;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdidong.Model.Khoa;
import com.example.projectdidong.Model.SinhVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ListViewAdapter.KhoaAdapter;

public class KhoaActivity extends AppCompatActivity {

    private ArrayList<Khoa> listKhoa;
    private KhoaAdapter khoaAdapter;
    DatabaseReference myRef;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_khoa );

        ListView listView = findViewById ( R.id.listview );
        getActionBar ( ).setTitle ( "Khoa" );

        myRef = FirebaseDatabase.getInstance ( ).getReference ("dbKhoa" );
        listKhoa = new ArrayList<Khoa> ( );
        Intent intent = getIntent ( );
        Bundle bundle = intent.getBundleExtra ( "BUNDLE" );

        myRef.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                Khoa khoa = dataSnapshot.getValue ( Khoa.class );
                Log.d ( "key" , dataSnapshot.getKey ( ) );
                listKhoa.add ( new Khoa ( khoa.getTenKhoa ( ) , khoa.getMaSV ( ) , dataSnapshot.getKey ( ) ) );
                khoaAdapter.notifyDataSetChanged ( );
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w ( "TAG" , "Failed to read value." , databaseError.toException ( ) );
            }
        } );
        khoaAdapter = new KhoaAdapter ( this , R.layout.item_khoa , listKhoa );
        listView.setAdapter ( khoaAdapter );
        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick ( AdapterView<?> parent , View view , int position , long id ) {
                Intent intent = new Intent ( getApplication ( ) , DiemActivity.class );
                String tenKhoa = listKhoa.get ( position ).getTenKhoa ( ).toString ( );
                Bundle bundle = new Bundle ( );
                bundle.putString ( "TENKHOA" , tenKhoa );
                intent.putExtra ( "BUNDLE" , bundle );
                startActivity ( intent );
            }
        } );
    }

    public void dialogThem ( ) {
        //khoitao dialog
        final Dialog dialog = new Dialog ( KhoaActivity.this );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.layout_suamon );
        //xet tieu de cho dialog
        dialog.setTitle ( "Thêm môn học" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText edtMH;
        edtMH = (EditText) dialog.findViewById ( R.id.edtTenMonHoc );

        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String ten;
                ten = edtMH.getText ( ).toString ( );
                Intent intent = getIntent ( );
                Bundle bundle = intent.getBundleExtra ( "BUNDLE" );
                String sMasv = bundle.getString ( "MASV" );
                String maSv = sMasv;
                if (edtMH.equals ( "" ) || edtMH.length ( ) == 0) {
                    edtMH.setError ( "Không được để trống" );
                } else {
                    Khoa svnew = new Khoa ( ten , maSv );
//                    Khoa.add(svnew);
                    myRef.child ( "MonHoc" ).push ( ).setValue ( svnew );
                    dialog.dismiss ( );
                    Toast.makeText ( KhoaActivity.this , "thêm thành công " + edtMH.getText ( ) , Toast.LENGTH_SHORT ).show ( );
                }

            }
        } );
        btnThoat.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                //sua kien dong dia log chinh sua
                dialog.dismiss ( );
            }
        } );
        //toi day la song phan khoi tao gio chi viec show();
        dialog.show ( );
        //hien thi dialog


    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater ( ).inflate ( R.menu.menu , menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == R.id.menuitemthem) {
            SinhVien sinhVien = new SinhVien ( );
            dialogThem ( );
        }
        return super.onOptionsItemSelected ( item );
    }
}