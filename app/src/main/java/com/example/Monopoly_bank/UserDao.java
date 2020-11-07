package com.example.Monopoly_bank;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getUser();

    @Query("SELECT user_name FROM User")
    String getUsername();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     void insert(User user);

    @Delete
    void delete(User user);

    @Delete
    void deleteAll();

}
