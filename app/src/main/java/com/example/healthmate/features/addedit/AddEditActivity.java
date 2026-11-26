package com.example.healthmate.features.addedit;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthmate.R;
import com.example.healthmate.databinding.ActivityAddEditBinding;
import com.example.healthmate.util.DateUtils;

public class AddEditActivity extends AppCompatActivity {
    ActivityAddEditBinding binding;
    private AddEditViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AddEditViewModel.class);
        binding.inputDate.setText(DateUtils.today());
        binding.buttonSave.setOnClickListener(v -> saveRecord());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void saveRecord() {
        String stepsText = binding.inputSteps.getText().toString().trim();

        if (stepsText.isEmpty()) {
            binding.layoutSteps.setError("Steps cannot be empty");
            return;
        }
        binding.layoutSteps.setError(null);

        String caloriesText = binding.inputCalories.getText().toString().trim();

        if (caloriesText.isEmpty()) {
            binding.layoutCalories.setError("Calories cannot be empty");
            return;
        }
        binding.layoutCalories.setError(null);

        String waterText = binding.inputWater.getText().toString().trim();

        if (waterText.isEmpty()) {
            binding.layoutWater.setError("Water cannot be empty");
            return;
        }
        binding.layoutWater.setError(null);

        int steps, calories, water;
        try {
            steps = Integer.parseInt(stepsText);
            calories = Integer.parseInt(caloriesText);
            water = Integer.parseInt(waterText);
        } catch (NumberFormatException e) {
            binding.layoutSteps.setError("Enter valid numbers");
            return;
        }
        if (steps < 1 || steps > 30000) {
            binding.layoutSteps.setError("Enter 1 to 30000");
            return;
        }
        if (calories < 1 || calories > 20000) {
            binding.layoutCalories.setError("Enter 1 to 20000");
            return;
        }
        if (water < 1 || water > 10000) {
            binding.layoutWater.setError("Enter 1 to 10000");
            return;
        }
        String date = binding.inputDate.getText().toString();
        viewModel.save(date, steps, calories, water);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}