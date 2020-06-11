package com.avalinejad.addressirtest.extentions

import androidx.fragment.app.Fragment

fun Fragment.getStatusBarHeight() = activity!!.getStatusBarHeight()

fun Fragment.getActionBarHeight() = activity!!.getActionBarHeight()