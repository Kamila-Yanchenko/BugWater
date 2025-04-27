package com.example.bugwater.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Region {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public Region(@NonNull String name) {
        this.name = name;
    }
}
