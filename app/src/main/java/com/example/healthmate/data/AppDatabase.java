package com.example.healthmate.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {HealthRecord.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HealthRecordDao healthRecordDao();
    private static volatile AppDatabase INSTANCE;
    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(2);
    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "healthmate.db")
                            .addCallback(seedCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback seedCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseExecutor.execute(() -> {
                HealthRecordDao dao = INSTANCE.healthRecordDao();


                dao.insert(new HealthRecord(daysAgo(0), 7850, 420, 530));
                dao.insert(new HealthRecord(daysAgo(0), 6000, 220, 1500));
                dao.insert(new HealthRecord(daysAgo(1), 8005, 220, 2050));
                dao.insert(new HealthRecord(daysAgo(2), 7920, 170, 1503));
                dao.insert(new HealthRecord(daysAgo(3), 8320, 320, 984));
                dao.insert(new HealthRecord(daysAgo(4), 4300, 250, 749));
                dao.insert(new HealthRecord(daysAgo(5), 5346, 450, 1400));
                dao.insert(new HealthRecord(daysAgo(6), 7539, 670, 2345));
                dao.insert(new HealthRecord(daysAgo(7), 6873, 520, 2865));
                dao.insert(new HealthRecord(daysAgo(8), 5673, 230, 1243));
                dao.insert(new HealthRecord(daysAgo(9), 4887, 330, 2784));
                dao.insert(new HealthRecord(daysAgo(10), 4850, 250, 2900));

                // Added some dummy data so the dashboard SUM means something.
            });
        }
    };

    private static String daysAgo(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -n);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(c.getTime());
    }
}