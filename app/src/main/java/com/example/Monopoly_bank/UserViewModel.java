package com.example.Monopoly_bank;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepo;

    LiveData<List<User>> mUser;
    public UserViewModel(Application application) {
        super(application);
        mRepo = new UserRepository(application);
        mUser = mRepo.getUser();
    }
    void insert(User user) {
        mRepo.insert(user);
    }
    LiveData<List<User>> getmUser(){
        return mUser;
    }
}
