package com.example.projectdidong;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdidong.Model.Diem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ListViewAdapter.DiemAdapter;

public class DiemActivity extends AppCompatActivity {
    private ArrayList<Diem> diemArrayList;
    private DiemAdapter diemAdapter;
    DatabaseReference myRef;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_diem );

        ListView listView = findViewById ( R.id.listview );
        getActionBar ( ).setTitle ( "Điểm" );

        myRef = FirebaseDatabase.getInstance ( ).getReference ( "dbDiem" );
        diemArrayList = new ArrayList<Diem> ( );
        Intent intent = getIntent ( );
        Bundle bundle = intent.getBundleExtra ( "BUNDLE" );

        myRef.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                Diem diem = dataSnapshot.getValue ( Diem.class );
                Log.d ( "key" , dataSnapshot.getKey ( ) );
                diemArrayList.add ( new Diem ( diem.getsTenMH ( ) , diem.getsDiem ( ) , dataSnapshot.getKey ( ) ) );
                diemAdapter.notifyDataSetChanged ( );
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.w ( "TAG" , "Failed to read value." , databaseError.toException ( ) );
            }
        } );
        diemAdapter = new DiemAdapter ( this , R.layout.item_diem , diemArrayList );
        listView.setAdapter ( diemAdapter );
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater ( ).inflate ( R.menu.menu_diem , menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == R.id.EditItem) {
            Diem diem = new Diem ( );
            dialogThem ( );
        }
        return super.onOptionsItemSelected ( item );
    }

    private void dialogThem ( ) {
//khoitao dialog
        final Dialog dialog = new Dialog ( DiemActivity.this );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.layout_suadiem );
        //xet tieu de cho dialog
        dialog.setTitle ( "Thêm Điểm" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText EditTenDiem, EditDiem;
        EditTenDiem = (EditText) dialog.findViewById ( R.id.edtTenMH );
        EditDiem = (EditText) dialog.findViewById ( R.id.edtDiem );

        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String ten, diem;
                ten = EditTenDiem.getText ( ).toString ( );
                diem = EditDiem.getText ( ).toString ( );

                Intent intent = getIntent ( );


                Bundle bundle = intent.getBundleExtra ( "BUNDLE" );

                String tenMH = bundle.getString ( "TENKHOA" );

                String tenMon = tenMH;

                if (EditTenDiem.equals ( "" ) || EditTenDiem.length ( ) == 0) {
                    EditTenDiem.setError ( "Không được để trống" );
                } else if (EditDiem.equals ( "" ) || EditDiem.length ( ) == 0) {
                    EditDiem.setError ( "Không được để trống" );
                } else {
                    Diem newdiem = new Diem ( ten , diem , tenMH );
//                    arDiem.add(svnew);
                    myRef.child ( "Diem" ).push ( ).setValue ( newdiem );
//                    adapterDiem.notifyDataSetChanged();
                    dialog.dismiss ( );
                    Toast.makeText ( DiemActivity.this , "Thêm thành công " + EditTenDiem.getText ( ) , Toast.LENGTH_SHORT ).show ( );
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
}