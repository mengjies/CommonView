package com.mj.commonview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.mj.common_view.CommonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.commonView)
    CommonView commonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        commonView.setOnViewClickListener(new CommonView.OnViewClickListener() {
            @Override
            public void onErrorClick() {
                refresh();
            }

            @Override
            public void onEmptyClick() {
                refresh();
            }
        });

    }

    @OnClick({R.id.btn_showProBar, R.id.btn_showEmpty, R.id.btn_showErro})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_showProBar:
                refresh();
                break;
            case R.id.btn_showEmpty:
                commonView.showEmpty();
                break;
            case R.id.btn_showErro:
                commonView.showError();
                break;
        }
    }

    private void refresh() {
        commonView.hideView();
        commonView.showProBar();
        Log.d(TAG, "onClick: "+ Thread.currentThread().getId());
        commonView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + Thread.currentThread().getId());
                commonView.hideProBar();
            }
        },2000);
    }
}
