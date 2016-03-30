package com.bh.android.snapasktest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BH_Lin on 3/30/16.
 */
class DataParser {

    public static UserData parseUserData(String userDataString) {

        UserData userData = new UserData();
        try {
            JSONObject object = new JSONObject(userDataString);
            JSONObject nameObject = object.getJSONObject("name");
            userData.firstName = nameObject.getString("first");
            userData.lastName = nameObject.getString("last");

            userData.email = object.getString("email");
            userData.phone = object.getString("phone");
            userData.school = object.getString("school");
            userData.picUrl = object.getString("profile_pic_url");

        } catch (JSONException e) {
            e.printStackTrace();
            userData = null;
        }

        return userData;
    }


    public static QuestionData parserQuestionData(String questionData) {

        QuestionData question = new QuestionData();
        try {
            JSONObject object = new JSONObject(questionData).getJSONObject("data");

            JSONObject asked_byObj = object.getJSONObject("asked_by");
            question.questionerCreatedTime = object.getString("created_at");
            question.questionerName = asked_byObj.getString("username");
            question.questionerProfilePicUrl = asked_byObj.getString("profile_pic_url");
            question.questionerDescription = object.getString("description");
            question.questionerAttachment = object.getString("picture_url");

            JSONObject answered_byObj = object.getJSONObject("answered_by");
            question.answererName = answered_byObj.getString("username");
            question.answererProfilePicUrl = answered_byObj.getString("profile_pic_url");

        } catch (JSONException e) {
            e.printStackTrace();
            question = null;
        }

        return question;
    }
}
