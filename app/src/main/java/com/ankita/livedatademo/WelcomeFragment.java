package com.ankita.livedatademo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ankita.livedatademo.databinding.FragmentWelcomeBinding;

import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class WelcomeFragment extends Fragment {
//    private EditText edtName, edtAge, edtSalary;

    private FragmentWelcomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false);
        MyViewModel model = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MyViewModel.class);
        try {
            binding.setName(model.responseLiveData.getValue().name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            binding.setAge(model.responseLiveData.getValue().age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            binding.setSalary(model.responseLiveData.getValue().salary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
       View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        init(view);
*/
        Observer<String> animalsObserver = getAnimalsObserver();
        Observable<String> animalsObservable = getAnimalsObservable();
        animalsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.toLowerCase().startsWith("b");
                    }
                })
                .subscribeWith(animalsObserver);
//        edtName.setText(model.responseLiveData.getValue().name);
//        edtAge.setText(model.responseLiveData.getValue().age);
//        edtSalary.setText(model.responseLiveData.getValue().salary);

        return binding.getRoot();
    }


    private Observer<String> getAnimalsObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("$$", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("$$", "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("$$", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("$$", "All items are emitted!");
            }
        };


}
    private Observable<String> getAnimalsObservable() {
        return Observable.fromArray(
                "Ant", "Ape",
                "Bat", "Bee", "Bear", "Butterfly",
                "Cat", "Crab", "Cod",
                "Dog", "Dove",
                "Fox", "Frog");
    }


    /*private void init(View view) {
        edtName = view.findViewById(R.id.edtName);
        edtAge = view.findViewById(R.id.edtAge);
        edtSalary = view.findViewById(R.id.edtSalary);
    }*/

}
