package com.example.projectdidong.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectdidong.R;

public class DiemSinhVien extends Fragment {

    private ModelDiem modelDiem;

    public View onCreateView ( @NonNull LayoutInflater inflater ,
                               ViewGroup container , Bundle savedInstanceState ) {
        modelDiem =
                ViewModelProviders.of ( this ).get ( ModelDiem.class );
        View root = inflater.inflate ( R.layout.layout_adddiem , container , false );
        final TextView textView = root.findViewById ( R.id.text_diem );
        modelDiem.getText ( ).observe ( getViewLifecycleOwner ( ) , new Observer<String> ( ) {
            @Override
            public void onChanged ( @Nullable String s ) {
                textView.setText ( s );
            }
        } );
        return root;
    }
}