package com.example.nushfrate;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_table LIMIT 1")
    LiveData<User> getUser();

    @Query("SELECT user_name FROM user_table")
    String getUsername();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     void insert(User user);

    @Delete
    void delete(User user);

}
