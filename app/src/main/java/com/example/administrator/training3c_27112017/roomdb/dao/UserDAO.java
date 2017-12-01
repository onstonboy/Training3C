package com.example.administrator.training3c_27112017.roomdb.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.administrator.training3c_27112017.model.User;
import com.example.administrator.training3c_27112017.roomdb.entity.UserEntity;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by Administrator on 11/29/17.
 */
@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);
    @Insert()
    void insertListUser(List<UserEntity> user);

    @Query("SELECT * FROM users")
    Flowable<List<UserEntity>> getListUser();
}
