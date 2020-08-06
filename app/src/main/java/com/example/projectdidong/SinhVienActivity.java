package com.example.projectdidong;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdidong.Model.Lop;
import com.example.projectdidong.Model.SinhVien;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ListViewAdapter.SinhVienAdapter;

public class SinhVienActivity extends AppCompatActivity {
    Toast toast;
    public static ArrayList<SinhVien> arsinhvien;
    public static SinhVienAdapter adaptersinhvien;
    ArrayList<Lop> ar;
    DatabaseReference mData;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sinh_vien );
        ListView listView = (ListView) findViewById ( R.id.listview );
        getSupportActionBar ( ).setTitle ( "Danh Sách Sinh Viên" );
        mData = FirebaseDatabase.getInstance ( ).getReference ( );
        arsinhvien = new ArrayList<SinhVien> ( );
        mData = FirebaseDatabase.getInstance ( ).getReference ( "Lop" );
        Intent intent = getIntent ( );
        Bundle bundle = intent.getBundleExtra ( "BUNDLE" );
        String tenLop = bundle.getString ( "TENLOP" );
        mData.child ( "SinhVien" ).orderByChild ( "lop" ).equalTo ( tenLop ).addChildEventListener ( new ChildEventListener ( ) {
            @Override
            public void onChildAdded ( DataSnapshot dataSnapshot , String s ) {
                SinhVien sinhVien = dataSnapshot.getValue ( SinhVien.class );
                arsinhvien.add ( new SinhVien ( sinhVien.getTen ( ) , sinhVien.getMaSv ( ) , sinhVien.getGioiTinh ( ) , sinhVien.getDiaChi ( ) , sinhVien.getNgaySinh ( ) , sinhVien.getDienThoai ( ) , sinhVien.getCmnd ( ) , sinhVien.getLop ( )  , dataSnapshot.getKey ( ) ) );
                adaptersinhvien.notifyDataSetChanged ( );
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
        adaptersinhvien = new SinhVienAdapter ( this , R.layout.dialog_themsv , arsinhvien );
        listView.setAdapter ( adaptersinhvien );

    }

    public void dialogthem ( ) {
        //khoitao dialog
        final Dialog dialog = new Dialog ( SinhVienActivity.this );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.dialog_themsv );
        //xet tieu de cho dialog
        dialog.setTitle ( "Thêm sinh viên" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText EditTen, EditMaSV, EditGioiTinh, EditDiaChi, EditNgaySinh, EditDienThoai, EditCMND, EditLop = null, EditHinh;

        EditTen = (EditText) dialog.findViewById ( R.id.EditTenSV );
        EditMaSV = (EditText) dialog.findViewById ( R.id.EditMaSV );

        EditDiaChi = (EditText) dialog.findViewById ( R.id.EditDiaChi );
        EditNgaySinh = (EditText) dialog.findViewById ( R.id.EditNgaySinh );
        EditDienThoai = (EditText) dialog.findViewById ( R.id.EditSDT );
        EditCMND = (EditText) dialog.findViewById ( R.id.EditCMND );

        

        Intent intent = getIntent ( );


        Bundle bundle = intent.getBundleExtra ( "BUNDLE" );

        String tenLop = bundle.getString ( "TENLOP" );
        EditLop.setText ( "" + tenLop );
        EditLop.setEnabled ( false );
        //set cac gia tri hien thoi cua sinh vien can sua


        //bat su kien cho nut dang ki

        final Spinner spinnergioitinh;
        spinnergioitinh = (Spinner) dialog.findViewById ( R.id.spinnerGIOITINH );
        final ArrayList<String> argioitinh = new ArrayList<> ( );
        argioitinh.add ( "Nam" );
        argioitinh.add ( "Nữ" );
        ArrayAdapter arrayAdapter = new ArrayAdapter ( SinhVienActivity.this , android.R.layout.simple_dropdown_item_1line , argioitinh );
        spinnergioitinh.setAdapter ( arrayAdapter );


        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                final String ten, masv, gioitinh, diachi, ngaysinh, dienthoai, cmnd, lop, hinh;
                ten = EditTen.getText ( ).toString ( );
                masv = EditMaSV.getText ( ).toString ( );

                diachi = EditDiaChi.getText ( ).toString ( );
                ngaysinh = EditNgaySinh.getText ( ).toString ( );
                dienthoai = EditDienThoai.getText ( ).toString ( );
                cmnd = EditCMND.getText ( ).toString ( );


                lop = EditLop.getText ( ).toString ( );


                gioitinh = spinnergioitinh.getSelectedItem ( ).toString ( );

                if (EditTen.equals ( "" ) || EditTen.length ( ) == 0) {
                    EditTen.setError ( "Không được để trống" );
                } else if (EditMaSV.equals ( "" ) || EditMaSV.length ( ) == 0) {
                    EditMaSV.setError ( "Không được để trống" );
                } else if (EditDiaChi.equals ( "" ) || EditDiaChi.length ( ) == 0) {
                    EditDiaChi.setError ( "Không được để trống" );
                } else if (EditNgaySinh.equals ( "" ) || EditNgaySinh.length ( ) == 0) {
                    EditNgaySinh.setError ( "Không được để trống" );
                } else if (EditDienThoai.equals ( "" ) || EditDienThoai.length ( ) == 0) {
                    EditDienThoai.setError ( "Không được để trống" );
                } else if (EditCMND.equals ( "" ) || EditCMND.length ( ) == 0) {
                    EditCMND.setError ( "Không được để trống" );
                } else if (EditLop.equals ( "" ) || EditLop.length ( ) == 0) {
                    EditLop.setError ( "Không được để trống" );

                } else {
                    dialog.dismiss ( );
                    Toast.makeText ( SinhVienActivity.this , "Thêm thành công " + EditTen.getText ( ) , Toast.LENGTH_SHORT ).show ( );
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
        dialog.show ( );
        //hien thi dialog
    }

    public void dialogsua2 ( final SinhVien sinhviensua , final int vitri ) {


        //khoitao dialog
        final Dialog dialog = new Dialog ( SinhVienActivity.this );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.dialog_themlop );
        //xet tieu de cho dialog
        dialog.setTitle ( "Thông Tin Sinh Viên" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText EditTen, EditMaSV, EditGioiTinh, EditDiaChi, EditNgaySinh, EditDienThoai, EditCMND, EditLop = null, EditHinh;
        EditTen = (EditText) dialog.findViewById ( R.id.EditTenSV );
        EditMaSV = (EditText) dialog.findViewById ( R.id.EditMaSV );

        EditDiaChi = (EditText) dialog.findViewById ( R.id.EditDiaChi );
        EditNgaySinh = (EditText) dialog.findViewById ( R.id.EditNgaySinh );
        EditDienThoai = (EditText) dialog.findViewById ( R.id.EditSDT );
        EditCMND = (EditText) dialog.findViewById ( R.id.EditCMND );
        //set cac gia tri hien thoi cua sinh vien can sua
        EditTen.setText ( sinhviensua.getTen ( ) );
        EditMaSV.setText ( sinhviensua.getMaSv ( ) );

        EditDiaChi.setText ( sinhviensua.getDiaChi ( ) );
        EditNgaySinh.setText ( sinhviensua.getNgaySinh ( ) );
        EditDienThoai.setText ( sinhviensua.getDienThoai ( ) );
        EditCMND.setText ( sinhviensua.getCmnd ( ) );


        EditLop.setEnabled ( false );

        //bat su kien cho nut dang ki
        final Spinner spinnergioitinh;
        spinnergioitinh = (Spinner) dialog.findViewById ( R.id.spinnerGIOITINH );
        final ArrayList<String> argioitinh = new ArrayList<> ( );
        argioitinh.add ( "Nam" );
        argioitinh.add ( "Nữ" );
        ArrayAdapter arrayAdapter = new ArrayAdapter ( SinhVienActivity.this , android.R.layout.simple_dropdown_item_1line , argioitinh );
        spinnergioitinh.setAdapter ( arrayAdapter );


        //cach 3
        int vitriseleced = argioitinh.indexOf ( sinhviensua.getGioiTinh ( ) );
        spinnergioitinh.setSelection ( vitriseleced );


        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                final String ten, masv, gioitinh, diachi, ngaysinh, dienthoai, cmnd, lop, hinh;
                ten = EditTen.getText ( ).toString ( );
                masv = EditMaSV.getText ( ).toString ( );

                diachi = EditDiaChi.getText ( ).toString ( );
                ngaysinh = EditNgaySinh.getText ( ).toString ( );
                dienthoai = EditDienThoai.getText ( ).toString ( );
                cmnd = EditCMND.getText ( ).toString ( );
                lop = EditLop.getText ( ).toString ( );

                gioitinh = spinnergioitinh.getSelectedItem ( ).toString ( );

                if (EditTen.equals ( "" ) || EditTen.length ( ) == 0) {
                    EditTen.setError ( "Không được để trống" );
                } else if (EditMaSV.equals ( "" ) || EditMaSV.length ( ) == 0) {
                    EditMaSV.setError ( "Không được để trống" );
                } else if (EditDiaChi.equals ( "" ) || EditDiaChi.length ( ) == 0) {
                    EditDiaChi.setError ( "Không được để trống" );
                } else if (EditNgaySinh.equals ( "" ) || EditNgaySinh.length ( ) == 0) {
                    EditNgaySinh.setError ( "Không được để trống" );
                } else if (EditDienThoai.equals ( "" ) || EditDienThoai.length ( ) == 0) {
                    EditDienThoai.setError ( "Không được để trống" );
                } else if (EditCMND.equals ( "" ) || EditCMND.length ( ) == 0) {
                    EditCMND.setError ( "Không được để trống" );
                } else if (EditLop.equals ( "" ) || EditLop.length ( ) == 0) {
                    EditLop.setError ( "Không được để trống" );

                }

                    dialog.dismiss ( );
                    Toast.makeText ( SinhVienActivity.this , "Sủa thành công " + EditTen.getText ( ) , Toast.LENGTH_SHORT ).show ( );
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