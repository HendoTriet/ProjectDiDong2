package ListViewAdapter;

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
import androidx.appcompat.app.AlertDialog;

import com.example.projectdidong.Model.Lop;
import com.example.projectdidong.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LopAdapter extends ArrayAdapter<Lop> {

    private int resource;
    private Context context;
    private ArrayList<Lop> lopArrayList;
    DatabaseReference myRef;
    private TextView tvLop;
    private ImageView imgHinh;

    public LopAdapter ( @NonNull Context context , int resource , @NonNull ArrayList<Lop> objects ) {
        super ( context , resource , objects );
        this.context = context;
        this.resource = resource;
        this.lopArrayList = objects;
        myRef = FirebaseDatabase.getInstance ( ).getReference ( "dbLop" );
    }

    @NonNull
    @Override
    public View getView ( final int position , @Nullable View convertView , @NonNull ViewGroup parent ) {
        View view = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from ( context );
        view = layoutInflater.inflate ( resource , null );
        tvLop = view.findViewById ( R.id.tvLop );
        imgHinh = view.findViewById ( R.id.imvHinh );

        Lop svCu = lopArrayList.get ( position );
        tvLop.setText ( svCu.getTenLop ( ) );
        imgHinh.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                showMenuItem ( v , position );
            }
        } );

        return view;
    }

    public void showMenuItem ( final View view , final int vitri ) {
        PopupMenu popupMenu = new PopupMenu ( context , view );
        popupMenu.getMenuInflater ( ).inflate ( R.menu.menu_list_lop , popupMenu.getMenu ( ) );
        popupMenu.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick ( MenuItem item ) {
                if (item.getItemId ( ) == R.id.RemoveItem) {
                    AlertDialog.Builder builder = new AlertDialog.Builder ( context );
                    builder.setTitle ( "Xóa" );// Set tiêu đề
                    builder.setMessage ( "Bạn có muốn xóa không" );//Set nội dung cho Dialog
                    builder.setCancelable ( false );//Set có cho người dùng Cancer bằng nút quay lại (back) ko? false: ko
                    builder.setPositiveButton ( "Có" , new DialogInterface.OnClickListener ( ) {

                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            myRef.child ( "Lop" ).child ( lopArrayList.get ( vitri ).getIdLop ( ) ).removeValue ( );
                            lopArrayList.remove ( vitri );
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
                    dialogsua ( lopArrayList.get ( vitri ),vitri );
                    notifyDataSetChanged ( );
                }
                return false;
            }
        } );
        popupMenu.show ( );
    }

    public void dialogsua ( Lop suaLop , final int vitri ) {

        //khoitao dialog
        final Dialog dialog = new Dialog ( context );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.layout_sualop );
        //xet tieu de cho dialog
        dialog.setTitle ( "Sửa lớp" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText EditTenLop;
        EditTenLop = (EditText) dialog.findViewById ( R.id.edtLop );

        myRef = FirebaseDatabase.getInstance ( ).getReference ( "dbLop" );

        //set cac gia tri hien thoi cua sinh vien can sua
        EditTenLop.setText ( suaLop.getTenLop ( ) );
        //bat su kien cho nut dang ki
        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String ten;
                ten = EditTenLop.getText ( ).toString ( );

                if (EditTenLop.equals ( "" ) || EditTenLop.length ( ) == 0) {
                    EditTenLop.setError ( "Không được để trống" );
                } else {
                    final Lop lop = new Lop ( ten );
                    myRef.child ( "Lop" ).child ( lopArrayList.get ( vitri ).getIdLop ( ) ).addListenerForSingleValueEvent ( new ValueEventListener ( ) {

                        @Override
                        public void onDataChange ( DataSnapshot dataSnapshot ) {
                            Log.d ( "bienar01" , lopArrayList.get ( vitri ).getTenLop ( ) );
                            dataSnapshot.getRef ( ).setValue ( lop );


//                            mData.child("Lop").child(ar.get(vitri).getIdlop()).setValue(svnew);
                            String idcu = lopArrayList.get ( vitri ).getIdLop ( );
                            lopArrayList.set ( vitri , new Lop ( lop.getTenLop ( ) , idcu ) );
                            Log.d ( "bienar02" , lopArrayList.get ( vitri ).getTenLop ( ) );
                            notifyDataSetChanged ( );
                        }
                        @Override
                        public void onCancelled ( DatabaseError databaseError ) {

                        }
                    } );

//                    ActivityLop.userId = mData.push().getKey();
//                    mData.child("Lop").child(userId).setValue(vitri,svnew);

                    notifyDataSetChanged ( );
                    dialog.dismiss ( );
                    Toast.makeText ( context , "Sủa thành công " + EditTenLop.getText ( ) , Toast.LENGTH_SHORT ).show ( );
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
