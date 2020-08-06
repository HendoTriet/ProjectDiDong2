package com.example.projectdidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdidong.Model.SinhVien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ListViewAdapter.SinhVienAdapter;

public class ThongTinSVActivity extends AppCompatActivity {
    private TextView Ten, MaSV, GioiTinh, DiaChi, NgaySinh, DienThoai, CMND, Lop;
    public static ArrayList<SinhVien> arsinhvien;
    public static SinhVienAdapter adaptersinhvien;
    DatabaseReference mData;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {

        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_thong_tin_s_v );

        mData = FirebaseDatabase.getInstance ( ).getReference ( );
    }

    public void dialogsua ( final SinhVien sinhviensua , final int vitri ) {
        Button btnThoat = (Button) findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) findViewById ( R.id.btnLuu );
        final EditText EditTen, EditMaSV, EditGioiTinh, EditDiaChi, EditNgaySinh, EditDienThoai, EditCMND, EditLop = null, EditHinh;
        EditTen = (EditText) findViewById ( R.id.EditTenSV );
        EditMaSV = (EditText) findViewById ( R.id.EditMaSV );

        EditDiaChi = (EditText) findViewById ( R.id.EditDiaChi );
        EditNgaySinh = (EditText) findViewById ( R.id.EditNgaySinh );
        EditDienThoai = (EditText) findViewById ( R.id.EditSDT );
        EditCMND = (EditText) findViewById ( R.id.EditCMND );

        //set cac gia tri hien thoi cua sinh vien can sua
        Intent intent = getIntent ( );


        Bundle bundle = intent.getBundleExtra ( "BUNDLE" );

        String ten = bundle.getString ( "TEN" );
        String masv = bundle.getString ( "MASV" );
        String gioitinh = bundle.getString ( "GIOITINH" );
        String diachi = bundle.getString ( "DIACHI" );
        String ngaysinh = bundle.getString ( "NGAYSINH" );
        String dienthoai = bundle.getString ( "DIENTHOAI" );
        String cmnd = bundle.getString ( "CMND" );
        String lop = bundle.getString ( "LOP" );
        String imageView = bundle.getString ( "HINH" );
        EditTen.setText ( "" + ten );
        EditMaSV.setText ( "" + masv );

        EditDiaChi.setText ( "" + diachi );
        EditNgaySinh.setText ( "" + ngaysinh );
        EditDienThoai.setText ( "" + dienthoai );
        EditCMND.setText ( "" + cmnd );
        EditLop.setText ( "" + lop );


        EditLop.setEnabled ( false );

        final Spinner spinnergioitinh;
        spinnergioitinh = (Spinner) findViewById ( R.id.spinnerGIOITINH );
        final ArrayList<String> argioitinh = new ArrayList<> ( );
        argioitinh.add ( "Nam" );
        argioitinh.add ( "Nữ" );
        ArrayAdapter arrayAdapter = new ArrayAdapter ( this , android.R.layout.simple_dropdown_item_1line , argioitinh );
        spinnergioitinh.setAdapter ( arrayAdapter );
//        int vitriseleced=argioitinh.indexOf(gioitinh.getGioiTinh());
//        spinnergioitinh.setSelection(vitriseleced);

        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {


//                dialogsua(arsinhvien.get());
//                arsinhvien.set(vitri,EditTen.getText().toString());

                String ten, masv, gioitinh, diachi, ngaysinh, dienthoai, cmnd, lop, hinh;
                ten = EditTen.getText ( ).toString ( );
                masv = EditMaSV.getText ( ).toString ( );

                diachi = EditDiaChi.getText ( ).toString ( );
                ngaysinh = EditNgaySinh.getText ( ).toString ( );
                dienthoai = EditDienThoai.getText ( ).toString ( );
                cmnd = EditCMND.getText ( ).toString ( );
                lop = EditLop.getText ( ).toString ( );


//
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

                    final SinhVien svnew = new SinhVien ( ten , masv , gioitinh , diachi , ngaysinh , dienthoai , cmnd , lop );

                    mData.child ( "SinhVien" ).child ( arsinhvien.get ( vitri ).getIdSinhVien ( ) ).addListenerForSingleValueEvent ( new ValueEventListener ( ) {
                        @Override
                        public void onDataChange ( DataSnapshot dataSnapshot ) {
                            dataSnapshot.getRef ( ).setValue ( svnew );
                            String idcu = arsinhvien.get ( vitri ).getIdSinhVien ( );
                            arsinhvien.set ( vitri , new SinhVien ( svnew.getTen ( ) , svnew.getMaSv ( ) , svnew.getGioiTinh ( ) , svnew.getDiaChi ( ) ,
                                    svnew.getNgaySinh ( ) , svnew.getDienThoai ( ) , svnew.getCmnd ( ) , svnew.getLop ( ) , idcu ) );
                            adaptersinhvien.notifyDataSetChanged ( );
                        }

                        @Override
                        public void onCancelled ( DatabaseError databaseError ) {

                        }
                    } );

                    adaptersinhvien.notifyDataSetChanged ( );
                    finish ( );
                    Toast.makeText ( getApplicationContext ( ) , "Sủa thành công " + EditTen.getText ( ) , Toast.LENGTH_SHORT ).show ( );
                }

            }
        } );
        btnThoat.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                //sua kien dong dia log chinh sua
                finish ( );
            }
        } );
    }
}