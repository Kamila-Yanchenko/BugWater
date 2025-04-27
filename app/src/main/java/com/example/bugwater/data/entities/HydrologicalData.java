package com.example.bugwater.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = HydrologicalPost.class,
                parentColumns = "id",
                childColumns = "post_id",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Indicator.class,
                parentColumns = "id",
                childColumns = "indicator_id",
                onDelete = ForeignKey.CASCADE)
})
public class HydrologicalData {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "post_id")
    public int postId;

    @ColumnInfo(name = "indicator_id")
    public int indicatorId;

    public int year;

    public int month;

    public float value;
}
