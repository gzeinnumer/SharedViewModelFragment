package com.gzeinnumer.sharedviewmodelfragment;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class SharedVM extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<>();

    void select(String item) {
        selected.setValue(item);
    }

    LiveData<String> getSelected() {
        return selected;
    }
}
