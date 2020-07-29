package com.example.projectdidong.ui.home;

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

public class MonHoc extends Fragment {

    private ModelMH modelMH;

    public View onCreateView ( @NonNull LayoutInflater inflater ,
                               ViewGroup container , Bundle savedInstanceState ) {
        modelMH =
                ViewModelProviders.of ( this ).get ( ModelMH.class );
        View root = inflater.inflate ( R.layout.layput_addmh , container , false );
        final TextView textView = root.findViewById ( R.id.text_home );
        modelMH.getText ( ).observe ( getViewLifecycleOwner ( ) , new Observer<String> ( ) {
            @Override
            public void onChanged ( @Nullable String s ) {
                textView.setText ( s );
            }
        } );
        return root;
    }
}