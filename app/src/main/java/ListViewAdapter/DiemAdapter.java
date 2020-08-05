package ListViewAdapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdidong.Model.Diem;
import com.example.projectdidong.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiemAdapter extends ArrayAdapter<Diem> {
    private int resource;
    ArrayList<Diem> diemList;
    Context context;
    DatabaseReference myRef;
    TextView tvTenMH, tvDiem;
    ImageView imgDiem;

    public DiemAdapter ( @NonNull Context context , int resource , @NonNull ArrayList<Diem> objects ) {
        super ( context , resource , objects );
        this.resource = resource;
        this.context = context;
        this.diemList = objects;
        myRef = FirebaseDatabase.getInstance ( ).getReference ("dbDiem" );
    }

    public View getView ( final int position , @Nullable View convertView , @NonNull ViewGroup parent ) {
        View view = convertView;
        LayoutInflater inflater = LayoutInflater.from ( context );
        view = inflater.inflate ( resource , null );

        tvTenMH = view.findViewById ( R.id.tvTendiem );
        tvDiem = view.findViewById ( R.id.tvDiem );
        imgDiem = view.findViewById ( R.id.imgDiem );
        Diem diem = diemList.get ( position );
//gan gia tri
        tvTenMH.setText ( diem.getsTenMH ( ) );
        tvDiem.setText ( diem.getsDiem ( ) );
        imgDiem.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                ShowmenuItem ( v , position );
            }
        } );
        return view;
    }

    public void ShowmenuItem ( View view , final int vitri ) {
        PopupMenu popupMenu = new PopupMenu ( context , view );
        popupMenu.getMenuInflater ( ).inflate ( R.menu.menu_list_monhoc , popupMenu.getMenu ( ) );
        popupMenu.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick ( MenuItem item ) {
                if (item.getItemId ( ) == R.id.RemoveItem) {

                    AlertDialog.Builder builder = new AlertDialog.Builder ( context );
                    builder.setTitle ( "Xóa" );// Set tiêu đề
                    builder.setMessage ( "Bạn có muốn Xóa không" );//Set nội dung cho Dialog
                    builder.setCancelable ( false );//Set có cho người dùng Cancer bằng nút quay lại (back) ko? false: ko
                    builder.setPositiveButton ( "Có" , new DialogInterface.OnClickListener ( ) {

                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            //dialog.dismiss();
                            myRef.child ( "MonHoc" ).child ( diemList.get ( vitri ).getsTenMH ( ) ).removeValue ( );
                            diemList.remove ( vitri );
                            notifyDataSetChanged ( );
                        }
                    } );
                    builder.setNegativeButton ( "Không" , new DialogInterface.OnClickListener ( ) {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            dialog.cancel ( );
                        }
                    } );

                    builder.show ( );//Hiển thị Dialog

                } else if (item.getItemId ( ) == R.id.EditItem) {
                    dialogsua ( diemList.get ( vitri ) , vitri );
                    notifyDataSetChanged ( );

                }

                return false;
            }
        } );
        popupMenu.show ( );
    }

    public void dialogsua ( Diem suaDiem , final int vitri ) {
        //khoitao dialog
        final Dialog dialog = new Dialog ( context );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.layout_suadiem );
        //xet tieu de cho dialog
        dialog.setTitle ( "Sửa môn học" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText edtTenMH, edtDiemMH;
        edtTenMH = (EditText) dialog.findViewById ( R.id.edtTenMonHoc );
        edtDiemMH = (EditText) dialog.findViewById ( R.id.edtTenMonHoc2 );
        //set cac gia tri hien thoi cua sinh vien can sua
        edtTenMH.setText ( suaDiem.getsTenMH ( ) );
        edtDiemMH.setText ( suaDiem.getsTenMH ( ) );
        //bat su kien cho nut dang ki
        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                String tenmh, diem;
                tenmh = edtTenMH.getText ( ).toString ( );
                diem = edtDiemMH.getText ( ).toString ( );
                if (edtTenMH.equals ( "" ) || edtTenMH.length ( ) == 0) {
                    edtTenMH.setError ( "Không được để trống!!!" );
                } else if (edtDiemMH.equals ( "" ) || edtDiemMH.length ( ) == 0) {
                    edtDiemMH.setError ( "Không được để trống!!!" );
                } else {
                     final Diem newDiem = new Diem ( tenmh , diem );
                    myRef.child ( "Diem" ).child ( diemList.get ( vitri ).getsDiem ( ) ).addListenerForSingleValueEvent ( new ValueEventListener ( ) {
                        @Override
                        public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                            dataSnapshot.getRef ( ).setValue ( newDiem );
                            String idcu = diemList.get ( vitri ).getsDiem ( );
                            diemList.set ( vitri , new Diem ( newDiem.getsTenMH ( ) , newDiem.getsDiem ( ) , idcu ) );
                            notifyDataSetChanged ();
                        }

                        @Override
                        public void onCancelled ( @NonNull DatabaseError databaseError ) {
                            Log.w("TAG", "Failed to read value.", databaseError.toException());
                        }
                    } );
                }
            }
        } );
        btnThoat.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                dialog.dismiss ( );
            }
        } );
        dialog.show ( );
        //hien thi dialog
    }
}
