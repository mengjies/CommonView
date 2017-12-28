package com.mj.commonview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mj.common_view.CommonView;

public class MainActivity extends AppCompatActivity {
private CommonView commonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commonView = (CommonView) findViewById(R.id.commonView);

        commonView.showProBar();
    }
}
