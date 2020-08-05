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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdidong.Model.Khoa;
import com.example.projectdidong.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KhoaAdapter extends ArrayAdapter<Khoa> {

    private int resource;
    private ArrayList<Khoa> khoaList;
    private Context context;
    DatabaseReference myRef;
    private TextView tvKhoa;
    private ImageView imgKhoa;

    public KhoaAdapter ( @NonNull Context context , int resource , @NonNull ArrayList<Khoa> objects ) {
        super ( context , resource , objects );
        this.resource = resource;
        this.context = context;
        this.khoaList = objects;
        myRef = FirebaseDatabase.getInstance ( ).getReference ( "dbKhoa");
    }

    @NonNull
    @Override
    public View getView ( final int position , @Nullable View convertView , @NonNull ViewGroup parent ) {
        View view = convertView;
        LayoutInflater inflater = LayoutInflater.from ( context );
        view = inflater.inflate ( resource , null );

        tvKhoa = view.findViewById ( R.id.tvKhoa );
        imgKhoa = view.findViewById ( R.id.imvHinh );
        Khoa khoa = khoaList.get ( position );
//gan gia tri
        tvKhoa.setText ( khoa.getTenKhoa ( ) );
        imgKhoa.setOnClickListener ( new View.OnClickListener ( ) {
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
                            myRef.child ( "MonHoc" ).child ( khoaList.get ( vitri ).getIdKhoa ( ) ).removeValue ( );
                            khoaList.remove ( vitri );
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
                    dialogsua ( khoaList.get ( vitri ) , vitri );
                    notifyDataSetChanged ( );

                }

                return false;
            }
        } );
        popupMenu.show ( );
    }
    public void dialogsua( Khoa suaMH, final int vitri){
        //khoitao dialog
        final Dialog dialog = new Dialog(context);
        //xet layout cho dialog
        dialog.setContentView(R.layout.layout_suamon);
        //xet tieu de cho dialog
        dialog.setTitle("Sửa môn học");
        //khai bao button trong dialog de bat su kien
        Button btnThoat=(Button) dialog.findViewById(R.id.btnThoat);
        Button btnLuu =(Button) dialog.findViewById(R.id.btnLuu);
        final EditText EditTenMonHoc,EditTenMonHoc2;
        EditTenMonHoc = (EditText)dialog.findViewById(R.id.edtTenMonHoc);
        EditTenMonHoc2 = (EditText)dialog.findViewById(R.id.edtTenMonHoc2);
        //set cac gia tri hien thoi cua sinh vien can sua
        EditTenMonHoc.setText(suaMH.getTenKhoa());
        EditTenMonHoc2.setText(suaMH.getMaSV());
        //bat su kien cho nut dang ki
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten,masv;
                ten=EditTenMonHoc.getText().toString();
                masv= EditTenMonHoc2.getText().toString();
                if (EditTenMonHoc.equals("") || EditTenMonHoc.length() == 0){
                    EditTenMonHoc.setError("Không được để trống");
                }else {
                    final Khoa newKhoa = new Khoa(ten,masv);
                    myRef.child("MonHoc").child(khoaList.get(vitri).getIdKhoa()).addListenerForSingleValueEvent(new ValueEventListener () {
                        @Override
                        public void onDataChange( DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().setValue(newKhoa);
                            String idcu=khoaList.get(vitri).getIdKhoa();
                            khoaList.set(vitri, new Khoa(newKhoa.getTenKhoa(),newKhoa.getMaSV(),idcu));
                            notifyDataSetChanged();
                        }
                        @Override
                        public void onCancelled( DatabaseError databaseError) {
                            Log.w("TAG", "Failed to read value.", databaseError.toException());

                        }
                    });
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Sủa thành công "+EditTenMonHoc.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        //hien thi dialog
    }
}
