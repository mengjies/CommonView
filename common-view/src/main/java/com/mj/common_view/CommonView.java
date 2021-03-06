package com.mj.common_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by MengJie on 2017/9/5.
 * CommonView
 */

public class CommonView extends RelativeLayout {

    private static final int DEF_TEXT_COLOR = Color.GRAY;
    private static final float DEF_TEXT_SIZE_SP = 15;
    //value
    private int mTextColor;
    private float mTextSize;
    private String mEmptyText;
    private Bitmap mErrorImage;
    private String mErrorText;
    private Bitmap mEmptyImage;
    private ContentLoadingProgressBar clProBar;
    //view
    private LinearLayout llProBar, llEmpty, llError;
    private ImageView ivEmpty, ivError;
    private TextView tvEmpty, tvError;
    private OnViewClickListener listener;

    public CommonView(Context context) {
        this(context, null, 0);
    }

    public CommonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CommonView, defStyleAttr, 0);

        try {
            /*mTextColor = ta.getColor(R.styleable.CommonView_textColor, DEF_TEXT_COLOR);
            float defTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEF_TEXT_SIZE_SP,
                    getResources().getDisplayMetrics());
            mTextSize = ta.getDimensionPixelSize(R.styleable.CommonView_textSize, (int) defTextSize);*/


            mEmptyText = ta.getString(R.styleable.CommonView_emptyText);
            mErrorText = ta.getString(R.styleable.CommonView_errorText);
            mEmptyImage = BitmapFactory.decodeResource(getResources(), ta.getResourceId(R.styleable.CommonView_emptyImage, 0));
            mErrorImage = BitmapFactory.decodeResource(getResources(), ta.getResourceId(R.styleable.CommonView_errorImage, 0));
        } finally {
            ta.recycle();
        }

        init(context);

    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_common, this, true);
        llProBar = (LinearLayout) findViewById(R.id.ll_pro_bar);
        clProBar = (ContentLoadingProgressBar) findViewById(R.id.clProgressBar);
        llEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        llError = (LinearLayout) findViewById(R.id.ll_error);
        ivEmpty = (ImageView) findViewById(R.id.iv_empty);
        ivError = (ImageView) findViewById(R.id.iv_error);
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
        tvError = (TextView) findViewById(R.id.tv_error);

        //init
        llProBar.setVisibility(GONE);
        llEmpty.setVisibility(GONE);
        llError.setVisibility(GONE);


        //set image
        if (mEmptyImage != null) {
            ivEmpty.setImageBitmap(mEmptyImage);
        }
        if (mErrorImage != null) {
            ivError.setImageBitmap(mErrorImage);
        }

        //text size and color
        /*tvEmpty.setTextSize(mTextSize);
        tvEmpty.setTextColor(mTextColor);
        tvError.setTextSize(mTextSize);
        tvError.setTextColor(mTextColor);*/

        //set text
        if (TextUtils.isEmpty(mEmptyText)) {
            mEmptyText = "啥也没有！点击刷新";
        }
        tvEmpty.setText(mEmptyText);
        if (TextUtils.isEmpty(mErrorText)) {
            mErrorText = "网络异常！点击重试";
        }
        tvError.setText(mErrorText);

        llEmpty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    hideView();
                    listener.onEmptyClick();
                }
            }
        });

        llError.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    hideView();
                    listener.onErrorClick();
                }
            }
        });

    }

    public void showProBar() {
        llProBar.setVisibility(VISIBLE);
        clProBar.setVisibility(VISIBLE);
        clProBar.show();
    }

    public void hideProBar() {
        llProBar.setVisibility(GONE);
        clProBar.setVisibility(GONE);
        clProBar.hide();
    }

    public void showError() {
        llError.setVisibility(VISIBLE);
        llEmpty.setVisibility(GONE);
        tvError.setText(mErrorText);
    }

    public void showError(String msg) {
        llError.setVisibility(VISIBLE);
        llEmpty.setVisibility(GONE);
        tvError.setText(msg);
    }

    public void showEmpty() {
        llEmpty.setVisibility(VISIBLE);
        llError.setVisibility(GONE);
        tvEmpty.setText(mEmptyText);
    }

    public void showEmpty(String msg) {
        llEmpty.setVisibility(VISIBLE);
        llError.setVisibility(GONE);
        tvEmpty.setText(msg);
    }

    public void hideView() {
        llEmpty.setVisibility(GONE);
        llError.setVisibility(GONE);
    }

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.listener = listener;
    }

    public interface OnViewClickListener {

        void onErrorClick();

        void onEmptyClick();

    }

}
