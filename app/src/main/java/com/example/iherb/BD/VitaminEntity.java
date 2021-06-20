package com.example.iherb.BD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "RulesPriem")
public class VitaminEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    Integer id;
    @ColumnInfo(name = "nameVitamin")
    String nameVitamin;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "countVitamin")
    String countVitamin;
    @ColumnInfo(name = "day")
    Integer day;
    @ColumnInfo(name = "check")
    boolean check;

    public VitaminEntity(Integer id, String nameVitamin, String time, String countVitamin,
                         Integer day) {
        this.id = id;
        this.day = day;
        this.nameVitamin = nameVitamin;
        this.time = time;
        this.countVitamin = countVitamin;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameVitamin() {
        return nameVitamin;
    }

    public void setNameVitamin(String nameVitamin) {
        this.nameVitamin = nameVitamin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getCountVitamin() {
        return time;
    }

    public void setCountVitamin(String countVitamin) {
        this.countVitamin = countVitamin;
    }

    public VitaminEntity() {
    }
}
