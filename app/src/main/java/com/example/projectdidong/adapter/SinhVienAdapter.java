package com.example.projectdidong.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVienAdapter> {
    private Activity activity;
    private int resource;
    private List<SinhVienAdapter> objects;
    public SinhVienAdapter(@NonNull Activity activity, int resource, @NonNull List<SinhVienAdapter> objects) {
        super(activity, resource, objects);
    }
}
