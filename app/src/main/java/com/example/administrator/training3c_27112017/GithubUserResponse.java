package com.example.administrator.training3c_27112017;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Administrator on 11/27/17.
 */

public class GithubUserResponse implements Parcelable {
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<User> users = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<User> getItems() {
        return users;
    }

    public void setItems(List<User> users) {
        this.users = users;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.totalCount);
        dest.writeValue(this.incompleteResults);
        dest.writeTypedList(this.users);
    }

    public GithubUserResponse() {
    }

    protected GithubUserResponse(Parcel in) {
        this.totalCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.incompleteResults = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.users = in.createTypedArrayList(User.CREATOR);
    }

    public static final Parcelable.Creator<GithubUserResponse> CREATOR =
            new Parcelable.Creator<GithubUserResponse>() {
                @Override
                public GithubUserResponse createFromParcel(Parcel source) {
                    return new GithubUserResponse(source);
                }

                @Override
                public GithubUserResponse[] newArray(int size) {
                    return new GithubUserResponse[size];
                }
            };
}

