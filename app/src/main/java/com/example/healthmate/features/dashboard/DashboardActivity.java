package com.example.healthmate.features.dashboard;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import com.example.healthmate.R;
import com.example.healthmate.databinding.ActivityDashboardBinding;
import com.example.healthmate.features.addedit.AddEditActivity;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    private DashboardViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding.textDate.setText(viewModel.getToday());

        viewModel.getTotalSteps().observe(this, steps -> {
            binding.textSteps.setText(String.valueOf(steps));
        });
        viewModel.getTotalCalories().observe(this, calories -> {
            binding.textCalories.setText(String.valueOf(calories));
        });
        viewModel.getTotalWater().observe(this, water -> {
            binding.textWater.setText(String.valueOf(water));
        });
        binding.buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AddEditActivity.class);
            startActivity(intent);
        });
    }
}