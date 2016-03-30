package com.bh.android.snapasktest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class QuestionActivity extends AppCompatActivity {

    private final String TAG = "BH_" + this.getClass().getSimpleName();

    private DataStore mDataStore = null;

    private TextView mViewQuestionerCreatedTime = null;

    private TextView mViewQuestionerName = null;
    private ImageView mViewQuestionerAvatar = null;
    private TextView mViewQuestionerDescription = null;
    private ImageView mViewQuestionAttachment = null;

    private TextView mViewAnswererName = null;
    private ImageView mViewAnswererAvatar = null;

    private String mQueryId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Context mContext = this;


        Intent intent = getIntent();
        String dataString = intent.getDataString();
        if(dataString != null) {
            mQueryId = dataString.replace("snapaskinterview://question/", "");
        }


        if (mDataStore == null) {
            mDataStore = new DataStore(mContext);
        }

        mViewQuestionerName = (TextView) findViewById(R.id.questioner_name);
        mViewQuestionerCreatedTime = (TextView) findViewById(R.id.timestamp);
        mViewQuestionerAvatar = (ImageView) findViewById(R.id.questioner_avatar);
        mViewQuestionerDescription = (TextView) findViewById(R.id.description);
        mViewQuestionAttachment = (ImageView) findViewById(R.id.questioner_Attachment);

        mViewAnswererName = (TextView) findViewById(R.id.answerer_name);
        mViewAnswererAvatar = (ImageView) findViewById(R.id.answerer_avatar);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mQueryId != null && !mQueryId.equals("1qzf3")) {
            mQueryId = null;
            finish();
        }

        if (mDataStore.getQuestionData() != null) {
            QuestionData questionData = DataParser.parserQuestionData(mDataStore.getQuestionData());
            fillQuestionData(questionData);
            mViewQuestionerAvatar.setImageBitmap(MyUtils.stringToBitmap(mDataStore.getAnswererProfilePic()));
            mViewAnswererAvatar.setImageBitmap(MyUtils.stringToBitmap(mDataStore.getQuestionerProfilePic()));
            mViewQuestionAttachment.setImageBitmap(MyUtils.stringToBitmap(mDataStore.getQuestionerAttachment()));

        } else {
            requestQuestionData();
        }
    }

    private void fillQuestionData(QuestionData questionData) {
        mViewQuestionerCreatedTime.setText(questionData.questionerCreatedTime);
        mViewQuestionerName.setText(questionData.questionerName);
        mViewQuestionerDescription.setText(questionData.questionerDescription);

        mViewAnswererName.setText(questionData.answererName);
    }

    private RequestQueue mRequestQueue = null;



    private void requestQuestionData() {
        final String url = "https://api.myjson.com/bins/1qzf3";

        // Instantiate the RequestQueue.
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
        }

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "Response is: " + response);

                        QuestionData questionData = DataParser.parserQuestionData(response);
                        if(questionData != null) {
                            mDataStore.storeQuestionData(response);
                        }
                        if (questionData != null) {
                            fillQuestionData(questionData);
                        }

                        if (questionData != null) {
                            String picUrl = questionData.questionerProfilePicUrl;
                            ImageRequest ir = new ImageRequest(picUrl, new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    mViewQuestionerAvatar.setImageBitmap(response);
                                    String tmp = MyUtils.bitmapToString(response);
                                    mDataStore.storeQuestionerProfilePic(tmp);
                                }
                            }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    mViewQuestionerAvatar.setImageResource(R.drawable.signs);
                                }
                            });
                            mRequestQueue.add(ir);

                            picUrl = questionData.answererProfilePicUrl;
                            ir = new ImageRequest(picUrl, new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    mViewAnswererAvatar.setImageBitmap(response);
                                    String tmp = MyUtils.bitmapToString(response);
                                    mDataStore.storeAnswererProfilePic(tmp);
                                }
                            }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    mViewQuestionerAvatar.setImageResource(R.drawable.signs);
                                }
                            });
                            mRequestQueue.add(ir);

                            picUrl = questionData.questionerAttachment;
                            ir = new ImageRequest(picUrl, new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap response) {
                                    mViewQuestionAttachment.setImageBitmap(response);
                                    String tmp = MyUtils.bitmapToString(response);
                                    mDataStore.storeQuestionerAttachment(tmp);
                                }
                            }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    mViewQuestionAttachment.setImageResource(R.drawable.signs);
                                }
                            });
                            mRequestQueue.add(ir);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);
    }
}
