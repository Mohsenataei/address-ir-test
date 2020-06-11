package com.avalinejad.addressirtest.ui.base

import androidx.lifecycle.ViewModel
import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.extentions.clearAllDisposables
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseViewModel(val eventBus: EventBus) : ViewModel() {
    private val disposableJobs by lazy { mutableListOf<Job>() }


    fun addToUnsubscribe(job: Job) {
        disposableJobs.add(job)
    }

    override fun onCleared() {
        disposableJobs.clearAllDisposables()
    }
}