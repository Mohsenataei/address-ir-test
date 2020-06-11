package com.avalinejad.addressirtest.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment

class CustomNavHostFragment : NavHostFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return WindowInsetsFrameLayout(inflater.context).apply {
            id = this@CustomNavHostFragment.id
            fitsSystemWindows = true
        }
    }
}