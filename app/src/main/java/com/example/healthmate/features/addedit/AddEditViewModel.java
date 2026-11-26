package com.example.healthmate.features.addedit;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.healthmate.data.HealthRecord;
import com.example.healthmate.data.HealthRecordRepository;

public class AddEditViewModel extends AndroidViewModel {
    private final HealthRecordRepository repository;
    public AddEditViewModel(Application application) {
        super(application);
        repository = new HealthRecordRepository(application);
    }
    public void save(String date, int steps, int calories, int water) {
        HealthRecord record = new HealthRecord(date, steps, calories, water);
        repository.insert(record);
    }
}
