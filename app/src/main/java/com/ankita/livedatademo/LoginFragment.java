package com.ankita.livedatademo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ankita.livedatademo.databinding.FragmentLoginBinding;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private MyViewModel model;
    private EditText edtName, edtAge, edtSalary;
    private Button btnLogin;

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_login, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MyViewModel.class);
        binding.setMyViewModel(model);

        binding.setLoginFragment(this);
//        init(view);
//        binding.btnLoginTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                User user = new User(edtName.getText().toString(), edtAge.getText().toString(), edtSalary.getText().toString());
//                model.getEmplooyes();
//
//            }
//        });
        model.successLiveData.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (getActivity().getSupportFragmentManager() != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, new WelcomeFragment(), WelcomeFragment.class.getSimpleName())
                            .commit();
                }
            }
        });
        return binding.getRoot();
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLoginTest:
                Log.e("!_@_", "btnLoginTest");
                model.createEmplloyes();
                break;
            case R.id.btnLoginTestTwo:
                Log.e("!_@_", "btnLoginTestTwo");
                break;
            default:
                break;
        }
//        model.getEmplooyes();
    }

    /*   private void init(View view) {
           edtName = view.findViewById(R.id.edtName);
           edtAge = view.findViewById(R.id.edtAge);
           edtSalary = view.findViewById(R.id.edtSalary);
           btnLogin = view.findViewById(R.id.btnLogin);
       }
   */
}
