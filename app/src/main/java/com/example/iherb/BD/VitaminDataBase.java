package com.example.iherb.BD;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {VitaminEntity.class},version = 1)
public abstract class VitaminDataBase extends RoomDatabase {
    public abstract VitaminDao getUserDao();
}
