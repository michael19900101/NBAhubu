package com.aotuman.nbahubu.ui.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.aotuman.nbahubu.R;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

import org.jetbrains.annotations.NotNull;

class LoadingFooter extends LinearLayout implements RefreshFooter {

    LottieAnimationView mAnimationView;

    public LoadingFooter(Context context) {
        super(context);
        initView(context);
    }

    public LoadingFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadingFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_loading_more, this);
        mAnimationView = view.findViewById(R.id.loading_lottie);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.FixedBehind;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPrimaryColors(int... colors) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        mAnimationView.playAnimation();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        mAnimationView.cancelAnimation();
        return 0;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }
}
