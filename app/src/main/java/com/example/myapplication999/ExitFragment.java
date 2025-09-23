package com.example.myapplication999;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExitFragment extends Fragment {

    public ExitFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exit, container, false);

        Button exitButton = view.findViewById(R.id.btnExitApp);
        exitButton.setOnClickListener(v -> {
            requireActivity().finishAffinity();  // Close all activities
            System.exit(0);                      // Kill the app process
        });

        return view;
    }
}
