package com.example.bugwater.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bugwater.data.entities.*;

import java.util.List;

@Dao
public interface HydroDao {
    @Insert
    void insertRiver(River river);

    @Insert
    void insertRegion(Region region);

    @Insert
    void insertPost(HydrologicalPost post);

    @Insert
    void insertData(HydrologicalData data);

    @Query("SELECT * FROM River")
    List<River> getAllRivers();


}


