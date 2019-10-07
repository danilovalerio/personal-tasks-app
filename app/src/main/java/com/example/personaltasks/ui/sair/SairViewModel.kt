package com.example.personaltasks.ui.sair

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SairViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sair Fragment"
    }
    val text: LiveData<String> = _text
}