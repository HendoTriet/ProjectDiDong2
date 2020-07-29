package ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<SinhVien> {
    Context context;
    int resource;
    ArrayList<SinhVien> data;

    public Adapter ( Context context , int resource , ArrayList<SinhVien> data ) {
        super ( context , resource );
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount ( ) {
        return data.size ( );
    }

    @NonNull
    @Override
    public View getView ( int position , View convertView , ViewGroup parent ) {
        View view = LayoutInflater.from ( context ).inflate ( resource,null );


        return view;
    }
}
