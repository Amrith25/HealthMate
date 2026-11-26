package com.example.healthmate.features.records;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.healthmate.data.HealthRecord;
import com.example.healthmate.databinding.ItemRecordBinding;
import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private List<HealthRecord> records = new ArrayList<>();

    static class RecordViewHolder extends RecyclerView.ViewHolder {
        ItemRecordBinding binding;

        RecordViewHolder(ItemRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnRecordLongClickListener {
        void onLongClick(HealthRecord record);
    }

    public void setOnRecordLongClickListener(OnRecordLongClickListener listener) {
        this.listener = listener;
    }
    private OnRecordLongClickListener listener;

    public interface OnRecordClickListener {
        void onClick(HealthRecord record);
    }
    private OnRecordClickListener clickListener;
    public void setOnRecordClickListener(OnRecordClickListener listener) {
        this.clickListener = listener;
    }
    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecordBinding binding = ItemRecordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        HealthRecord record = records.get(position);
        holder.binding.textRowDate.setText(record.getDate());
        holder.binding.textRowDetails.setText(String.format("Steps: %d, Calories: %d, Water: %d", record.getSteps(), record.getCalories(), record.getWater()));

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(record);
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onLongClick(record);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<HealthRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }
}


