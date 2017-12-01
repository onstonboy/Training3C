package com.example.administrator.training3c_27112017.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.administrator.training3c_27112017.MainApplication;
import com.example.administrator.training3c_27112017.roomdb.database.Database;
import com.example.administrator.training3c_27112017.model.User;

/**
 * Created by Administrator on 11/29/17.
 */

public class InsertUserWhenClickOnRecyclerViewAsyncTask extends AsyncTask<User, Void, Void> {

    private Database mDatabase;
    private Context mContext;

    public InsertUserWhenClickOnRecyclerViewAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDatabase = MainApplication.getDatabase();
    }

    @Override
    protected Void doInBackground(User... users) {
//        mDatabase.getUserDAO().insertUser(users[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(mContext, "Insert user Success", Toast.LENGTH_SHORT).show();
    }
}
