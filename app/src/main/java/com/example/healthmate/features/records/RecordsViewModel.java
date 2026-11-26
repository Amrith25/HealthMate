package com.example.healthmate.features.records;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.healthmate.data.HealthRecord;
import com.example.healthmate.data.HealthRecordRepository;

import java.util.List;

public class RecordsViewModel extends AndroidViewModel {

    private HealthRecordRepository repository;
    private LiveData<List<HealthRecord>> records;

    public RecordsViewModel(Application application) {
        super(application);
        repository = new HealthRecordRepository(application);
        records = repository.getAllRecords();
    }
    public LiveData<List<HealthRecord>> getRecords() {
        return records;
    }
    public void delete(HealthRecord record) {
        repository.delete(record);
    }
}
