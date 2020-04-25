package com.gzeinnumer.sharedviewmodelfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzeinnumer.sharedviewmodelfragment.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    FragmentSecondBinding binding;
    public SecondFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedVM model = new ViewModelProvider(getActivity()).get(SharedVM.class);
        model.getSelected().observe(getViewLifecycleOwner(), s -> {
            binding.tvFr2.setText(s);
        });
    }
}
