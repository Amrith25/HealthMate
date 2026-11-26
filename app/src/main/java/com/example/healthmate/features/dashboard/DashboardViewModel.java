package com.example.healthmate.features.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.healthmate.data.HealthRecordRepository;
import com.example.healthmate.util.DateUtils;

public class DashboardViewModel extends AndroidViewModel {

    private HealthRecordRepository repository;
    private String today;
    private LiveData<Integer> totalSteps;
    private LiveData<Integer> totalCalories;
    private LiveData<Integer> totalWater;

    public DashboardViewModel(Application application) {
        super(application);
        repository = new HealthRecordRepository(application);
        today = DateUtils.today();
        totalSteps = repository.getTotalSteps(today);
        totalCalories = repository.getTotalCalories(today);
        totalWater = repository.getTotalWater(today);
    }

    public String getToday() { return today; }
    public LiveData<Integer> getTotalSteps() { return totalSteps; }
    public LiveData<Integer> getTotalCalories() { return totalCalories; }
    public LiveData<Integer> getTotalWater() { return totalWater; }
}
