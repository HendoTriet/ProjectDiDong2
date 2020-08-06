package ListViewAdapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.projectdidong.Model.SinhVien;
import com.example.projectdidong.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    int re;
    ArrayList<SinhVien> ar;
    Context c;
    DatabaseReference mData;
    ImageView imageViewUp;
    FirebaseStorage storage = FirebaseStorage.getInstance ( );


    public SinhVienAdapter ( @NonNull Context context , int resource , @NonNull ArrayList<SinhVien> objects ) {
        super ( context , resource , objects );
        this.re = resource;
        this.c = context;
        this.ar = objects;
        mData = FirebaseDatabase.getInstance ( ).getReference ( );
    }

    @NonNull
    @Override
    public View getView ( int position , @Nullable View convertView , @NonNull ViewGroup parent ) {

        View view = convertView;
        final ViewHolor viewholor;
        if (view == null) {

            LayoutInflater layoutInflater = LayoutInflater.from ( c );
            view = layoutInflater.inflate ( re , null );
            viewholor = new ViewHolor ( );
            viewholor.textviewTen = (TextView) view.findViewById ( R.id.txtTenLop );
            view.setTag ( viewholor );
        } else {
            viewholor = (ViewHolor) view.getTag ( );


        }

        return view;
    }

    public void ShowmenuItem ( View view , final int vitri ) {
        PopupMenu popupMenu = new PopupMenu ( c , view );
        popupMenu.getMenuInflater ( ).inflate ( R.menu.menu_chucnang , popupMenu.getMenu ( ) );
        popupMenu.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick ( MenuItem item ) {
                if (item.getItemId ( ) == R.id.idDelete) {
                    AlertDialog.Builder builder = new AlertDialog.Builder ( c );
                    builder.setTitle ( "Xóa" );// Set tiêu đề
                    builder.setMessage ( "Bạn có muốn xóa không" );//Set nội dung cho Dialog
                    builder.setCancelable ( false );//Set có cho người dùng Cancer bằng nút quay lại (back) ko? false: ko
                    builder.setPositiveButton ( "Có" , new DialogInterface.OnClickListener ( ) {

                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            //Làm cái gì đó khi ấn Yes tại đây
                            mData.child ( "SinhVien" ).child ( ar.get ( vitri ).getIdSinhVien ( ) ).removeValue ( );
                            ar.remove ( vitri );
                            notifyDataSetChanged ( );
                        }
                    } );
                    builder.setNegativeButton ( "Không" , new DialogInterface.OnClickListener ( ) {

                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {//
                            dialog.cancel ( );
                        }
                    } );
                    builder.show ( );//Hiển thị Dialog


                } else if (item.getItemId ( ) == R.id.idEdit) {

                }
                return false;
            }
        } );
        popupMenu.show ( );
    }

    public void dialogsua ( final SinhVien sinhviensua , final int vitri ) {
        //khoitao dialog
        final Dialog dialog = new Dialog ( c );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.dialog_themsv );
        //xet tieu de cho dialog
        dialog.setTitle ( "Sửa sinh viên" );
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
        EditLop.setText ( sinhviensua.getLop ( ) );

        EditLop.setEnabled ( false );

        //bat su kien cho nut dang ki
        final Spinner spinnergioitinh;
        spinnergioitinh = (Spinner) dialog.findViewById ( R.id.spinnerGIOITINH );
        final ArrayList<String> argioitinh = new ArrayList<> ( );
        argioitinh.add ( "Nam" );
        argioitinh.add ( "Nữ" );
        ArrayAdapter arrayAdapter = new ArrayAdapter ( c , android.R.layout.simple_dropdown_item_1line , argioitinh );
        spinnergioitinh.setAdapter ( arrayAdapter );
        int vitriseleced = argioitinh.indexOf ( sinhviensua.getGioiTinh ( ) );
        spinnergioitinh.setSelection ( vitriseleced );

        spinnergioitinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(c, "Bạn vừa chọn : "+spinnergioitinh.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

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

                    final SinhVien svnew = new SinhVien ( ten , masv , gioitinh , diachi , ngaysinh , dienthoai , cmnd , lop  );

                    mData.child ( "SinhVien" ).child ( ar.get ( vitri ).getIdSinhVien ( ) ).addListenerForSingleValueEvent ( new ValueEventListener ( ) {
                        @Override
                        public void onDataChange ( DataSnapshot dataSnapshot ) {
                            dataSnapshot.getRef ( ).setValue ( svnew );
                            String idcu = ar.get ( vitri ).getIdSinhVien ( );
                            ar.set ( vitri , new SinhVien ( svnew.getTen ( ) , svnew.getMaSv ( ) , svnew.getGioiTinh ( ) , svnew.getDiaChi ( ) ,
                                    svnew.getNgaySinh ( ) , svnew.getDienThoai ( ) , svnew.getCmnd ( ) , svnew.getLop ( ) , idcu ) );
                            notifyDataSetChanged ( );
                        }

                        @Override
                        public void onCancelled ( DatabaseError databaseError ) {

                        }
                    } );

                    notifyDataSetChanged ( );
                    dialog.dismiss ( );
                    Toast.makeText ( c , "Sủa thành công " + EditTen.getText ( ) , Toast.LENGTH_SHORT ).show ( );
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


    public class ViewHolor {
        TextView textviewTen;
        TextView textviewLop;
        ImageView avatar, imageView;
    }
}
