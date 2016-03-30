package com.bh.android.snapasktest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        Button mBtn2ProfilePage = (Button) findViewById(R.id.btn2userdata);
        if (mBtn2ProfilePage != null) {
            mBtn2ProfilePage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, UserDataActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button mBtn2QuestionPage = (Button) findViewById(R.id.btn2questionpage);
        if (mBtn2QuestionPage != null) {
            mBtn2QuestionPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, QuestionActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
