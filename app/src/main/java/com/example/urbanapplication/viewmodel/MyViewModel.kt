package com.example.urbanapplication.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urbanapplication.extensions.toCalendar
import com.example.urbanapplication.model.UrbanResponse
import com.example.urbanapplication.repository.remote.UrbanService
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyViewModel @Inject constructor(
    private val urbanService: UrbanService
) : ViewModel() {

    val showClear = ObservableField<Boolean>(false)
    var name = MutableLiveData("")

    private val _urbanResponse = MutableLiveData<UrbanResponse>()
    val urbanResponse: LiveData<UrbanResponse>
        get() = _urbanResponse

    fun fetchDefinition(query: String) {
        viewModelScope.launch {
            val response = urbanService.getDefinitions(query)
            _urbanResponse.postValue(response)
        }
        val cal = "2020-06-17T00:00:00.000Z".toCalendar()
    }
}