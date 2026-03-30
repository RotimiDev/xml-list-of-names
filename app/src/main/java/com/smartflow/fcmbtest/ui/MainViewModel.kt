package com.smartflow.fcmbtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartflow.fcmbtest.data.model.Names
import com.smartflow.fcmbtest.data.repository.NamesRepository
import com.smartflow.fcmbtest.utils.InputSanitizer

class MainViewModel(
    private val repository: NamesRepository = NamesRepository() //Would inject this for a prod task
) : ViewModel() {

    private val _names = MutableLiveData<List<Names>>()
    val names: LiveData<List<Names>> get() = _names

    init {
        _names.value = repository.getNames()
    }

    fun filter(query: String) {
        val sanitized = InputSanitizer.sanitize(query)

        _names.value = if (sanitized.isEmpty()) {
            repository.getNames()
        } else {
            repository.filterNames(sanitized)
        }
    }
}
