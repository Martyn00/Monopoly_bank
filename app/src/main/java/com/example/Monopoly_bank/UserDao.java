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
    LiveData<User> getUser();

    @Query("SELECT user_name FROM User")
    String getUsername();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("SELECT * FROM User")
    List<User> getUserStatic();

    @Delete
    void delete(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("UPDATE User SET user_name = :name WHERE uid = 1")
    void updateName(String name);

    @Query("UPDATE User SET worth = :value WHERE uid = 1")
    void updateWorth(long value);

}
