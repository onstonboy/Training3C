package com.example.administrator.training3c_27112017.roomdb.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.administrator.training3c_27112017.roomdb.dao.UserDAO;
import com.example.administrator.training3c_27112017.roomdb.entity.User;

/**
 * Created by Administrator on 11/29/17.
 */

@android.arch.persistence.room.Database(entities = { User.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public static final String DB_NAME = "UserGetFromServerByRetrofit.DB";
    public abstract UserDAO getUserDAO();
    public static final Database initDB(Context context){
        return Room.databaseBuilder(context, Database.class, DB_NAME).build();
    }
}
