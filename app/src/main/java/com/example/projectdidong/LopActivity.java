package com.example.projectdidong;

import android.app.Dialog;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdidong.Model.Lop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ListViewAdapter.LopAdapter;

public class LopActivity extends AppCompatActivity {
    public static String userId;
    DatabaseReference myRef;
    ArrayList<Lop> lopArrayList;
    LopAdapter lopAdapter;
    private ListView listView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_lop );

        listView = findViewById ( R.id.listview );
        getActionBar ( ).setTitle ( "Lop" );

        myRef = FirebaseDatabase.getInstance ( ).getReference ( "dbLop" );
        Intent intent = getIntent ( );
        Bundle bundle = intent.getBundleExtra ( "BUNDLE" );

        myRef.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                Lop lop = dataSnapshot.getValue ( Lop.class );
                Log.d ( "key" , dataSnapshot.getKey ( ) );
                lopArrayList.add ( new Lop ( lop.getTenLop ( ) , dataSnapshot.getKey ( ) ) );
                lopAdapter.notifyDataSetChanged ( );
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Log.d ( "TAG" , "Failed to read value." , databaseError.toException ( ) );
            }
        } );
        lopAdapter = new LopAdapter ( this , R.layout.item_lop , lopArrayList );
        listView.setAdapter ( lopAdapter );
        listView.setOnItemClickListener ( new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick ( AdapterView<?> parent , View view , int position , long id ) {

            }
        } );
    }

    public void dialogthem ( ) {
        //khoitao dialog
        final Dialog dialog = new Dialog ( LopActivity.this );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.layout_sualop );
        //xet tieu de cho dialog
        dialog.setTitle ( "Thêm lớp" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText EditTenLop;
        EditTenLop = (EditText) dialog.findViewById ( R.id.edtLop );

        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String ten;
                ten = EditTenLop.getText ( ).toString ( );
                if (EditTenLop.equals ( "" ) || EditTenLop.length ( ) == 0) {
                    EditTenLop.setError ( "Không được để trống!!" );
                } else {
                    Lop lop = new Lop ( );
                    userId = myRef.push ( ).getKey ( );
                    myRef.child ( "Lop" ).child ( userId ).setValue ( lop );
                    dialog.dismiss ( );
                    Toast.makeText ( LopActivity.this , "Thêm thành công" + EditTenLop.getText ( ) , Toast.LENGTH_SHORT ).show ( );
                }
            }
        } );

        dialog.show ( );
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater ( ).inflate ( R.menu.menu_lop , menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == R.id.menuitemthem) {
            dialogthem ( );
        } else if (item.getItemId ( ) == R.id.menuitemLogOut) {
            AlertDialog.Builder builder = new AlertDialog.Builder ( LopActivity.this , android.R.style.Theme_DeviceDefault_Light_Dialog );
            builder.setTitle ( "Bạn có thật sự muốn đăng suất khỏi ứng dụng?" );
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
        return super.onOptionsItemSelected ( item );
    }
}