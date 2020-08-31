package com.example.nushfrate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "user_name")
    public String username;

    @ColumnInfo(name = "worth")
    public long sum;
    User(int uid, String username,long sum){
        this.username = username;
        this.sum = sum;
        this.uid = uid;
    }
}
