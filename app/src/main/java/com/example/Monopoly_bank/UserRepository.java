package com.example.Monopoly_bank;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mUser;
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
    LiveData<List<User>> getUser(){
        return mUser;
    }
}
