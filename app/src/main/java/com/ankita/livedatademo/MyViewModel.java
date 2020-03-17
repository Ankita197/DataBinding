package com.ankita.livedatademo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ankita.livedatademo.apiclasse.APIClient;
import com.ankita.livedatademo.apiclasse.APIInterface;
import com.ankita.livedatademo.apiclasse.CreateResponse;
import com.ankita.livedatademo.apiclasse.User;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyViewModel extends ViewModel {

    public User user = new User();
    MutableLiveData<CreateResponse.Datum> responseLiveData = new MutableLiveData<>();
    MutableLiveData<String> successLiveData = new MutableLiveData<>();
    MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    UserBaseObservable userBaseObservable = new UserBaseObservable();
    private CreateResponse resource;

    void createEmplloyes() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Observable<CreateResponse> call = apiInterface.CreateEmployee(user);
        call.subscribeOn(Schedulers.io()).subscribe(new Observer<CreateResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("!_@_", "====onSubscribe======");
            }

            @Override
            public void onNext(CreateResponse createResponse) {
                Log.e("!_@_", "====onNext======");
                if (createResponse != null && createResponse.status.equals("success")) {
                    Log.d("###", createResponse.toString());
                    Log.d("###", createResponse.status);
                    Log.d("###", createResponse.datum.name);
                    responseLiveData.postValue(createResponse.datum);
//                    responseLiveData.setValue(createResponse.datum);
                    successLiveData.postValue("login Successfully");
//                    successLiveData.setValue("login Successfully");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("!_@_", "====onError======");

            }

            @Override
            public void onComplete() {
                Log.e("!_@_", "====onComplete======");

            }
        });
    /* call.enqueue(new Callback<CreateResponse>() {
            @Override
            public void onResponse(Call<CreateResponse> call, Response<CreateResponse> response) {
                resource = response.body();
                if (resource != null && resource.status.equals("success")) {
                    successLiveData.postValue("login Successfully");
                    responseLiveData.postValue(resource.datum);
                }
            }

            @Override
            public void onFailure(Call<CreateResponse> call, Throwable t) {
                Log.d("@!!!", "Connection Failed");
                errorLiveData.postValue(t.getLocalizedMessage());
            }
        });*/
    }
}