package com.avalinejad.addressirtest.customview

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.WindowInsets
import android.widget.FrameLayout

/**
 * A FrameLayout subclass that dispatches WindowInsets to its children instead of adjusting its padding.
 * Useful for Fragment containers.
 *
 * @author Pkmmte Xeleon
 */
class WindowInsetsFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        val childCount = childCount
        for (index in 0 until childCount) getChildAt(index).dispatchApplyWindowInsets(insets)
        return insets
    }
}