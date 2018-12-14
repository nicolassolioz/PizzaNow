package com.mycompany.pizzanow.database.dao;


import android.arch.lifecycle.LiveData;
        import android.arch.persistence.room.Dao;
        import android.arch.persistence.room.Query;
        import android.arch.persistence.room.Delete;
        import android.arch.persistence.room.Insert;
        import android.arch.persistence.room.OnConflictStrategy;
        import android.arch.persistence.room.Query;
        import android.arch.persistence.room.Transaction;
        import android.arch.persistence.room.Update;

        import com.mycompany.pizzanow.database.entity.PosEntity;

        import java.util.List;

@Dao
public interface PosDao {
    @Query("SELECT * FROM pos")
    LiveData<List<PosEntity>> getAll();

    @Insert
    void insert(PosEntity posEntity);

    @Query("SELECT * FROM pos WHERE IdFiliale=1")
    List<PosEntity> getOnePizza();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PosEntity> posEntities);

    @Update
    void update(PosEntity posEntity);

    @Delete
    void delete(PosEntity posEntity);


    @Query("SELECT * FROM pos WHERE IdFiliale = :id")
    LiveData<PosEntity> getById(int id);

}
