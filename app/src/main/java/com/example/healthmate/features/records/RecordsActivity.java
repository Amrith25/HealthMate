package com.example.healthmate.features.records;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import com.example.healthmate.R;
import com.example.healthmate.databinding.ActivityRecordsBinding;
import com.example.healthmate.features.addedit.AddEditActivity;


public class RecordsActivity extends AppCompatActivity {


    ActivityRecordsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRecordsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            RecordAdapter adapter = new RecordAdapter();
            binding.recyclerRecords.setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerRecords.setAdapter(adapter);
            RecordsViewModel viewModel = new ViewModelProvider(this).get(RecordsViewModel.class);
            adapter.setOnRecordLongClickListener(record -> viewModel.delete(record));
            adapter.setOnRecordClickListener(record -> {
            Intent intent = new Intent(RecordsActivity.this, AddEditActivity.class);
            intent.putExtra("id", record.getId());
            intent.putExtra("date", record.getDate());
            intent.putExtra("steps", record.getSteps());
            intent.putExtra("calories", record.getCalories());
            intent.putExtra("water", record.getWater());
            startActivity(intent);
        });
            viewModel.getRecords().observe(this, list -> {
                adapter.setRecords(list);
                if (list.isEmpty()) {
                    binding.textEmpty.setVisibility(View.VISIBLE);
                } else {
                    binding.textEmpty.setVisibility(View.GONE);
                }
            });
        }
    }


