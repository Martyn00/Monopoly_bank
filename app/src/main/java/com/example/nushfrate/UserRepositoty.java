package com.example.nushfrate;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class UserRepositoty {
    private UserDao mUserDao;
    private LiveData<User> mUser;
    UserRepositoty(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUser = mUserDao.getUser();
    }
    void insert(User user){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insert(user);
        });
    }
    LiveData<User> getUser(){
//        AppDatabase.databaseWriteExecutor.execute(() -> {
//            usr =  mUser;
//        });
        return mUser;
    }
}
