package com.example.Monopoly_bank;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<User> mUser;
    UserRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUser = mUserDao.getUser();
    }
    void insert(User user){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }
    void updateName(String name){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.updateName(name);
        });
    }
    void updateWorth(long value){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.updateWorth(value);
        });
    }

    String getUsername(){
        return mUserDao.getUsername();
    }

    LiveData<User> getUser(){
        return mUser;
    }
}
