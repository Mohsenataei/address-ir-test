package com.avalinejad.addressirtest.extentions

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

fun List<Job>.clearAllDisposables(){
    forEach{job ->
        job.apply {
            cancelChildren()
            cancel()
        }
    }
}