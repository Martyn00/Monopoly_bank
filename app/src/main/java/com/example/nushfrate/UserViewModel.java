package com.example.nushfrate;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private UserRepositoty mRepo;

    LiveData<User> mUser;
    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepo = new UserRepositoty(application);
        mUser = mRepo.getUser();
    }
    void insert(User user) {
        mRepo.insert(user);
    }
    LiveData<User> getmUser(){
        return mUser;
    }
}
