package com.bh.android.snapasktest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by BH_Lin on 2016/03/29/
 */
class DataStore {

    public final String TAG = "BH_SA_" + this.getClass().getSimpleName();

    public DataStore(Context context) {

        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }

        if (mPrefEditor == null) {
            mPrefEditor = mSharedPreferences.edit();
        }
    }

    private final String KEY_PROFILE_DATA = "KEY_PROFILE_DATA";
    private final String KE_QUESTION_DATA = "KE_QUESTION_DATA";
    private final String KEY_USERDATA_PIC = "KEY_USERDATA_PIC";
    private final String KEY_QUESTIONER_ATTACHMENT = "KEY_QUESTIONER_ATTACHMENT";
    private final String KEY_QUESTIONER_PROFILE_PIC = "KEY_QUESTIONER_PROFILE_PIC";
    private final String KEY_ANSWERER_PROFILE_PIC = "KEY_ANSWERER_PROFILE_PIC";

    private SharedPreferences mSharedPreferences = null;
    private SharedPreferences.Editor mPrefEditor = null;

    public void storeQuestionData(String value) {
        mPrefEditor.putString(KE_QUESTION_DATA, value).apply();
    }

    public String getQuestionData() {
        return mSharedPreferences.getString(KE_QUESTION_DATA, null);
    }

    public void storeUserData(String value) {
        mPrefEditor.putString(KEY_PROFILE_DATA, value).apply();
    }

    public String getUserData() {
        return mSharedPreferences.getString(KEY_PROFILE_DATA, null);
    }

    public void storeUserDataPic(String value) {
        mPrefEditor.putString(KEY_USERDATA_PIC, value).apply();
    }

    public String getUserDataPic() {
        return mSharedPreferences.getString(KEY_USERDATA_PIC, null);
    }

    public void storeQuestionerProfilePic(String value) {
        mPrefEditor.putString(KEY_QUESTIONER_PROFILE_PIC, value).apply();
    }

    public String getQuestionerProfilePic() {
        return mSharedPreferences.getString(KEY_QUESTIONER_PROFILE_PIC, null);
    }

    public void storeAnswererProfilePic(String value) {
        mPrefEditor.putString(KEY_ANSWERER_PROFILE_PIC, value).apply();
    }

    public String getAnswererProfilePic() {
        return mSharedPreferences.getString(KEY_ANSWERER_PROFILE_PIC, null);
    }

    public void storeQuestionerAttachment(String value) {
        mPrefEditor.putString(KEY_QUESTIONER_ATTACHMENT, value).apply();
    }

    public String getQuestionerAttachment() {
        return mSharedPreferences.getString(KEY_QUESTIONER_ATTACHMENT, null);
    }
}
