package com.example.healthmate.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
    public interface HealthRecordDao {
        @Insert
        void insert(HealthRecord record);

        @Update
        void update(HealthRecord record);

        @Delete
        void delete(HealthRecord record);

        @Query("SELECT * FROM health_records ORDER BY date DESC, id DESC")
        LiveData<List<HealthRecord>> getAllRecords();
        @Query("SELECT * FROM health_records WHERE date = :date ORDER BY id DESC")
        LiveData<List<HealthRecord>> getRecordsByDate(String date);

    }




