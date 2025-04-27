package com.example.bugwater.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bugwater.data.dao.HydroDao;
import com.example.bugwater.data.entities.*;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;

public abstract class AppDatabase extends RoomDatabase {
    public abstract HydroDao hydroDao();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "hydro_database")
                    .addCallback(roomCallback)
                    .allowMainThreadQueries() // лише для тестів
                    .build();
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new Thread(() -> {
                HydroDao dao = INSTANCE.hydroDao();

                // Річки
                dao.insertRiver(new River("Південний Буг"));
                dao.insertRiver(new River("Інгул"));
                dao.insertRiver(new River("Синюха"));
                dao.insertRiver(new River("Велика Вись"));

                // Регіони
                dao.insertRegion(new Region("с. Александрівка"));
                dao.insertRegion(new Region("с. Лелітка"));
                dao.insertRegion(new Region("с. Тростянчик"));
                dao.insertRegion(new Region("с. Підгір'я"));
                dao.insertRegion(new Region("м. Первомайськ"));
                dao.insertRegion(new Region("м. Кіровоград"));
                dao.insertRegion(new Region("с. Седнівка"));
                dao.insertRegion(new Region("с. Новогорожене"));
                dao.insertRegion(new Region("с. Синюхін Брід"));
                dao.insertRegion(new Region("Ново-Архангельська ГЕС"));
                dao.insertRegion(new Region("с. Ямпіль"));
            }).start();
        }
    };
}
