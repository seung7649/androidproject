package com.example.seung.labhelper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class StartActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바 안보이게 하는것, 위에 FragmentActivity 상속받음
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);

    }
}
