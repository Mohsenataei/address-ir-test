package com.avalinejad.addressirtest.extentions

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout

val Toolbar.titleView: TextView?
    get() {
        for (i in 0 until childCount) {
            val view: View = getChildAt(i)
            if (view is TextView) {
                if (view.text == title) {
                    return view
                }
            }
        }
        return null
    }

fun Toolbar.initToolbar(menuId: Int? = null, menuIcon: Int? = null) {
    menuId?.let { inflateMenu(it) }
//    setupWithNavController(findNavController())
    menuIcon?.let { setNavigationIcon(it) }
//    setNavigationOnClickListener {
//        context.openDrawer()
//    }
}

fun CollapsingToolbarLayout.initCollapsingToolbar(toolbar: Toolbar) {
    setupWithNavController(toolbar, findNavController())
    isTitleEnabled = false
}