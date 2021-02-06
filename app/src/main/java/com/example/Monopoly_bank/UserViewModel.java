package com.example.Monopoly_bank;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepo;

    LiveData<User> mUser;
    public UserViewModel(Application application) {
        super(application);
        mRepo = new UserRepository(application);
        mUser = mRepo.getUser();
    }
    void insert(User user) {
        mRepo.insert(user);
    }
    void updateName(String name) {
        mRepo.updateName(name);
    }
    void updateWorth(long value) {
        mRepo.updateWorth(value);
    }
    String getUsername(){
        return mRepo.getUsername();
    }
    LiveData<User> getmUser(){
        return mUser;
    }
}
