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
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

import org.jetbrains.annotations.NotNull;

class LoadingHeader extends LinearLayout implements RefreshHeader {

    LottieAnimationView mAnimationView;

    public LoadingHeader(Context context) {
        super(context);
        initView(context);
    }

    public LoadingHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadingHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    @NotNull
    @Override
    public View getView() {
        return this;
    }

    @NotNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPrimaryColors(int... colors) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onInitialized(@NonNull @NotNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onReleased(@NonNull @NotNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStartAnimator(@NonNull @NotNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        mAnimationView.playAnimation();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public int onFinish(@NonNull @NotNull RefreshLayout refreshLayout, boolean success) {
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
    public void onStateChanged(@NonNull @NotNull RefreshLayout refreshLayout, @NonNull @NotNull RefreshState oldState, @NonNull @NotNull RefreshState newState) {

    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_loading_basketball, this);
        mAnimationView = view.findViewById(R.id.loading_lottie);
    }

    /**
     * Set animation view json.
     *
     * @param animName json文件名
     */
    public void setAnimationViewJson(String animName){
        mAnimationView.setAnimation(animName);
    }

    /**
     * Set animation view json.
     *
     * @param anim the anim
     */
    public void setAnimationViewJson(Animation anim){
        mAnimationView.setAnimation(anim);
    }
}
