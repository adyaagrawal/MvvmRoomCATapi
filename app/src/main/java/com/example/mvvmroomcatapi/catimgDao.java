package com.example.mvvmroomcatapi;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface catimgDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Model> cats);

    @Query("SELECT DISTINCT * FROM catimg")
    LiveData<List<Model>>  getcats();

    @Query("DELETE FROM catimg")
    void deleteAll();
}
