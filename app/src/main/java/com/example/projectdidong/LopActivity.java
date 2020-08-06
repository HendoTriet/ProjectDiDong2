package com.example.projectdidong;

import android.app.Dialog;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectdidong.Model.Lop;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import ListViewAdapter.LopAdapter;

public class LopActivity extends AppCompatActivity {
    public static String userId;
    Toast toast;
    public static ArrayList<Lop> arLop;
    LopAdapter adapterlop;
    DatabaseReference mData;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_lop );
        setTitle ( "Danh sach lop" );
        ListView listView = findViewById ( R.id.lvLop );
        arLop = new ArrayList<> ( );

        adapterlop = new LopAdapter ( this , R.layout.custom_item_lop , arLop );
        mData = FirebaseDatabase.getInstance ( ).getReference ( "Lop" );
        mData.child ( "Lop" ).addChildEventListener ( new ChildEventListener ( ) {
            @Override
            public void onChildAdded ( DataSnapshot dataSnapshot , String s ) {
                Lop lop = dataSnapshot.getValue ( Lop.class );
                Log.d ( "xemkey" , dataSnapshot.getKey ( ) );
                arLop.add ( new Lop ( lop.getsTenLop ( ) , dataSnapshot.getKey ( ) ) );
                adapterlop.notifyDataSetChanged ( );
            }

            @Override
            public void onChildChanged ( DataSnapshot dataSnapshot , String s ) {
            }

            @Override
            public void onChildRemoved ( DataSnapshot dataSnapshot ) {
            }

            @Override
            public void onChildMoved ( DataSnapshot dataSnapshot , String s ) {
            }

            @Override
            public void onCancelled ( DatabaseError databaseError ) {
            }
        } );
        listView.setAdapter ( adapterlop );

    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        getMenuInflater ( ).inflate ( R.menu.menu , menu );
        return super.onCreateOptionsMenu ( menu );
    }

    @Override
    public boolean onOptionsItemSelected ( @NonNull MenuItem item ) {
        if (item.getItemId ( ) == R.id.menuitemthem) {
            addLop ( );
        } else if (item.getItemId ( ) == R.id.menuitemLogOut) {
            finish ( );
        }
        return super.onOptionsItemSelected ( item );
    }

    private void addLop ( ) {
//khoitao dialog
        final AlertDialog dialog = (AlertDialog) new Dialog ( LopActivity.this );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.dialog_themlop );
        //xet tieu de cho dialog
        dialog.setTitle ( "Thêm lớp" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText EditTenLop;
        EditTenLop = (EditText) dialog.findViewById ( R.id.editTextLop );
        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String ten;
                ten = EditTenLop.getText ( ).toString ( );
                if (EditTenLop.equals ( "" ) || EditTenLop.length ( ) == 0) {
                    EditTenLop.setError ( "Không được để trống" );
//                }else if(!ten.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]+$")) {
//                    EditTenLop.setError("Tên lớp không được có dâu");
//                }
                } else {
                    Lop svnew = new Lop ( ten );
//                    arLop.add(svnew);
                    userId = mData.push ( ).getKey ( );
//                    mData.child("Lop").child(userId).setValue(svnew);
                    mData.child ( "Lop" ).child ( userId ).setValue ( svnew );
//                    adapterlop.notifyDataSetChanged();
                    dialog.dismiss ( );
                    Toast.makeText ( LopActivity.this , "thêm thành công " + EditTenLop.getText ( ) , Toast.LENGTH_SHORT ).show ( );
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