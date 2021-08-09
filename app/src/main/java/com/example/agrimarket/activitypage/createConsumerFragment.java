package com.example.agrimarket.activitypage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agrimarket.R;
import com.example.agrimarket.databinding.FragmentCreateConsumerBinding;


public class createConsumerFragment extends AppCompatDialogFragment {
    FragmentCreateConsumerBinding binding;
  //  private createConsumerListener consumerListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentCreateConsumerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        builder.setView(view);
        return builder.create();

    }
}