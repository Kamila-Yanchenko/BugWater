package com.example.bugwater.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Unit.class,
        parentColumns = "id",
        childColumns = "unit_id",
        onDelete = ForeignKey.CASCADE
))
public class Indicator {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    @ColumnInfo(name = "unit_id")
    public int unitId;
}
