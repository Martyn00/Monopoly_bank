package com.example.Monopoly_bank;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = true) public int uid;

    @ColumnInfo(name = "user_name") public String username;

    @ColumnInfo(name = "worth")public long sum;

    User(String username, long sum){
        this.username = username;
        this.sum = sum;
    }
}
