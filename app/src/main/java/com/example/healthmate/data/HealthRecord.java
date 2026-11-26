package com.example.healthmate.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "health_records")
public class HealthRecord{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "steps")
    private int steps;
    @ColumnInfo(name = "calories")
    private int calories;
    @ColumnInfo(name = "water")
    private int water;

    public HealthRecord(String date, int steps, int calories, int water){
        this.date = date;
        this.steps = steps;
        this.calories = calories;
        this.water = water;
    }

    public int getId(){
        return id;
    }
    public String getDate(){
        return date;
    }
    public int getSteps(){
        return steps;
    }
    public int getCalories(){
        return calories;
    }
    public int getWater(){
        return water;
    }

    public void setId(int id) {
        this.id = id;
    }
}
