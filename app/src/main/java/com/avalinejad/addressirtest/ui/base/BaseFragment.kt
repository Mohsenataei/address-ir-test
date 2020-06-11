package com.avalinejad.addressirtest.ui.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.di.DaggerViewModelFactory
import com.avalinejad.addressirtest.extentions.clearAllDisposables
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    private val pauseDisposableJobs by lazy { mutableListOf<Job>() }

    @Inject
    lateinit var eventBus: EventBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }
    override fun onResume() {
        super.onResume()
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                    return onBackPressed()
                }
                return false
            }
        })
    }
    open fun onBackPressed() = false
    protected fun addToPauseUnsubscribe(job: Job) {
        pauseDisposableJobs.add(job)
    }

    override fun onPause() {
        pauseDisposableJobs.clearAllDisposables()
        view?.setOnKeyListener(null)
        super.onPause()
    }
}