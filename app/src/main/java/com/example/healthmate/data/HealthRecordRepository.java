package com.example.healthmate.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HealthRecordRepository {
    private HealthRecordDao healthRecordDao;

    public HealthRecordRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        healthRecordDao = db.healthRecordDao();
    }
    public LiveData<List<HealthRecord>> getAllRecords() {
        return healthRecordDao.getAllRecords();
    }
    public LiveData<List<HealthRecord>> getRecordsByDate(String date) {
        return healthRecordDao.getRecordsByDate(date);
    }
    public LiveData<Integer> getTotalSteps(String date) {
        return healthRecordDao.getTotalSteps(date);
    }
    public LiveData<Integer> getTotalCalories(String date) {
        return healthRecordDao.getTotalCalories(date);
    }
    public LiveData<Integer> getTotalWater(String date) {
        return healthRecordDao.getTotalWater(date);
    }
    public void insert(HealthRecord record) {
        AppDatabase.databaseExecutor.execute(() -> healthRecordDao.insert(record));
    }
    public void update(HealthRecord record) {
        AppDatabase.databaseExecutor.execute(() -> healthRecordDao.update(record));
    }
    public void delete(HealthRecord record) {
        AppDatabase.databaseExecutor.execute(() -> healthRecordDao.delete(record));
    }
}
