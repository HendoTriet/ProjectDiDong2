package ListViewAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdidong.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {

    Activity activity;
    int resource;
    ArrayList<SinhVien> data;
    ImageView btnMenu;
    TextView txtHoTen, txtGioiTinh, txtMaSV, txtMonHoc, txtDiem;

    public SinhVienAdapter ( @NonNull Activity activity , int resource , @NonNull ArrayList<SinhVien> data ) {
        super ( activity , resource , data );
        this.activity = activity;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount ( ) {
        return data.size ( );
    }

    @NonNull
    @Override
    public View getView ( int position , @Nullable View convertView , @NonNull final ViewGroup parent ) {
        LayoutInflater inflater = this.activity.getLayoutInflater ( );
        final View view = inflater.inflate ( this.resource , null );

        //khai báo thuộc tính
        txtHoTen = view.findViewById ( R.id.txtHoTen );
        txtGioiTinh = view.findViewById ( R.id.txtGioiTinh );
        txtMaSV = view.findViewById ( R.id.txtMaSV );
        txtMonHoc = view.findViewById ( R.id.txtMonHoc );
        txtDiem = view.findViewById ( R.id.txtDiem );
        btnMenu = view.findViewById ( R.id.btnMenu );
        //đưa dữ liệu vào
        final SinhVien sinhVien = this.data.get ( position );
        txtHoTen.setText ( sinhVien.getsHoTen ( ) );
        txtGioiTinh.setText ( sinhVien.getsGioiTinh ( ) );
        txtMaSV.setText ( sinhVien.getsMaSV ( ) );
        txtMonHoc.setText ( sinhVien.getsMonHoc ( ) );
        txtDiem.setText ( sinhVien.getsDiem ( ) );
        //
        btnMenu.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                PopupMenu popupMenu = new PopupMenu ( activity , view );
                popupMenu.setOnMenuItemClickListener ( new PopupMenu.OnMenuItemClickListener ( ) {
                    @Override
                    public boolean onMenuItemClick ( MenuItem menuItem ) {
                        if (menuItem.getItemId ( ) == R.id.itemThemSV) {
                            Toast.makeText ( activity , "them thong tin" , Toast.LENGTH_SHORT ).show ( );
                        } else if (menuItem.getItemId ( ) == R.id.itemSuaSV) {
                            Toast.makeText ( activity , "sua sv" + sinhVien.getsHoTen ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "sua sv" + sinhVien.getsGioiTinh ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "sua sv" + sinhVien.getsMaSV ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "sua sv" + sinhVien.getsMonHoc ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "sua sv" + sinhVien.getsDiem ( ) , Toast.LENGTH_SHORT ).show ( );
                        } else if (menuItem.getItemId ( ) == R.id.itemXoa) {
                            Toast.makeText ( activity , "Xoa" + sinhVien.getsHoTen ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "Xoa" + sinhVien.getsGioiTinh ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "Xoa" + sinhVien.getsMaSV ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "Xoa" + sinhVien.getsMonHoc ( ) , Toast.LENGTH_SHORT ).show ( );
                            Toast.makeText ( activity , "Xoa" + sinhVien.getsDiem ( ) , Toast.LENGTH_SHORT ).show ( );
                        }
                        return false;
                    }
                } );
                popupMenu.getMenuInflater ( ).inflate ( R.menu.menu , popupMenu.getMenu ());
                //show icon
                try {
                    Field[] fields = popupMenu.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popupMenu);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon",boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace ();
                }
                popupMenu.show ( );
            }
        } );
        return view;
    }
}
