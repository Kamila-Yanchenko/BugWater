package com.example.bugwater.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class River {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public River(@NonNull String name) {
        this.name = name;
    }
}
