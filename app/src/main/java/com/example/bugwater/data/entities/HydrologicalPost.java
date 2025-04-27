package com.example.bugwater.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = River.class,
                parentColumns = "id",
                childColumns = "river_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Region.class,
                parentColumns = "id",
                childColumns = "region_id",
                onDelete = ForeignKey.CASCADE)
        })
public class HydrologicalPost {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public int area;

    @ColumnInfo(name = "river_id")
    public int riverId;

    @ColumnInfo(name = "region_id")
    public int regionId;
}
