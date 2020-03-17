package com.ankita.livedatademo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UserBaseObservable extends BaseObservable {

    private String name;

    private boolean enableButton = false;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public boolean isEnableButton() {
        return enableButton;
    }

    public void setEnableButton(boolean enableButton) {
        this.enableButton = enableButton;
        notifyPropertyChanged(BR.age);
    }
}

