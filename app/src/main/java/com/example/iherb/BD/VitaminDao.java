package com.example.iherb.BD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface VitaminDao {
    @Insert
    public long addUser(VitaminEntity vitaminEntity);

    @Update
    public void updateUser(VitaminEntity vitaminEntity);

    @Delete
    public void deleteUser(VitaminEntity vitaminEntity);

    @Query("SELECT * FROM RulesPriem")
    public List<VitaminEntity> getAllUser();

    @Query("SELECT * FROM RulesPriem WHERE day ==:dayId")
    public List<VitaminEntity> getDay(Integer dayId);

    //    @Query("SELECT * FROM RulesPriem WHERE day =:id")
//    public List<VitaminEntity> loadSingle(int id);
    @Query("DELETE FROM RulesPriem")
    public void clearTable();
}
