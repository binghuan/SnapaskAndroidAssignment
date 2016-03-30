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

public class UserDataActivity extends AppCompatActivity {

    private final String TAG = "BH_" + this.getClass().getSimpleName();

    private ImageView mViewAvatar = null;
    private TextView mViewUserName = null;
    private TextView mViewEmail = null;
    private TextView mViewPhone = null;
    private TextView mViewSchool = null;

    private DataStore mDataStore = null;

    /* Result after calling API.
    {
        id: 723,
        gender: "male",
        name: {
            first: "adrian",
            last: "martinez"
        },
        email: "adrian.martinez@example.com",
        username: "greenbear131",
        registered: 1153910367,
        dob: 1290592394,
        school: "HKU",
        country_code: "852",
        phone: "12345678",
        profile_pic_url: "https://randomuser.me/api/portraits/men/5.jpg",
        role: {
            id: 3,
            name: "Tutor"
        },
        rating: 4.3,
        rating_total: 5
        }
     */

    private String mQueryId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        Context mContext = this;

        Intent intent = getIntent();
        String dataString = intent.getDataString();
        if (dataString != null) {
            mQueryId = dataString.replace("snapaskinterview://user/", "");
        }

        mViewAvatar = (ImageView) findViewById(R.id.avatar);
        mViewUserName = (TextView) findViewById(R.id.name);
        mViewEmail = (TextView) findViewById(R.id.email);
        mViewPhone = (TextView) findViewById(R.id.phonenumber);
        mViewSchool = (TextView) findViewById(R.id.university);

        mDataStore = new DataStore(mContext);
    }

    private void fillUserData(UserData userData) {
        mViewUserName.setText(userData.firstName + " " + userData.lastName);
        mViewEmail.setText(userData.email);
        mViewPhone.setText(userData.phone);
        mViewSchool.setText(userData.school);
    }


    private RequestQueue mRequestQueue = null;

    private void requestUserData() {
        final String url = "https://api.myjson.com/bins/mosv";

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

                        UserData userData = DataParser.parseUserData(response);
                        if (userData != null) {
                            mDataStore.storeUserData(response);
                            fillUserData(userData);
                        }

                        if (userData != null) {
                            String picUrl = userData.picUrl;
                            ImageRequest ir = new ImageRequest(picUrl, new Response.Listener<Bitmap>() {

                                @Override
                                public void onResponse(Bitmap response) {
                                    mViewAvatar.setImageBitmap(response);
                                    String tmp = MyUtils.bitmapToString(response);
                                    mDataStore.storeUserDataPic(tmp);
                                }
                            }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                                public void onErrorResponse(VolleyError error) {
                                    mViewAvatar.setImageResource(R.drawable.signs);
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

    @Override
    protected void onResume() {
        super.onResume();

        if (mQueryId != null && !mQueryId.equals("mosv")) {
            mQueryId = null;
            finish();
        }

        if (mDataStore.getUserData() != null && mDataStore.getUserDataPic() != null) {
            String tmp = mDataStore.getUserDataPic();
            mViewAvatar.setImageBitmap(MyUtils.stringToBitmap(tmp));
            UserData userData = DataParser.parseUserData(mDataStore.getUserData());
            fillUserData(userData);
        } else {
            requestUserData();
        }

    }
}
