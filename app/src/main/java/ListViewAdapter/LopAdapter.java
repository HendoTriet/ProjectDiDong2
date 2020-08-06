package ListViewAdapter;

import android.app.Activity;
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
    int re;
    ArrayList<Lop> ar;
    Context c;
    DatabaseReference mData;
    public static String userId;


    public LopAdapter ( @NonNull Activity activity , int resource , @NonNull ArrayList<Lop> objects ) {
        super ( activity , resource , objects );
        this.re = resource;
        this.c = activity;
        this.ar = objects;
        mData = FirebaseDatabase.getInstance ( ).getReference ( );
    }

    @NonNull
    @Override
    public View getView ( final int position , @Nullable View convertView , @NonNull ViewGroup parent ) {

        View view = convertView;
        final ViewHolor viewholor;
        if (view == null) {
//            ar=new ArrayList<Lop>()
            LayoutInflater layoutInflater = LayoutInflater.from ( c );
            view = layoutInflater.inflate ( re , null );
            viewholor = new ViewHolor ( );
            viewholor.textViewLop = (TextView) view.findViewById ( R.id.txtTenLop );

            view.setTag ( viewholor );
        } else {
            viewholor = (ViewHolor) view.getTag ( );
        }
        final Lop sinhvienhientai = ar.get ( position );
        //gan gia tri len cac textview

        viewholor.textViewLop.setText ( sinhvienhientai.getsTenLop ( ) );

        viewholor.imageView.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                ShowmenuItem ( v, position );
            }
        } );
        return view;
    }

    public void ShowmenuItem ( final View view , final int vitri ) {
        PopupMenu popupMenu = new PopupMenu ( c , view );
        popupMenu.getMenuInflater ( ).inflate ( R.menu.menu_chucnang , popupMenu.getMenu ( ) );
        popupMenu.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
            @Override
            public boolean onMenuItemClick ( MenuItem item ) {
                if (item.getItemId ( ) == R.id.idEdit) {
                    AlertDialog.Builder builder = new AlertDialog.Builder ( c );
                    builder.setTitle ( "Xóa" );// Set tiêu đề
                    builder.setMessage ( "Bạn có muốn xóa không" );//Set nội dung cho Dialog
                    builder.setCancelable ( false );//Set có cho người dùng Cancer bằng nút quay lại (back) ko? false: ko
                    builder.setPositiveButton ( "Có" , new DialogInterface.OnClickListener ( ) {

                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            //Làm cái gì đó khi ấn Yes tại đây
//                        dialog.dismiss();
                            mData.child ( "Lop" ).child ( ar.get ( vitri ).getsID ( ) ).removeValue ( );
                            ar.remove ( vitri );
                            notifyDataSetChanged ( );
                        }
                    } );
                    builder.setNegativeButton ( "Không" , new DialogInterface.OnClickListener ( ) {

                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            //
                            dialog.cancel ( );
                        }
                    } );
                    builder.show ( );//Hiển thị Dialog
                } else if (item.getItemId ( ) == R.id.idEdit) {
                    dialogsua ( ar.get ( vitri ) , vitri );
                    notifyDataSetChanged ( );
                }
                return false;
            }
        } );
        popupMenu.show ( );
    }
    public void dialogsua ( final Lop sualop , final int vitri ) {
        //khoitao dialog
        final Dialog dialog = new Dialog ( c );
        //xet layout cho dialog
        dialog.setContentView ( R.layout.item_cn );
        //xet tieu de cho dialog
        dialog.setTitle ( "Sửa lớp" );
        //khai bao button trong dialog de bat su kien
        Button btnThoat = (Button) dialog.findViewById ( R.id.btnThoat );
        Button btnLuu = (Button) dialog.findViewById ( R.id.btnLuu );
        final EditText EditTenLop;
        EditTenLop = (EditText) dialog.findViewById ( R.id.editTextLop );

        mData = FirebaseDatabase.getInstance ( ).getReference ( );
        //set cac gia tri hien thoi cua sinh vien can sua
        EditTenLop.setText ( sualop.getsTenLop ( ) );
        //bat su kien cho nut dang ki
        btnLuu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                String ten;
                ten = EditTenLop.getText ( ).toString ( );
                if (EditTenLop.equals ( "" ) || EditTenLop.length ( ) == 0) {
                    EditTenLop.setError ( "Không được để trống" );
                } else {
                    final Lop svnew = new Lop ( ten );
//                    mData.child("Lop").child(ar.get(vitri).getIdlop()).updateChildren(svnew);

//                    mData.child("Lop").child(ar.get(vitri).getIdlop()).setValue(svnew);
                    mData.child ( "Lop" ).child ( ar.get ( vitri ).getsID ( ) ).addListenerForSingleValueEvent ( new ValueEventListener ( ) {
                        @Override
                        public void onDataChange ( DataSnapshot dataSnapshot ) {
                            Log.d ( "bienar01" , ar.get ( vitri ).getsTenLop ( ) );
                            dataSnapshot.getRef ( ).setValue ( svnew );

//                            mData.child("Lop").child(ar.get(vitri).getIdlop()).setValue(svnew);
                            String idcu = ar.get ( vitri ).getsID ( );
                            ar.set ( vitri , new Lop ( svnew.getsTenLop ( ) , idcu ) );
                            Log.d ( "bienar02" , ar.get ( vitri ).getsTenLop ( ) );
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
                    Toast.makeText ( c , "Sủa thành công " + EditTenLop.getText ( ) , Toast.LENGTH_SHORT ).show ( );

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
        TextView textViewLop;
        ImageView imageViewLop, imageView;
    }
}
