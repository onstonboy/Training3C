package com.example.administrator.training3c_27112017.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.administrator.training3c_27112017.MainApplication;
import com.example.administrator.training3c_27112017.roomdb.database.Database;
import com.example.administrator.training3c_27112017.model.User;
import java.util.List;

/**
 * Created by Administrator on 11/29/17.
 */

public class GetListUserFromDBAsynctask extends AsyncTask<Void, Void, List<User>> {

    private Context mContext;
    private Database mDatabase;

    public GetListUserFromDBAsynctask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDatabase = MainApplication.getDatabase();
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
//        return mDatabase.getUserDAO().getListUser();
        return null;
    }

    @Override
    protected void onPostExecute(List<User> users) {
        super.onPostExecute(users);
        Toast.makeText(mContext, "Get List Succese", Toast.LENGTH_SHORT).show();
    }
}
