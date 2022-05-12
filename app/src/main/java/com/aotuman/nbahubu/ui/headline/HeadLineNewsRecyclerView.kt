package com.aotuman.nbahubu.ui.headline

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


class HeadLineNewsRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): RecyclerView(context, attrs, defStyleAttr) {

    private var startX = 0
    private var startY:Int = 0
    private var isTouchBanner = false

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x.toInt()
                startY = ev.y.toInt()

                // todo 这段丑陋的判断代码，后期要处理一下
                val bannerView = (getChildAt(0) as? ViewGroup)?.getChildAt(0)
                bannerView?.let {
                    isTouchBanner = isTransformedTouchPointInView(ev, bannerView)
                }
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x.toInt()
                val endY = ev.y.toInt()
                val disX = abs(endX - startX)
                val disY = abs(endY - startY)
                if (!isTouchBanner) {
                    if (disX > disY) {
                        parent.requestDisallowInterceptTouchEvent(canScrollHorizontally(startX - endX))
                    } else {
                        parent.requestDisallowInterceptTouchEvent(canScrollVertically(startY - endY))
                    }
                } else {
                    // 不要拦截banner的touch事件
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
            {
                isTouchBanner = false
                parent.requestDisallowInterceptTouchEvent(false)
            }

        }
        return super.dispatchTouchEvent(ev)
    }

    /***
     * 判断MotionEvent是否处于View上面
     */
    fun isTransformedTouchPointInView(ev: MotionEvent, view: View): Boolean {
        val x = ev.rawX
        val y = ev.rawY
        val rect = IntArray(2)
        view.getLocationInWindow(rect)
        val localX = x - rect[0]
        val localY = y - rect[1]
        return localX >= 0 && localX < view.right - view.left && localY >= 0 && localY < view.bottom - view.top
    }
}