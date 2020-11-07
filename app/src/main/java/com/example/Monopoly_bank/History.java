package com.example.Monopoly_bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class History extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstancesState) {
        return inflater.inflate(R.layout.history, container, false);
    }
}
