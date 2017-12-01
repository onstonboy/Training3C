package com.example.administrator.training3c_27112017.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.administrator.training3c_27112017.GithubUserResponse;
import com.example.administrator.training3c_27112017.MainApplication;
import com.example.administrator.training3c_27112017.roomdb.database.Database;
import com.example.administrator.training3c_27112017.roomdb.entity.User;

/**
 * Created by Administrator on 11/29/17.
 */

public class InsertUserToDBAsynctask extends AsyncTask<GithubUserResponse, Void, Void> {

    private Database mDatabase;
    private Context mContext;

    public InsertUserToDBAsynctask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDatabase = MainApplication.getDatabase();

    }

    @Override
    protected Void doInBackground(GithubUserResponse... githubUserResponses) {
//        for (User user : githubUserResponses[0].getItems()) {
//            mDatabase.getUserDAO().insertUser(user);
//        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(mContext, "Inserted data to local DB", Toast.LENGTH_SHORT).show();
    }
}
