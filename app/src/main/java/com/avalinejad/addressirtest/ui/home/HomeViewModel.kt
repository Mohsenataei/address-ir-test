package com.avalinejad.addressirtest.ui.home

import androidx.lifecycle.MutableLiveData
import com.avalinejad.addressirtest.App
import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.data.model.Response
import com.avalinejad.addressirtest.repository.CoordinatesRepository
import com.avalinejad.addressirtest.ui.base.BaseViewModel
import com.avalinejad.addressirtest.util.Coroutines
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
    private val app: App,
    private val coordinatesRepository: CoordinatesRepository,
    eventBus: EventBus
) : BaseViewModel(eventBus) {
    val coordinates = MutableLiveData<Response>()


    fun loadData(lat: Double, lon: Double) {
        Coroutines.ioThenMain(
            {
                coordinatesRepository.getCoordinates(lat, lon)
            }
        ){
            onComplete {
                coordinates.value = it
            }
        }.also { addToUnsubscribe(it) }
    }
}