package com.example.authapi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.authapi.databinding.FragmentRegisterBinding;
import com.example.authapi.models.User;
import com.example.authapi.models.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;

    IRegister am;

    String fullname, age, weight, address, email, pass;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IRegister) {
            am = (IRegister) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    public interface IRegister {

        void setUser(User user);

        void alert(String msg);

        void goBack();

        void sendProfileView();

        void register(MainActivity.Return response, String... data);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Register");

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.cancelButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                am.goBack();
            }
        });

        binding.registerButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullname = binding.edittext.getText().toString();
                age = binding.edittext2.getText().toString();
                weight = binding.edittext3.getText().toString();
                address = binding.edittext4.getText().toString();
                email = binding.edittext5.getText().toString();
                pass = binding.edittext6.getText().toString();

                if(email.isEmpty() || fullname.isEmpty() || age.isEmpty() || weight.isEmpty() || address.isEmpty() || pass.isEmpty()){
                    am.alert("Please enter all values for registering!");
                    return;
                }

                Integer fage = Utils.parseInt(age);
                Integer fweight = Utils.parseInt(weight);
                if(fage == null || fweight == null){
                    Toast.makeText(getContext(), "Please enter valid values for age | weight!", Toast.LENGTH_SHORT).show();
                    return;
                }

                am.register(new MainActivity.Return() {
                    @Override
                    public void response(@NotNull String response) {
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        User user = gson.fromJson(response, User.class);
                        am.setUser(user);
                        am.sendProfileView();
                    }

                    @Override
                    public void error(@NotNull String response) {
                    }
                }, fullname, age, weight, address, email, pass);

            }
        });

        return view;
    }
}