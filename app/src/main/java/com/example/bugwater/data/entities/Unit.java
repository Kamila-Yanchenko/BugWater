package com.example.bugwater.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Unit {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;
}
